/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

/**
 *
 * @author Ahmed
 */

/* Imports */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataSheet {

    public void dataSheet() throws FileNotFoundException, IOException {
         
        // setting the excel file path
        String excelFilePath = "src//data//control//patients.xlsx";
        
        // loading the file as an input stream
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        // reading the input stream as a workbook excel file
        Workbook workbook = new XSSFWorkbook(inputStream);        
        Sheet firstSheet = workbook.getSheetAt(0);
        
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        cell.setCellValue("hello");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        cell.setCellValue("hello");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        cell.setCellValue("hello");
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();
        }
        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
        
        // done writing data
        workbook.close();
        inputStream.close();
    }
    
    
}
