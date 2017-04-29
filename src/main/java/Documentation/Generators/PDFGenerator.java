package Documentation.Generators;

import Documentation.Model.IModelViewer;

/**
 * Created by angre on 29.04.2017.
 */
public class PDFGenerator<T> implements IGenerator<T>{
    private boolean isProtected = false;
    private IModelViewer<T> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IModelViewer<T> getModelViewer()
    {
        return  modelViewer;
    }

    public void setModelViewer(IModelViewer<T> item) {
        modelViewer = item;
    }

    public String getDocumentName() {
        String filename = java.util.UUID.randomUUID().toString();

        return String.format("%s%s", filename, getDocumentType());
    }

    public String getDocumentType() {
        return "pdf";
    }

    public String getContentType() {
        return null;
    }
}
