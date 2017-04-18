package WebUI;

import Database_layer.Repositories.TeacherRepository;
import Entities.ControlPoint;
import Entities.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/teachers/")
public class TeacherController {

    private static TeacherRepository repository =new TeacherRepository();

    @RequestMapping(value = "{id}",method = GET)
    public ResponseEntity<Teacher> getById(@PathVariable int id){
        try {
            Teacher teacher = repository.Get(id);
            return ResponseEntity.ok(repository.Get(id));
        } catch (SQLException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
