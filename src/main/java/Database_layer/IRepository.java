package Database_layer;

import java.sql.SQLException;

/**
 * Created by alexb on 14-Mar-17.
 */
public interface IRepository<T>  {
    Iterable< T > GetAll() throws SQLException;
    T Get(int id);
    void Delete(int id);
    void Update(T item);
}
