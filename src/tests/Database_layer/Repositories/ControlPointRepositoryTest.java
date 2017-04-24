package Database_layer.Repositories;

import Entities.ControlPoint;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alexb on 09-Apr-17.
 */
public class ControlPointRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private ControlPointRepository repository;
    @Before
    public void setUp()throws Exception{
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        repository = (ControlPointRepository) context.getBean("controlPointRepository");
    }

    @Test
    public void getAll() throws Exception {
        ArrayList<ControlPoint> controlPoints = (ArrayList<ControlPoint>) repository.GetAll();
    }

    @Test
    public void get() throws Exception {
        ControlPoint controlPoint = (ControlPoint) repository.Get(5);
    }
    @Test
    public void add() throws Exception{
        ControlPoint controlPoint = new ControlPoint();
        controlPoint.setDate(new Date(2005,5,5));
        repository.Add(controlPoint);
    }
    @Test
    public void delete() throws Exception {
        repository.Delete(16);
    }

    @Test
    public void update() throws Exception {
        ControlPoint controlPoint = new ControlPoint();
        controlPoint.setDate(new Date(2017,5,5));
        repository.Update(18,controlPoint);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addException() throws SQLException {
        repository.Add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getException() throws SQLException{
        repository.Get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateException_Id() throws SQLException{
        repository.Update(-1, new ControlPoint());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateException_Item() throws SQLException{
        repository.Update(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteException() throws SQLException{
        repository.Delete(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateInvalidEntity() throws SQLException{
        ControlPoint controlPoint = new ControlPoint();
        controlPoint.setDate(null);
        repository.Update(1, controlPoint);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidEntity() throws SQLException{
        ControlPoint controlPoint = new ControlPoint();
        controlPoint.setDate(null);
        repository.Add(controlPoint);
    }
}