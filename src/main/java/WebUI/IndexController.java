package WebUI;
import Database_layer.Repositories.UserRepository;
import Entities.Credentials;
import Entities.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by alexb on 14-Mar-17.
 */
@Controller
public class IndexController implements ApplicationContextAware{


    private UserRepository repository;

    @RequestMapping(value = {"/","/index","/contacts","/admin", "/all_news", "/all_comments", "/authorization", "/registration", "/404","/403"},method = RequestMethod.GET)
    public ModelAndView AngularIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> SignInService(@RequestBody Credentials credentials){
        try {
            int id = repository.CheckByLoginandPass(credentials.getUsername(),credentials.getPassword());
            if (id > 1){
                User user = repository.Get(id);
                String authString = user.getLogin() + ":" + user.getPassword_hash();
                byte[] authEncBytes = Base64.encode(authString.getBytes());
                String authStringEnc = new String(authEncBytes);
                String result = String.format("{\"userId\":%d,\"role\":\"%s\",\"userLogin\":\"%s\",\"authHeader\":\"%s\"}",user.getUser_id(),user.getRole(), user.getLogin(), authStringEnc );
                return new ResponseEntity<String>(result, HttpStatus.OK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/checklogin",method = RequestMethod.GET)
    public ResponseEntity CheckLogin(){
        return new ResponseEntity(HttpStatus.OK);
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        repository = (UserRepository) applicationContext.getBean("userRepository");
    }
}
