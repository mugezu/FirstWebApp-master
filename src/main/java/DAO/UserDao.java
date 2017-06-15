package DAO;

import util.Hiber.Model.UserdbEntity;

import java.sql.SQLException;

/**
 * Created by user on 17.11.2016.
 */
public interface UserDao {
    public UserdbEntity selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException, SQLException;

}
