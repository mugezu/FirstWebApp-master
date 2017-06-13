package controller;

import ClassJava.User;
import DAO.*;
import DB.DataBase;
import DB.ThreadCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by user on 15.11.2016.
 */
public class LoginController extends HttpServlet {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTRIBUTE_MODEL_TO_VIEW_USER = "user";
    private static final String PAGE_OK = "login.jsp";
    private static final String PAGE_ERROR_ACCESS = "error.jsp";

    private final static String conn = "CONNECTION";

    private UserDao userDao = new UserDaoMock();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        try {

            ThreadCache.getInstance().setCache(conn, DataBase.getInstance().getConnection());
            System.out.println(ThreadCache.getInstance().getCache(conn));
            User model = userDao.selectByLoginPassword(login, password);
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW_USER, model);
            RequestDispatcher view = request.getRequestDispatcher(PAGE_OK);
            view.forward(request, response);
            return;
        } catch (NoAccessException | NoSuchEntityException | DaoSystemException e) {
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        response.sendRedirect(PAGE_ERROR_ACCESS);
    }
}
