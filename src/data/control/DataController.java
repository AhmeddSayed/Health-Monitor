/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

import java.util.ArrayList;
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
        loadPatients();
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
        this.patients = dataSheet.getPatients();
    }

    private void updateFromInput() {

    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public void addPatient(Patient newPatient) {
        dataSheet.addPatient(newPatient);
    }

    public void update(ArrayList<Patient> allPatients) {
        this.patients = allPatients;
        dataSheet.update(patients);
    }

}
