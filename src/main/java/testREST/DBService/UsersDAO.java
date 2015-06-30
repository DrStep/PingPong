package testREST.DBService;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testREST.DBService.Tables.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by stepa on 29.06.15.
 */
@Repository("usersDAO")
@Transactional
public class UsersDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(Users user) {
        em.persist(user);
    }

    public void updateCalls(String username) {
        em.createQuery("update Users u set u.numberOfCalls = u.numberOfCalls + 1"
                + "where u.userName = :userName")
                .setParameter("userName", username)
                .executeUpdate();
    }

    public List<Users> getUserByName(String username) {
        return em.createQuery("select u from Users u where u.userName=:userName", Users.class)
                .setParameter("userName", username).getResultList();
    }
}
