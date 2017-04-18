package Database_layer.Repositories;

import Entities.Comment;
import Entities.User;
import Database_layer.Enumerations.Roles;
import org.junit.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

        Assert.assertEquals(users.size(), 16);
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

        Assert.assertEquals("Hienadz", repository.Get(16).getLogin());
    }

    @Test(expected = SQLException.class)
    public void delete() throws Exception {
        repository.Delete(16);

        repository.Get(16);
    }

    @Test
    public void update() throws Exception {
        User user = repository.Get(15);
        user.setLogin("sandal gnome");
        repository.Update(15, user);

        Assert.assertEquals("sandal gnome", repository.Get(15).getLogin());
    }

    @Test(expected = SQLException.class)
    public void add_throwsSQLException() throws Exception{
        User user = new User();
        repository.Add(user);
    }

    @Test(expected = SQLException.class)
    public void delete_throwsSQLException() throws Exception{
        repository.Delete(20);
    }

    @Test(expected = SQLException.class)
    public void get_throwsSQLException() throws Exception{
        repository.Get(20);
    }

    @Test(expected = SQLException.class)
    public void update_throwsSQLException() throws Exception{
        repository.Update(20, new User());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_throwsIllegalArgumentException()
    {
        repository.Add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_throwsIllegalArgumentException()
    {
        repository.Delete(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_throwsIllegalArgumentException()
    {
        repository.Get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_throwsIllegalArgumentException_item()
    {
        repository.Update(13, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_throwsIllegalArgumentException_id()
    {
        repository.Update(-1, new User());
    }
}