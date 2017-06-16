package DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import util.Hiber.Hiber;
import util.Hiber.Model.ProductdbEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Роман on 15.06.2017.
 */
public class ProductHiberDao implements ProductDao {
    private final static Map<Integer, ProductdbEntity> productsBase = new ConcurrentHashMap<>();

    public ProductHiberDao() {
        List<ProductdbEntity> productdbEntities;
        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(util.Hiber.Model.ProductdbEntity.class);
            productdbEntities = criteria.list();
            for (ProductdbEntity productdbEntity : productdbEntities) {
                int id = productdbEntity.getId();
                this.productsBase.put(id, productdbEntity);
            }
        } finally {
            if (session.isConnected())
                session.close();
        }
    }

    @Override
    public ProductdbEntity selectById(int id) throws DaoSystemException, NoSuchEntityException {
        ProductdbEntity productdbEntity;
        productdbEntity = productsBase.get(id);
        if (productdbEntity == null) {
            throw new NoSuchEntityException("No productdbEntity by selected id");
        }
        return productdbEntity;
    }

    @Override
    public List<ProductdbEntity> selectAll() throws DaoSystemException {
        List<ProductdbEntity> productdbEntities = new CopyOnWriteArrayList();
        if (productsBase == null)
            throw new DaoSystemException("Data base empty");
        for (Integer key : productsBase.keySet()) {
            productdbEntities.add(productsBase.get(key));
        }
        return productdbEntities;

    }
}
