package WebUI;

import Database_layer.Repositories.TeacherRepository;
import Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Teacher> getTeacher(){
        try {
            return repository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    Teacher getTeacher(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    void deleteTeacher(@PathVariable int id){
        try {
            repository.Delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void addGroup(@RequestBody Teacher teacher){
        try {
            repository.Add(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    void updateGroup(@PathVariable int id,@RequestBody Teacher teacher){
        try {
            repository.Update(id,teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
