package testREST.Authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by stepa on 30.06.15.
 */

public class TokenFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain
        chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String authtoken = request.getParameter("authtoken");
        if (authtoken.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You need to sign in first.");
            return;
        }

        chain.doFilter(req, res);
    }


    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}