package Database_layer.Repositories;

import Entities.Person;
import Database_layer.IRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by alexb on 15-Apr-17.
 */
public class PersonRepository implements IRepository<Person> {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Person> GetAll() throws SQLException {
        ArrayList<Person> people = new ArrayList<Person>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `person`");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Person person = new Person();
                person.setId(rs.getInt("person_id"));
                person.setUser_id(rs.getInt("user_id"));
                people.add(person);
            }
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return people;
    }

    public Person Get(int id) throws SQLException, IllegalArgumentException {
        if (id < 1){
            throw new IllegalArgumentException();
        }
        Person person = new Person();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `person` WHERE person_id="+id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            person.setId(rs.getInt("person_id"));
            person.setUser_id(rs.getInt("user_id"));
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return person;
    }

    public void Add(Person entity) throws SQLException, IllegalArgumentException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if (entity.getUser_id() < 1){
            throw new IllegalArgumentException();
        }
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format("INSERT INTO `person` (user_id) VALUES ('%d')",
                    entity.getUser_id()));
            int resultSet = statement.executeUpdate();

            System.out.println("Rows affected during Add: " + resultSet);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void Delete(int id) throws SQLException,IllegalArgumentException {
        if (id < 1){
            throw new IllegalArgumentException();
        }
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `person` WHERE person_id="+id);
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Delete: " + resultSet);
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, Person item) throws SQLException,IllegalArgumentException {
        if (item == null || id<1){
            throw new IllegalArgumentException();
        }
        if (item.getUser_id() < 1){
            throw new IllegalArgumentException();
        }
        Person person = new Person();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format("UPDATE `person` SET user_id='%d' WHERE person_id='%s'",
                    item.getUser_id(),
                    id));
            int resultSet = statement.executeUpdate();
            System.out.println("Rows affected during Update: " + resultSet);
            connection.close();
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }


}
