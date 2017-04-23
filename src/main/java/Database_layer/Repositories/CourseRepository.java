package Database_layer.Repositories;

import Entities.Course;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by angre on 10.04.2017.
 */

public class CourseRepository implements IRepository<Course> {
    private java.sql.Connection connection;
    private Statement statement;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public CourseRepository() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","1234");
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

    public Iterable<Course> GetAll() throws SQLException {
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from course");
            while(rs.next()){
                Course course = new Course();
                course.setCourse_id(rs.getInt("course_id"));
                course.setTitle(rs.getString("title"));
                course.setPrice(rs.getFloat("price"));
                course.setDescription(rs.getString("description"));
                course.setNumberOfHours(rs.getInt("hours"));
                course.setLanguage(rs.getString("language"));
                course.setStartDate(formatter.parse(rs.getString("start_date")));
                courses.add(course);
            }
        }
        catch (Exception e){System.out.println(e);}

        return courses;
    }

    public Course Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        Course course = new Course();
        String query = String.format("SELECT * FROM course WHERE course_id=%1$d", id);
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            rs.next();
            course.setCourse_id(rs.getInt("course_id"));
            course.setTitle(rs.getString("title"));
            course.setPrice(rs.getFloat("price"));
            course.setDescription(rs.getString("description"));
            course.setNumberOfHours(rs.getInt("hours"));
            course.setLanguage(rs.getString("language"));
            course.setStartDate(formatter.parse(rs.getString("start_date")));
        } catch(Exception e){
            System.out.println(e);
            throw new IllegalAccessError();
        }
        return course;
    }

    public void Delete(int id) {
        if (id < 1) throw new IllegalArgumentException();

        String query = String.format("DELETE FROM course WHERE course_id=%1$d", id);
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, Course item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format(Locale.ENGLISH, "UPDATE course SET title='%2$s', price='%3$.3f', description='%4$s', hours=%5$d, language='%6$s', start_date='%7$s' WHERE course_id=%1$d",
                id, item.getTitle(), item.getPrice(), item.getDescription(), item.getNumberOfHours(), item.getLanguage(), formatter.format(item.getStartDate()));
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Add(Course item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format(Locale.ENGLISH, "insert into course values(%1$d, '%2$s', '%3$.3f', '%4$s', %5$d, '%6$s', '%7$s')",
                item.getCourse_id(), item.getTitle(), item.getPrice(), item.getDescription(), item.getNumberOfHours(), item.getLanguage(), formatter.format(item.getStartDate()));

        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    private boolean IsEmpty(Course item)
    {
        if (item.getDescription() == null) return true;
        if (item.getLanguage() == null) return true;
        if (item.getStartDate() == null) return true;
        if (item.getTitle() == null) return true;
        return false;
    }
}
