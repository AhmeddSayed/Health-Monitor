/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.control;

import java.text.DateFormat;

/**
 *
 * @author Ahmed
 */
public class Patient {

    int id;
    String name;
    Float rate;
    Float tempreature;
    DateFormat dateFormat;

    public boolean create() {
        /* maybe would be needed for date-added field 
        
         dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
         return dateFormat.format(date);
         */
        return false;
    }
}
