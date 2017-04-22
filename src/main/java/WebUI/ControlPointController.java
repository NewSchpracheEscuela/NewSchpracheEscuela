package WebUI;

import Database_layer.Repositories.ControlPointRepository;
import Entities.ControlPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/controlpoints")
public class ControlPointController {

    private final ControlPointRepository repository;

    public ControlPointController() {
        repository = new ControlPointRepository();
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

}
