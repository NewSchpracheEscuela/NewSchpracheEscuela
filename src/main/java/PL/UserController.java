package PL;
import java.sql.*;
import DAL.User;
import DAL.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alexb on 14-Mar-17.
 */
@Controller
public class UserController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String HelloWorld() throws ClassNotFoundException, SQLException {
        UserRepository userRepository = new UserRepository();
        Iterable<User> users = userRepository.GetAll();
        return "index";
    }
}
