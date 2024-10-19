package org.example.Utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilExcel {
    static Workbook workbook;
    static Sheet sheet;
    public static String FILE_NAME="src/test/java/Resources/login (1).xlsx";
    public static Object[][] gettestdata(String sheetname){
        FileInputStream file=null;
        try {
            file=new FileInputStream(FILE_NAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook= WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sheet=workbook.getSheet(sheetname);
        Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=0;i<sheet.getLastRowNum();i++){
            for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
                data[i][j]=sheet.getRow(i+1).getCell(j).toString();
            }
        }

        return data;
    }
    @DataProvider
    public Object[][] getData(){
        return gettestdata("Sheet1");
    }
}
