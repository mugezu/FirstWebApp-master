package DAO;

import exception.DaoSystemException;
import exception.NoAccessException;
import exception.NoSuchEntityException;
import org.hibernate.HibernateError;
import util.Hiber.Model.BasketProductsEntity;
import util.Hiber.Model.UserdbEntity;

import javax.jws.soap.SOAPBinding;

/**
 * Created by user on 17.11.2016.
 */
public interface UserDao extends IGenericDAO<UserdbEntity> {
    public UserdbEntity selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException;
}
