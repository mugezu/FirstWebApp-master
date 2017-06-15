package DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.Hiber.Hiber;
import util.Hiber.Model.UserdbEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Роман on 15.06.2017.
 */
public class UserHiberDao implements UserDao {
    @Override
    public UserdbEntity selectByLoginPassword(String login, String password) throws DaoSystemException, NoSuchEntityException, NoAccessException, SQLException {
        UserdbEntity resutl;
        List<UserdbEntity> userbd;
        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            System.out.println(session.isConnected());
            System.out.println("l=" + login + " p=" + password);
            Criteria criteria = session.createCriteria(UserdbEntity.class);
            criteria.add(Restrictions.eq("name", login));
            criteria.add(Restrictions.eq("password", password));
            userbd = criteria.list();
            if (userbd.isEmpty()) {
                throw new NoAccessException("Неверный пароль или логин");
            }
            resutl = userbd.get(0);
        } finally {
            if (session.isConnected())
                session.close();
        }
        return resutl;
    }
}
