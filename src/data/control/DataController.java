/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public volatile int patient1BPM = 0, patient2BPM = 0;
    public volatile Float patient1Temp = new Float(0), patient2Temp = new Float(0);
    public SwingWorker updateInput;

    public DataController() {
        loadPatients();
    }

    public void getInput() {

        // initialize database, load all data in memory as Patients
        // initialize datainput from serial port
        // setup automated data refresh
        String message = input.read();
        //String message = generateMessage();

        if (!message.equals("") && message != null && !message.isEmpty() && message.split(":").length >= 2) {
            //System.out.println(message);

            //System.out.println("Message is: " + message);
            if (!message.split(":")[2].isEmpty()) {
                switch (message.split(":")[2]) {
                    case "1":
                        if (!message.split(":")[1].isEmpty() && !message.split(":")[0].isEmpty()) {

                            this.patients.get(0).setBPM(Integer.valueOf(message.split(":")[1]));
                            this.patients.get(0).setTemp(Float.valueOf(message.split(":")[0]));
                            System.out.println(this.patients.get(0).getName() + " Temp: " + this.patients.get(0).getTemp() + "  BPM: " + this.patients.get(0).getBPM());

                        }
                        break;
                    case "2":
                    default:

                        if (!message.split(":")[1].isEmpty() && !message.split(":")[0].isEmpty()) {

                            this.patients.get(1).setBPM(Integer.valueOf(message.split(":")[1]));
                            this.patients.get(1).setTemp(Float.valueOf(message.split(":")[0]));
                            System.out.println(this.patients.get(1).getName() + " Temp: " + this.patients.get(1).getTemp() + "BPM: " + this.patients.get(1).getBPM());
                        }

                        break;
                }
            }

        }
    }

    public void updateInput() {
        updateInput = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                while (true && !isCancelled()) {
                    getInput();
                }
                return null;
            }
        };

        updateInput.execute();
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

    String generateMessage() {
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        Random r = new Random();
        int id = r.nextInt(2) + 1;
        //int BPM = r.nextInt(60) + 50;
        int BPM = r.nextInt(70) + 60;
        Float temp = new Float(r.nextInt(300) + 3500) / 100;

        String theMessage = temp + ":" + BPM + ":" + id;
        return theMessage;
    }
}
