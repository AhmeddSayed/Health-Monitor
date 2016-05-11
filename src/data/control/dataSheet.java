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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 *  Class that handles database connections and data reading and writing
 *  From the Excel file
 */
public class dataSheet {

    // setting class variables
    String excelFilePath = "src//data//control//patients.xlsx";
    XSSFSheet firstSheet;
    FileInputStream inputStream;
    XSSFWorkbook theWorkbook;

    /*
     *  function that initiates database connection
     */
    public void connect() {
        try {
            // loading the file as an input stream
            inputStream = new FileInputStream(new File(excelFilePath));

            // reading the input stream as a workbook excel file
            theWorkbook = new XSSFWorkbook(inputStream);
            // Getting the first sheet
            firstSheet = theWorkbook.getSheetAt(0);

        } catch (IOException ex) {
            Logger.getLogger(dataSheet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<XSSFRow> fetchRows() {
        // variable that holds all rows in the sheet
        ArrayList<XSSFRow> allRows = new ArrayList();

        // looping through the rows (each row = a patient)
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            // reading the row
            allRows.add((XSSFRow) iterator.next());
        }
        // return all rows in the sheet
        return allRows;
    }

    public void closeConnection() {
        try {
            // done reading data
            inputStream.close();

        } catch (IOException ex) {
            System.err.println("here");
        }
    }

    private void newPatient(Patient newPatient) {

        XSSFRow newRow;
        // creating a new row
        if (firstSheet.getRow(newPatient.getId()) == null) {
            newRow = firstSheet.createRow(newPatient.getId());
        } else {
            newRow = firstSheet.getRow(newPatient.getId());
        }

        // creating a new cell
        XSSFCell cell = newRow.getCell(0);
        if (cell == null) {
            cell = newRow.createCell(0);
        }

        // adding the patient ID
        cell.setCellValue(newPatient.getId());

        // adding the patient Name
        // creating a new cell
        cell = newRow.getCell(1);
        if (cell == null) {
            cell = newRow.createCell(1);
        }
        cell.setCellValue(newPatient.getName());

        // adding the patient blood type
        cell = newRow.getCell(12);
        if (cell == null) {
            cell = newRow.createCell(12);
        }
        cell.setCellValue(newPatient.getBloodType());

        // adding the patient sex
        cell = newRow.getCell(13);
        if (cell == null) {
            cell = newRow.createCell(13);
        }
        cell.setCellValue(newPatient.getSex());

        // adding the patient age
        cell = newRow.getCell(14);
        if (cell == null) {
            cell = newRow.createCell(14);
        }
        cell.setCellValue(newPatient.getAge());

        // setting up the date format
        XSSFCellStyle cellStyle = theWorkbook.createCellStyle();
        CreationHelper createHelper = theWorkbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy hh:mm:ss"));

        // adding the patient dateAdded
        cell = newRow.getCell(15);
        if (cell == null) {
            cell = newRow.createCell(15);
        }

        if (newPatient.getDateCreated() != null) {
            cell.setCellValue(newPatient.getDateCreated());
        } else {
            cell.setCellValue(new Date());
        }
        cell.setCellStyle(cellStyle);

        // adding the date modified
        if (newPatient.getLastModified() != null) {
            cell = newRow.getCell(16);
            if (cell == null) {
                cell = newRow.createCell(16);
            }
            cell.setCellValue(newPatient.getLastModified());
            cell.setCellStyle(cellStyle);
        }

        // adding the lastAlarm
        if (newPatient.getLastAlarmed() != null) {
            cell = newRow.getCell(17);
            if (cell == null) {
                cell = newRow.createCell(17);
            }
            cell.setCellValue(newPatient.getLastAlarmed());
            cell.setCellStyle(cellStyle);
        }

    }

    public ArrayList<Patient> getPatients() {
        ArrayList<XSSFRow> theRows;
        ArrayList<Patient> thePatients = new ArrayList();
        boolean firstRowSkipped = false;

        connect();
        theRows = fetchRows();

        // looping through the rows
        Iterator<XSSFRow> rowIterator = theRows.iterator();

        while (rowIterator.hasNext()) {

            // reading the row
            Row aRow = rowIterator.next();

            if (!firstRowSkipped) {
                firstRowSkipped = true;
                continue;
            }

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
                            // blood_type
                            aPatient.setBloodType(cell.getStringCellValue());

                            break;
                        case 13:
                            // sex
                            aPatient.setSex(cell.getStringCellValue());
                            break;
                        case 14:
                            // age
                            aPatient.setAge((int) cell.getNumericCellValue());
                            break;
                        case 15:
                            // date_added
                            aPatient.setDateAdded(cell.getDateCellValue());
                            break;
                        case 16:
                            // last_updated
                            aPatient.setLastUpdated(cell.getDateCellValue());
                            break;
                        case 17:
                            // last_alarmed
                            aPatient.setLastAlarm(cell.getDateCellValue());
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

        // Getting the first sheet
        firstSheet = theWorkbook.getSheetAt(0);

        // looping through the rows
        for (int i = 1; i <= firstSheet.getLastRowNum(); i++) {
            // clearing data
            XSSFRow row = firstSheet.getRow(i);
            firstSheet.removeRow(row);
        }

        // adding patients again
        for (Patient patientX : patients) {
            newPatient(patientX);
        }

        saveToDisk();

    }

    private void saveToDisk() {
        // saving to disk
        FileOutputStream fileOut;
        try {
            closeConnection();
            fileOut = new FileOutputStream(excelFilePath);
            theWorkbook.write(fileOut);
            //theWorkbook.close();
            fileOut.close();

        } catch (IOException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addPatient(Patient aPatient) {
        this.newPatient(aPatient);
        saveToDisk();
    }
}
