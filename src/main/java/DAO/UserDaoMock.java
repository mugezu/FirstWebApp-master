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
public class UserDaoMock implements UserDao {
    public UserDaoMock() {

    }

    @Override
    public User selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException, SQLException {
        User resutl = null;
        Connection connection = new DataBase().getConnection();
        System.out.println(connection.toString());
        ResultSet rs;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, password FROM userdb WHERE name=? AND password=?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        rs = preparedStatement.executeQuery();
        rs.next();
        resutl = new User(rs.getString("name"), rs.getString("password"));
        rs.close();
        preparedStatement.close();
        new DataBase().closeConnection(connection);
        return resutl;
    }
}
