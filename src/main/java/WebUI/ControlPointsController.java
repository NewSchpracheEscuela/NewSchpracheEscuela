package WebUI;

import Database_layer.Repositories.ControlPointRepository;
import Entities.ControlPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

/**
  Created by alexb on 17-Apr-17.
 */
@Controller
@RequestMapping("/controlpoints/")
public class ControlPointsController {
    public static ControlPointRepository repository = new ControlPointRepository();

    @RequestMapping(value = "{id}",method = GET)
    public ResponseEntity<ControlPoint> getById(@PathVariable int id){
        try {
            ControlPoint controlPoint = repository.Get(id);
            return ResponseEntity.ok(repository.Get(id));
        } catch (SQLException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
