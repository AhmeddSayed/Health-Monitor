/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package port.input;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import org.apache.poi.util.IOUtils;

/**
 *
 * @author Ahmed
 * @source https://github.com/Fazecast
 */
public class DataInput {

    private static final byte DELIMITER = '\n';

    boolean shouldExit = false;

    public String read() {
        // Choosing the active port
        SerialPort comPort = SerialPort.getCommPorts()[0];

        // setting the default baud rate
        comPort.setBaudRate(57600);

        // for testing
        //System.out.println("P: " + comPort.getDescriptivePortName() + " rate " + comPort.getBaudRate());
        // openning the port
        comPort.openPort();

        //setting the timeouts to conserve memory and proceccing power
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);

        char[] message = new char[11];
        int messageIndex = 0;

        // reading data
        InputStream in = comPort.getInputStream();

        try {

            while (messageIndex < 11 && !shouldExit) {
                // if the shouldExit flag is set, do not continue
                if (in != null) {

                    message[messageIndex] = (char) in.read();

                    if (message[messageIndex] == DELIMITER) {
                        break;
                    }

                    messageIndex++;

                    //System.out.print((char) in.read());
                    //theMessage.concat(String.valueOf((char) in.read))
                } else {
                    messageIndex = 0;
                    break;
                }
            }
            // close the input stream
            if (in != null) {
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // done, closing port
        comPort.closePort();
        return new String(message);
    }

    public void closeConnection() {
        // setting the should exit flag
        this.shouldExit = true;
    }
}
