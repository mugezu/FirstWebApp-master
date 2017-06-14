package DAO;

import ClassJava.Order;
import DB.DataBase;

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
    public List<Order> selectByName_bayer(String name_bayer) throws DaoSystemException, NoAccessException, NoSuchEntityException {
        String SQL = "SELECT * FROM basket_products WHERE name_bayer=?";
        List<Order> result = new ArrayList<>();
        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, name_bayer);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Order model = new Order();
                    model.setId_order(resultSet.getInt("id_order"));
                    model.setName_buyer(name_bayer);
                    model.setList_product(resultSet.getString("list_product"));
                    model.setSumm_order(resultSet.getInt("summ_order"));
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
}
