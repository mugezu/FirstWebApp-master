package controller;

import ClassJava.Product;
import DAO.DaoSystemException;
import DAO.ProductDao;
import DAO.ProductDaoInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 21.11.2016.
 */
public class ProductController extends HttpServlet {
    private static final String ATTRIBUTE_ALL_PRODUCTS = "products";
    private static final String PAGE_OK = "allProducts.jsp";
    private static final String PAGE_ERROR_ACCESS = "error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> product;
        try {
            ProductDao p = new ProductDaoInfo();
            product = p.selectAll();
            req.setAttribute(ATTRIBUTE_ALL_PRODUCTS, product);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (DaoSystemException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(PAGE_ERROR_ACCESS);
    }
}
