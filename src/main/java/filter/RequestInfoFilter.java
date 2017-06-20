package filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 16.11.2016.
 */
public class RequestInfoFilter extends AbstractFilter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(">>Init RequestInfoFilter");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        String queryString = request.getQueryString();
        String protocol = request.getProtocol();
        log.info(">>RequestInfoFilter:\n>>Method='" + method + "'\n>>RemoveAddr='" + remoteAddr + "'\n>>Query='" + queryString + "'\n>>Protocol='" + protocol + "'\n");
        chain.doFilter(request, response);
    }
}
