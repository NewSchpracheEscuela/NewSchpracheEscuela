package Documentation.Generators;

import Documentation.Factories.IFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by angre on 29.04.2017.
 */
public interface IGenerator<T> {
    boolean getIsProtected();
    void setIsProtected(boolean value);

    IFactory<T> getModelViewer();
    void setModelViewer(IFactory<T> item);

    String getDocumentName();
    String getDocumentType();
    String getContentType();

    void writeToResponse(List<T> list, HttpServletResponse response);
}
