package Database_layer;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by alexb on 14-Mar-17.
 */
public interface IRepository<T>  {
    Iterable< T > GetAll() throws SQLException;
    T Get(int id) throws SQLException;
    void Add(T entity) throws SQLException;
    void Delete(int id) throws SQLException;
    void Update(int id,T item) throws SQLException;
}
