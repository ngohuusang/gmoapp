/**
 * 
 */
package vn.gmostore.basic.util;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.util.StringUtils;

/**
 * This class is used to notify on events which are core based. Adapted from
 * another project.
 * 
 * @author
 * 
 */
public class ExceptionNotificationHelper {

    /**
     * Private logger of this class.
     */
    private static Logger logger = LoggerFactory.getLogger(ExceptionNotificationHelper.class);

    /**
     * Method to log that an object could not be retrieved. This method will
     * always throw an exception.
     * 
     * @param entityClass
     *            The persistent class.
     * @param identifier
     *            The ID of the object that should have been retrieved.
     * @param objectName
     *            The name of the object.
     */
    public static void notifyObjectRetrievalFailure(Class<?> entityClass, Object identifier, String objectName) throws ObjectRetrievalFailureException {
        notifyObjectRetrievalFailure(entityClass, identifier, objectName, null, null);
    }

    /**
     * Method to log that an object could not be retrieved. This method will
     * always throw an exception.
     * 
     * @param entityClass
     *            The persistent class.
     * @param identifier
     *            The ID of the object that should have been retrieved.
     * @param objectName
     *            The name of the object.
     * @param detailedMessage
     *            A detailed message.
     * @param cause
     *            The source of the exception.
     */
    public static void notifyObjectRetrievalFailure(Class<?> entityClass, Object identifier, String objectName, String detailedMessage, Throwable cause)
            throws ObjectRetrievalFailureException {
        String message = StringUtils.hasText(detailedMessage) ? detailedMessage : "The desired " + objectName + " with ID " + identifier + " could not be"
                + " retrieved.";
        logger.error(message);

        throw new ObjectRetrievalFailureException(entityClass, identifier, message, cause);
    }

    /**
     * Method to log that the object has already been modified. This method will
     * always throw an exception.
     * 
     * @param detailedMessage
     *            Is the detailed message.
     * @param objectName
     *            Is the name of the object.
     * @param optionalException
     *            optionally returns the original
     *            {@link OptimisticLockingFailureException} (in order not to
     *            loose information)
     * @throws OptimisticLockingFailureException
     *             Will be thrown in every case.
     */
    public static void notifyOptimisticLockingFailure(String detailedMessage, String objectName, OptimisticLockingFailureException optionalException)
            throws OptimisticLockingFailureException {
        String message = StringUtils.hasText(detailedMessage) ? detailedMessage : objectName + " was modified or deleted in the meantime.";
        logger.error(message);

        // in order not to loose information
        if (optionalException != null) {
            throw optionalException;
        } else {
            throw new OptimisticLockingFailureException(message);
        }
    }

    /**
     * Method to log that the object has already been modified. This method will
     * always throw an exception.
     * 
     * @param detailedMessage
     *            Is the detailed message.
     * @param optionalException
     *            optionally returns the original
     *            {@link OptimisticLockingFailureException} (in order not to
     *            loose information)
     * @throws DataIntegrityViolationException
     *             Will be thrown in every case.
     */
    public static void notifyDataIntegrityViolationFailure(String detailedMessage, ConstraintViolationException e) throws DataIntegrityViolationException {
        String message = StringUtils.hasText(e.getConstraintName()) ? "Duplicate value for '" + e.getConstraintName() + "'" : e.getCause().getMessage();

        logger.error(message);
        // in order not to loose information
        throw new DataIntegrityViolationException(message);
    }
}
