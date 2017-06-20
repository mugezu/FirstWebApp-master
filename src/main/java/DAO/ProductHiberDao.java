package DAO;

import util.Hiber.Model.ProductdbEntity;

/**
 * Created by Роман on 15.06.2017.
 */
public class ProductHiberDao extends GenericDAO<ProductdbEntity> implements ProductDao {
    @Override
    protected Class<ProductdbEntity> getClassDef() {
        return ProductdbEntity.class;
    }
}
