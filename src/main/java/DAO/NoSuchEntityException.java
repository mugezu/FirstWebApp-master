package DAO;

/**
 * Created by user on 17.11.2016.
 */
public class NoSuchEntityException extends Exception {
    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
