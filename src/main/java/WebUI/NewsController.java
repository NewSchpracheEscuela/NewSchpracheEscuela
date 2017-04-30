package WebUI;

import Database_layer.Repositories.NewsRepository;
import Database_layer.Repositories.UserRepository;
import Entities.News;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/news")
public class NewsController implements ApplicationContextAware {

    private NewsRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<ArrayList<News>> getAll()
    {
        try{
            return new ResponseEntity<ArrayList<News>>((ArrayList<News>)repository.GetAll(), HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<ArrayList<News>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<News> getControlPoint(@PathVariable int id){

        try {
            return new ResponseEntity<News>(repository.Get(id), HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<News>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addNews(@RequestBody News item) {
        try {
            repository.Add(item);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody
    ResponseEntity deleteUser(@PathVariable int id)
    {
        try {
            repository.Delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody
    ResponseEntity updateNews(@PathVariable int id, @RequestBody News item) {
        try {
            repository.Update(id, item);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        repository = (NewsRepository) applicationContext.getBean("newsRepository");
    }
}