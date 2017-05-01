package WebUI;

import Database_layer.Repositories.CourseRepository;
import Database_layer.Repositories.GroupRepository;
import Database_layer.Repositories.PersonRepository;
import Database_layer.Repositories.UserRepository;
import Documentation.Builder;
import Documentation.Enumerations.DocumentType;
import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.IFactory;
import Documentation.Factories.Implementations.Course_Application_Blank;
import Entities.Course;
import Entities.User;
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
import java.util.List;

/**
 * Created by angre on 29.04.2017.
 */
@RestController
@RequestMapping("/documents")
public class DocumentationController {
    ApplicationContext context;

    public DocumentationController(){
        context = new ClassPathXmlApplicationContext("beans.xml");
        //repository = (GroupRepository) context.getBean("groupRepository");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/blank/{type}")
    public @ResponseBody
    void getBlank(@PathVariable String type, @RequestBody BlankBody blankBody, @RequestParam boolean isProtected, HttpServletResponse response){
        PersonRepository personRepository = (PersonRepository) context.getBean("personRepository");
        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");

        User user = userRepository.Get(blankBody.getUser_id());
        Course course = courseRepository.Get(blankBody.getCourse_id());

        BlankInfo info = new BlankInfo();

        info.setCourseLanguage(course.getLanguage());
        info.setCourseTitle(course.getTitle());
        info.setFirstName(user.getFirstName());
        info.setLastName(user.getLastName());
        info.setPatronym(user.getPatronym());
        info.setTelephone(user.getContactInfo());

        List<BlankInfo> blanks= new ArrayList<>();
        blanks.add(info);

        Course_Application_Blank factory = new Course_Application_Blank();

        createDocument(DocumentType.valueOf(type), factory, isProtected, response, blanks);

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

    private <T> void createDocument(DocumentType type, IFactory<T> factory, boolean isProtected, HttpServletResponse response, List<T> entities)
    {
        Builder documentBuilder = new Builder<T>().setModelViewer(factory).setDocumentType(type).setProtectedFromCopy(isProtected);

        try {
            documentBuilder.writeToResponse(entities, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
