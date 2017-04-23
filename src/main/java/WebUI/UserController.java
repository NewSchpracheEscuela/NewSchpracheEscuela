package WebUI;
import java.sql.*;
import java.util.ArrayList;

import Entities.User;
import Database_layer.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alexb on 14-Mar-17.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    public UserController() {
        repository = new UserRepository();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<User> getAll()
    {
        try{
            return (ArrayList<User>)repository.GetAll();
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    User getUser(@PathVariable int id){

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

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public @ResponseBody
    boolean createUser(@PathVariable int id)
    {
        try {
            repository.Delete(id);
            return true;
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return false;
    }
}
