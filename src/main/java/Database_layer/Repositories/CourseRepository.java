package Database_layer.Repositories;

import Database_layer.Entities.Course;
import Database_layer.IRepository;

import java.sql.SQLException;

/**
 * Created by angre on 10.04.2017.
 */
public class CourseRepository implements IRepository<Course> {
    public Iterable<Course> GetAll() throws SQLException {
        return null;
    }

    public Course Get(int id) {
        return null;
    }

    public void Delete(int id) {

    }

    public void Update(int id, Course item) {

    }

    public void Add(Course item) {

    }
}
