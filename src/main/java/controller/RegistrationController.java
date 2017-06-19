package controller;

import DAO.UserDao;
import exception.DaoSystemException;
import exception.NoSuchEntityException;
import util.Hiber.Model.RoleEntity;
import util.Hiber.Model.UserdbEntity;
import util.Spring.SpringContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Роман on 16.06.2017.
 */
public class RegistrationController extends AbstractHttpServlet {
    private static final String PAGE_ERROR_ACCESS = "registration.jsp";
    private static final String PAGE_OK = "login.do";
    private static final String ATTRIBUTE_MASSAGE = "massage";

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";
    private static final String ATTRIBUTE_MODEL_TO_VIEW_USER = "user";

    private UserDao newUser = (UserDao) SpringContext.getInstance().getContext().getBean("userDao");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAM_LOGIN);
        String password = req.getParameter(PARAM_PASSWORD);
        String email = req.getParameter(PARAM_EMAIL);
        UserdbEntity user = new UserdbEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(login);
        user.setRoleByUserRole(new RoleEntity());
        try {
            if (!newUser.add(user)) throw new DaoSystemException("login or email busy");
            resp.addCookie(new Cookie(PARAM_LOGIN, user.getName()));
            resp.addCookie(new Cookie(PARAM_PASSWORD, user.getPassword()));
            req.getSession().setAttribute(ATTRIBUTE_MODEL_TO_VIEW_USER, user);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (DaoSystemException | NoSuchEntityException e) {
            log.warn(e.getMessage());
            req.setAttribute(ATTRIBUTE_MASSAGE, "Error registration: " + e.getMessage());
            RequestDispatcher view = req.getRequestDispatcher(PAGE_ERROR_ACCESS);
            view.forward(req, resp);
        }

    }
}
