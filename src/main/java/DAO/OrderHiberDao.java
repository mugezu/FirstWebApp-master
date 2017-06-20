package DAO;

import exception.DaoSystemException;
import exception.NoSuchEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.Hiber.Hiber;
import util.Hiber.Model.BasketProductsEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Роман on 16.06.2017.
 */
public class OrderHiberDao extends GenericDAO<BasketProductsEntity> implements OrderDao {

    @Override
    public List<BasketProductsEntity> selectById_buyer(int id_buyer) throws DaoSystemException, NoSuchEntityException {
        List<BasketProductsEntity> basketProductsEntities = new LinkedList<>();

        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(BasketProductsEntity.class);
            criteria.add(Restrictions.eq("userdbByIdBuyer.id", id_buyer));
            criteria.addOrder(Order.asc("id"));
            if (criteria.list() == null)
                throw new NoSuchEntityException("Orders list empty");
            basketProductsEntities = criteria.list();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isConnected())
                session.close();
            return basketProductsEntities;

        }
    }


    @Override
    public boolean addOrders(List<BasketProductsEntity> orders) throws DaoSystemException, NoSuchEntityException {
        Session session = Hiber.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BasketProductsEntity.class);
            criteria.setProjection(Projections.max("idOrder"));
            Integer max = (Integer) criteria.list().get(0) + 1;
            orders.forEach(order -> {
                        order.setIdOrder(max);
                        session.save(order);
                    }
            );
            session.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Adding entities failed ");
        } finally {
            if (session != null)
                session.close();
            return true;
        }
    }

    @Override
    protected Class<BasketProductsEntity> getClassDef() {
        return BasketProductsEntity.class;
    }
}
