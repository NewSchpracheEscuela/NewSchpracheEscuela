package Database_layer.Repositories;

import Database_layer.Enumerations.Languages;
import Entities.Comment;
import org.junit.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import Entities.Course;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by angre on 16.04.2017.
 */
public class CourseRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private CourseRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new CourseRepository();
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
        ArrayList<Course> courses = (ArrayList<Course>)repository.GetAll();

        Assert.assertEquals(courses.size(), 15);
    }

    @Test
    public void get() throws Exception {
        Course course = repository.Get(15);

        Assert.assertEquals(Languages.italian.toString(), course.getLanguage());
    }

    @Test(expected = IllegalAccessError.class)
    public void delete() throws Exception {
        repository.Delete(15);

        repository.Get(15);
    }

    @Test
    public void update() throws Exception {
        Course course = repository.Get(15);
        course.setLanguage(Languages.italian);
        repository.Update(15, course);

        Assert.assertEquals(Languages.italian.toString(), repository.Get(15).getLanguage());
    }

    @Test
    public void add() throws Exception {
        Course course = new Course();
        course.setTitle("Итальянский");
        course.setPrice((float)2.16);
        course.setStartDate(new Date());
        course.setLanguage(Languages.italian);
        course.setDescription("Способ общения для тех, у кого поселился маленький венецианский гондольер в сердце");
        course.setNumberOfHours(168);
        course.setCourse_id(15);
        repository.Add(course);

        Assert.assertEquals(Languages.italian.toString(), repository.Get(15).getLanguage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_throwsSQLException() throws Exception{
        Course course = new Course();
        repository.Add(course);
    }

    @Test
    public void delete_throwsSQLException() throws Exception{
        repository.Delete(20);
    }

    @Test(expected = IllegalAccessError.class)
    public void get_throwsSQLException() throws Exception{
        repository.Get(20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_throwsSQLException() throws Exception{
        repository.Update(20, new Course());
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
        repository.Update(-1, new Course());
    }
}