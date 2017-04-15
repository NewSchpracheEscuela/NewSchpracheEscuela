package Database_layer.Repositories;

import Database_layer.Entities.ControlPoint;
import Database_layer.Entities.Group;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by alexb on 15-Apr-17.
 */
public class GroupRepository implements IRepository<Group>{
    private java.sql.Connection connection;
    private Statement statement;
    public GroupRepository(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","admin");
            statement=connection.createStatement();
        }
        catch (Exception e){System.out.println(e);}
    }
    @Override
    protected void finalize() throws SQLException {
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Iterable<Group> GetAll() throws SQLException {
        ArrayList<Group> groups = new ArrayList<Group>();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM `group`");
            while(rs.next()){
                Group group = new Group();
                 group.setId(rs.getInt("group_id"));
                 group.setAmount(rs.getInt("amount"));
                 group.setCourse_id(rs.getInt("course_id"));
                 group.setLevel(rs.getString("level"));
                 group.setPerson_id(rs.getInt("person_id"));
            }
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return groups;
    }

    public Group Get(int id) throws SQLException {
        Group group = new Group();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM `group` WHERE group_id="+id);
            rs.next();
            group.setId(rs.getInt("group_id"));
            group.setAmount(rs.getInt("amount"));
            group.setCourse_id(rs.getInt("course_id"));
            group.setLevel(rs.getString("level"));
            group.setPerson_id(rs.getInt("person_id"));
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return group;
    }

    public void Add(Group entity) throws SQLException {
        try {
            int resultSet = statement.executeUpdate(
                    String.format("INSERT INTO `group` (level,amount,course_id,person_id) VALUES ('%s','%d','%d','%d')",
                            entity.getLevel(),
                            entity.getAmount(),
                            entity.getCourse_id(),
                            entity.getPerson_id()
                            ));

            System.out.println("Rows affected during Add: " + resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void Delete(int id) throws SQLException {
        try{
            int resultSet = statement.executeUpdate("DELETE FROM `group` WHERE group_id="+id);
            System.out.println("Rows affected during Delete: " + resultSet);
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, Group item) throws SQLException {
        try{
            int resultSet = statement.executeUpdate(String.format("UPDATE `group` SET level='%s',amount='%d',course_id='%d',person_id='%d' WHERE group_id='%s'"
                    ,item.getLevel()
                    ,item.getAmount()
                    ,item.getCourse_id()
                    ,item.getPerson_id(),
                    id));
            System.out.println("Rows affected during Update: " + resultSet);
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }
}
