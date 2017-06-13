package DAO;

import ClassJava.Product;
import DB.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by user on 21.11.2016.
 */
public class ProductDaoInfo implements ProductDao {
    private static final String SQL = "SELECT * FROM productdb";
    private final static Map<Integer, Product> productsBase = new ConcurrentHashMap<>();

    public ProductDaoInfo() {
        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                this.productsBase.put(id, new Product(id, name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Product product;
        product = productsBase.get(id);
        if (product == null) {
            throw new NoSuchEntityException("No product by selected id");
        }
        return product;
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        List<Product> products = new CopyOnWriteArrayList();
        if (productsBase == null)
            throw new DaoSystemException("Data base empty");
        for (Integer key : productsBase.keySet()) {
            products.add(productsBase.get(key));
        }
        return products;

    }
}
