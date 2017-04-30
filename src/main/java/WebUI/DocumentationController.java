package WebUI;

import Database_layer.Repositories.GroupRepository;
import Documentation.Enumerations.DocumentType;
import Documentation.Factories.IFactory;
import WebUI.RequestBodies.BlankBody;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;


import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by angre on 29.04.2017.
 */
@RestController
@RequestMapping("/documents")
public class DocumentationController {
    ApplicationContext context;

    public DocumentationController(){
        context =
                new ClassPathXmlApplicationContext("beans.xml");
        //repository = (GroupRepository) context.getBean("groupRepository");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/blank/{type}")
    public @ResponseBody
    void getBlank(@PathVariable String type, @RequestBody BlankBody blankBody, HttpServletResponse response){

    }

    @RequestMapping(method = RequestMethod.POST, value = "/groups/{type}")
    public @ResponseBody
    void getGroups(@PathVariable String type){
    }

    @RequestMapping(method = RequestMethod.POST, value = "/attendance/{type}")
    public @ResponseBody
    void getAttendance(@PathVariable String type){
    }

    @RequestMapping(method = RequestMethod.POST, value = "/marks/{type}")
    public @ResponseBody
    void getControlPoints(@PathVariable String type){
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teachers/{type}")
    public @ResponseBody
    void getTeachers(@PathVariable String type){
    }

    private <T> void createDocument(DocumentType type, IFactory factory, boolean isProtected, HttpServletResponse responce)
    {

    }
}
