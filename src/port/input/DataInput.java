/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package port.input;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;

/**
 *
 * @author Ahmed
 * @source https://github.com/Fazecast
 */
public class DataInput {

    boolean shouldExit = false;

    public void read() {
        // Choosing the active port
        SerialPort comPort = SerialPort.getCommPorts()[0];

        // for testing
        //System.out.println("P: "+comPort.getDescriptivePortName()+" rate "+comPort.getBaudRate());
        // setting the default baud rate
        comPort.setBaudRate(115200);

        // openning the port
        comPort.openPort();

        //setting the timeouts to conserve memory and proceccing power
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);

        // reading data
        InputStream in = comPort.getInputStream();
        try {
            while (true) {
                // if the shouldExit flag is set, do not continue
                if (!shouldExit) {
                    System.out.print((char) in.read());
                } else {
                    break;
                }
            }
            // close the input stream
            in.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // done, closing port
        comPort.closePort();
    }

    public void closeConnection() {
        // setting the should exit flag
        this.shouldExit = true;
    }
}