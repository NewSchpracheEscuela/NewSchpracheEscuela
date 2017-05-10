package Documentation.Generators;

import Documentation.Factories.IFactory;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

public class CSVGenerator<T> implements IGenerator<T> {
    private boolean isProtected = false;
    private IFactory<T> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<T> getModelViewer() {
        return modelViewer;
    }

    public void setModelViewer(IFactory<T> item) {
        modelViewer = item;
    }

    public String getDocumentName() {
        String filename = java.util.UUID.randomUUID().toString();
        return String.format("%s%s", filename, getDocumentType());
    }

    public String getDocumentType() {
        return ".csv";
    }

    public String getContentType() {
        return "text/csv; charset=utf-8";
    }

    public void writeToResponse(List<T> list, HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment;filename=" + getDocumentName());
        response.setHeader("Content-type", getContentType());
        response.setStatus(SC_CREATED);

        try {
            ICsvListWriter listWriter = new CsvListWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            addContent(list, listWriter);
            listWriter.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addContent(List<T> model, ICsvListWriter listWriter) throws IOException {
        listWriter.writeHeader(modelViewer.getHeaders().toArray(new String[modelViewer.getHeaders().size()]));
        List<List<String>> modelList =  modelViewer.map(model);

        for (List<String> row : modelList) {

            listWriter.write(row);

        }

    }
}
