package Documentation.Factories.Implementations;

import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.Entities.Course_Blank;
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
public class Courses_List implements IFactory<Course_Blank>{
    private Function<Course_Blank, List<String>> mapper;
    private List<String> headers;

    Courses_List(){

        headers = new ArrayList<String>();

        headers.add("Название");
        headers.add("Изучаемый язык");
        headers.add("Описание");
        headers.add("Кол-во часов");
        headers.add("Дата начала");
        headers.add("Цена");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getTitle());
            fields.add(blank.getLanguage());
            fields.add(blank.getDescription());
            fields.add(String.valueOf(blank.getNumberOfHours()));
            fields.add(String.valueOf(LocalDateTime.parse((CharSequence) blank.getStartDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
            fields.add(String.format(java.util.Locale.US,"%.2f",blank.getPrice()));
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
    public void setMapper(Function<Course_Blank, List<String>> item) {
        mapper = item;
    }

    @Override
    public List<List<String>> map(List<Course_Blank> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
