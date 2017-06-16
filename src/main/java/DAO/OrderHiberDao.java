package DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.Hiber.Hiber;
import util.Hiber.Model.BasketProductsEntity;

import java.util.List;

/**
 * Created by Роман on 16.06.2017.
 */
public class OrderHiberDao implements OrderDao {

    @Override
    public List<BasketProductsEntity> selectById_buyer(int id_buyer) throws DaoSystemException, NoAccessException, NoSuchEntityException {
        List<BasketProductsEntity> basketProductsEntities = null;

        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(BasketProductsEntity.class);
            criteria.add(Restrictions.eq("userdbByIdBuyer.id", id_buyer));
            criteria.addOrder(Order.asc("id"));
            basketProductsEntities = criteria.list();
            if (basketProductsEntities == null)
                throw new NoSuchEntityException("Orders list empty");
            return basketProductsEntities;
        } finally {
            if (session != null)
                session.close();
        }

    }

    @Override
    public List<BasketProductsEntity> selectAll() throws DaoSystemException, NoAccessException, NoSuchEntityException {
        return null;
    }

    @Override
    public BasketProductsEntity selectById_order(int id_order) throws DaoSystemException, NoAccessException, NoSuchEntityException {
        return null;
    }

    @Override
    public boolean addOrder(List<BasketProductsEntity> orders) throws DaoSystemException, NoAccessException, NoSuchEntityException {
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
        } finally {
            if (session != null)
                session.close();
        }
        return true;
    }
}
