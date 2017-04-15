package Database_layer.Repositories;

import Database_layer.Entities.Person;
import Database_layer.Entities.Teacher;
import Database_layer.IRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by alexb on 15-Apr-17.
 */
public class PersonRepositoryTest {
    private static java.sql.Connection connection;
    private static Statement statement;
    private IRepository<Person> repository;
    @Before
    public void setUp()throws Exception{
        repository = new PersonRepository();
    }
    @Test
    public void getAll() throws Exception {
        ArrayList<Person> people = (ArrayList<Person>) repository.GetAll();
        //Assert.assertEquals(11,people.size());
    }

    @Test
    public void get() throws Exception {
        Person person = repository.Get(5);
        //Assert.assertEquals(9,person.getUser_id());
    }

    @Test
    public void add() throws Exception {
        Person person = new Person();
        person.setUser_id(6);
        repository.Add(person);

        //int count = ((ArrayList<Person>)repository.GetAll()).size();
        //Assert.assertEquals(12,count);
    }

    @Test
    public void delete() throws Exception {
        repository.Delete(12);

        int count = ((ArrayList<Person>)repository.GetAll()).size();
        Assert.assertEquals(11,count);
    }

    @Test
    public void update() throws Exception {
        Person person = new Person();
        person.setUser_id(7);
        repository.Update(10,person);

        //Assert.assertEquals(7,repository.Get(10).getUser_id());
    }

}