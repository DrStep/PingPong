package testREST.Authentication;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import testREST.DBService.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by stepa on 29.06.15.
 */
@Component
public class FailAuthHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static final Logger log = LoggerFactory.getLogger(FailAuthHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException{

        log.info("Auth failed.");
        response.setContentType("application/json");
        PrintWriter res = response.getWriter();
        JSONObject jsonRes = new JSONObject();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!username.isEmpty() && !password.isEmpty()) {
            UsersService usersServ = context.getBean(UsersService.class);
            usersServ.createUser(username, password);
            try {
                jsonRes.append("message", "New user was created.");
                jsonRes.append("token", username);
            } catch (JSONException exc) {
                log.debug("Problem with json creation.");
            }
        } else {
            try {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                jsonRes.append("auth_error", "Send us full credentials for correct sign up.");
            } catch (JSONException exc) {
                log.debug("Problem with json creation.");
            }
        }
        res.print(jsonRes);
    }
}
