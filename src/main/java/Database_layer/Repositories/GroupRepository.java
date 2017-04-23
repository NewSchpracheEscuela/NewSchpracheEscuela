package Database_layer.Repositories;

import Entities.Group;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","1234");
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
                 groups.add(group);
            }
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return groups;
    }

    public Group Get(int id) throws SQLException, IllegalArgumentException {
        if (id<1){
            throw new IllegalArgumentException();
        }
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

    public void Add(Group entity) throws SQLException,IllegalArgumentException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if(entity.getCourse_id() <1 || entity.getAmount() < 1 || entity.getLevel() == null){
            throw new IllegalArgumentException();
        }
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

    public void Delete(int id) throws SQLException,IllegalArgumentException {
        if (id < 1){
            throw new IllegalArgumentException();
        }
        try{
            int resultSet = statement.executeUpdate("DELETE FROM `group` WHERE group_id="+id);
            System.out.println("Rows affected during Delete: " + resultSet);
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, Group item) throws SQLException, IllegalArgumentException {
        if (item == null || id<1){
            throw new IllegalArgumentException();
        }
        if(item.getCourse_id() <1 || item.getAmount() < 1 || item.getLevel() == null){
            throw new IllegalArgumentException();
        }
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
