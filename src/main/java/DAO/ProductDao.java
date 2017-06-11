package DAO;

import ClassJava.Product;

import java.util.List;

/**
 * Created by user on 21.11.2016.
 */
public interface ProductDao {
        public Product selectById(int id) throws DaoSystemException,NoSuchEntityException;
    public List<Product> selectAll() throws DaoSystemException;

}
