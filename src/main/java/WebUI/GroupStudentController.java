package WebUI;

import Database_layer.Repositories.GroupStudentRepository;
import Entities.GroupStudent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/groupstudents")
public class GroupStudentController implements ApplicationContextAware {
    private GroupStudentRepository repository;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Iterable<GroupStudent>> getMarks(){
        try {
            return new ResponseEntity<Iterable<GroupStudent>>(repository.GetAll(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<Iterable<GroupStudent>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Iterable<GroupStudent>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<GroupStudent> getMark(@PathVariable int id){

        try {
            return new ResponseEntity<GroupStudent>(repository.Get(id),HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<GroupStudent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<GroupStudent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    ResponseEntity<GroupStudent> deleteMark(@PathVariable int id){
        try {
            repository.Delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<GroupStudent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addPerson(@RequestBody GroupStudent groupStudent){
        try {
            repository.Add(groupStudent);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<GroupStudent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<GroupStudent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    ResponseEntity<GroupStudent> updatePerson(@PathVariable int id,@RequestBody GroupStudent groupStudent){
        try {
            repository.Update(id,groupStudent);
            return new ResponseEntity<GroupStudent>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<GroupStudent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<GroupStudent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        repository = (GroupStudentRepository) applicationContext.getBean("groupStudentRepository");
    }
}
