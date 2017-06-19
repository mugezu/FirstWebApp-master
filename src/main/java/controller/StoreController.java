package controller;

import DAO.ProductDao;
import exception.DaoSystemException;
import exception.NoSuchEntityException;
import util.Hiber.Model.ProductdbEntity;
import util.Spring.SpringContext;

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
public class StoreController extends AbstractHttpServlet {
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
                Map<ProductdbEntity, Integer> oldStore;
                ProductDao p = (ProductDao) SpringContext.getInstance().getContext().getBean("productDao");
                ProductdbEntity productdbEntity = p.selectById(id);
                Long total = (Long) session.getAttribute(Attribute_TotalMoney);
                oldStore = (Map) session.getAttribute(Attribute_storeProducts);
                if (oldStore == null) {
                    session.setAttribute(Attribute_storeProducts, singletonMap(productdbEntity, 1));
                    session.setAttribute(Attribute_TotalMoney, productdbEntity.getPrice());
                } else {
                    Map<ProductdbEntity, Integer> newStore = new LinkedHashMap<>(oldStore);
                    Long newTotal = total + productdbEntity.getPrice();
                    if (!oldStore.containsKey(productdbEntity)) {
                        newStore.put(productdbEntity, 1);
                    } else {
                        newStore.put(productdbEntity, newStore.get(productdbEntity) + 1);
                    }
                    session.setAttribute(Attribute_storeProducts, newStore);
                    session.setAttribute(Attribute_TotalMoney, newTotal);
                }
                resp.sendRedirect(PAGE_OK);
                return;
            } catch (NoSuchEntityException | DaoSystemException e) {
                log.warn(e.getMessage());
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
