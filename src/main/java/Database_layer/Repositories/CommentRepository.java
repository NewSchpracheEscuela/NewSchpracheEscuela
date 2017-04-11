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
        return null;
    }

    public Comment Get(int id) {
        return null;
    }

    public void Delete(int id) {
        String query = String.format("DELETE FROM comment WHERE comment_id=%1$d", id);
        try{
            statement=connection.createStatement();

            statement.executeQuery(query);
        } catch(Exception e){System.out.println(e);}
    }

    public void Update(int id, Comment item) {

    }

    public void Add(Comment item) {

    }
}
