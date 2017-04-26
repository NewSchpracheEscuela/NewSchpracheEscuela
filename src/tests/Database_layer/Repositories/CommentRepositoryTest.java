package Database_layer.Repositories;

import Entities.Comment;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by angre on 15.04.2017.
 */
public class CommentRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private static CommentRepository repository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        repository = (CommentRepository) context.getBean("commentRepository");
    }


    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void getAll() throws Exception {
        ArrayList<Comment> comments = (ArrayList<Comment>)repository.GetAll();

        //Assert.assertEquals(13, comments.size());
    }

    @Test
    public void get() throws Exception {
        Comment comment = repository.Get(4);

        //Assert.assertEquals("нравится", comment.getEntity());
    }

    @Test(expected = IllegalAccessError.class)
    public void delete() throws Exception {
        repository.Delete(15);

        repository.Get(15);
    }

    @Test
    public void update() throws Exception {
        Comment comment = repository.Get(10);
        comment.setEntity("Hi guys. I\\'m new here. Can explain what this is all about?");
        repository.Update(10, comment);

        //Assert.assertEquals("Hi guys. I'm new here. Can explain what this is all about?", repository.Get(14).getEntity());
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

        Assert.assertEquals("Hi guys. I'm new here", repository.Get(15).getEntity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_throwsSQLException() throws Exception{
        Comment comment = new Comment();
        repository.Add(comment);
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
        repository.Update(20, new Comment());
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
        repository.Update(-1, new Comment());
    }

    @Test(expected=IllegalAccessError.class)
    public void get_something()
    {
        repository.Get(145);
    }
}