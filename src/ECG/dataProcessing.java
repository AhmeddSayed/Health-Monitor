/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECG;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed
 */
public class dataProcessing extends Thread {

    ArrayList al = new ArrayList();
    ArrayList d_al;

    public dataProcessing(ArrayList a_l) {
        d_al = a_l;
        this.start();
        d_al = a_l;
    }

    @Override
    public void run() {

        while (true) {
            while (d_al.size() < 10) {
                try {
                    int p = (int) (Math.random() * 100);
                    d_al.add(new Point(0, p - 50));
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(dataProcessing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}
