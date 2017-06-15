package controller;

import ClassJava.Order;
import DAO.DaoSystemException;
import DAO.NoAccessException;
import DAO.NoSuchEntityException;
import DAO.OrderDao;
import util.Spring.SpringContext;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Роман on 14.06.2017.
 */
public class ListOrderController extends HttpServlet {
    private static final String PAGE_ERROR = "error.jsp";
    private static final String PAGE_OK = "allOrder.jsp";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_ORDERS = "orders";
    private OrderDao model = (OrderDao) SpringContext.getInstance().getContext().getBean("orderDao");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++)
                if (cookies[i].getName().equals(PARAM_LOGIN)) {
                    login = cookies[i].getValue();
                }
        try {
            List<Order> orders = model.selectByName_buyer(login);
            for (Order i : orders) {
                System.out.println("ORDER -> " + i.toString());
            }
            req.setAttribute(PARAM_ORDERS, orders);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (DaoSystemException | NoAccessException | NoSuchEntityException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(PAGE_ERROR);

    }
}
