package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 18.11.2016.
 */
public class CookieFilter extends AbstractFilter {
    private static final String COUNTER_ENTER = "counterCookie";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie fromClient = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie c : cookies) {
                if (COUNTER_ENTER.equals(c.getName())) {
                    fromClient = c;
                    break;
                }
            }
        if (fromClient == null) {
            response.addCookie(new Cookie(COUNTER_ENTER, "" + 1));
            request.setAttribute(COUNTER_ENTER, 1);
        } else {
            int countVisit = Integer.valueOf(fromClient.getValue());
            response.addCookie(new Cookie(COUNTER_ENTER, "" + (++countVisit)));
            request.setAttribute(COUNTER_ENTER, countVisit);
        }
        chain.doFilter(request, response);
    }
}

