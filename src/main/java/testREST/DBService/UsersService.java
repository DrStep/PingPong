package testREST.DBService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testREST.DBService.Tables.Users;

import java.util.List;

/**
 * Created by stepa on 29.06.15.
 */

@Service("usersService")
public class UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersDAO usersDAO;

    public void createUser(String username, String password) {
        log.info("Create user: " + username);
        Users user = new Users(username, password);
        usersDAO.save(user);
    }

    public List<Users> getUserByName(String username) {
        log.info("Get user: " + username);
        return usersDAO.getUserByName(username);
    }

    public void updateCallsForUser(String username) {
        log.info("Update calls number for user: " + username);
        usersDAO.updateCalls(username);
    }
}
