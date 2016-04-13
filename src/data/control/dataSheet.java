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
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
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
     *  function that initiates database connection
     */
    public void connect() {
        try {
            // loading the file as an input stream
            inputStream = new FileInputStream(new File(excelFilePath));
            // reading the input stream as a workbook excel file
            workbook = new XSSFWorkbook(inputStream);
            // Getting the first sheet
            firstSheet = workbook.getSheetAt(0);

        } catch (IOException ex) {
            Logger.getLogger(dataSheet.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public void closeConnection() {
        try {
            // done reading data
            workbook.close();
            inputStream.close();

        } catch (IOException ex) {
            System.err.println("here");
        }
    }

    public void addPatient(Patient newPatient) {
        // connect to the databse
        connect();
        // creating a new row
        Row newRow = firstSheet.createRow(newPatient.getId() - 1);

        // creating a new cell
        Cell cell = newRow.createCell(0);

        // adding the patient ID
        cell.setCellValue(newPatient.getId());

        // adding the patient Name
        cell = newRow.createCell(1);
        cell.setCellValue(newPatient.getName());

        // adding the patient dataAdded
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy hh:mm"));
        cell = newRow.createCell(12);

        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        // adding the patient sex
        cell = newRow.createCell(15);
        cell.setCellValue(newPatient.getSex());

        // adding the patient age
        cell = newRow.createCell(16);
        cell.setCellValue(newPatient.getAge());

        saveToDisk();

    }

    public ArrayList<Patient> getPatients() {
        ArrayList<Row> theRows;
        ArrayList<Patient> thePatients = new ArrayList();

        connect();
        theRows = fetchRows();

        // looping through the rows
        Iterator<Row> rowIterator = theRows.iterator();

        while (rowIterator.hasNext()) {
            // reading the row
            Row aRow = rowIterator.next();
            Patient aPatient = new Patient();

            // loading the cells
            Iterator<Cell> cellIterator = aRow.cellIterator();

            // looping through the cells
            while (cellIterator.hasNext()) {
                // reading the cell
                Cell cell = cellIterator.next();

                if (cell != null) {
                    switch (cell.getColumnIndex()) {

                        case 0:
                            // ID
                            aPatient.setID((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            // Name
                            aPatient.setName(cell.getStringCellValue());
                            break;
                        case 2: // heart rate
                        case 3: // heart rate
                        case 4: // heart rate
                        case 5: // heart rate
                        case 6: // heart rate
                            aPatient.addHeartRate(cell.getNumericCellValue());
                            break;
                        case 7: // tempreature
                        case 8: // tempreature
                        case 9: // tempreature
                        case 10:// tempreature
                        case 11:// tempreature
                            aPatient.addTempreature(cell.getNumericCellValue());
                            break;
                        case 12:
                            // date_added
                            aPatient.setDateAdded(cell.getDateCellValue());
                            break;
                        case 13:
                            // last_updated
                            aPatient.setLastUpdated(cell.getDateCellValue());
                            break;
                        case 14:
                            // last_alarmed
                            aPatient.setLastAlarm(cell.getDateCellValue());
                            break;
                        case 15:
                            // sex
                            aPatient.setSex(cell.getStringCellValue());
                            break;
                        case 16:
                            // age
                            aPatient.setAge((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                }
            }
            // adding patient to the collection
            if (aPatient.getName() != null) {
                thePatients.add(aPatient);
            }
            //aPatient.printAll();
        }

        //closeConnection();
        return thePatients;
    }

    void update(ArrayList<Patient> patients) {

        // deleting the old rows
        ArrayList<Row> theRows = fetchRows();
        // looping through the rows
        Iterator<Row> rowIterator = theRows.iterator();

        while (rowIterator.hasNext()) {
            firstSheet.removeRow(rowIterator.next());
        }
        saveToDisk();
        
        // adding patients again
        for (Patient patientX : patients) {
            addPatient(patientX);
        }
    }

    private void saveToDisk() {
        // saving to disk
        try {
            inputStream.close();
            FileOutputStream fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.close();

        } catch (IOException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
