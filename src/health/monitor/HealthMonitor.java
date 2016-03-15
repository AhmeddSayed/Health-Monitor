/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health.monitor;

import GUI.mainFrame;
import data.control.dataSheet;
import javax.swing.JFrame;

/**
 *
 * @author Ahmed
 */
public class HealthMonitor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame theFrame = new mainFrame();
        theFrame.setVisible(true);
        dataSheet a = new dataSheet();
    }
    
}
