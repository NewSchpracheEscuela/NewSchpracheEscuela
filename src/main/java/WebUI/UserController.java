package WebUI;
import java.sql.*;
import Entities.User;
import Database_layer.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alexb on 14-Mar-17.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView Users(ModelMap model) throws ClassNotFoundException, SQLException {
        UserRepository userRepository = new UserRepository();
        Iterable<User> users = userRepository.GetAll();
        model.addAttribute("users", users);
        return new ModelAndView("users", "users", users);
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ModelAndView AngularIndex(){
        return new ModelAndView("index");
    }

}
