package Database_layer.Repositories;

import Database_layer.Entities.ControlPoint;
import Database_layer.Repositories.ControlPointRepository;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.DriverManager;
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
        repository = new ControlPointRepository();
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

}