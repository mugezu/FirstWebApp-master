package DAO;

import exception.DaoSystemException;
import exception.NoAccessException;
import exception.NoSuchEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.Hiber.Hiber;
import util.Hiber.Model.RoleEntity;
import util.Hiber.Model.UserdbEntity;

import java.util.List;

/**
 * Created by Роман on 15.06.2017.
 */
public class UserHiberDao implements UserDao {
    @Override
    public UserdbEntity selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException {
        UserdbEntity resutl;
        List<UserdbEntity> userbd;
        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(UserdbEntity.class);
            criteria.add(Restrictions.eq("name", login));
            criteria.add(Restrictions.eq("password", password));
            userbd = criteria.list();
            if (userbd.isEmpty()) {
                throw new NoAccessException("Invalid login or password");
            }
            resutl = userbd.get(0);
        } finally {
            if (!session.isConnected())
                session.close();
        }
        return resutl;
    }

    @Override
    public UserdbEntity registrationUser(String login, String password, String email) throws DaoSystemException, NoSuchEntityException, NoAccessException {
        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UserdbEntity.class);
            criteria.add(Restrictions.or(Restrictions.eq("name", login), Restrictions.eq("email", email)));


            if (!criteria.list().isEmpty()) {
                session.getTransaction().rollback();
                throw new DaoSystemException("User exists");
            }
            UserdbEntity user = new UserdbEntity();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(login);
            user.setRoleByUserRole(new RoleEntity());
            session.save(user);
            session.getTransaction().commit();
            return user;
        } finally {
            if (session != null)
                session.close();
        }

    }
}
