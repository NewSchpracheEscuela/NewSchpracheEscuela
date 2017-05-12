package Documentation.Generators;

import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.IFactory;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Documentation.Generators.PDFGeneratorDiploma.getCurrentDate;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by angre on 12.05.2017.
 */
public class CSVGeneratorDiploma implements IGenerator<BlankInfo> {
    private boolean isProtected = false;
    private IFactory<BlankInfo> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<BlankInfo> getModelViewer() {
        return modelViewer;
    }

    public void setModelViewer(IFactory<BlankInfo> item) {
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

    public void writeToResponse(List<BlankInfo> list, HttpServletResponse response) {
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

    private void addContent(List<BlankInfo> model, ICsvListWriter listWriter) throws IOException {
        BlankInfo item = (BlankInfo) model.toArray()[0];
        String[] headers = new String[]{"Тип документа", "Описание", "ФИО", "Курс" ,"Язык", "Дата"};
        String[] items = new String[]{"Диплом", "Окончание курса", item.getFirstName() + " " + item.getLastName() + " " + item.getPatronym(),  item.getCourseTitle(), item.getCourseLanguage(), getCurrentDate()};
        listWriter.writeHeader(headers);
        List<List<String>> modelList =  modelViewer.map(model);

        listWriter.write(items);

    }
}
