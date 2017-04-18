package Database_layer.Repositories;

import Entities.Teacher;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by alexb on 15-Apr-17.
 */
public class TeacherRepository implements IRepository<Teacher> {
    private java.sql.Connection connection;
    private Statement statement;

    public TeacherRepository(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","root");
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

    public Iterable<Teacher> GetAll() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM `teacher`");
            while(rs.next()){
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("teacher_id"));
                teacher.setUser_id(rs.getInt("user_id"));
                teachers.add(teacher);
            }
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return teachers;
    }

    public Teacher Get(int id) throws SQLException {
        Teacher teacher = new Teacher();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM `teacher` WHERE teacher_id="+id);
            rs.next();
            teacher.setId(rs.getInt("teacher_id"));
            teacher.setUser_id(rs.getInt("user_id"));

        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return teacher;
    }

    public void Add(Teacher entity) throws SQLException {
        try {
            int resultSet = statement.executeUpdate(
                    String.format("INSERT INTO `teacher` (user_id) VALUES ('%d')",
                            entity.getUser_id()));

            System.out.println("Rows affected during Add: " + resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void Delete(int id) throws SQLException {
        try{
            int resultSet = statement.executeUpdate("DELETE FROM `teacher` WHERE teacher_id="+id);
            System.out.println("Rows affected during Delete: " + resultSet);
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, Teacher item) throws SQLException {
        Teacher teacher = new Teacher();
        try{
            int resultSet = statement.executeUpdate(String.format("UPDATE `teacher` SET user_id='%d' WHERE teacher_id='%s'",
                    item.getUser_id(),
                    id));
            System.out.println("Rows affected during Update: " + resultSet);
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }
}
