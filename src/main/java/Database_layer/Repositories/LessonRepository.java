package Database_layer.Repositories;

import Entities.Group;
import Entities.Teacher;
import Entities.Lesson;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class LessonRepository implements IRepository<Lesson> {
    private java.sql.Connection connection;
    private Statement statement;
    private TeacherRepository teacherRepository = new TeacherRepository();
    private GroupRepository groupRepository = new GroupRepository();
    private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    public LessonRepository() {
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

    public Iterable<Lesson> GetAll() throws SQLException {
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from teacher_group");
            while(rs.next()){
                Lesson lesson = new Lesson();
                lesson.setLesson_id(rs.getInt("teacher_group_id"));
                lesson.setTime(formatter.parse(rs.getString("time")));
                lesson.setDay(rs.getString("day"));
                lesson.setTeacher(teacherRepository.Get(rs.getInt("teacher_id")));
                lesson.setGroup(groupRepository.Get(rs.getInt("group_id")));
                lesson.setRoom(rs.getString("room"));
                lessons.add(lesson);
            }
            connection.close();
        }
        catch (Exception e){System.out.println(e);}

        return lessons;
    }

    public Lesson Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        Lesson lesson = new Lesson();
        String query = String.format("SELECT * FROM comment WHERE teacher_group_id=%1$d", id);
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            rs.next();
            lesson.setLesson_id(rs.getInt("teacher_group_id"));
            lesson.setTime(formatter.parse(rs.getString("date")));
            lesson.setDay(rs.getString("day"));
            lesson.setTeacher(teacherRepository.Get(rs.getInt("teacher_id")));
            lesson.setGroup(groupRepository.Get(rs.getInt("group_id")));
            lesson.setRoom(rs.getString("room"));
        } catch(Exception e){
            System.out.println(e);
            throw new IllegalAccessError();
        }
        return lesson;
    }

    public void Delete(int id) {
        if (id < 1) throw new IllegalArgumentException();

        String query = String.format("DELETE FROM comment WHERE teacher_group_id=%1$d", id);
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, Lesson item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("UPDATE teacher_group SET room='%2$s', teacher_id=%3$d, group_id=%4$d, time='%5$s', day='%6$s' WHERE teacher_group_id=%1$d",
                id, item.getRoom(), item.getTeacher().getId(), item.getGroup().getId(), formatter.format(item.getTime()), item.getDay());
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Add(Lesson item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("insert into teacher_group values(%1$d, '%2$s', %3$s, %4$d, %5$d, '%6$s')",
                item.getLesson_id(), item.getRoom(), formatter.format(item.getTime()), item.getTeacher().getId(), item.getGroup().getId(), item.getDay());
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    private boolean IsEmpty(Lesson item)
    {
        if (item.getDay() == null) return true;
        if (item.getGroup() == null) return true;
        if (item.getTeacher() == null) return true;
        if (item.getTime() == null) return true;
        return false;
    }
}
