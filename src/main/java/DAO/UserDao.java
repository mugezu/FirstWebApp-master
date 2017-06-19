package DAO;

import exception.DaoSystemException;
import exception.NoAccessException;
import exception.NoSuchEntityException;
import util.Hiber.Model.UserdbEntity;

/**
 * Created by user on 17.11.2016.
 */
public interface UserDao {
    public UserdbEntity selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException;

    public UserdbEntity registrationUser(String login, String password, String email) throws DaoSystemException, NoSuchEntityException, NoAccessException;
}
