package WebUI;

import Database_layer.Repositories.GroupRepository;
import Entities.Group;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/groups")
public class GroupController implements ApplicationContextAware{

    private GroupRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Iterable<Group>> getGroups(){
        try {
            return new ResponseEntity<Iterable<Group>>(repository.GetAll(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<Group> getGroup(@PathVariable int id){
        try {
            return new ResponseEntity<Group>(repository.Get(id),HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<Group>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Group>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    ResponseEntity deleteGroup(@PathVariable int id){
        try {
            repository.Delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addGroup(@RequestBody Group group){
        try {
            repository.Add(group);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    ResponseEntity updateGroup(@PathVariable int id,@RequestBody Group group){
        try {
            repository.Update(id,group);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        repository = (GroupRepository) applicationContext.getBean("groupRepository");
    }
}
