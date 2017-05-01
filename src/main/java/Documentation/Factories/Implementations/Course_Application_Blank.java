package Documentation.Factories.Implementations;

import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.IFactory;
import Entities.User;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by angre on 29.04.2017.
 */
public class Course_Application_Blank implements IFactory<BlankInfo>{
    private Function<BlankInfo, List<String>> mapper;
    private List<String> headers;

    public Course_Application_Blank()
    {
        headers = new ArrayList<String>();

        headers.add("ФИО");
        headers.add("Телефон");
        headers.add("Название курса");
        headers.add("Язык");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getFirstName() + ' ' + blank.getLastName() + ' ' + blank.getPatronym());
            fields.add(blank.getTelephone());
            fields.add(blank.getCourseTitle());
            fields.add(blank.getCourseLanguage());
            return fields;
        };
    }

    public List<String> getHeaders() {
        return headers;
    }

     public void setHeaders(List<String> item) {
        headers = item;
    }

    public void setMapper(Function<BlankInfo, List<String>> item) {
        mapper = item;
    }

    public List<List<String>> map(List<BlankInfo> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
