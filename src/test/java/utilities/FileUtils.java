package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import tests.beymen.Locators;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static  String PAGE_NAME = "Sayfa1";

    public static List<String> readSearchProductNameFromExcell() throws IOException {
        List<String> willBeSearchWords = new ArrayList<>();

        FileInputStream fileInputStream;
        Workbook workbook;
        Sheet firstPage;

        try {
            fileInputStream = new FileInputStream(Locators.FILE_PATH);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }

        try {
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        firstPage = workbook.getSheet(PAGE_NAME);
        Row row = firstPage.getRow(0);

        willBeSearchWords.add(row.getCell(0).getStringCellValue());
        willBeSearchWords.add(row.getCell(1).getStringCellValue());
        workbook.close();
        return willBeSearchWords;
    }

}
