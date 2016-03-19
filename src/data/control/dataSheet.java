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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    Sheet firstSheet;
    FileInputStream inputStream;
    Workbook workbook;


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
    public boolean connect() {

        // if the database isValid
        if (this.isValid()) {

            try {
                // loading the file as an input stream
                inputStream = new FileInputStream(new File(excelFilePath));

                // reading the input stream as a workbook excel file
                workbook = new XSSFWorkbook(inputStream);

                // Getting the first sheet
                firstSheet = workbook.getSheetAt(0);

                // all good!
                return true;

            } catch (FileNotFoundException ex) {
                return false;
            } catch (IOException ex) {
                return false;
            }

        } else {
            // datafile not found!
            return false;
        }
    }

    public boolean updateRow(Patient aPatient) {

        // looping through the rows (each row = a patient)
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            // reading the row
            Row aRow = iterator.next();

            // checking if it's the row we want to modify
            if ((int) aRow.getCell(0).getNumericCellValue() == aPatient.id) {
                // looping through the cells
                break;
            }
        }

        /*
         workbook.write(outputStream);
         */
        return false;
    }

    public ArrayList<Row> fetchRows() {
        // variable that holds all rows in the sheet
        ArrayList<Row> allRows = new ArrayList();

        // looping through the rows (each row = a patient)
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            // reading the row
            allRows.add(iterator.next());
        }
        // return all rows in the sheet
        return allRows;
    }

    public boolean closeConnection() {
        try {
            // done reading data
            workbook.close();
            inputStream.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
