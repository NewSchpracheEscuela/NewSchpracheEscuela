package Database_layer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by alexb on 14-Mar-17.
 */
public class UserRepository implements IRepository<User> {

    private java.sql.Connection connection;
    private Statement statement;
    public UserRepository() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","root");
        }
            catch (Exception e){System.out.println(e);}
    }

    public Iterable<User> GetAll() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from user");
            while(rs.next()){
                User user = new User();
                user.user_id = rs.getInt("user_id");
                user.email = rs.getString("email");
                user.login = rs.getString("login");
                user.password_hash = rs.getString("password");
                user.role = Roles.valueOf(rs.getString("role"));
                users.add(user);
            }
            connection.close();
        }
        catch (Exception e){System.out.println(e);}

        return users;
    }

    public User Get(int id) {
        throw new NotImplementedException();
    }

    public void Delete(int id) {
        throw new NotImplementedException();
    }

    public void Update(User item) {
        throw new NotImplementedException();
    }
}
