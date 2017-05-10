package Documentation.Generators;

import Documentation.Factories.IFactory;
import javafx.scene.control.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

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
        return ".xls";
    }

    public String getContentType() {
        return "application/vnd.ms-excel";
    }

    public void writeToResponse(List<T> list, HttpServletResponse response) {
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

    private void addContent(List<T> model, Sheet sheet, CellStyle style) {
        Row header = sheet.createRow(0);

        for (int i = 0; i < modelViewer.getHeaders().size(); i++) {
            String caption = modelViewer.getHeaders().get(i);
            header.createCell(i).setCellValue(caption);
            header.getCell(i).setCellStyle(style);
            header.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        }

        int rowCount = 1;

        for (List<String> row : modelViewer.map(model)) {
            Row tableRow = sheet.createRow(rowCount++);

            for (int i = 0; i < row.size(); i++) {
                String item = row.get(i);
                tableRow.createCell(i).setCellValue(item);
                tableRow.getCell(i).setCellStyle(style);
                tableRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            }
        }

        for (int i = 0; i < modelViewer.getHeaders().size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private XSSFWorkbook getWorkBook(List<T> list)
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
