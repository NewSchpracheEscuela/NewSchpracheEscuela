package Database_layer.Repositories;

import Entities.Group;
import Entities.Teacher;
import Entities.Lesson;
import Database_layer.IRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class LessonRepository implements IRepository<Lesson>,ApplicationContextAware {
    private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private DataSource dataSource;
    private ApplicationContext context;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Lesson> GetAll() throws SQLException {
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from teacher_group");
            ResultSet rs = statement.executeQuery();
            GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
            TeacherRepository teacherRepository = (TeacherRepository) context.getBean("teacherRepository");
            while(rs.next()){
                Lesson lesson = new Lesson();
                lesson.setLesson_id(rs.getInt("teacher_group_id"));
                lesson.setTime(formatter.parse(rs.getString("time")));
                lesson.setDay(rs.getString("day"));
                lesson.setTeacher(rs.getInt("teacher_id"));
                lesson.setGroup(rs.getInt("group_id"));
                lesson.setRoom(rs.getString("room"));
                lessons.add(lesson);
            }
            connection.close();
            return lessons;
        }
        catch (Exception e){System.out.println(e);}

        return null;
    }

    public Lesson Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        Lesson lesson = new Lesson();
        String query = String.format("SELECT * FROM teacher_group WHERE teacher_group_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
            TeacherRepository teacherRepository = (TeacherRepository) context.getBean("teacherRepository");

            rs.next();
            lesson.setLesson_id(rs.getInt("teacher_group_id"));
            lesson.setTime(formatter.parse(rs.getString("time")));
            lesson.setDay(rs.getString("day"));
            lesson.setTeacher(rs.getInt("teacher_id"));
            lesson.setGroup(rs.getInt("group_id"));
            lesson.setRoom(rs.getString("room"));
            connection.close();
            return lesson;
        } catch(Exception e){
            System.out.println(e);
            throw new IllegalAccessError();
        }
    }

    public void Delete(int id) {
        if (id < 1) throw new IllegalArgumentException();

        String query = String.format("DELETE FROM teacher_group WHERE teacher_group_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, Lesson item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("UPDATE teacher_group SET room='%2$s', teacher_id=%3$d, group_id=%4$d, time='%5$s', day='%6$s' WHERE teacher_group_id=%1$d",
                id, item.getRoom(), item.getTeacher(), item.getGroup(), formatter.format(item.getTime()), item.getDay());
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    public void Add(Lesson item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("insert into teacher_group values(%1$d, '%2$s', '%3$s', %4$d, %5$d, '%6$s')",
                item.getLesson_id(), item.getRoom(), formatter.format(item.getTime()), item.getTeacher(), item.getGroup(), item.getDay());
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    private boolean IsEmpty(Lesson item)
    {
        if (item.getDay() == null) return true;
        if (item.getGroup() == 0) return true;
        if (item.getTeacher() == 0) return true;
        if (item.getTime() == null) return true;
        return false;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
