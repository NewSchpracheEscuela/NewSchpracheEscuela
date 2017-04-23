package WebUI;

import Database_layer.Repositories.CommentRepository;
import Entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository repository;

    public CommentController() {
        repository = new CommentRepository();
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

}