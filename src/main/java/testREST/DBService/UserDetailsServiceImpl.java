package testREST.DBService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import testREST.DBService.Tables.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stepa on 29.06.15.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UsersDAO usersDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        List<Users> userList = usersDAO.getUserByName(username);
        Users user = null;
        log.info("User: " + username + " trying to log in.");
        if (userList.isEmpty()) {
            throw new UsernameNotFoundException("No such user");
        } else {
            user = userList.get(0);
        }

        String role = "USER";

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new User(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
    }

}
