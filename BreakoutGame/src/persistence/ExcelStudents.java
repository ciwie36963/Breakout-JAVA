package persistence;


import domain.Student;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Matthias
 */
public class ExcelStudents {

    public List<String> ImportStudents(String bestandsNaam) {
        List<String> listStudents=new ArrayList<>();
        
        try (XSSFWorkbook wb = readFile(bestandsNaam)) {
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                XSSFSheet sheet = wb.getSheetAt(k);
                int rows = sheet.getPhysicalNumberOfRows();
                for (int r = 0; r < rows; r++) {
                    XSSFRow row = sheet.getRow(r);

                    if (row == null) {
                        continue;
                    }

                    for (int c = 0; c < row.getLastCellNum(); c++) {
                        XSSFCell cell = row.getCell(c);
                        if (cell != null) {
                            //Dit heb je nodig om het klasscijfer om te zetten naar een String in excel
                            DataFormatter formatter = new DataFormatter();
                            listStudents.add(formatter.formatCellValue(cell));
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ExcelStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStudents;
    }

    private static XSSFWorkbook readFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return new XSSFWorkbook(fis);        // NOSONAR - should not be closed here
        }
    }

}
