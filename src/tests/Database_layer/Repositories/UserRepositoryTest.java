package Database_layer.Repositories;

import Database_layer.Entities.User;
import Database_layer.Enumerations.Roles;
import org.junit.*;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by angre on 15.04.2017.
 */
public class UserRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new UserRepository();
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","1234");
        }
        catch (Exception e){System.out.println(e);}
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        connection.close();
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void getAll() throws Exception {
        ArrayList<User> users = (ArrayList<User>) repository.GetAll();
    }

    @Test
    public void get() throws Exception {
        User user = (User) repository.Get(16);
    }

    @Test
    public void add() throws Exception {
        User user = new User();
        user.setContactInfo("+375293460944");
        user.setPatronym("Hienadziavic");
        user.setFirstName("Hienadz");
        user.setLastName("Hienadzka");
        user.setEmail("16@gmail.com");
        user.setPassword_hash("1234");
        user.setRole(Roles.admin);
        user.setLogin("Hienadz");
        user.setUser_id(16);
        repository.Add(user);
    }

    @Test
    public void delete() throws Exception {
        repository.Delete(16);
    }

    @Test
    public void update() throws Exception {
        User user = repository.Get(15);
        user.setLogin("sandal gnome");
        repository.Update(15, user);
    }

}