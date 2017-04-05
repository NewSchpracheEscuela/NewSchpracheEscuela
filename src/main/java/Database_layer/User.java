package Database_layer;

/**
 * Created by alexb on 14-Mar-17.
 */

public class User {

    public int user_id;
    public String email;
    public String login;
    public String password_hash;
    public Roles role;

    public String getLogin() {
        return login;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role.toString();
    }
    public int getUser_id() {
        return user_id;
    }
}
