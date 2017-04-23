package WebUI;

import Database_layer.Repositories.CourseRepository;
import Entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController() {
        repository = new CourseRepository();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Course> getAll()
    {
        try{
            return (ArrayList<Course>)repository.GetAll();
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    Course getControlPoint(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return null;
    }

}