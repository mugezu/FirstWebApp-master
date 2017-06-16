package DAO;

import util.Hiber.Model.BasketProductsEntity;

import java.util.List;

/**
 * Created by Роман on 14.06.2017.
 */
public interface OrderDao {
    public List<BasketProductsEntity> selectById_buyer(int id_buyer) throws DaoSystemException, NoAccessException, NoSuchEntityException;

    public List<BasketProductsEntity> selectAll() throws DaoSystemException, NoAccessException, NoSuchEntityException;

    public BasketProductsEntity selectById_order(int id_order) throws DaoSystemException, NoAccessException, NoSuchEntityException;

    public boolean addOrder(List<BasketProductsEntity> orders) throws DaoSystemException, NoAccessException, NoSuchEntityException;
}
