package querydsl.tonels.web.error;

public final class CustomerException extends RuntimeException {


    public CustomerException() {
        super();
    }

    public CustomerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CustomerException(final String message) {
        super(message);
    }

    public CustomerException(final Throwable cause) {
        super(cause);
    }

}
