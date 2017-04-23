package WebUI;

import Database_layer.Repositories.NewsRepository;
import Entities.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsRepository repository;

    public NewsController() {
        repository = new NewsRepository();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<News> getAll()
    {
        try{
            return (ArrayList<News>)repository.GetAll();
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    News getControlPoint(@PathVariable int id){

        try {
            return repository.Get(id);
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        }
        return null;
    }

}