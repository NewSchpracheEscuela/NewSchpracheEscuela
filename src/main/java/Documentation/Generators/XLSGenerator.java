package Documentation.Generators;

import Documentation.Factories.IFactory;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by angre on 29.04.2017.
 */
public class XLSGenerator<T> implements IGenerator<T>{
    private boolean isProtected = false;
    private IFactory<T> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<T> getModelViewer() {
        return null;
    }

    public void setModelViewer(IFactory<T> item) {

    }

    public String getDocumentName() {
        return null;
    }

    public String getDocumentType() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public void writeToResponse(List<T> list, HttpServletResponse response) {

    }

}
