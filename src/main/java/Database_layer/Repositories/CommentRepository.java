package Database_layer.Repositories;

import Database_layer.Entities.Comment;
import Database_layer.IRepository;

import java.sql.SQLException;

/**
 * Created by angre on 10.04.2017.
 */
public class CommentRepository implements IRepository<Comment> {

    public Iterable<Comment> GetAll() throws SQLException {
        return null;
    }

    public Comment Get(int id) {
        return null;
    }

    public void Delete(int id) {

    }

    public void Update(int id, Comment item) {

    }

    public void Add(Comment item) {

    }
}
