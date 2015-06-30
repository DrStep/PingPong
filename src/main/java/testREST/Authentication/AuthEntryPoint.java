package testREST.Authentication;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by stepa on 29.06.15.
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static final Logger log = LoggerFactory.getLogger(Authentication.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter res = response.getWriter();
        JSONObject jsonRes = new JSONObject();
        try {
            jsonRes.append("error", "Need authorization");
            jsonRes.append("message", "Send us your username and password to sign up.");
        } catch (JSONException exc) {
            log.debug("Problem with json creation.");
        }
    }
}
