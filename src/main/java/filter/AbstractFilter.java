package filter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 16.11.2016.
 */
public abstract class AbstractFilter implements Filter {
    protected final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest)servletRequest,(HttpServletResponse)servletResponse,  chain);
    }
    public abstract  void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    public void destroy() {

    }

}
