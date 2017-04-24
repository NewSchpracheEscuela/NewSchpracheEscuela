package Database_layer.Repositories;

import Entities.Comment;
import Database_layer.IRepository;
import Entities.ControlPoint;
import Entities.ControlPointEvent;
import Entities.Group;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class ControlPointEventRepository implements IRepository<ControlPointEvent> {
    private java.sql.Connection connection;
    private Statement statement;
    private GroupRepository groupRepository = new GroupRepository();
    private TeacherRepository teacherRepository = new TeacherRepository();
    private ControlPointRepository pointRepository = new ControlPointRepository();

    public ControlPointEventRepository() {
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

    public Iterable<ControlPointEvent> GetAll() throws SQLException {
        ArrayList<ControlPointEvent> pointEvents = new ArrayList<ControlPointEvent>();
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from control_point_group");
            while(rs.next()){
                ControlPointEvent pointEvent = new ControlPointEvent();
                pointEvent.setControlPointEvent_id(rs.getInt("control_point_group_id"));
                pointEvent.setGroup(groupRepository.Get(rs.getInt("group_id")));
                pointEvent.setTeacher(teacherRepository.Get(rs.getInt("teacher_id")));
                pointEvent.setControlPoint(pointRepository.Get(rs.getInt("control_point_id")));
                pointEvents.add(pointEvent);
            }
        }
        catch (Exception e){System.out.println(e);}

        return pointEvents;
    }

    public ControlPointEvent Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        ControlPointEvent pointEvent = new ControlPointEvent();
        String query = String.format("SELECT * FROM control_point_group WHERE control_point_group_id=%1$d", id);
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            rs.next();
            pointEvent.setControlPointEvent_id(rs.getInt("control_point_group_id"));
            pointEvent.setGroup(groupRepository.Get(rs.getInt("group_id")));
            pointEvent.setTeacher(teacherRepository.Get(rs.getInt("teacher_id")));
            pointEvent.setControlPoint(pointRepository.Get(rs.getInt("control_point_id")));
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
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, ControlPointEvent item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("UPDATE control_point_group SET group_id=%2$d, control_point_id=%3$d, teacher_id=%4$d WHERE control_point_group_id=%1$d",
                id, item.getGroup().getId(), item.getControlPoint().getId(), item.getTeacher().getId());
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Add(ControlPointEvent item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("insert into control_point_group values(%1$d, %2$d, %3$d, %4$d)",
                item.getControlPointEvent_id(), item.getGroup().getId(), item.getControlPoint().getId(), item.getTeacher().getId());
        try{
            statement=connection.createStatement();

            statement.executeUpdate(query);
        } catch(Exception e){System.out.println(e);}
    }

    private boolean IsEmpty(ControlPointEvent item)
    {
        if (item.getGroup() == null) return true;
        if (item.getTeacher() == null) return true;
        if (item.getControlPoint() == null) return true;
        return false;
    }
}
