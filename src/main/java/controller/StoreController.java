package controller;

import ClassJava.Product;
import DAO.DaoSystemException;
import DAO.NoSuchEntityException;
import DAO.ProductDao;
import DAO.ProductInfoJDBCDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * Created by user on 22.11.2016.
 */
public class StoreController extends HttpServlet {
    private static final String Attribute_storeProducts = "basketProducts";
    private static final String Attribute_TotalMoney = "totalMoney";
    private static final String PARAM_ID = "id";
    private static final String PAGE_ERROR = "error.jsp";
    private static final String PAGE_OK = "allProducts.html";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter(PARAM_ID));
        if (id != null) {
            try {
                HttpSession session = req.getSession();
                Map<Product, Integer> oldStore;
                ProductDao p = new ProductInfoJDBCDao();
                Product product = p.selectById(id);
                Integer total = (Integer) session.getAttribute(Attribute_TotalMoney);
                oldStore = (Map) session.getAttribute(Attribute_storeProducts);
                if (oldStore == null) {
                    session.setAttribute(Attribute_storeProducts, singletonMap(product, 1));
                    session.setAttribute(Attribute_TotalMoney, product.getPrice());
                } else {
                    Map<Product, Integer> newStore = new LinkedHashMap<>(oldStore);
                    Integer newTotal = total + product.getPrice();
                    if (!oldStore.containsKey(product)) {
                        newStore.put(product, 1);
                    } else {
                        newStore.put(product, newStore.get(product) + 1);
                    }
                    session.setAttribute(Attribute_storeProducts, newStore);
                    session.setAttribute(Attribute_TotalMoney, newTotal);
                }
                resp.sendRedirect(PAGE_OK);
                return;
            } catch (NoSuchEntityException | DaoSystemException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
