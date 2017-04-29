package Documentation.Generators;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by angre on 29.04.2017.
 */
public class XLSGenerator<T> implements IGenerator<T>{
    private boolean isProtected = false;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public String getType()
    {
        return "xls";
    }
}
