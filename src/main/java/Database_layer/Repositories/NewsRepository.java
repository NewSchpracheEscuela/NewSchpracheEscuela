package Database_layer.Repositories;

import Database_layer.Entities.News;
import Database_layer.IRepository;

import java.sql.SQLException;

/**
 * Created by angre on 10.04.2017.
 */
public class NewsRepository implements IRepository<News> {
    public Iterable<News> GetAll() throws SQLException {
        return null;
    }

    public News Get(int id) {
        return null;
    }

    public void Delete(int id) {

    }

    public void Update(int id, News item) throws SQLException {

    }

    public void Add(News item) {

    }
}
