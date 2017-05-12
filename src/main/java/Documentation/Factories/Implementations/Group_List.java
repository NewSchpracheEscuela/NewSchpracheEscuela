package Documentation.Factories.Implementations;

import Documentation.Factories.Entities.Group_Blank;
import Documentation.Factories.IFactory;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by angre on 29.04.2017.
 */
public class Group_List implements IFactory<Group_Blank>{
    private Function<Group_Blank, List<String>> mapper;
    private List<String> headers;

    public Group_List(){
        headers = new ArrayList<String>();

        headers.add("ФИО");
        headers.add("Электронная почта");
        headers.add("Телефон");
        headers.add("Курс");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getFirstName() + ' ' + blank.getLastName() + ' ' + blank.getPatronym());
            fields.add(blank.getEmail());
            fields.add(blank.getPhone());
            fields.add(blank.getCourse_name());
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
    public void setMapper(Function<Group_Blank, List<String>> item) {
        mapper= item;
    }

    @Override
    public List<List<String>> map(List<Group_Blank> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
