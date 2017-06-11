package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 20.11.2016.
 */
public class SessionFilter extends AbstractFilter {
    private static final String COUNTER_ENTER = "counterSession";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session!=null) {
            AtomicInteger counter = (AtomicInteger) session.getAttribute(COUNTER_ENTER);
            if (counter == null) {
                counter = new AtomicInteger(1);
                session.setAttribute(COUNTER_ENTER, counter);
            }
            request.setAttribute(COUNTER_ENTER, counter.getAndIncrement());
        }
        chain.doFilter(request, response);
    }
}
