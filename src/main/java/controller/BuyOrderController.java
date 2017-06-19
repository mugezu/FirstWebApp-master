package controller;

import DAO.OrderDao;
import exception.DaoSystemException;
import exception.NoSuchEntityException;
import org.apache.log4j.Logger;
import util.Hiber.Model.BasketProductsEntity;
import util.Hiber.Model.ProductdbEntity;
import util.Hiber.Model.UserdbEntity;
import util.Spring.SpringContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by Роман on 13.06.2017.
 */
public class BuyOrderController extends AbstractHttpServlet {
    private static final String PAGE_ERROR = "error.jsp";
    private static final String Attribute_storeProducts = "basketProducts";
    private static final String Attribute_TotalMoney = "totalMoney";
    private static final String ATTRIBUTE_MODEL_TO_VIEW_USER = "user";

    protected final Logger log = Logger.getLogger(this.getClass());

    private static OrderDao orderDao = (OrderDao) SpringContext.getInstance().getContext().getBean("orderDao");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            UserdbEntity user = (UserdbEntity) session.getAttribute(ATTRIBUTE_MODEL_TO_VIEW_USER);
            Map<ProductdbEntity, Integer> products = (Map) session.getAttribute(Attribute_storeProducts);
            if (products == null) {
                throw new NoSuchEntityException("Basket is empty");
            }
            Iterator<ProductdbEntity> iProsucts = products.keySet().iterator();
            Iterator<Integer> iCountProduct = products.values().iterator();
            List<BasketProductsEntity> basket = new LinkedList<>();
            while (iProsucts.hasNext()) {
                BasketProductsEntity oneOrder = new BasketProductsEntity();
                oneOrder.setCountProduct(iCountProduct.next());
                oneOrder.setUserdbByIdBuyer(user);
                oneOrder.setProductdbByIdProduct(iProsucts.next());
                oneOrder.setDate(new Date(System.currentTimeMillis()));
                basket.add(oneOrder);
            }
            orderDao.addOrders(basket);
            session.setAttribute(Attribute_storeProducts, null);
            session.setAttribute(Attribute_TotalMoney, null);
            return;
        } catch (DaoSystemException | NoSuchEntityException e) {
            log.warn(e.getMessage());
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
