package Database_layer.Repositories;

import Entities.ControlPoint;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by alexb on 09-Apr-17.
 */
public class ControlPointRepository implements IRepository<ControlPoint> {
    private java.sql.Connection connection;
    private Statement statement;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    public ControlPointRepository(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","admin");
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

    public Iterable<ControlPoint> GetAll() throws SQLException {
        ArrayList<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM control_point");
            while(rs.next()){
                ControlPoint controlPoint = new ControlPoint();
                controlPoint.setId(rs.getInt("control_point_id"));
                controlPoint.setDate(formatter.parse(rs.getString("date")));
                controlPoints.add(controlPoint);
            }
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM control_point WHERE control_point_id="+id);
            resultSet.next();
            controlPoint.setId(resultSet.getInt("control_point_id"));
            controlPoint.setDate(formatter.parse(resultSet.getString("date")));
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
            int resultSet = statement.executeUpdate(
                    String.format("INSERT INTO control_point (date) VALUES ('%s')",
                            dateFormat.format(entity.getDate())));

            System.out.println("Rows affected during Add: " + resultSet);
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
            int resultSet = statement.executeUpdate("DELETE FROM control_point WHERE control_point_id="+id);
            System.out.println("Rows affected during Delete: " + resultSet);
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            int resultSet = statement.executeUpdate(String.format("UPDATE control_point SET date='%s' WHERE control_point_id='%s'",
                    dateFormat.format(item.getDate()),
                    id));
            System.out.println("Rows affected during Update: " + resultSet);
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }
}
