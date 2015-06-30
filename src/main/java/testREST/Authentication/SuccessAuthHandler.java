package testREST.Authentication;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by stepa on 29.06.15.
 */
@Component
public class SuccessAuthHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(SuccessAuthHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("Successful login");
        response.setContentType("application/json");
        PrintWriter res = response.getWriter();
        JSONObject jsonRes = new JSONObject();
        try {
            jsonRes.append("message", "Sign in sucessfully!");
            jsonRes.append("token", authentication.getName());
        } catch (JSONException exc) {
            log.debug("Problem with json creation.");
        }
        res.print(jsonRes);
    }
}
