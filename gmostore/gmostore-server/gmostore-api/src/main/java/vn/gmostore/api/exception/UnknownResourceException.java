package vn.gmostore.api.exception;

/**
 * Simulated business-logic exception indicating a desired business entity or
 * record cannot be found.
 */
public class UnknownResourceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UnknownResourceException(String msg) {
        super(msg);
    }
}
