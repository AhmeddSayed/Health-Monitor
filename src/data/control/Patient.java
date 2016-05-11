/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

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
    String sex;
    int age;
    Date dateCreated, lastUpdated, lastAlarm;
    Float Temp;
    int BPM;
    String bloodType;

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
        this.dateCreated = dateAdded;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setLastAlarm(Date lastAlarm) {
        this.lastAlarm = lastAlarm;
    }

    public void printAll() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Rate: " + this.heartRate.toString());
        System.out.println("Temp: " + this.tempreature.toString());
        System.out.println("dateAdded: " + this.dateCreated.toString());
        System.out.println("last: " + this.lastUpdated);
        System.out.println("Alarm: " + this.lastAlarm);
        System.out.println("Sex: " + this.sex);
        System.out.println("age: " + this.age);
        System.out.println("blood: " + this.bloodType);
        System.out.println("----------------------------");
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setTemp(Float newTemp) {
        this.Temp = newTemp;
    }

    public void setBPM(int newBPM) {
        this.BPM = newBPM;
    }

    public Float getTemp() {
        return this.Temp;
    }

    public int getBPM() {
        return this.BPM;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getBloodType() {
        return this.bloodType;
    }

    public Date getLastModified() {
        return this.lastUpdated;
    }

    public Date getLastAlarmed() {
        return this.lastAlarm;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

}
