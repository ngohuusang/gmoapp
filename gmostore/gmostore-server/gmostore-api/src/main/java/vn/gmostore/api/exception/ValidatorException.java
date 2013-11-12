package vn.gmostore.api.exception;

public class ValidatorException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ValidatorException() {
    }

    public ValidatorException(String msg) {
        super(msg);
    }

    public ValidatorException(String msg, Throwable e) {
        super(msg, e);
    }
}
