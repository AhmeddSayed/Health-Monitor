/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package port.input;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Ahmed
 * @source https://github.com/Fazecast
 */
public class DataInput {

    boolean shouldExit = false;
    SerialPort comPort;

    public DataInput() {

        if (SerialPort.getCommPorts().length > 0) {
            // Choosing the active port
            comPort = SerialPort.getCommPorts()[0];

            // setting the default baud rate
            comPort.setBaudRate(57600);

            // openning the port
            comPort.openPort();

            //setting the timeouts to conserve memory and proceccing power
            comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        }
    }

    public String read() {

        // reading data
        InputStream in = comPort.getInputStream();
        try {
            // if the shouldExit flag is set, do not continue
            if (!shouldExit) {
                // need more work when I have the real data
                // TODO: update dleimiter and string validation
                Scanner theScanner = new Scanner(in, "UTF-8").useDelimiter("\n");

                if (theScanner.hasNext()) {
                    return theScanner.next();
                } else {
                    return "";
                }
            } else {
                // close the input stream
                in.close();
                this.closeConnection();
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public void closeConnection() {
        // setting the should exit flag
        this.shouldExit = true;
        // done, closing port
        comPort.closePort();
    }
}
