package DAO;

import ClassJava.Order;
import util.DB.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 14.06.2017.
 */
public class OrderJDBCDao implements OrderDao {
    @Override
    public List<Order> selectByName_buyer(String name_buyer) throws DaoSystemException, NoAccessException, NoSuchEntityException {
        String SQL = "SELECT * FROM basket_products WHERE name_buyer=?";
        List<Order> result = new ArrayList<>();
        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, name_buyer);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Order model = null;
                    //TODO
                    result.add(model);
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> selectAll() throws DaoSystemException, NoAccessException, NoSuchEntityException {
        return null;
    }

    @Override
    public Order selectById_order(int id_order) throws DaoSystemException, NoAccessException, NoSuchEntityException {
        return null;
    }

    @Override
    public boolean addOrder(List<Order> orders) throws DaoSystemException, NoAccessException, NoSuchEntityException {
        return false;
    }
}
