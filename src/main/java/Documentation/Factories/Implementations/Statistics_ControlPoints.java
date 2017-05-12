package Documentation.Factories.Implementations;

import Documentation.Factories.Entities.ControlPointStudent;
import Documentation.Factories.IFactory;
import org.springframework.cglib.core.internal.Function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by angre on 29.04.2017.
 */
public class Statistics_ControlPoints implements IFactory<ControlPointStudent> {
    private Function<ControlPointStudent, List<String>> mapper;
    private List<String> headers;

    Statistics_ControlPoints(){
        headers = new ArrayList<String>();

        headers.add("ФИО");
        headers.add("Оценка");
        headers.add("Дата");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getFirstName() + ' ' + blank.getLastName() + ' ' + blank.getPatronym());
            fields.add(String.valueOf(blank.getMark()));
            fields.add(String.valueOf(LocalDateTime.parse((CharSequence) blank.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
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
    public void setMapper(Function<ControlPointStudent, List<String>> item) {
        mapper = item;
    }

    @Override
    public List<List<String>> map(List<ControlPointStudent> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
