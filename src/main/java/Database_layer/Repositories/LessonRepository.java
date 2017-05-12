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

    public Iterable<Lesson> GetForTeacher(int id) {
        if (id < 1) throw new IllegalArgumentException();

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        String query = String.format("SELECT `teacher_group_id`,`day`,`room`,`time`,`user_id`,teacher_group.group_id, course.title, course.language, database_nse.group.level FROM database_nse.teacher_group left join database_nse.teacher on (teacher_group.teacher_id= teacher.teacher_id) left join database_nse.group on (teacher_group.group_id = database_nse.group.group_id)left join database_nse.course on (database_nse.group.course_id = database_nse.course.course_id)Where user_id =%1$d order by time", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLesson_id(rs.getInt("teacher_group_id"));
                lesson.setTime(formatter.parse(rs.getString("time")));
                lesson.setDay(rs.getString("day"));
                lesson.setGroup(rs.getInt("group_id"));
                lesson.setRoom(rs.getString("room"));
                lesson.setCourse_language(rs.getString("language"));
                lesson.setCourse_title(rs.getString("title"));
                lesson.setGroup_level(rs.getString("level"));
                lessons.add(lesson);
            }
            connection.close();
            return lessons;
        } catch(Exception e){
            System.out.println(e);
            throw new IllegalAccessError();
        }
    }

    public Iterable<Lesson> GetForStudent(int id) {
        if (id < 1) throw new IllegalArgumentException();

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        String query = String.format("SELECT `teacher_group_id`, `room`, `time`, `day` FROM database_nse.teacher_group LEFT JOIN database_nse.group ON (teacher_group.group_id = database_nse.group.group_id) WHERE teacher_group.group_id = (select group_id from database_nse.person_group where person_id = (select person_id from database_nse.person where user_id =%1$d)) ORDER BY time", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setLesson_id(rs.getInt("teacher_group_id"));
                lesson.setTime(formatter.parse(rs.getString("time")));
                lesson.setDay(rs.getString("day"));
                lesson.setRoom(rs.getString("room"));
                lessons.add(lesson);
            }
            connection.close();
            return lessons;
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

        String query = String.format("insert into teacher_group (room,time,teacher_id,group_id,day) values('%1$s', '%2$s', %3$d, %4$d, '%5$s')",
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
