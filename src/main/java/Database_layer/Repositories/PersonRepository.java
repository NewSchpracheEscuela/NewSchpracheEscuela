package Database_layer.Repositories;

import Entities.Person;
import Database_layer.IRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by alexb on 15-Apr-17.
 */
public class PersonRepository implements IRepository<Person> {
    private java.sql.Connection connection;
    private Statement statement;

    public PersonRepository(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_nse","root","admin");
            statement=connection.createStatement();
        }
        catch (Exception e){System.out.println(e);}
    }
    @Override
    protected void finalize() throws SQLException {
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Iterable<Person> GetAll() throws SQLException {
        ArrayList<Person> people = new ArrayList<Person>();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM `person`");
            while(rs.next()){
                Person person = new Person();
                person.setId(rs.getInt("person_id"));
                person.setUser_id(rs.getInt("user_id"));
                people.add(person);
            }
        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return people;
    }

    public Person Get(int id) throws SQLException {
        Person person = new Person();
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM `person` WHERE person_id="+id);
            rs.next();
            person.setId(rs.getInt("person_id"));
            person.setUser_id(rs.getInt("user_id"));

        }
        catch (SQLException e){
            System.out.println(e);
            throw e;
        }
        return person;
    }

    public void Add(Person entity) throws SQLException {
        try {
            int resultSet = statement.executeUpdate(
                    String.format("INSERT INTO `person` (user_id) VALUES ('%d')",
                            entity.getUser_id()));

            System.out.println("Rows affected during Add: " + resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void Delete(int id) throws SQLException {
        try{
            int resultSet = statement.executeUpdate("DELETE FROM `person` WHERE person_id="+id);
            System.out.println("Rows affected during Delete: " + resultSet);
        }
        catch (SQLException e){
            System.out.println(e); throw e;
        }
    }

    public void Update(int id, Person item) throws SQLException {
        Person person = new Person();
        try{
            int resultSet = statement.executeUpdate(String.format("UPDATE `person` SET user_id='%d' WHERE person_id='%s'",
                    item.getUser_id(),
                    id));
            System.out.println("Rows affected during Update: " + resultSet);
        }
        catch (SQLException e){System.out.println(e); throw e;}
    }
}
