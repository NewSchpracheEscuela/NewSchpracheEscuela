package Documentation.Generators;

import Documentation.Factories.IFactory;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfTemplate;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

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
    public void writeToResponse(List<T> list, HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment;filename=" + getDocumentName());
        response.setHeader("Content-type", getContentType());
        response.setStatus(SC_CREATED);

        try {
            String fontFile = "./arial.ttf";
            String templateUrl = "./template.pdf";
            String boldFontFile = "./arial_bold.ttf";

            BaseFont bf = BaseFont.createFont(fontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font bold = FontFactory.getFont(boldFontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 9f);
            Font font = new Font(bf, 7f);
            OutputStream outputStream = response.getOutputStream();
            PdfReader letterhead = new PdfReader(templateUrl);
            Rectangle pageSize = letterhead.getPageSizeWithRotation(1);
            Document document = new Document(pageSize);
            document.setMargins(0, 0, 170, 20);
            document.setMarginMirroringTopBottom(true);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(new PdfNewPageEventHandler());
            setEncryption(writer);
            document.open();
            addHeader(letterhead, writer);
            addMetadata(document);
            addContent(list, document, font, bold);
            document.close();
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEncryption(PdfWriter writer) throws DocumentException {
        if(isProtected) writer.setEncryption(null, null, ~(PdfWriter.ALLOW_COPY), PdfWriter.STANDARD_ENCRYPTION_128);
    }

    private void addContent(List<T> model, Document document, Font font, Font bold) throws DocumentException {

        Paragraph paragraph =  new Paragraph(" ");

        document.add(paragraph);
        PdfPTable pdfTable = createTable(model, font, bold);
        document.add(pdfTable);
    }

    private PdfPTable createTable(List<T> model, Font font, Font bold) {
        PdfPTable pdfTable = new PdfPTable(modelViewer.getHeaders().size());

        for (String header : modelViewer.getHeaders()) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header));
            headerCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            Paragraph paragraph = new Paragraph(header, bold);
            //paragraph.setAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(paragraph);
        }

        pdfTable.setHeaderRows(1);

        for (List<String> row : modelViewer.map(model)) {

            for (String cell : row) {
                pdfTable.addCell(new Paragraph(cell, font));
            }

        }

        return pdfTable;
    }

    private void addMetadata(Document document) {
        document.addAuthor("nse-project");
        document.addCreationDate();
        document.addTitle("NSE Document");
    }

    private void addHeader(PdfReader letterhead, PdfWriter writer) {
        PdfContentByte content = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(letterhead, 1);
        content.addTemplate(page, 0, 0);
    }
}

class PdfNewPageEventHandler extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        document.setMargins(0, 0, 20, 20);

    }
}