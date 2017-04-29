package Documentation.Generators;

import Documentation.Model.IModelViewer;

/**
 * Created by angre on 29.04.2017.
 */
public interface IGenerator<T> {
    boolean getIsProtected();
    void setIsProtected(boolean value);

    IModelViewer<T> getModelViewer();
    void setModelViewer(IModelViewer<T> item);

    String getDocumentName();
    String getDocumentType();
    String getContentType();
}
