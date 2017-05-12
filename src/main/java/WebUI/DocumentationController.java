package WebUI;

import Database_layer.Enumerations.Level;
import Database_layer.Enumerations.Roles;
import Database_layer.Repositories.*;
import Documentation.Builder;
import Documentation.BuilderForBlanks;
import Documentation.Enumerations.DocumentType;
import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.Entities.Group_Blank;
import Documentation.Factories.Entities.LanguageStatistics;
import Documentation.Factories.Entities.Teacher_Blank;
import Documentation.Factories.IFactory;
import Documentation.Factories.Implementations.Course_Application_Blank;
import Documentation.Factories.Implementations.Group_List;
import Documentation.Factories.Implementations.LanguageStatisticsFactory;
import Documentation.Factories.Implementations.Teacher_List;
import Entities.*;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
import java.util.stream.Collectors;

/**
 * Created by angre on 29.04.2017.
 */
@RestController
@RequestMapping("/documents")
public class DocumentationController implements ApplicationContextAware{
    ApplicationContext context;

    @RequestMapping(method = RequestMethod.GET, value = "/blank/{type}")
    public @ResponseBody
    void getBlank(@PathVariable String type, @RequestParam int user_id, @RequestParam boolean isProtected, HttpServletResponse response){
        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");
        PersonRepository personRepository = (PersonRepository) context.getBean("personRepository");
        GroupStudentRepository groupStudentRepository = (GroupStudentRepository) context.getBean("groupStudentRepository");
        GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");

        ArrayList<GroupStudent> groupStudents = null;
        ArrayList<Person> people = null;
        try {
            people = (ArrayList<Person>) personRepository.GetAll();
            groupStudents = (ArrayList<GroupStudent>) groupStudentRepository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = userRepository.Get(user_id);
        int course_id = 1;
        Person person = people.stream().filter(x -> x.getUser_id() == user.getUser_id()).findFirst().orElse(null);
        if (person != null){
            GroupStudent groupStudent = groupStudents.stream().filter(student->student.getGroupId() == person.getUser_id()).findAny().orElse(null);
            if (groupStudent!=null){
                try {
                    course_id = courseRepository.Get(groupRepository.Get(groupStudent.getGroupId()).getCourse_id()).getCourse_id();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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
        createBlanks(DocumentType.valueOf(type + 'b'), factory, isProtected, response, blanks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/diploma/{type}")
    public @ResponseBody
    void getDiploma(@PathVariable String type, @RequestParam int user_id, @RequestParam boolean isProtected, HttpServletResponse response){
        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");

        User user = userRepository.Get(user_id);

        PersonRepository personRepository = (PersonRepository) context.getBean("personRepository");
        GroupStudentRepository groupStudentRepository = (GroupStudentRepository) context.getBean("groupStudentRepository");
        GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");

        ArrayList<GroupStudent> groupStudents = null;
        ArrayList<Person> people = null;
        try {
            people = (ArrayList<Person>) personRepository.GetAll();
            groupStudents = (ArrayList<GroupStudent>) groupStudentRepository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int course_id = 1;
        Person person = people.stream().filter(x -> x.getUser_id() == user.getUser_id()).findFirst().orElse(null);
        if (person != null){
            GroupStudent groupStudent = groupStudents.stream().filter(student->student.getGroupId() == person.getUser_id()).findAny().orElse(null);
            if (groupStudent!=null){
                try {
                    course_id = courseRepository.Get(groupRepository.Get(groupStudent.getGroupId()).getCourse_id()).getCourse_id();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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
        createBlanks(DocumentType.valueOf(type + 'd'), factory, isProtected, response, blanks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/groups/{type}")
    public @ResponseBody
    void getGroups(@PathVariable String type, @RequestParam boolean isProtected, HttpServletResponse response){
        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        GroupStudentRepository groupStudentRepository = (GroupStudentRepository) context.getBean("groupStudentRepository");
        GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
        PersonRepository personRepository = (PersonRepository) context.getBean("personRepository");
        CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");
        try {
            ArrayList<User> users = (ArrayList<User>) userRepository.GetAll();
            List<Group_Blank> blanks = new ArrayList<Group_Blank>();
            ArrayList<GroupStudent> groupStudents = (ArrayList<GroupStudent>) groupStudentRepository.GetAll();
            ArrayList<Person> people = (ArrayList<Person>) personRepository.GetAll();

            for (User user :
                    users) {
                String title = "Немецкий";
                Person person = people.stream().filter(x -> x.getUser_id() == user.getUser_id()).findFirst().orElse(null);
                if (person != null){
                    GroupStudent groupStudent = groupStudents.stream().filter(student->student.getGroupId() == person.getUser_id()).findAny().orElse(null);
                    if (groupStudent!=null){
                        title = courseRepository.Get(groupRepository.Get(groupStudent.getGroupId()).getCourse_id()).getTitle();
                    }
                }


                Group_Blank blank = new Group_Blank();
                if(Roles.valueOf(user.getRole()) == Roles.ROLE_STUDENT){
                    blank.setFirstName(user.getFirstName());
                    blank.setLastName(user.getLastName());
                    blank.setPatronym(user.getPatronym());
                    blank.setPhone(user.getContactInfo());
                    blank.setEmail(user.getEmail());
                    blank.setCourse_name(title);
                    blanks.add(blank);
                }
            }
            blanks.sort((o1, o2) -> o1.getLastName().compareTo(o2.getFirstName()));
            Group_List factory = new Group_List();
            createDocument(DocumentType.valueOf(type), factory, isProtected, response, blanks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/languages/{type}")
    public @ResponseBody
    void getLanguagesStatistics(@PathVariable String type, @RequestParam boolean isProtected, HttpServletResponse response){
        GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
        CourseRepository courseRepository = (CourseRepository) context.getBean("courseRepository");

        try {
            ArrayList<Group> groups = (ArrayList<Group>) groupRepository.GetAll();
            ArrayList<Course> courses = (ArrayList<Course>) courseRepository.GetAll();
            List<LanguageStatistics> blanks = new ArrayList<LanguageStatistics>();

                for (Course course :
                        courses) {
                    ArrayList<Group> languageGroups = (ArrayList<Group>) groups.stream().filter(group -> group.getCourse_id() == course.getCourse_id())
                                                                                        .collect(Collectors.toList());;
                    int A1Groups = Math.toIntExact(languageGroups.stream().filter(x -> Level.valueOf(x.getLevel()) == Level.a1).count());
                    int A2Groups = Math.toIntExact(languageGroups.stream().filter(x -> Level.valueOf(x.getLevel()) == Level.a2).count());
                    int B1Groups = Math.toIntExact(languageGroups.stream().filter(x -> Level.valueOf(x.getLevel()) == Level.b1).count());
                    int B2Groups = Math.toIntExact(languageGroups.stream().filter(x -> Level.valueOf(x.getLevel()) == Level.b2).count());
                    int C1Groups = Math.toIntExact(languageGroups.stream().filter(x -> Level.valueOf(x.getLevel()) == Level.c1).count());
                    int C2Groups = Math.toIntExact(languageGroups.stream().filter(x -> Level.valueOf(x.getLevel()) == Level.c2).count());
                    LanguageStatistics statistics = new LanguageStatistics();

                    statistics.setCourseName(course.getTitle());
                    if (languageGroups.size()!=0){
                        statistics.setA1Percent((double)A1Groups/languageGroups.size()*100 + "%");
                        statistics.setA2Percent((double)A2Groups/languageGroups.size()*100 + "%");
                        statistics.setB1Percent((double)B1Groups/languageGroups.size()*100 + "%");
                        statistics.setB2Percent((double)B2Groups/languageGroups.size()*100 + "%");
                        statistics.setC1Percent((double)C1Groups/languageGroups.size()*100 + "%");
                        statistics.setC2Percent((double)C2Groups/languageGroups.size()*100 + "%");
                    }else {
                        statistics.setA1Percent("-");
                        statistics.setA2Percent("-");
                        statistics.setB1Percent("-");
                        statistics.setB2Percent("-");
                        statistics.setC1Percent("-");
                        statistics.setC2Percent("-");
                    }
                    statistics.setGroupAmount(String.valueOf(languageGroups.size()));
                    blanks.add(statistics);
                }
            blanks.sort(Comparator.comparing(LanguageStatistics::getCourseName));
            LanguageStatisticsFactory factory = new LanguageStatisticsFactory();
            createDocument(DocumentType.valueOf(type), factory, isProtected, response, blanks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/marks/{type}")
//    public @ResponseBody
//    void getControlPoints(@PathVariable String type, HttpServletResponse response){
//        GroupRepository groupRepository = (GroupRepository)context.getBean("groupRepository");
//        ControlPointRepository controlPointRepository = (ControlPointRepository)context.getBean("controlPointRepository");
//
//    }

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
                    if (lesson.getTeacher() == teacher.getId()){
                        Group group = groupRepository.Get(lesson.getGroup());
                        Course course = courseRepository.Get(group.getCourse_id());
                        languages.add(course.getTitle());
                    }
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

    private void createBlanks(DocumentType type, IFactory<BlankInfo> factory, boolean isProtected, HttpServletResponse response, List<BlankInfo> entities)
    {
        BuilderForBlanks documentBuilder = new BuilderForBlanks().setModelViewer(factory).setDocumentType(type).setProtectedFromCopy(isProtected);

        try {
            documentBuilder.writeToResponse(entities, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
