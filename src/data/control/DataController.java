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

    public void getInput() {
        // initialize database, load all data in memory as Patients
        // initialize datainput from serial port
        // setup automated data refresh
        String message = input.read();

        //System.out.println("here " + message);
        if (!message.equals("") && message != null && !message.isEmpty() && message.split(":").length >= 2) {
            //System.out.println(message);

            System.out.println("Message is: " + message);

            switch (message.split(":")[2]) {
                case "1":
                    this.patients.get(0).setBPM(Integer.valueOf(message.split(":")[1]));
                    this.patients.get(0).setTemp(Float.valueOf(message.split(":")[0]));
                    break;
                case "2":
                    this.patients.get(1).setBPM(Integer.valueOf(message.split(":")[1]));
                    this.patients.get(1).setTemp(Float.valueOf(message.split(":")[0]));
                    break;
                default:
                    break;
            }
        }
    }


    /*
     *  Function that loads the patients from database into memory
     *  Each row is a patient with cells:(ID, Name, heartrate[5], temp[5], date_added
     *  last_updated, last_alarmed
     */
    private void loadPatients() {
        this.patients = dataSheet.getPatients();
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

    public int getBPM(int i) {
        this.getInput();
        return this.patients.get(i - 1).getBPM();

    }

    public Float getTemp(int i) {
        this.getInput();
        return this.patients.get(i - 1).getTemp();

    }

}
