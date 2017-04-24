package Database_layer.Repositories;

import Entities.ControlPoint;
import Database_layer.IRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by alexb on 09-Apr-17.
 */
public class ControlPointRepository implements IRepository<ControlPoint> {
    private DataSource dataSource;

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<ControlPoint> GetAll() throws SQLException {
        ArrayList<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM control_point");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ControlPoint controlPoint = new ControlPoint();
                controlPoint.setId(rs.getInt("control_point_id"));
                controlPoint.setDate(formatter.parse(rs.getString("date")));
                controlPoints.add(controlPoint);
            }
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        } catch (ParseException e) {
            System.out.println(e);
        }
        return controlPoints;
    }

    public ControlPoint Get(int id) throws SQLException, IllegalArgumentException {
        if (id<1){
            throw new IllegalArgumentException();
        }
        ControlPoint controlPoint = new ControlPoint();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM control_point WHERE control_point_id="+id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            controlPoint.setId(resultSet.getInt("control_point_id"));
            controlPoint.setDate(formatter.parse(resultSet.getString("date")));
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        } catch (ParseException e) {
            System.out.println(e);
        }
        return controlPoint;
    }

    public void Add(ControlPoint entity) throws SQLException, IllegalArgumentException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if(entity.getDate() == null){
            throw new IllegalArgumentException();
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format("INSERT INTO control_point (date) VALUES ('%s')",
                    dateFormat.format(entity.getDate())));
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Add: " + resultSet);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void Delete(int id) throws SQLException, IllegalArgumentException {
        if (id<1){
            throw new IllegalArgumentException();
        }
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM control_point WHERE control_point_id="+id);
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Delete: " + resultSet);
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id,ControlPoint item) throws SQLException, IllegalArgumentException {
        if (id<1 || item == null){
            throw new IllegalArgumentException();
        }
        if (item.getDate() == null){
            throw new IllegalArgumentException();
        }
        ControlPoint controlPoint = new ControlPoint();
        try{
            Connection connection = dataSource.getConnection();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            PreparedStatement statement = connection.prepareStatement(String.format("UPDATE control_point SET date='%s' WHERE control_point_id='%s'",
                    dateFormat.format(item.getDate()),
                    id));
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Update: " + resultSet);
            connection.close();
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }
}
