package Database_layer.Repositories;

import Entities.Teacher;
import Database_layer.IRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by alexb on 15-Apr-17.
 */
public class TeacherRepository implements IRepository<Teacher> {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Teacher> GetAll() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `teacher`");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("teacher_id"));
                teacher.setUser_id(rs.getInt("user_id"));
                teachers.add(teacher);
            }
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return teachers;
    }

    public Teacher Get(int id) throws SQLException, IllegalArgumentException {
        if (id<1){
            throw new IllegalArgumentException();
        }
        Teacher teacher = new Teacher();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `teacher` WHERE teacher_id="+id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            teacher.setId(rs.getInt("teacher_id"));
            teacher.setUser_id(rs.getInt("user_id"));
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return teacher;
    }

    public void Add(Teacher entity) throws SQLException, IllegalArgumentException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if(entity.getUser_id() <1){
            throw new IllegalArgumentException();
        }
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    String.format("INSERT INTO `teacher` (user_id) VALUES ('%d')",
                        entity.getUser_id()));
            int resultSet = statement.executeUpdate();
            connection.close();
            System.out.println("Rows affected during Add: " + resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void Delete(int id) throws SQLException,IllegalArgumentException {
        if (id<1){
            throw new IllegalArgumentException();
        }
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `teacher` WHERE teacher_id="+id);
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Delete: " + resultSet);
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, Teacher item) throws SQLException, IllegalArgumentException {
        if (item == null || id<1){
            throw new IllegalArgumentException();
        }
        if(item.getUser_id() <1){
            throw new IllegalArgumentException();
        }
        Teacher teacher = new Teacher();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    String.format("UPDATE `teacher` SET user_id='%d' WHERE teacher_id='%s'",
                        item.getUser_id(),
                        id));
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Update: " + resultSet);
            connection.close();
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }


}
