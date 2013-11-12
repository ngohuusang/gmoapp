package vn.gmostore.api.exhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ValidationException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import vn.gmostore.api.exception.ValidatorException;
import vn.gmostore.basic.dto.FieldErrorDto;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionConverter {
    public static void convertFrom(Exception e, MessageSource messageSource) {

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) e;
            notifyValidatorException(validException.getBindingResult(), messageSource);
        }

    }

    /**
     * Notify ValidatorException if has errors
     * 
     * @param bindingResult
     * @param messageSource
     * @throws ValidatorException
     */
    private static void notifyValidatorException(BindingResult bindingResult, MessageSource messageSource) throws ValidatorException {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<FieldErrorDto> errors = new ArrayList<FieldErrorDto>();
            for (FieldError fieldError : fieldErrors) {
                String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError, messageSource);
                FieldErrorDto error = new FieldErrorDto(fieldError.getField(), localizedErrorMessage);
                errors.add(error);
            }

            throw new ValidationException(convertToJson(errors));
        }
    }

    private static String convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String resolveLocalizedErrorMessage(FieldError fieldError, MessageSource messageSource) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}
