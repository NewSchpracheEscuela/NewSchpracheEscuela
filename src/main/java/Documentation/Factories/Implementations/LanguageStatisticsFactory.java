package Documentation.Factories.Implementations;

import Documentation.Factories.Entities.LanguageStatistics;
import Documentation.Factories.Entities.Teacher_Blank;
import Documentation.Factories.IFactory;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageStatisticsFactory implements IFactory<LanguageStatistics> {
    private Function<LanguageStatistics, List<String>> mapper;
    private List<String> headers;

    public LanguageStatisticsFactory(){
        headers = new ArrayList<String>();

        headers.add("Язык");
        headers.add("Количество групп");
        headers.add("A1");
        headers.add("A2");
        headers.add("B1");
        headers.add("B2");
        headers.add("C1");
        headers.add("C2");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getCourseName());
            fields.add(blank.getGroupAmount());
            fields.add(blank.getA1Percent());
            fields.add(blank.getA2Percent());
            fields.add(blank.getB1Percent());
            fields.add(blank.getB2Percent());
            fields.add(blank.getC1Percent());
            fields.add(blank.getC2Percent());
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
    public void setMapper(Function<LanguageStatistics, List<String>> item) {
        mapper = item;
    }

    @Override
    public List<List<String>> map(List<LanguageStatistics> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
