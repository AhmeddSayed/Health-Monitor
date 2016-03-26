/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import port.input.DataInput;

/**
 *
 * @author Ahmed
 */
public class DataController {

    ArrayList<Patient> patients = new ArrayList();
    dataSheet dataSheet = new dataSheet();
    DataInput input = new DataInput();

    public DataController() {
        dataSheet.connect();
        loadPatients();
        updateFromInput();
    }

    private void getInput() {
        // initialize database, load all data in memory as Patients
        // initialize datainput from serial port
        // setup automated data refresh

        /*
         while (true) {
         String message = s.read(); 
         if (!message.equals("") && !message.isEmpty()) {
         System.out.println(message);
         }
         }
         */
    }

    /*
     *  Function that loads the patients from database into memory
     *  Each row is a patient with cells:(ID, Name, heartrate[5], temp[5], date_added
     *  last_updated, last_alarmed
     */
    private void loadPatients() {
        ArrayList<Row> theRows;

        if (dataSheet.isValid()) {
            theRows = dataSheet.fetchRows();

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

                    // TODO: loop through cells by column index, and construct the patients
                    //then after each row, add patient to the patients array
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
                this.patients.add(aPatient);
                //aPatient.printAll();
            }

        }
    }

    private void updateFromInput() {

    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

}
