package Database_layer.Repositories;

import Database_layer.Entities.Comment;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by angre on 10.04.2017.
 */
public class CommentRepository implements IRepository<Comment> {
    private java.sql.Connection connection;
    private Statement statement;
    private UserRepository userRepository = new UserRepository();
    private CourseRepository courseRepository = new CourseRepository();

    public CommentRepository() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","1234");
        }
        catch (Exception e){System.out.println(e);}
    }

    public Iterable<Comment> GetAll() throws SQLException {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from news");
            while(rs.next()){
                Comment comment = new Comment();
                comment.setComment_id(rs.getInt("comment_id"));
                comment.setDate(rs.getDate("date"));
                comment.setEntity(rs.getString("entity"));
                comment.setAuthor(userRepository.Get(rs.getInt("user_id")));
                comment.setCourse(courseRepository.Get(rs.getInt("course_id")));
                comments.add(comment);
            }
            connection.close();
        }
        catch (Exception e){System.out.println(e);}

        return comments;
    }

    public Comment Get(int id) {
        Comment comment = new Comment();
        String query = String.format("SELECT * FROM course WHERE course_id=%1$d", id);
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            comment.setComment_id(rs.getInt("comment_id"));
            comment.setDate(rs.getDate("date"));
            comment.setEntity(rs.getString("entity"));
            comment.setAuthor(userRepository.Get(rs.getInt("user_id")));
            comment.setCourse(courseRepository.Get(rs.getInt("course_id")));
        } catch(Exception e){System.out.println(e);}
        return comment;
    }

    public void Delete(int id) {
        String query = String.format("DELETE FROM comment WHERE comment_id=%1$d", id);
        try{
            statement=connection.createStatement();

            statement.executeQuery(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, Comment item) {
        String query = String.format("UPDATE comment SET entity=%2$s, course_id=%3$d, user_id=%4$d, date=%5$s WHERE comment_id=%1$d",
                id, item.getEntity(), item.getCourse().getCourse_id(), item.getAuthor().getUser_id(), item.getDate().toString());
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Add(Comment item) {
        String query = String.format("insert into news (%1$d, %2$s, %3$d, %4$d, %5$s)",
                item.getComment_id(), item.getEntity(), item.getCourse().getCourse_id(), item.getAuthor().getUser_id(), item.getDate().toString());
        try{
            statement=connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
        } catch(Exception e){System.out.println(e);}
    }
}
