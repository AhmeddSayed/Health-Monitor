/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class Patient {

    int id;
    String name;
    ArrayList<Double> heartRate = new ArrayList();
    ArrayList<Double> tempreature = new ArrayList();
    String dateAdded;
    String lastUpdated;
    String lastAlarm;

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void addHeartRate(double heartRate) {
        // using the array list as a stack
        if (this.heartRate.size() == 5) {
            // if reached the size of 5, remove the first element
            this.heartRate.remove(0);
        }
        this.heartRate.add(heartRate);
    }

    public void addTempreature(double tempreature) {
        // using the array list as a stack
        if (this.tempreature.size() == 5) {
            // if reached the size of 5, remove the first element
            this.tempreature.remove(0);
        }
        this.tempreature.add(tempreature);
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = normalizeDate(dateAdded);
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = normalizeDate(lastUpdated);
    }

    public void setLastAlarm(Date lastAlarm) {
        this.lastAlarm = normalizeDate(lastAlarm);
    }

    private String normalizeDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);

    }

    public void printAll() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Rate: " + this.heartRate.toString());
        System.out.println("Temp: " + this.tempreature.toString());
        System.out.println("dateAdded: " + this.dateAdded);
        System.out.println("last: " + this.lastUpdated);
        System.out.println("Alarm: " + this.lastAlarm);
        System.out.println("----------------------------");
    }
}
