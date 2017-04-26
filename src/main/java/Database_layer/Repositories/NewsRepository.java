package Database_layer.Repositories;

import Entities.News;
import Database_layer.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class NewsRepository implements IRepository<News> {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Iterable<News> GetAll() throws SQLException {
        ArrayList<News> newsList = new ArrayList<News>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from news");
            ResultSet rs = statement.executeQuery();
            ApplicationContext context =
                    new ClassPathXmlApplicationContext("beans.xml");

            UserRepository userRepository = (UserRepository) context.getBean("userRepository");
            while(rs.next()){
                News news = new News();
                news.setNews_id(rs.getInt("news_id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("description"));
                news.setDate(formatter.parse(rs.getString("date")));
                news.setAuthor(rs.getInt("user_id"));
                newsList.add(news);
            }
            connection.close();
        }
        catch (Exception e){System.out.println(e);
            throw new IllegalAccessError();}

        return newsList;
    }

    public News Get(int id) {
        if (id < 1) throw new IllegalArgumentException();
        News news = new News();
        String query = String.format("SELECT * FROM news WHERE news_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            ApplicationContext context =
                    new ClassPathXmlApplicationContext("beans.xml");

            UserRepository userRepository = (UserRepository) context.getBean("userRepository");

            rs.next();
            news.setNews_id(rs.getInt("news_id"));
            news.setTitle(rs.getString("title"));
            news.setContent(rs.getString("description"));
            news.setDate(formatter.parse(rs.getString("date")));
            news.setAuthor(rs.getInt("user_id"));
            connection.close();
        } catch(Exception e){System.out.println(e);}
        return news;
    }

    public void Delete(int id) {
        if (id < 1) throw new IllegalArgumentException();
        String query = String.format("DELETE FROM news WHERE news_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    public void Update(int id, News item) throws SQLException {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();
        String query = String.format("UPDATE news SET title='%2$s', description='%3$s', date='%4$s', user_id=%5$d WHERE news_id=%1$d",
                id, item.getTitle(), item.getContent(), formatter.format(item.getDate()), item.getAuthor());
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    public void Add(News item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("insert into news (title, description, date, user_id) values('%2$s', '%3$s', '%4$s', %5$d)",
                item.getNews_id(), item.getTitle(), item.getContent(), formatter.format(item.getDate()), item.getAuthor());
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    private boolean IsEmpty(News item)
    {
        if (item.getAuthor() == 0) return true;
        if (item.getContent() == null) return true;
        if (item.getDate() == null) return true;
        if (item.getTitle() == null) return true;
        return false;
    }
}
