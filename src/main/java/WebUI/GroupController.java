package WebUI;

import Database_layer.Repositories.ControlPointRepository;
import Database_layer.Repositories.GroupRepository;
import Entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    private GroupRepository repository;

    public GroupController(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        repository = (GroupRepository) context.getBean("groupRepository");
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Group> getGroups(){
        try {
            return repository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    Group getGroup(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    void deleteGroup(@PathVariable int id){
        try {
            repository.Delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void addGroup(@RequestBody Group group){
        try {
            repository.Add(group);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    void updateGroup(@PathVariable int id,@RequestBody Group group){
        try {
            repository.Update(id,group);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}