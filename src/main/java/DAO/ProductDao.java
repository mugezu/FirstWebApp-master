package DAO;

import exception.*;
import util.Hiber.Model.ProductdbEntity;

import java.util.List;

/**
 * Created by user on 21.11.2016.
 */
public interface ProductDao {

    public ProductdbEntity selectById(int id) throws DaoSystemException, NoSuchEntityException;

    public List<ProductdbEntity> selectAll() throws DaoSystemException;

}
