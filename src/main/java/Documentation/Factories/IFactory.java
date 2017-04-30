package Documentation.Factories;

import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;

/**
 * Created by angre on 29.04.2017.
 */
public interface IFactory<T> {
    ArrayList<String> getHeaders();
    void setHeaders(ArrayList<String> item);

    void setMapper(Function<T, ArrayList<String>> item);

    Iterable<Iterable<String>> map(ArrayList<T> in);
}
