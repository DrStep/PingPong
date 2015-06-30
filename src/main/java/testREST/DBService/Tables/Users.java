package testREST.DBService.Tables;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by stepa on 29.06.15.
 */

@Entity
@Table(name = "USERS")
@Data
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "calls")
    private int numberOfCalls;

    public Users() { }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.numberOfCalls = 0;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public int getNumberOfCalls() {
        return this.numberOfCalls;
    }
}
