package WebUI;

import Database_layer.Repositories.ControlPointRepository;
import Entities.ControlPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/controlpoints")
public class ControlPointController {

    private ControlPointRepository repository;

    public ControlPointController(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        repository = (ControlPointRepository) context.getBean("controlPointRepository");
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<ControlPoint> getControlPoints(){
        try {
            return repository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ControlPoint getControlPoint(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void addControlPoint(@RequestBody String date){
        try {
            ControlPoint controlPoint = new ControlPoint();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            controlPoint.setDate(dateFormat.parse(date));
            repository.Add(controlPoint);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public @ResponseBody
    void updateControlPoint(@PathVariable int id,@RequestBody String date){
        ControlPoint controlPoint = new ControlPoint();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            controlPoint.setDate(dateFormat.parse(date));
            repository.Update(id,controlPoint);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    void deleteControlPoint(@PathVariable int id){
        try {
            repository.Delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
