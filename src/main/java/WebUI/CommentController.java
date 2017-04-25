package WebUI;

import Database_layer.Repositories.CommentRepository;
import Entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository repository;

    public CommentController() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        repository = (CommentRepository) context.getBean("commentRepository");
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Comment> getAll()
    {
        try{
            return (ArrayList<Comment>)repository.GetAll();
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    Comment getControlPoint(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody
    boolean deleteUser(@PathVariable int id)
    {
        try {
            repository.Delete(id);
            return true;
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    boolean addComment(@RequestBody Comment item) {
        try {
            repository.Add(item);
            return true;
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    boolean addComment(@PathVariable int id, @RequestBody Comment item) {
        try {
            repository.Update(id, item);
            return true;
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return false;
    }
}