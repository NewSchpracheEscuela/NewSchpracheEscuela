package Documentation.Generators;

import Documentation.Factories.IFactory;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by angre on 29.04.2017.
 */
public class PDFGenerator<T> implements IGenerator<T>{
    private boolean isProtected = false;
    private IFactory<T> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<T> getModelViewer()
    {
        return  modelViewer;
    }

    public void setModelViewer(IFactory<T> item) {
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
        return "application/pdf";
    }

    @Override
    public void writeToResponse(Iterable<T> list, HttpServletResponse response) {

    }

}

class PdfNewPageEventHandler extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        document.setMargins(0, 0, 20, 20);

    }
}