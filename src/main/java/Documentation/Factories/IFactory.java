package Documentation.Factories;

import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angre on 29.04.2017.
 */
public interface IFactory<T> {
    List<String> getHeaders();
    void setHeaders(List<String> item);

    void setMapper(Function<T, List<String>> item);

    List<List<String>> map(List<T> in);
}
