package WebUI;
import java.sql.*;
import java.util.ArrayList;

import Entities.User;
import Database_layer.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        repository = (UserRepository) context.getBean("userRepository");

    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<ArrayList<User>> getAll()
    {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/json; charset=windows-1251");
            return new ResponseEntity<ArrayList<User>>((ArrayList<User>)repository.GetAll(),headers, HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<ArrayList<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<User> getUser(@PathVariable int id){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/json; charset=windows-1251");
            return new ResponseEntity<User>(repository.Get(id),headers,HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    ResponseEntity addUser(@RequestBody User item) {
        try {
            repository.Add(item);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    ResponseEntity addUser(@PathVariable int id, @RequestBody User item) {
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

}
