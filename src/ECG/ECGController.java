/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECG;

import data.control.Patient;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed
 */
public class ECGController extends Thread {

    boolean isCancelled;
    ecgDrawPanel panel1, panel2;
    Patient patient1, patient2;

    public ECGController() {

    }

    @Override
    public void run() {

        while (!isCancelled) {
            panel1.move_1(110 * patient1.getBPM()/283 );
            panel2.move_1(110* patient2.getBPM()/283);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ECGController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void plot(ecgDrawPanel ecgDrawPanel1, ecgDrawPanel ecgDrawPanel2, Patient firstPatient, Patient secondPatient) {
        this.panel1 = ecgDrawPanel1;
        this.panel2 = ecgDrawPanel2;
        this.patient1 = firstPatient;
        this.patient2 = secondPatient;
        start();
    }

    public void cancel() {
        this.isCancelled = true;
    }
}
