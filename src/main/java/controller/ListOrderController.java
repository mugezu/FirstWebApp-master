package controller;

import DAO.OrderDao;
import exception.DaoSystemException;
import exception.NoSuchEntityException;
import util.Hiber.Model.BasketProductsEntity;
import util.Hiber.Model.UserdbEntity;
import util.Spring.SpringContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Роман on 14.06.2017.
 */
public class ListOrderController extends AbstractHttpServlet {
    private static final String PAGE_ERROR = "error.jsp";
    private static final String PAGE_OK = "allOrder.jsp";
    private static final String PARAM_ORDERS = "orders";
    private static final String ATTRIBUTE_MODEL_TO_VIEW_USER = "user";
    private OrderDao model = (OrderDao) SpringContext.getInstance().getContext().getBean("orderDao");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(model);
        HttpSession session = req.getSession();
        UserdbEntity user = (UserdbEntity) session.getAttribute(ATTRIBUTE_MODEL_TO_VIEW_USER);
        System.out.println(user.getId());
        try {
            List<BasketProductsEntity> orders = model.selectById_buyer(user.getId());
            req.setAttribute(PARAM_ORDERS, orders);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (DaoSystemException | NoSuchEntityException e) {
            log.warn(e.getMessage());
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
