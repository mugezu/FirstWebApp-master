package filter;

import util.Hiber.Hiber;
import util.Spring.SpringContext;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by user on 17.11.2016.
 */
public class DataFilter extends AbstractFilter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(">>Init DataFilter");
        System.out.println(">>Init Hibernate");
        Hiber.getSessionFactory();
        System.out.println(">>Init SpringContext");
        SpringContext.getInstance();
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Enumeration<String> parametrsEnumeration = request.getParameterNames();
        Enumeration<String> attributesEnum = request.getSession().getAttributeNames();
        Cookie[] cookieEnum = request.getCookies();

        System.out.println(">>Start DataFilter. List Parameters request:");
        System.out.println(request.getRequestURI());
        while (parametrsEnumeration.hasMoreElements()) {
            String s = parametrsEnumeration.nextElement();
            String s1 = request.getParameter(s);
            System.out.println(">>" + s + "=" + s1);
        }
        System.out.println(">>List attributes session:");
        while (attributesEnum.hasMoreElements()) {
            String s = attributesEnum.nextElement();
            String s1 = request.getSession().getAttribute(s).toString();
            System.out.println(">>" + s + "=" + s1);
        }
        System.out.println();
        System.out.println(">>Cookie:");
        if (cookieEnum != null) {
            for (int i = 0; i < cookieEnum.length; i++) {
                System.out.println(">>" + cookieEnum[i].getName() + " = " + cookieEnum[i].getValue());
            }
        }
        System.out.println();
        chain.doFilter(request, response);
    }
}
