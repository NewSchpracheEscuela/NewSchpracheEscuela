package Database_layer.Repositories;

import Entities.Group;
import Database_layer.IRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by alexb on 15-Apr-17.
 */
public class GroupRepository implements IRepository<Group>{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Group> GetAll() throws SQLException {
        ArrayList<Group> groups = new ArrayList<Group>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `group`");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Group group = new Group();
                 group.setId(rs.getInt("group_id"));
                 group.setAmount(rs.getInt("amount"));
                 group.setCourse_id(rs.getInt("course_id"));
                 group.setLevel(rs.getString("level"));
                 group.setPerson_id(rs.getInt("person_id"));
                 groups.add(group);
            }
            connection.close();
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
            Connection connection = dataSource.getConnection();
            PreparedStatement statement =  connection.prepareStatement("SELECT * FROM `group` WHERE group_id="+id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            group.setId(rs.getInt("group_id"));
            group.setAmount(rs.getInt("amount"));
            group.setCourse_id(rs.getInt("course_id"));
            group.setLevel(rs.getString("level"));
            group.setPerson_id(rs.getInt("person_id"));
            connection.close();
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
            Connection connection = dataSource.getConnection();
            PreparedStatement statement =  connection.prepareStatement(String.format("INSERT INTO `group` (level,amount,course_id,person_id) VALUES ('%s','%d','%d','%d')",
                    entity.getLevel(),
                    entity.getAmount(),
                    entity.getCourse_id(),
                    entity.getPerson_id()
            ));
            int resultSet = statement.executeUpdate();

            System.out.println("Rows affected during Add: " + resultSet);
            connection.close();
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
            Connection connection = dataSource.getConnection();
            PreparedStatement statement =  connection.prepareStatement("DELETE FROM `group` WHERE group_id="+id);
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Delete: " + resultSet);
            connection.close();
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
            Connection connection = dataSource.getConnection();
            PreparedStatement statement =  connection.prepareStatement(String.format("UPDATE `group` SET level='%s',amount='%d',course_id='%d',person_id='%d' WHERE group_id='%s'"
                    ,item.getLevel()
                    ,item.getAmount()
                    ,item.getCourse_id()
                    ,item.getPerson_id(),
                    id));
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Update: " + resultSet);
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }
}
