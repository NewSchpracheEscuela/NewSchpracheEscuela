package Database_layer.Repositories;

import Database_layer.IRepository;
import Entities.User;

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

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","admin");
        }
            catch (Exception e){System.out.println(e);}
    }

    @Override
    protected void finalize() throws SQLException
    {
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Iterable<User> GetAll() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from user");
            while(rs.next()){
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword_hash(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPatronym(rs.getString("patronym"));
                user.setContactInfo(rs.getString("telephone"));
                users.add(user);
            }
            connection.close();
        }
        catch (Exception e){System.out.println(e);}

        return users;
    }

    public User Get(int id) {
        User user = new User();
        String query = String.format("SELECT * FROM user WHERE user_id=%1$d", id);
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            rs.next();
            user.setUser_id(rs.getInt("user_id"));
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setPassword_hash(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPatronym(rs.getString("patronym"));
            user.setContactInfo(rs.getString("telephone"));
        } catch(Exception e){System.out.println(e);}
        return user;
    }

    public void Add(User entity) {
        String query = String.format("insert into user values(%1$d, '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s', '%8$s', '%9$s')",
                entity.getUser_id(), entity.getLogin(), entity.getPassword_hash(), entity.getEmail(), entity.getRole(), entity.getFirstName(), entity.getLastName(),
                entity.getPatronym(), entity.getContactInfo());
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Delete(int id) {
        String query = String.format("DELETE FROM user WHERE user_id=%1$d", id);
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, User item) {
        String query = String.format("UPDATE user SET login='%2$s', password='%3$s', email='%4$s', role='%5$s', first_name='%6$s', last_name='%7$s', patronym='%8$s', telephone='%9$s' WHERE user_id=%1$d",
                id, item.getLogin(), item.getPassword_hash(), item.getEmail(), item.getRole(), item.getFirstName(), item.getLastName(),
                item.getPatronym(), item.getContactInfo());
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }
}
