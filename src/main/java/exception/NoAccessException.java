package exception;

/**
 * Created by user on 17.11.2016.
 */
public class NoAccessException extends Exception {

    public NoAccessException(String message) {
        super(message);
    }

    public NoAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
