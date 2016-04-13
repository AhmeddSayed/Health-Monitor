/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author Ahmed
 */
public class Alarm {

    String wavFilePath = "src//resources//sounds//Alarm_Sounds_2.wav";
    Clip clip;
    boolean isTriggered = false;
    boolean patient1Triggered = false;
    boolean patient2Triggered = false;

    public Alarm() {

    }

    public void trigger(int patientID) {
        if (patientID == 1) {
            this.patient1Triggered = true;
        } else {
            this.patient2Triggered = true;
        }
        start();
    }

    private void start() {

        try {
            File alarmSound = new File(wavFilePath);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            this.isTriggered = true;

            stream = AudioSystem.getAudioInputStream(alarmSound);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            //whatevers
        }
    }

    public void stop(int patientID) {

        if (patientID == 1) {
            this.patient1Triggered = false;
        } else {
            this.patient2Triggered = false;
        }

        if (!patient1Triggered && !patient2Triggered) {
            clip.stop();
            this.isTriggered = false;
        }
    }

    boolean isTriggered(int i) {
        return this.isTriggered;
    }
}
