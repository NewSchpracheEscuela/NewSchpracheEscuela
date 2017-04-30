package WebUI;

import Database_layer.Repositories.CommentRepository;
import Entities.Comment;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/comments")
public class CommentController implements ApplicationContextAware {

    private CommentRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<ArrayList<Comment>> getAll()
    {
        try{
            return new ResponseEntity<ArrayList<Comment>>((ArrayList<Comment>)repository.GetAll(), HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<ArrayList<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<Comment> getControlPoint(@PathVariable int id){

        try {
            return new ResponseEntity<Comment>(repository.Get(id), HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody
    ResponseEntity deleteUser(@PathVariable int id)
    {
        try {
            repository.Delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addComment(@RequestBody Comment item) {
        try {
            repository.Add(item);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    ResponseEntity addComment(@PathVariable int id, @RequestBody Comment item) {
        try {
            repository.Update(id, item);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.repository = (CommentRepository) applicationContext.getBean("commentRepository");
    }
}