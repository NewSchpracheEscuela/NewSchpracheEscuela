package Documentation.Generators;

import Documentation.Factories.Entities.BlankInfo;
import Documentation.Factories.IFactory;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Documentation.Generators.PDFGeneratorDiploma.getCurrentDate;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by angre on 12.05.2017.
 */
public class XLSGeneratorBlank implements IGenerator<BlankInfo> {
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
        return ".xls";
    }

    public String getContentType() {
        return "application/vnd.ms-excel";
    }

    public void writeToResponse(List<BlankInfo> list, HttpServletResponse response) {
        response.setHeader("Content-type", getContentType());
        response.setHeader("Content-disposition", "attachment;filename=" + getDocumentName());
        response.setStatus(SC_CREATED);

        XSSFWorkbook workbook = getWorkBook(list);

        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addContent(List<BlankInfo> model, Sheet sheet, CellStyle style) {
        BlankInfo item = (BlankInfo) model.toArray()[0];

        Row header = sheet.createRow(0);
        Row description1 = sheet.createRow(1);
        Row rowFIO = sheet.createRow(2);
        Row rowCourse = sheet.createRow(3);

        header.createCell(0).setCellValue("Заявление");
        header.createCell(1);

        description1.createCell(0).setCellValue("о зачислении на курс");
        description1.createCell(1);

        rowFIO.createCell(0).setCellValue("ФИО");
        rowFIO.createCell(1).setCellValue(item.getFirstName() + " " + item.getLastName() + " " + item.getPatronym());

        rowCourse.createCell(0).setCellValue("Курс");
        rowCourse.createCell(1).setCellValue(item.getCourseTitle() + "(" + item.getCourseLanguage() + ")");

        for (int i=0; i<4; i++)
            for(int j=0; j<2; j++) sheet.getRow(i).getCell(j).setCellStyle(style);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,1));

        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private XSSFWorkbook getWorkBook(List<BlankInfo> list)
    {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Document");
        sheet.setDefaultColumnWidth(30);
        PrintSetup ps = sheet.getPrintSetup();
        sheet.setAutobreaks(true);

        ps.setFitHeight((short)1);
        ps.setFitWidth((short)1);
        sheet.setFitToPage(true);

        CellStyle style = getCellStyle(workbook);
        addContent(list, sheet, style);

        return workbook;
    }

    private CellStyle getCellStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");

        style.setWrapText(true);
        font.setBoldweight((short) 10);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);

        return style;
    }
}
