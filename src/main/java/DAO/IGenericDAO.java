package DAO;

import exception.DaoSystemException;
import exception.NoSuchEntityException;
import org.hibernate.HibernateError;
import org.springframework.data.repository.CrudRepository;
import util.Hiber.Model.AbstractModel;

import java.util.List;

/**
 * Created by Роман on 19.06.2017.
 */
public interface IGenericDAO<T extends AbstractModel> /*extends CrudRepository<T, Long> */{
    public T selectById(int id) throws DaoSystemException, NoSuchEntityException;

    public List<T> selectAll() throws DaoSystemException, NoSuchEntityException;

    public boolean add(T entity) throws DaoSystemException, NoSuchEntityException;
}
