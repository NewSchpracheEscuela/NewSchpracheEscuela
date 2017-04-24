package WebUI;

import Database_layer.Repositories.GroupStudentRepository;
import Entities.GroupStudent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/groupstudents")
public class GroupStudentController {
    private GroupStudentRepository repository;

    public GroupStudentController(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        repository = (GroupStudentRepository) context.getBean("groupStudentRepository");
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<GroupStudent> getMarks(){
        try {
            return repository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    GroupStudent getMark(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    void deleteMark(@PathVariable int id){
        try {
            repository.Delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void addPerson(@RequestBody GroupStudent groupStudent){
        try {
            repository.Add(groupStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    void updatePerson(@PathVariable int id,@RequestBody GroupStudent groupStudent){
        try {
            repository.Update(id,groupStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
