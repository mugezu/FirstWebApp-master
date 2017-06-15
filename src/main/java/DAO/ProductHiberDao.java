package DAO;

import ClassJava.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.DB.DataBase;
import util.Hiber.Hiber;
import util.Hiber.Model.ProductdbEntity;
import util.Hiber.Model.UserdbEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Роман on 15.06.2017.
 */
public class ProductHiberDao implements ProductDao {
    private final static Map<Integer, Product> productsBase = new ConcurrentHashMap<>();
    public ProductHiberDao(){
   /*     List<Product> products;
        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ProductdbEntity.class);

            products = criteria.list();
            if (products.isEmpty()) {
                throw new DaoSystemException("Empty shop");
            }

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
        }*/
    }

    @Override

    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        return null;
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        return null;
    }
}
