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
        log.info(">>Init DataFilter");
        log.info(">>Init Hibernate");
        Hiber.getSessionFactory();
        log.info(">>Init SpringContext");
        SpringContext.getInstance().getContext();
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Enumeration<String> parametrsEnumeration = request.getParameterNames();
        Enumeration<String> attributesEnum = request.getSession().getAttributeNames();
        Cookie[] cookieEnum = request.getCookies();

        log.info(">>Start DataFilter. List Parameters request:");
        log.info(request.getRequestURI());
        while (parametrsEnumeration.hasMoreElements()) {
            String s = parametrsEnumeration.nextElement();
            String s1 = request.getParameter(s);
            log.info(">>" + s + "=" + s1);
        }
        log.info(">>List attributes session:");
        while (attributesEnum.hasMoreElements()) {
            String s = attributesEnum.nextElement();
            String s1 = request.getSession().getAttribute(s).toString();
            log.info(">>" + s + "=" + s1);
        }

        log.info(">>Cookie:");
        if (cookieEnum != null) {
            for (int i = 0; i < cookieEnum.length; i++) {
                log.info(">>" + cookieEnum[i].getName() + " = " + cookieEnum[i].getValue());
            }
        }

        chain.doFilter(request, response);
    }
}
