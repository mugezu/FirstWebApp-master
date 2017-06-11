package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 22.11.2016.
 */
public class BasketController extends HttpServlet {
    private static final String PAGE_ERROR = "error.jsp";
    private static final String PAGE_OK = "basket.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PAGE_OK).forward(req,resp);
    }
}
