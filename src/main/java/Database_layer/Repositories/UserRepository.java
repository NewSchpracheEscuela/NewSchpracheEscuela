package Database_layer.Repositories;

import Database_layer.IRepository;
import Entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by alexb on 14-Mar-17.
 */
public class UserRepository implements IRepository<User> {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<User> GetAll() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from user");
            ResultSet rs = statement.executeQuery();
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
            return users;
        }
        catch (Exception e){System.out.println(e);}

        return null;
    }

    public int CheckByLoginandPass(String login,String pass) throws SQLException {
        if (login.equals("") || pass.equals(""))
            throw new IllegalArgumentException();
        String query = String.format("SELECT user_id,login FROM user WHERE login='%s' AND password='%s'",login,pass);
        Connection connection = dataSource.getConnection();
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("user_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public User Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        User user = new User();
        String query = String.format("SELECT * FROM user WHERE user_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
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
            connection.close();
            return user;
        } catch(Exception e){
            System.out.println(e);
            throw new IllegalAccessError();
        }
    }

    public void Add(User entity) {
        if (entity == null) throw new IllegalArgumentException();
        if (IsEmpty(entity)) throw new IllegalArgumentException();

        String query = String.format("insert into user values('%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s', '%8$s', '%9$s')"
                , entity.getLogin(), entity.getPassword_hash(), entity.getEmail(), entity.getRole(), entity.getFirstName(), entity.getLastName(),
                entity.getPatronym(), entity.getContactInfo());
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    public void Delete(int id) {
        if (id < 1) throw new IllegalArgumentException();
        String query;
            query = String.format("DELETE FROM user WHERE user_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, User item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();
        String query = String.format("UPDATE user SET login='%2$s', password='%3$s', email='%4$s', role='%5$s', first_name='%6$s', last_name='%7$s', patronym='%8$s', telephone='%9$s' WHERE user_id=%1$d",
                    id, item.getLogin(), item.getPassword_hash(), item.getEmail(), item.getRole(), item.getFirstName(), item.getLastName(),
                    item.getPatronym(), item.getContactInfo());
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    private boolean IsEmpty(User item)
    {
        if (item.getContactInfo() == null ) return true;
        if (item.getContactInfo().equals("")) return true;
        if (item.getEmail() == null) return true;
        if (item.getEmail().equals("")) return true;
        if (item.getFirstName() == null) return true;
        if (item.getFirstName().equals("")) return true;
        if (item.getLastName() == null) return true;
        if (item.getLastName().equals("")) return true;
        if (item.getLogin() == null) return true;
        if (item.getLogin().equals("")) return true;
        if (item.getPassword_hash() == null) return true;
        if (item.getPassword_hash().equals("")) return true;
        if (item.getPatronym() == null) return true;
        if (item.getPatronym().equals("")) return true;
        if (item.getRole() == null) return true;
        return false;
    }
}
