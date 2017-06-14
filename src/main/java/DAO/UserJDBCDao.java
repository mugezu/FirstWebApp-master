package DAO;

import ClassJava.User;
import DB.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 17.11.2016.
 */
public class UserJDBCDao implements UserDao {
    String SQL = "SELECT name, password FROM userdb WHERE name=? AND password=?";

    public UserJDBCDao() {
    }

    @Override
    public User selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException, SQLException {
        User resutl = null;
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBase.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Unknown user");
            }
            resutl = new User(rs.getString("name"), rs.getString("password"));
        } finally {
            if (rs != null)
                rs.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return resutl;
    }
}
