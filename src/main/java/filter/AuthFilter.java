package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Роман on 13.06.2017.
 */
public class AuthFilter extends AbstractFilter {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
           Cookie[] cookies = request.getCookies();
            if(cookies!=null)
                for(int i=0;i<cookies.length;i++){
                    if (cookies[i].getName()==PARAM_LOGIN)
                    {

                    }
                }

        chain.doFilter(request, response);
    }
}
