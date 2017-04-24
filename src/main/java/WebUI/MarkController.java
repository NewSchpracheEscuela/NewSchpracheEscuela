package WebUI;

import Database_layer.Repositories.MarkRepository;
import Entities.Mark;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/marks")
public class MarkController
{
    private MarkRepository repository;

    public MarkController(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        repository = (MarkRepository) context.getBean("markRepository");
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Mark> getMarks(){
        try {
            return repository.GetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    Mark getMark(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public @ResponseBody
    void deleteMark(@PathVariable int id){
        try {
            repository.Delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void addPerson(@RequestBody Mark mark){
        try {
            repository.Add(mark);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    void updatePerson(@PathVariable int id,@RequestBody Mark mark){
        try {
            repository.Update(id,mark);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
