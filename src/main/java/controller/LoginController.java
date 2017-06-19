package controller;


import exception.*;
import DAO.UserDao;
import org.springframework.context.annotation.Configuration;
import util.Hiber.Model.UserdbEntity;
import util.Spring.SpringContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by user on 15.11.2016.
 */
@Configuration
public class LoginController extends HttpServlet {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTRIBUTE_MODEL_TO_VIEW_USER = "user";
    private static final String ATTRIBUTE_MASSAGE = "massage";
    private static final String PAGE_OK = "login.jsp";
    private static final String PAGE_ERROR_ACCESS = "front.jsp";
    private UserDao userDao = (UserDao) SpringContext.getInstance().getContext().getBean("userDao");


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAM_LOGIN);
        String password = req.getParameter(PARAM_PASSWORD);
        try {
            UserdbEntity model = userDao.selectByLoginPassword(login, password);
            resp.addCookie(new Cookie(PARAM_LOGIN, model.getName()));
            resp.addCookie(new Cookie(PARAM_PASSWORD, model.getPassword()));
            req.getSession().setAttribute(ATTRIBUTE_MODEL_TO_VIEW_USER, model);
            RequestDispatcher view = req.getRequestDispatcher(PAGE_OK);
            view.forward(req, resp);
            return;
        } catch (NoAccessException | NoSuchEntityException | DaoSystemException  e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        req.setAttribute(ATTRIBUTE_MASSAGE, "Ошибка входа");
        RequestDispatcher view = req.getRequestDispatcher(PAGE_ERROR_ACCESS);
        view.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = null;
        String password = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(PARAM_LOGIN)) {
                    login = cookies[i].getValue();
                }
                if (cookies[i].getName().equals(PARAM_PASSWORD))
                    password = cookies[i].getValue();
            }
        try {
            System.out.println(login + " " + password);
            UserdbEntity model = userDao.selectByLoginPassword(login, password);
            req.getSession().setAttribute(ATTRIBUTE_MODEL_TO_VIEW_USER, model);
            RequestDispatcher view = req.getRequestDispatcher(PAGE_OK);
            view.forward(req, resp);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(PAGE_ERROR_ACCESS);
    }
}
