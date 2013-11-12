package vn.gmostore.basic.dto;

public class FieldErrorDto implements Dto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String field;

    private String message;

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
