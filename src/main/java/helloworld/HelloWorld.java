package helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alexb on 14-Mar-17.
 */
@Controller
public class HelloWorld {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String HelloWorld(){
        return "index";
    }
}
