package DAL;

import java.sql.SQLException;

/**
 * Created by alexb on 14-Mar-17.
 */
public interface IRepository<T>  {
    Iterable< T > GetAll() throws SQLException;
    T Get(int id);
    //E GetByPredicate(Expression<Func<T, bool>> f);
    //IEnumerable<E> GetAllByPredicate(Expression<Func<T, bool>> f);
    void Delete(int id);
    void Update(T item);
}
