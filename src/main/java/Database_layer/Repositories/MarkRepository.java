package Database_layer.Repositories;

import Database_layer.IRepository;
import Entities.Mark;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarkRepository implements IRepository<Mark>{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Mark> GetAll() throws SQLException {
        ArrayList<Mark> marks = new ArrayList<Mark>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `control_point_person`");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Mark mark = new Mark();
                mark.setId(rs.getInt("control_point_person_id"));
                mark.setMark(rs.getInt("mark"));
                mark.setPersonId(rs.getInt("person_id"));
                mark.setControlPointId(rs.getInt("control_point_id"));
                marks.add(mark);
            }
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return marks;
    }

    public Mark Get(int id) throws SQLException {
        if (id<1){
            throw new IllegalArgumentException();
        }
        Mark mark = new Mark();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `control_point_person` WHERE control_point_person_id="+id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            mark.setId(rs.getInt("control_point_person_id"));
            mark.setMark(rs.getInt("mark"));
            mark.setPersonId(rs.getInt("person_id"));
            mark.setControlPointId(rs.getInt("control_point_id"));
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return mark;
    }

    public void Add(Mark entity) throws SQLException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if(entity.getPersonId() <1 || entity.getControlPointId() < 1){
            throw new IllegalArgumentException();
        }
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    String.format("INSERT INTO `control_point_person` (mark,person_id,control_point_id) VALUES ('%d','%d','%d')",
                            entity.getMark(),
                            entity.getPersonId(),
                            entity.getControlPointId()));
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `control_point_person` WHERE control_point_person_id="+id);
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Delete: " + resultSet);
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, Mark item) throws SQLException {
        if (item == null || id<1){
            throw new IllegalArgumentException();
        }
        if(item.getPersonId() <1 || item.getControlPointId() < 1){
            throw new IllegalArgumentException();
        }
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    String.format("UPDATE `control_point_person` SET mark='%d',person_id='%d',control_point_id='%d' WHERE control_point_person_id='%s'",
                            item.getMark(),
                            item.getPersonId(),
                            item.getControlPointId(),
                            id));
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Update: " + resultSet);
            connection.close();
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }


}
