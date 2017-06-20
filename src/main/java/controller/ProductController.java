package controller;

import DAO.ProductDao;
import util.Hiber.Model.ProductdbEntity;
import util.Spring.SpringContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 21.11.2016.
 */
public class ProductController extends AbstractHttpServlet {
    private static final String ATTRIBUTE_ALL_PRODUCTS = "products";
    private static final String PAGE_OK = "allProducts.jsp";
    private static final String PAGE_ERROR_ACCESS = "error.jsp";

    private ProductDao productDao = (ProductDao) SpringContext.getInstance().getContext().getBean("productDao");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductdbEntity> productdbEntity;
        try {
            productdbEntity = productDao.selectAll();
            System.out.println(productdbEntity.isEmpty());
            req.setAttribute(ATTRIBUTE_ALL_PRODUCTS, productdbEntity);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (Exception e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        resp.sendRedirect(PAGE_ERROR_ACCESS);
    }
}
