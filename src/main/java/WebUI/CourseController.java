package WebUI;

import Database_layer.Repositories.ControlPointRepository;
import Database_layer.Repositories.CourseRepository;
import Entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        repository = (CourseRepository) context.getBean("courseRepository");
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

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    boolean addCourse(@RequestBody Course item) {
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
    boolean updateCourse(@PathVariable int id, @RequestBody Course item) {
        try {
            repository.Update(id, item);
            return true;
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody
    boolean deleteCourse(@PathVariable int id) {
        try {
            repository.Delete(id);
            return true;
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return false;
    }

}