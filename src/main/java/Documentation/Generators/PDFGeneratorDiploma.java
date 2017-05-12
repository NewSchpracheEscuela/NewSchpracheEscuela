package Documentation.Generators;

import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.IFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by angre on 11.05.2017.
 */
public class PDFGeneratorDiploma implements IGenerator<BlankInfo>{
    private boolean isProtected = false;
    private IFactory<BlankInfo> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<BlankInfo> getModelViewer()
    {
        return  modelViewer;
    }

    public void setModelViewer(IFactory<BlankInfo> item) {
        modelViewer = item;
    }

    public String getDocumentName() {
        String filename = java.util.UUID.randomUUID().toString();

        return String.format("%s%s", filename, getDocumentType());
    }

    public String getDocumentType() {
        return ".pdf";
    }

    public String getContentType() {
        return "application/pdf";
    }

    @Override
    public void writeToResponse(List<BlankInfo> list, HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment;filename=" + getDocumentName());
        response.setHeader("Content-type", getContentType());
        response.setStatus(SC_CREATED);

        try {
            String fontFile = "./arial.ttf";
            String boldFontFile = "./arial_bold.ttf";
            String templateUrl = "./template.pdf";

            BaseFont bf = BaseFont.createFont(fontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font bold = FontFactory.getFont(boldFontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 35f);
            Font font = new Font(bf, 17f);
            Font middle = FontFactory.getFont(boldFontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 17f);
            OutputStream outputStream = response.getOutputStream();
            PdfReader letterhead = new PdfReader(templateUrl);
            Rectangle pageSize = letterhead.getPageSizeWithRotation(1);
            Document document = new Document(pageSize);
            document.setMargins(0, 0, 150, 20);
            document.setMarginMirroringTopBottom(true);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(new PdfNewPageEventHandler());
            setEncryption(writer);
            document.open();
            addHeader(letterhead, writer);
            addMetadata(document);
            addContent(list, document, font, bold, middle);
            document.close();
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEncryption(PdfWriter writer) throws DocumentException {
        if(isProtected) writer.setEncryption(null, null, ~(PdfWriter.ALLOW_COPY), PdfWriter.STANDARD_ENCRYPTION_128);
    }

    private void addContent(List<BlankInfo> model, Document document, Font generalFont, Font bold, Font middle) throws DocumentException {
        BlankInfo item = (BlankInfo) model.toArray()[0];
        Paragraph paragraph =  new Paragraph(" ");
        Paragraph header = new Paragraph("ДИПЛОМ", bold);
        Paragraph description1  = new Paragraph("об окончании", generalFont);
        Paragraph description2  = new Paragraph("НАГРАЖДАЕТСЯ", bold);
        Paragraph fio = new Paragraph(item.getFirstName() + " " + item.getLastName() + " " + item.getPatronym(), middle);
        Paragraph description3 = new Paragraph("закончивший/ая курс", generalFont);
        Paragraph signy = new Paragraph("Подпись: _____________   " + getCurrentDate(), generalFont);
        Paragraph course = new Paragraph(item.getCourseTitle(), generalFont);
        Paragraph date = new Paragraph(getCurrentDate(), generalFont);
        header.setAlignment(Element.ALIGN_CENTER);
        description1.setAlignment(Element.ALIGN_CENTER);
        description2.setAlignment(Element.ALIGN_CENTER);
        description3.setAlignment(Element.ALIGN_CENTER);
        fio.setAlignment(Element.ALIGN_CENTER);
        course.setAlignment(Element.ALIGN_CENTER);
        date.setAlignment(Element.ALIGN_CENTER);
        signy.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);
        document.add(header);
        document.add(description1);
        document.add(paragraph);
        document.add(description2);
        document.add(fio);
        document.add(paragraph);
        document.add(description3);
        document.add(course);
        document.add(date);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(signy);
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

    public static String getCurrentDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        return dateFormat.format(date);
    }
}
