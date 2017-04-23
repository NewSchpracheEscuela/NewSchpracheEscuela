package WebUI;
import java.sql.*;
import java.util.ArrayList;

import Database_layer.Repositories.ControlPointEventRepository;
import Entities.ControlPointEvent;
import Entities.User;
import Database_layer.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alexb on 14-Mar-17.
 */
@RestController
@RequestMapping("/controlpointevents")
public class PointEventController {

    private final ControlPointEventRepository repository;

    public PointEventController() {
        repository = new ControlPointEventRepository();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<ControlPointEvent> getAll()
    {
        try{
            return (ArrayList<ControlPointEvent>)repository.GetAll();
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ControlPointEvent getPoint(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody
    boolean deletePoint(@PathVariable int id)
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
    boolean addPoint(@RequestBody ControlPointEvent item) {
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
    boolean updatePoint(@PathVariable int id, @RequestBody ControlPointEvent item) {
        try {
            repository.Update(id, item);
            return true;
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return false;
    }

}
