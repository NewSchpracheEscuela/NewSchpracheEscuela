package Database_layer.Repositories;

import Entities.Group;
import Database_layer.IRepository;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private IRepository<Group> repository;
    @Before
    public void setUp()throws Exception{
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        repository = (GroupRepository) context.getBean("groupRepository");
    }

    @Test
    public void getAll() throws Exception {
        ArrayList<Group> groups = (ArrayList<Group>) repository.GetAll();
    }

    @Test
    public void get() throws Exception {
        Group group = repository.Get(5);
    }

    @Test
    public void add() throws Exception {
        Group group = new Group();
        group.setLevel("a1");
        group.setAmount(10);
        group.setCourse_id(2);
        group.setPerson_id(2);
        repository.Add(group);
    }

    @Test
    public void update() throws Exception {
        Group group = new Group();
        group.setLevel("a2");
        group.setAmount(10);
        group.setPerson_id(3);
        group.setCourse_id(3);
        repository.Update(17,group);
    }

    @Test
    public void delete() throws Exception {
        repository.Delete(18);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionGet() throws SQLException {
        repository.Get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionAdd() throws SQLException {
        repository.Add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionUpdateIllegalId() throws SQLException {
        repository.Update(-1,new Group());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionUpdateIllegalItem() throws SQLException {
        repository.Update(3,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionDelete() throws SQLException {
        repository.Delete(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyEntityAdd() throws SQLException {
        Group group = new Group();
        group.setCourse_id(-1);
        repository.Add(group);
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyEntityUpdate() throws SQLException {
        Group group = new Group();
        group.setCourse_id(-1);
        repository.Update(1,group);
    }
}