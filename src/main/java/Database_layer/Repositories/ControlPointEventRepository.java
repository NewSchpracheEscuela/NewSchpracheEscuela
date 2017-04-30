package Database_layer.Repositories;

import Entities.Comment;
import Database_layer.IRepository;
import Entities.ControlPoint;
import Entities.ControlPointEvent;
import Entities.Group;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class ControlPointEventRepository implements IRepository<ControlPointEvent>,ApplicationContextAware {
    private java.sql.Connection connection;
    private Statement statement;
    private DataSource dataSource;
    private ApplicationContext context;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Iterable<ControlPointEvent> GetAll() throws SQLException {
        ArrayList<ControlPointEvent> pointEvents = new ArrayList<ControlPointEvent>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from control_point_group");
            //statement=connection.createStatement();
            GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
            TeacherRepository teacherRepository = (TeacherRepository) context.getBean("teacherRepository");
            ControlPointRepository pointRepository = (ControlPointRepository) context.getBean("controlPointRepository");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ControlPointEvent pointEvent = new ControlPointEvent();
                pointEvent.setControlPointEvent_id(rs.getInt("control_point_group_id"));
                pointEvent.setGroup(rs.getInt("group_id"));
                pointEvent.setTeacher(rs.getInt("teacher_id"));
                pointEvent.setControlPoint(rs.getInt("control_point_id"));
                pointEvents.add(pointEvent);
            }
            connection.close();
            return pointEvents;
        }
        catch (Exception e){System.out.println(e);}
        return null;

    }

    public ControlPointEvent Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        ControlPointEvent pointEvent = new ControlPointEvent();
        String query = String.format("SELECT * FROM control_point_group WHERE control_point_group_id=%1$d", id);
        try{
            //statement=connection.createStatement();
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
            TeacherRepository teacherRepository = (TeacherRepository) context.getBean("teacherRepository");
            ControlPointRepository pointRepository = (ControlPointRepository) context.getBean("controlPointRepository");
            rs.next();
            pointEvent.setControlPointEvent_id(rs.getInt("control_point_group_id"));
            pointEvent.setGroup(rs.getInt("group_id"));
            pointEvent.setTeacher(rs.getInt("teacher_id"));
            pointEvent.setControlPoint(rs.getInt("control_point_id"));
            connection.close();
        } catch(Exception e){
            System.out.println(e);
            throw new IllegalAccessError();
        }
        return pointEvent;
    }

    public void Delete(int id) {
        if (id < 1) throw new IllegalArgumentException();

        String query = String.format("DELETE FROM control_point_group WHERE control_point_group_id=%1$d", id);

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            //statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, ControlPointEvent item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("UPDATE control_point_group SET group_id=%2$d, control_point_id=%3$d, teacher_id=%4$d WHERE control_point_group_id=%1$d",
                id, item.getGroup(), item.getControlPoint(), item.getTeacher());
        try{
            //statement=connection.createStatement();
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    public void Add(ControlPointEvent item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("insert into control_point_group values(%1$d, %2$d, %3$d, %4$d)",
                item.getControlPointEvent_id(), item.getGroup(), item.getControlPoint(), item.getTeacher());
        try{
            //statement=connection.createStatement();


            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);}
    }

    private boolean IsEmpty(ControlPointEvent item)
    {
        if (item.getGroup() == 0) return true;
        if (item.getTeacher() == 0) return true;
        if (item.getControlPoint() == 0) return true;
        return false;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
