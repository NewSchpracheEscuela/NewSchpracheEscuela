package Database_layer.Repositories;

import Entities.Teacher;
import Database_layer.IRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by alexb on 15-Apr-17.
 */
public class TeacherRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private IRepository<Teacher> repository;
    @Before
    public void setUp()throws Exception{
        repository = new TeacherRepository();
    }
    @Test
    public void getAll() throws Exception {
        ArrayList<Teacher> teachers = (ArrayList<Teacher>) repository.GetAll();
    }

    @Test
    public void get() throws Exception {
        Teacher teacher = (Teacher) repository.Get(3);
    }

    @Test
    public void add() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setUser_id(3);
        repository.Add(teacher);
    }

    @Test
    public void delete() throws Exception {
        repository.Delete(4);
    }

    @Test
    public void update() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setUser_id(3);
        repository.Update(3,teacher);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addException() throws SQLException{
        repository.Add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getException() throws SQLException{
        repository.Get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateException_Id() throws SQLException{
        repository.Update(-1, new Teacher());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateException_Item() throws SQLException{
        repository.Update(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteException() throws SQLException{
        repository.Delete(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateInvalidEntity() throws SQLException{
        Teacher teacher = new Teacher();
        teacher.setUser_id(-1);
        repository.Update(1, teacher);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidEntity() throws SQLException{
        Teacher teacher = new Teacher();
        teacher.setUser_id(-1);
        repository.Add(teacher);
    }

}