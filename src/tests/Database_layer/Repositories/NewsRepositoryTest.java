package Database_layer.Repositories;

import org.junit.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Entities.News;


public class NewsRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private NewsRepository repository;
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        repository = new NewsRepository();
        userRepository = new UserRepository();
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try{
            Class.forName("com.mysql.jdbc.Driver");

<<<<<<< HEAD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","root");
=======
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","admin");
>>>>>>> workflow
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
        ArrayList<News> news = (ArrayList<News>)repository.GetAll();
    }

    @Test
    public void get() throws Exception {
        News news = repository.Get(3);
    }

    @Test
    public void delete() throws Exception {
        repository.Delete(15);
    }

    @Test
    public void update() throws Exception {
        News news = repository.Get(10);
        news.setContent("У нас все хорошо!");
        repository.Update(10, news);
    }

    @Test
    public void add() throws Exception {
        News news = new News();
        news.setNews_id(15);
        news.setContent("Скидочная акция");
        news.setTitle("Скидки!");
        news.setAuthor(userRepository.Get(3));
        news.setDate(new Date());
        repository.Add(news);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addException() throws SQLException{
        repository.Add(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getException() throws SQLException{
        repository.Get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateException_Id() throws SQLException{
        repository.Update(-1, new News());
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
        News news = new News();
        news.setDate(null);
        repository.Update(1, news);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidEntity() throws SQLException{
        News news = new News();
        news.setDate(null);
        repository.Add(news);
    }
}