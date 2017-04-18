package Database_layer.Repositories;

import org.junit.*;

import java.sql.DriverManager;
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
        News news = repository.Get(15);
        news.setContent("Как дела?");
        repository.Update(15, news);
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

}