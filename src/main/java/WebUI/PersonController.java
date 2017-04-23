package WebUI;

import Database_layer.Repositories.PersonRepository;
import Entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/people")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Person> getPeople(){
        try {
            return repository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    Person getPerson(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    void deletePerson(@PathVariable int id){
        try {
            repository.Delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void addPerson(@RequestBody Person person){
        try {
            repository.Add(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    void updatePerson(@PathVariable int id,@RequestBody Person person){
        try {
            repository.Update(id,person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
