package Database_layer.Repositories;

import Entities.Course;
import Database_layer.IRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by angre on 10.04.2017.
 */

public class CourseRepository implements IRepository<Course> {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Course> GetAll() throws SQLException {
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from course");
            ResultSet rs = statement.executeQuery();
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
            connection.close();
            return courses;
        }
        catch (Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    public Course Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        Course course = new Course();
        String query = String.format("SELECT * FROM course WHERE course_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            rs.next();
            course.setCourse_id(rs.getInt("course_id"));
            course.setTitle(rs.getString("title"));
            course.setPrice(rs.getFloat("price"));
            course.setDescription(rs.getString("description"));
            course.setNumberOfHours(rs.getInt("hours"));
            course.setLanguage(rs.getString("language"));
            course.setStartDate(formatter.parse(rs.getString("start_date")));
            connection.close();
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
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    public void Update(int id, Course item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format(Locale.ENGLISH, "UPDATE course SET title='%2$s', price='%3$.3f', description='%4$s', hours=%5$d, language='%6$s', start_date='%7$s' WHERE course_id=%1$d",
                id, item.getTitle(), item.getPrice(), item.getDescription(), item.getNumberOfHours(), item.getLanguage(), formatter.format(item.getStartDate()));
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    public void Add(Course item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format(Locale.ENGLISH, "insert into course (title, price, description, hours, language, start_date) values('%2$s', '%3$.3f', '%4$s', %5$d, '%6$s', '%7$s')",
                item.getCourse_id(), item.getTitle(), item.getPrice(), item.getDescription(), item.getNumberOfHours(), item.getLanguage(), formatter.format(item.getStartDate()));

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
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
