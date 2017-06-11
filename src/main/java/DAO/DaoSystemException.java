package DAO;

/**
 * Created by user on 17.11.2016.
 */
public class DaoSystemException extends Exception {

    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
