package testREST.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import testREST.DBService.Tables.Users;
import testREST.DBService.UsersService;
import testREST.ResponseJSON.Result;

import java.util.List;

/**
 * Created by stepa on 29.06.15.
 */

@RestController
public class CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(CommandHandler.class);
    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @RequestMapping(value = "/commands", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<Result> handleCommand(
            @RequestParam(value = "command", required = false, defaultValue = "ping") String command,
            @RequestParam(value = "authtoken") String authtoken) {

        log.info("Command " + command + " for release");
        DeferredResult<Result> result = new DeferredResult<>();

        switch (command) {
            case "ping":
                commandPong(result, authtoken);
                break;
            default:
                commandPong(result, authtoken);
        }

        return result;
    }

    private void commandPong(DeferredResult<Result> defResult, String usernameFromToken) {
        UsersService usersServ = context.getBean(UsersService.class);

        usersServ.updateCallsForUser(usernameFromToken);
        Users user = usersServ.getUserByName(usernameFromToken).get(0);
        defResult.setResult(new Result("pong", user.getNumberOfCalls()));
    }
}
