package controller;

import ClassJava.Order;
import ClassJava.Product;
import com.google.gson.Gson;
import util.DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;


/**
 * Created by Роман on 13.06.2017.
 */
public class BuyOrderController extends HttpServlet {
    private static final String PAGE_ERROR = "error.jsp";
    private static final String Attribute_TotalMoney = "totalMoney";
    private static final String Attribute_storeProducts = "basketProducts";
    private static final String PARAM_LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = null;
        Cookie[] cookies = req.getCookies();
        String SQL = "INSERT INTO basket_products (name_buyer,list_product,summ_order,date) VALUE (?,?,?,?)";
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(PARAM_LOGIN)) {
                    login = cookies[i].getValue();
                }
            }
        Map<Product, Integer> products = (Map) session.getAttribute(Attribute_storeProducts);
        String json;
        json = new Gson().toJson(products);
        System.out.println(json);
        Integer total = (Integer) session.getAttribute(Attribute_TotalMoney);
        try (Connection connection = DataBase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, json);
            preparedStatement.setInt(3, total);
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
