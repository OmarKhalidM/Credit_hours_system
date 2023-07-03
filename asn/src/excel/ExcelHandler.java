package excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class ExcelHandler {


    public static XSSFSheet readFile(String filePath) {


        try {
            File file = new File(filePath);
            OPCPackage pkg = OPCPackage.open(file);
            XSSFWorkbook workbook = new XSSFWorkbook(pkg);
            XSSFSheet sheet = workbook.getSheetAt(0);



            return sheet;


        } catch (InvalidFormatException | IOException e) {
            System.out.println("مشكلة في ملف ال excel");
        }
        return null;
    }
}
