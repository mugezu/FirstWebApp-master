package DAO;

import exception.DaoSystemException;
import exception.NoAccessException;
import exception.NoSuchEntityException;
import org.hibernate.HibernateError;
import util.Hiber.Model.BasketProductsEntity;

import java.util.List;

/**
 * Created by Роман on 14.06.2017.
 */
public interface OrderDao extends IGenericDAO<BasketProductsEntity> {
    public List<BasketProductsEntity> selectById_buyer(int id_buyer) throws DaoSystemException,  NoSuchEntityException;

    public boolean addOrders(List<BasketProductsEntity> orders) throws DaoSystemException,  NoSuchEntityException;
}
