package Database_layer.Repositories;

import Database_layer.IRepository;
import Entities.GroupStudent;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupStudentRepository implements IRepository<GroupStudent>{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<GroupStudent> GetAll() throws SQLException {
        ArrayList<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `person_group`");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                GroupStudent groupStudent = new GroupStudent();
                groupStudent.setGroupId(rs.getInt("group_id"));
                groupStudent.setGroupStudentId(rs.getInt("person_group_id"));
                groupStudent.setPersonId(rs.getInt("person_id"));
                groupStudents.add(groupStudent);
            }
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return groupStudents;
    }

    public GroupStudent Get(int id) throws SQLException {
        if (id<1){
            throw new IllegalArgumentException();
        }
        GroupStudent groupStudent = new GroupStudent();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `person_group` WHERE person_group_id="+id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            groupStudent.setGroupId(rs.getInt("group_id"));
            groupStudent.setGroupStudentId(rs.getInt("person_group_id"));
            groupStudent.setPersonId(rs.getInt("person_id"));
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return groupStudent;
    }

    public void Add(GroupStudent entity) throws SQLException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if(entity.getPersonId() <1 || entity.getGroupId() < 1){
            throw new IllegalArgumentException();
        }
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    String.format("INSERT INTO `person_group` (group_id,person_id) VALUES ('%d','%d')",
                            entity.getGroupId(),
                            entity.getPersonId()));
            int resultSet = statement.executeUpdate();
            connection.close();
            System.out.println("Rows affected during Add: " + resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void Delete(int id) throws SQLException {
        if (id < 1){
            throw new IllegalArgumentException();
        }
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `person_group` WHERE person_group_id="+id);
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Delete: " + resultSet);
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, GroupStudent item) throws SQLException {
        if (item == null || id<1){
            throw new IllegalArgumentException();
        }
        if(item.getPersonId() <1 || item.getGroupId() < 1){
            throw new IllegalArgumentException();
        }
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    String.format("UPDATE `person_group` SET group_id='%d',person_id='%d' WHERE person_group_id='%s'",
                            item.getGroupId(),
                            item.getPersonId(),
                            id));
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Update: " + resultSet);
            connection.close();
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }
}
