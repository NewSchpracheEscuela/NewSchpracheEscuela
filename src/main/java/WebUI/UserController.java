package WebUI;
import java.sql.*;
import java.util.ArrayList;

import Entities.User;
import Database_layer.Repositories.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class UserController implements ApplicationContextAware{

    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<ArrayList<User>> getAll()
    {
        try{
            return new ResponseEntity<ArrayList<User>>((ArrayList<User>)repository.GetAll(), HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<ArrayList<User>>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<User> getUser(@PathVariable int id){
        try {
            return new ResponseEntity<User>(repository.Get(id),HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody
    ResponseEntity deleteUser(@PathVariable int id)
    {
        try {
            boolean isAdmin = false;
            User user = repository.Get(id);
            if (user.getRole() == "ROLE_ADMIN"){
                  isAdmin = true;
                  Iterable<User> users = repository.GetAll();
                  int count=0;
                for (User tempUser :
                        users) {
                    if (tempUser.getRole() =="ROLE_ADMIN"){
                        count++;
                    }
                }
                if(count>1)
                    repository.Delete(id);
            }
            else
                repository.Delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    ResponseEntity addUser(@PathVariable int id, @RequestBody User item) {
        try {
            boolean isAdmin = false;
            User user = repository.Get(id);
            if (user.getRole() == "ROLE_ADMIN" && item.getRole() != user.getRole()){
                isAdmin = true;
                Iterable<User> users = repository.GetAll();
                int count=0;
                for (User tempUser :
                        users) {
                    if (tempUser.getRole() =="ROLE_ADMIN"){
                        count++;
                    }
                }
                if(count>1)
                    repository.Update(id,item);
            }
            else
                repository.Update(id,item);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        repository = (UserRepository) applicationContext.getBean("userRepository");
    }
}
