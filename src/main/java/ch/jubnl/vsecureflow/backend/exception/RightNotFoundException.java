package ch.jubnl.vsecureflow.backend.exception;

public class RightNotFoundException extends RuntimeException {
    public RightNotFoundException() {
        super();
    }

    public RightNotFoundException(String message) {
        super(message);
    }

    public RightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RightNotFoundException(Throwable cause) {
        super(cause);
    }

    protected RightNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
