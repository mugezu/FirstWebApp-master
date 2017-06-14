package DAO;

import ClassJava.Order;

import java.util.List;

/**
 * Created by Роман on 14.06.2017.
 */
public interface OrderDao {
    public List<Order> selectByName_bayer(String name_bayer) throws DaoSystemException, NoAccessException, NoSuchEntityException;

    public List<Order> selectAll() throws DaoSystemException, NoAccessException, NoSuchEntityException;

    public Order selectById_order(int id_order) throws DaoSystemException, NoAccessException, NoSuchEntityException;
}
