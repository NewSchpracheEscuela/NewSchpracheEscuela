package WebUI;

import Database_layer.Repositories.ControlPointRepository;
import Entities.ControlPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    ResponseEntity<Iterable<ControlPoint>> getControlPoints(){
        try {
            return new ResponseEntity<Iterable<ControlPoint>>(repository.GetAll(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<Iterable<ControlPoint>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Iterable<ControlPoint>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<ControlPoint> getControlPoint(@PathVariable int id){

        try {
            return new ResponseEntity<ControlPoint>(repository.Get(id),HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<ControlPoint>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ControlPoint>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addControlPoint(@RequestBody String date){
        try {
            ControlPoint controlPoint = new ControlPoint();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            controlPoint.setDate(dateFormat.parse(date));
            repository.Add(controlPoint);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public @ResponseBody
    ResponseEntity updateControlPoint(@PathVariable int id,@RequestBody String date){
        ControlPoint controlPoint = new ControlPoint();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            controlPoint.setDate(dateFormat.parse(date));
            repository.Update(id,controlPoint);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    ResponseEntity deleteControlPoint(@PathVariable int id){
        try {
            repository.Delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
