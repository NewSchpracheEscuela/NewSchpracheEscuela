package Database_layer.Repositories;

import Entities.Comment;
import org.junit.*;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by angre on 15.04.2017.
 */
public class CommentRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private CommentRepository repository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        repository = new CommentRepository();
        courseRepository = new CourseRepository();
        userRepository = new UserRepository();
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","root");
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
        ArrayList<Comment> news = (ArrayList<Comment>)repository.GetAll();
    }

    @Test
    public void get() throws Exception {
        Comment comment = repository.Get(3);
    }

    @Test
    public void delete() throws Exception {
        repository.Delete(15);
    }

    @Test
    public void update() throws Exception {
        Comment comment = repository.Get(15);
        comment.setEntity("Hi guys. I\\'m new here. Can explain what this is all about?");
        repository.Update(15, comment);
    }

    @Test
    public void add() throws Exception {
        Comment comment = new Comment();
        comment.setEntity("Hi guys. I\\'m new here");
        comment.setAuthor(userRepository.Get(8));
        comment.setDate(new Date());
        comment.setCourse(courseRepository.Get(4));
        comment.setComment_id(15);
        repository.Add(comment);
    }
}