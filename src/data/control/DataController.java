/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

import java.util.ArrayList;
import javax.swing.SwingWorker;
import port.input.DataInput;

/**
 *
 * @author Ahmed
 */
public class DataController {

    volatile ArrayList<Patient> patients = new ArrayList();
    dataSheet dataSheet = new dataSheet();
    DataInput input = new DataInput();
    SwingWorker updateInput;
    public volatile int patient1BPM = 0, patient2BPM = 0;
    public volatile Float patient1Temp = new Float(0), patient2Temp = new Float(0);

    public DataController() {
        loadPatients();

        updateInput = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                synchronized (patients) {
                    while (true && !isCancelled()) {
                        getInput();
                    }
                }
                return null;
            }
        };
        try {
            updateInput.execute();

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void getInput() {
        // initialize database, load all data in memory as Patients
        // initialize datainput from serial port
        // setup automated data refresh
        String message = input.read();

        if (!message.equals("") && message != null && !message.isEmpty() && message.split(":").length >= 2) {
            //System.out.println(message);

            System.out.println("Message is: " + message);

            switch (message.split(":")[2]) {
                case "1":
                    this.patients.get(0).setBPM(Integer.valueOf(message.split(":")[1]));
                    this.patients.get(0).setTemp(Float.valueOf(message.split(":")[0]));
                    System.out.println(this.patients.get(0).getName() + " Temp: " + this.patients.get(0).getTemp() + "BPM: " + this.patients.get(0).getBPM());
                    this.patient1BPM = Integer.valueOf(message.split(":")[1]);
                    this.patient1Temp = Float.valueOf(message.split(":")[0]);
                    break;
                case "2":
                default:

                    this.patients.get(1).setBPM(Integer.valueOf(message.split(":")[1]));
                    this.patients.get(1).setTemp(Float.valueOf(message.split(":")[0]));
                    System.out.println(this.patients.get(1).getName() + " Temp: " + this.patients.get(1).getTemp() + "BPM: " + this.patients.get(1).getBPM());

                    this.patient2BPM = Integer.valueOf(message.split(":")[1]);
                    this.patient2Temp = Float.valueOf(message.split(":")[0]);
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
        updateInput.cancel(true);
        dataSheet.addPatient(newPatient);
        updateInput.execute();

    }

    public void update(ArrayList<Patient> allPatients) {
        updateInput.cancel(true);
        this.patients = allPatients;
        dataSheet.update(patients);
        updateInput.execute();
    }

    public int getBPM(int i) {
        //this.getInput();
        //return this.patients.get(i - 1).getBPM();

        if (i == 1) {
            return this.patient1BPM;
        } else {
            return this.patient2BPM;
        }

    }

    public Float getTemp(int i) {
        //this.getInput();
        //return this.patients.get(i - 1).getTemp();
        if (i == 1) {
            return this.patient2Temp;
        } else {
            return this.patient2Temp;
        }
    }

}
