package DAO;

import ClassJava.User;
import DB.ThreadCache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 17.11.2016.
 */
public class UserDaoMock implements UserDao {
   private final static String CONNECTION="CONNECTION";

    public UserDaoMock() {
    }

    @Override
    public User selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException {
        User resutl = null;
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "SELECT name, password FROM userdb WHERE name=? AND password=?";
        try {
            connection = (Connection) ThreadCache.getInstance().getCache(CONNECTION);
            System.out.println(connection);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            rs.next();
            resutl = new User(rs.getString("name"), rs.getString("password"));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try {
                rs.close();
                preparedStatement.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return resutl;
            }
        }
    }
}
