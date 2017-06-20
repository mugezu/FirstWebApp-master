package DAO;

import exception.DaoSystemException;
import exception.NoSuchEntityException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.Hiber.Hiber;
import util.Hiber.Model.AbstractModel;
import util.Hiber.Model.ProductdbEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Роман on 19.06.2017.
 */
public abstract class GenericDAO<T extends AbstractModel> implements IGenericDAO<T> {
    protected final Logger log = Logger.getLogger(this.getClass());

    @Override
    public T selectById(int id) throws DaoSystemException, NoSuchEntityException {
        T entity = null;

        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(getClassDef());
            criteria.add(Restrictions.eq("id", id));
            criteria.addOrder(Order.asc("id"));
            if (criteria.uniqueResult() == null)
                throw new NoSuchEntityException("No such entity ");
            entity = (T) criteria.uniqueResult();
        } finally {
            if (session != null && session.isConnected())
                session.close();
            return entity;
        }
    }

    @Override
    public List<T> selectAll() throws DaoSystemException, NoSuchEntityException {
        T entity = null;
        List<T> entities = new LinkedList<>();

        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(getClassDef());
            criteria.addOrder(Order.asc("id"));
            if (criteria.list().isEmpty()) {
                System.out.println("lalal***///////////////");
                throw new NoSuchEntityException("No such entities: data base empty");
            }
            for (ProductdbEntity prod : (List<ProductdbEntity>) criteria.list()) {
                System.out.println(">*---->>>..." + prod.getId());
            }
            entities.addAll((List<T>) criteria.list());
            System.out.println("llalal");
            for (T prod : entities) {
                System.out.println(">>>>>>>>>>..." + prod.getId());
            }
        } finally {
            if (session != null && session.isConnected())
                session.close();
        }
        return entities;
    }

    @Override
    public boolean add(T entity) throws DaoSystemException, NoSuchEntityException {
        boolean result = false;
        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.flush();
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            log.warn("Adding entity failed :" + entity.getClass());
            log.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isConnected())
                session.close();
            return result;
        }
    }

    protected abstract Class<T> getClassDef();
}
