package controller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.Hiber.Hiber;
import util.Hiber.Model.UserdbEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Роман on 16.06.2017.
 */
public class RegistrationController extends HttpServlet {
    private static final String PAGE_ERROR_ACCESS = "front.jsp";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAM_LOGIN);
        String password = req.getParameter(PARAM_PASSWORD);
        String email = req.getParameter(PARAM_EMAIL);

        Session session = null;
        try {
            session = Hiber.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UserdbEntity.class);
            criteria.add(Restrictions.or(Restrictions.eq("name", login), Restrictions.eq("email", email)));
            if (criteria.list() != null) {
                RequestDispatcher view = req.getRequestDispatcher(PAGE_ERROR_ACCESS);
                view.forward(req, resp);
            }
            //   criteria=session.createCriteria(Use)


            session.getTransaction().commit();
        } finally {
            if (session == null)
                session.close();
        }

    }
}
