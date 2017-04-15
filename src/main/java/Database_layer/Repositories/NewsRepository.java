package Database_layer.Repositories;

import Database_layer.Entities.News;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class NewsRepository implements IRepository<News> {
    private java.sql.Connection connection;
    private Statement statement;
    private UserRepository userRepository = new UserRepository();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public NewsRepository() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","1234");
        }
        catch (Exception e){System.out.println(e);}
    }

    @Override
    protected void finalize() throws SQLException
    {
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Iterable<News> GetAll() throws SQLException {
        ArrayList<News> newsList = new ArrayList<News>();
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from news");
            while(rs.next()){
                News news = new News();
                news.setNews_id(rs.getInt("news_id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("description"));
                news.setDate(formatter.parse(rs.getString("date")));
                news.setAuthor(userRepository.Get(rs.getInt("user_id")));
                newsList.add(news);
            }
            connection.close();
        }
        catch (Exception e){System.out.println(e);}

        return newsList;
    }

    public News Get(int id) {
        News news = new News();
        String query = String.format("SELECT * FROM news WHERE news_id=%1$d", id);
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            rs.next();
            news.setNews_id(rs.getInt("news_id"));
            news.setTitle(rs.getString("title"));
            news.setContent(rs.getString("description"));
            news.setDate(formatter.parse(rs.getString("date")));
            news.setAuthor(userRepository.Get(rs.getInt("user_id")));
        } catch(Exception e){System.out.println(e);}
        return news;
    }

    public void Delete(int id) {
        String query = String.format("DELETE FROM news WHERE news_id=%1$d", id);
        try{
            statement=connection.createStatement();

            statement.executeQuery(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, News item) throws SQLException {
        String query = String.format("UPDATE news SET title=%2$s, description=%3$s, date=%4$s, user_id=%5$d WHERE user_id=%1$d",
                id, item.getTitle(), item.getContent(), formatter.format(item.getDate()), item.getAuthor().getUser_id());
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Add(News item) {
        String query = String.format("insert into news (%1$d, %2$s, %3$s, %4$s, %5$s)",
                item.getNews_id(), item.getTitle(), item.getContent(), item.getDate(), formatter.format(item.getDate()));
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
        } catch(Exception e){System.out.println(e);}
    }
}