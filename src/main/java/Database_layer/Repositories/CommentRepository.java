package Database_layer.Repositories;

import Entities.Comment;
import Database_layer.IRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class CommentRepository implements IRepository<Comment>,ApplicationContextAware {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DataSource dataSource;
    private ApplicationContext context;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Comment> GetAll() throws SQLException {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT `comment_id`,`entity`, comment.course_id,`title`,comment.user_id,`date`, `first_name`,`last_name` FROM database_nse.comment LEFT JOIN database_nse.user ON (comment.user_id = user.user_id) Left join database_nse.course ON (comment.course_id=course.course_id) order by `date` DESC");

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                UserRepository userRepository = (UserRepository) context.getBean("userRepository");
                CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");
                comment.setDate(formatter.parse(rs.getString("date")));
                comment.setComment_id(rs.getInt("comment_id"));
                comment.setEntity(rs.getString("entity"));
                comment.setAuthor(rs.getInt("user_id"));
                comment.setCourse(rs.getInt("course_id"));
                comment.setAuthor_name(rs.getString("last_name") + " " + rs.getString("first_name"));
                comment.setCourse_name(rs.getString("title"));
                comments.add(comment);
            }
            connection.close();
        }
        catch (Exception e){System.out.println(e);
            throw new IllegalAccessError();}

        return comments;
    }

    public Comment Get(int id) {
        if (id < 1) throw new IllegalArgumentException();

        Comment comment = new Comment();
        String query = String.format("SELECT * FROM comment WHERE comment_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            UserRepository userRepository = (UserRepository) context.getBean("userRepository");
            CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");
            rs.next();
            comment.setComment_id(rs.getInt("comment_id"));
            comment.setDate(rs.getDate("date"));
            comment.setEntity(rs.getString("entity"));
            comment.setAuthor(rs.getInt("user_id"));
            comment.setCourse(rs.getInt("course_id"));
            connection.close();
        } catch(Exception e){
            System.out.println(e);
            throw new IllegalAccessError();
        }
        return comment;
    }

    public void Delete(int id) {
        if (id < 1) throw new IllegalArgumentException();

        String query = String.format("DELETE FROM comment WHERE comment_id=%1$d", id);
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    public void Update(int id, Comment item) {
        if (id < 1) throw new IllegalArgumentException();
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("UPDATE comment SET entity='%2$s', course_id=%3$d, user_id=%4$d, date='%5$s' WHERE comment_id=%1$d",
                id, item.getEntity(), item.getCourse(), item.getAuthor(), formatter.format(item.getDate()));
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    public void Add(Comment item) {
        if (item == null) throw new IllegalArgumentException();
        if (IsEmpty(item)) throw new IllegalArgumentException();

        String query = String.format("insert into comment (entity,course_id,user_id,date) values('%1$s', %2$d, %3$d, '%4$s')",
                item.getEntity(), item.getCourse(), item.getAuthor(), formatter.format(item.getDate()));
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
            connection.close();
        } catch(Exception e){System.out.println(e);
            throw new IllegalAccessError();}
    }

    private boolean IsEmpty(Comment item)
    {
        if (item.getAuthor() == 0) return true;
        if (item.getCourse() == 0) return true;
        if (item.getDate() == null) return true;
        if (item.getEntity() == null) return true;
        if (item.getEntity().equals("")) return true;
        return false;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
