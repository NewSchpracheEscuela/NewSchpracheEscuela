package Documentation.Factories.Implementations;

import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.IFactory;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by angre on 29.04.2017.
 */
public class Course_Application_Blank<BlankInfo> implements IFactory<BlankInfo>{
    private Function<BlankInfo, ArrayList<String>> mapper;
    private ArrayList<String> headers;

    public Course_Application_Blank()
    {
        headers = new ArrayList<String>();

        headers.add("ФИО");
        headers.add("Телефон");
        headers.add("Название курса");
        headers.add("Язык");
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }

     public void setHeaders(ArrayList<String> item) {
        headers = item;
    }

    public void setMapper(Function<BlankInfo, ArrayList<String>> item) {
        mapper = item;
    }

    public Iterable<Iterable<String>> map(ArrayList<BlankInfo> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
