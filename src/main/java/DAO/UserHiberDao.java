package DAO;

import exception.DaoSystemException;
import exception.NoAccessException;
import exception.NoSuchEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.Hiber.Hiber;
import util.Hiber.Model.UserdbEntity;
import util.Spring.SpringContext;

import javax.persistence.EntityManager;

/**
 * Created by Роман on 15.06.2017.
 */
public class UserHiberDao extends GenericDAO<UserdbEntity> implements UserDao {
    public UserHiberDao() {

    }

    @Override
    public UserdbEntity selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException {
        System.out.println("------------------------ id:" + System.identityHashCode(this));
        UserdbEntity resutl;
        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(UserdbEntity.class);
            criteria.add(Restrictions.eq("name", login));
            criteria.add(Restrictions.eq("password", password));
            if (criteria.list().isEmpty()) {
                throw new NoAccessException("Invalid login or password");
            }
            resutl = (UserdbEntity) criteria.uniqueResult();
        } finally {
            if (!session.isConnected())
                session.close();
        }
        return resutl;
    }

    @Override
    protected Class<UserdbEntity> getClassDef() {
        return UserdbEntity.class;
    }
}
