import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDrivenFromExcel {
    ArrayList<String> a = new ArrayList<String>();
    String path = "E:/Road to SDET - Materials/Selenium/Excel Test data.xlsx";
    FileInputStream fis = new FileInputStream(path);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    int count = workbook.getNumberOfSheets();
    int column = 0;
    int k = 0;

    public DataDrivenFromExcel() throws IOException {
    }

    @Test
    public void getRowData(){
        for (int i = 0; i < count; i++) {

            if (workbook.getSheetName(i).equalsIgnoreCase("Testcase data")) {

                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cells = firstRow.cellIterator();
                while (cells.hasNext()) {

                    Cell cellValue = cells.next();
                    if (cellValue.getStringCellValue().equalsIgnoreCase("Testcases")) {
                        column = k;


                    }

                    k++;
                }
                System.out.println(column);

                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Campaign Name")) {
                        Iterator<Cell> c = r.cellIterator();
                        while (c.hasNext()) {
                            Cell c1=c.next();
                            if(c1.getCellType()==CellType.STRING) {
                                a.add(c1.getStringCellValue());
                            }
                            else{
                                a.add(NumberToTextConverter.toText(c1.getNumericCellValue()));
                            }
                        }
                    }

                }


            }
        }
        }



@Test
public void getDiagonalCellValues(){
    //practise 2 - get  cell values from a diagonal \

    int j=0;
    for (int i = 0; i < count; i++) {
        if (workbook.getSheetName(i).equalsIgnoreCase("Testcase data")){

            XSSFSheet sheet= workbook.getSheetAt(i);
            Iterator<Row> rows= sheet.iterator();
            Row firstRow=rows.next();
            while(rows.hasNext()){
                Row r=rows.next();
                if (r.getCell(j).getCellType() == CellType.STRING) {
                    System.out.println(r.getCell(j).getStringCellValue());
                }
                else{
                    System.out.println(r.getCell(j).getNumericCellValue());
                }

                j++;
            }

        }

    }


}


@Test
    public void getColumnValues(){
    //get all values from a single column
    int j=0;
    for (int i = 0; i < count; i++) {
        if (workbook.getSheetName(i).equalsIgnoreCase("Testcase data")){

            XSSFSheet sheet= workbook.getSheetAt(i);
            Iterator<Row> rows= sheet.iterator();
            Row firstRow=rows.next();
            while(rows.hasNext()){
                Row r=rows.next();
                if (r.getCell(j).getCellType() == CellType.STRING) {
                    System.out.println(r.getCell(j).getStringCellValue());
                }
                else{
                    System.out.println(r.getCell(j).getNumericCellValue());
                }

            }

        }

    }


}

@Test(dataProvider = "getData")
public void animalSoundTest(String animal, String sound){

System.out.println(animal + ", " + sound);
}


    @DataProvider
    public Object[][] getData() throws IOException {

        DataFormatter formatter = new DataFormatter();
        FileInputStream file = new FileInputStream(
                "E:/Road to SDET - Materials/Selenium/Excel Test data.xlsx");

        XSSFWorkbook book = new XSSFWorkbook(file);
        XSSFSheet sheet = book.getSheet("Animals test data");
        int rowCount = sheet.getLastRowNum();   // last row index (4 in your case)
        int colCount = sheet.getRow(0).getLastCellNum(); // 2 columns
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
            }
        }

        book.close();
        file.close();

        return data;
    }


}






