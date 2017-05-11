package WebUI;

import Database_layer.Repositories.*;
import Documentation.Builder;
import Documentation.Enumerations.DocumentType;
import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.Entities.Teacher_Blank;
import Documentation.Factories.IFactory;
import Documentation.Factories.Implementations.Course_Application_Blank;
import Documentation.Factories.Implementations.Teacher_List;
import Entities.*;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;


import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

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

    @RequestMapping(method = RequestMethod.GET, value = "/blank/{type}")
    public @ResponseBody
    void getBlank(@PathVariable String type, @RequestParam int user_id, @RequestParam int course_id, @RequestParam boolean isProtected, HttpServletResponse response){
        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");

        User user = userRepository.Get(user_id);
        Course course = courseRepository.Get(course_id);

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

    @RequestMapping(method = RequestMethod.GET, value = "/groups/{type}")
    public @ResponseBody
    void getGroups(@PathVariable String type){
    }

    @RequestMapping(method = RequestMethod.GET, value = "/attendance/{type}")
    public @ResponseBody
    void getAttendance(@PathVariable String type){
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marks/{type}")
    public @ResponseBody
    void getControlPoints(@PathVariable String type, HttpServletResponse response){
        GroupRepository groupRepository = (GroupRepository)context.getBean("groupRepository");
        ControlPointRepository controlPointRepository = (ControlPointRepository)context.getBean("controlPointRepository");

    }

    @RequestMapping(method = RequestMethod.GET, value = "/teachers/{type}")
    public @ResponseBody
    void getTeachers(@PathVariable String type, @RequestParam boolean isProtected, HttpServletResponse response){
        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        TeacherRepository teacherRepository= (TeacherRepository) context.getBean("teacherRepository");
        LessonRepository lessonRepository = (LessonRepository) context.getBean("lessonRepository");
        GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
        CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");

        try {
            ArrayList<Teacher> teachers = (ArrayList<Teacher>) teacherRepository.GetAll();
            List<Teacher_Blank> blanks = new ArrayList<Teacher_Blank>();

            for (Teacher teacher: teachers) {
                Teacher_Blank blank = new Teacher_Blank();
                User user = userRepository.Get(teacher.getUser_id());
                blank.setFirstName(user.getFirstName());
                blank.setLastName(user.getLastName());
                blank.setPatronym(user.getPatronym());
                blank.setPhone(user.getContactInfo());

                ArrayList<String> languages = new ArrayList<>();
                ArrayList<Lesson> lessons = (ArrayList<Lesson>) lessonRepository.GetAll();
                lessons.removeIf(lesson -> lesson.getTeacher() != teacher.getId());

                for (Lesson lesson: lessons) {
                    Group group = groupRepository.Get(lesson.getGroup());
                    Course course = courseRepository.Get(group.getCourse_id());
                    languages.add(course.getTitle());
                }

                blank.setLanguages(languages);
                blanks.add(blank);
            }

            blanks.sort((o1, o2) -> o1.getLastName().compareTo(o2.getFirstName()));
            Teacher_List factory = new Teacher_List();
            createDocument(DocumentType.valueOf(type), factory, isProtected, response, blanks);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
