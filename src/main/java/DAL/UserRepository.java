package DAL;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexb on 14-Mar-17.
 */
public class UserRepository implements IRepository<User> {

    public UserRepository() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/newschpracheescuela","root","admin");
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from user");
            ArrayList<User> users = new ArrayList<User>();
            while(rs.next()){
                User user = new User();
                user.user_id = rs.getInt(1);
                user.email = rs.getString(2);
                user.login = rs.getString(3);
                user.password_hash = rs.getString(4);
                user.role = Roles.values()[rs.getInt(5)];
                users.add(user);

            con.close();
            }
        }
            catch (Exception e){System.out.println(e);}
    }

    public Iterable<User> GetAll() {

        return null;
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
