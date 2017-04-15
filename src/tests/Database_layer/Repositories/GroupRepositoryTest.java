package Database_layer.Repositories;

import Database_layer.Entities.ControlPoint;
import Database_layer.Entities.Group;
import Database_layer.IRepository;
import org.junit.*;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by alexb on 15-Apr-17.
 */
public class GroupRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private IRepository<Group> repository;
    @Before
    public void setUp()throws Exception{
        repository = new GroupRepository();
    }
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","admin");
        }
        catch (Exception e){System.out.println(e);}
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        connection.close();
    }

    @Test
    public void getAll() throws Exception {
        ArrayList<Group> groups = (ArrayList<Group>) repository.GetAll();
    }

    @Test
    public void get() throws Exception {
        Group group = repository.Get(5);
    }

    @Test
    public void add() throws Exception {
        Group group = new Group();
        group.setLevel("a1");
        group.setAmount(10);
        group.setCourse_id(2);
        group.setPerson_id(2);
        repository.Add(group);
    }

    @Test
    public void update() throws Exception {
        Group group = new Group();
        group.setLevel("a2");
        group.setAmount(10);
        group.setPerson_id(3);
        group.setCourse_id(3);
        repository.Update(17,group);
    }

    @Test
    public void delete() throws Exception {
        repository.Delete(18);
    }
}