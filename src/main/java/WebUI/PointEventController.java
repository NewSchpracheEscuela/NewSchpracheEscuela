package WebUI;
import java.sql.*;
import java.util.ArrayList;

import Database_layer.Repositories.ControlPointEventRepository;
import Entities.ControlPointEvent;
import Entities.User;
import Database_layer.Repositories.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
@RequestMapping("/controlpointevents")
public class PointEventController implements ApplicationContextAware{

    private ControlPointEventRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<ArrayList<ControlPointEvent>> getAll()
    {
        try{
            return new ResponseEntity<ArrayList<ControlPointEvent>>((ArrayList<ControlPointEvent>)repository.GetAll(), HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<ArrayList<ControlPointEvent>>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<ControlPointEvent> getPoint(@PathVariable int id){

        try {
            return new ResponseEntity<ControlPointEvent>(repository.Get(id), HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<ControlPointEvent>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody
    ResponseEntity deletePoint(@PathVariable int id)
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
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addPoint(@RequestBody ControlPointEvent item) {
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
    ResponseEntity updatePoint(@PathVariable int id, @RequestBody ControlPointEvent item) {
        try {
            repository.Update(id, item);
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
        repository = (ControlPointEventRepository) applicationContext.getBean("controlPointEventRepository");
    }
}
