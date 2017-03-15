package PL;
import java.sql.*;
import DAL.User;
import DAL.UserRepository;
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
    public ModelAndView Index(ModelMap model) throws ClassNotFoundException, SQLException {
        UserRepository userRepository = new UserRepository();
        Iterable<User> users = userRepository.GetAll();
        for (User user:
             users) {
            System.out.println(user.login);
            //request.setAttribute("user", user.login);

        }
        model.addAttribute("users", users);
        return new ModelAndView("users", "users", users);
    }

}
