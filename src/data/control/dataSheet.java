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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 *  Class that handles database connections and data reading and writing
 *  From the Excel file
 */
public class dataSheet {

    // setting class variables
    String excelFilePath = "src//data//control//patients.xlsx";
    ArrayList<Patient> patients = new ArrayList();

    public void dataSheet() {
        // no cooking recipe for now
    }

    /*
     *  function that checks if database connection is established
     */
    public boolean isValid() {
        // loading the file 
        File database = new File(excelFilePath);

        // checking if file exists and is not a directory
        if (database.exists() && !database.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     *  function that initiates database connection
     */
    public void connect() {

        // declaring function variables
        FileInputStream inputStream;
        Workbook workbook;

        // if the database isValid
        if (this.isValid()) {

            try {
                // loading the file as an input stream
                inputStream = new FileInputStream(new File(excelFilePath));

                // reading the input stream as a workbook excel file
                workbook = new XSSFWorkbook(inputStream);

                // Getting the first sheet
                Sheet firstSheet = workbook.getSheetAt(0);

                // looping through the rows (each row = a patient)
                Iterator<Row> iterator = firstSheet.iterator();

                while (iterator.hasNext()) {
                    // reading the row
                    Row nextRow = iterator.next();

                    // loading the cells
                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    // looping through the cells
                    while (cellIterator.hasNext()) {
                        // reading the cell
                        Cell cell = cellIterator.next();

                        /*
                         TODO: loop through cells by column index, and construct the patients
                         then after each row, add patient to the patients array
                        
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
                         */
                    }

                }
                // done reading data
                workbook.close();
                inputStream.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(dataSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(dataSheet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
        }
    }

    public boolean hasLock() {
        // function that makes sure no other process is using the database file
        return false;
    }

    public Patient getPatients(int patientId) {
        if (!hasLock()) {
            return new Patient();
        } else {
            return null;
        }
    }

    public boolean updatePatient(int patientId) {

        /*
         workbook.write(outputStream);
         */
        if (!hasLock()) {
            return false;
        } else {
            return false;
        }
    }

}
