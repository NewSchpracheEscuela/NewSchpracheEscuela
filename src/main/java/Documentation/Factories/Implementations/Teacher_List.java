package Documentation.Factories.Implementations;

import Documentation.Factories.Entities.Teacher_Blank;
import Documentation.Factories.IFactory;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by angre on 29.04.2017.
 */
public class Teacher_List implements IFactory<Teacher_Blank>{
    private Function<Teacher_Blank, List<String>> mapper;
    private List<String> headers;

    Teacher_List(){
        headers = new ArrayList<String>();

        headers.add("ФИО");
        headers.add("Телефон");
//        headers.add("Преподаваемые языки");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getFirstName() + ' ' + blank.getLastName() + ' ' + blank.getPatronym());
            fields.add(blank.getPhone());
//            fields.add(blank.getLanguages);
            return fields;
        };
    }
    @Override
    public List<String> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(List<String> item) {
        headers = item;
    }

    @Override
    public void setMapper(Function<Teacher_Blank, List<String>> item) {
        mapper = item;
    }

    @Override
    public List<List<String>> map(List<Teacher_Blank> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
