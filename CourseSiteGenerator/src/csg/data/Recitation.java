/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Hengqi Zhu
 */
public class Recitation {
    
    private final  StringProperty section;
    private final  StringProperty instructor;
    private final  StringProperty dayTime;
    private final  StringProperty location;
    private  StringProperty ta1;
    private  StringProperty ta2;
    
    public Recitation(String sec, String ins, String day, String loc, String ta1, String ta2){
        section = new SimpleStringProperty(sec);
        instructor = new SimpleStringProperty(ins);
        this.dayTime = new SimpleStringProperty(day);
        location = new SimpleStringProperty(loc);
        this.ta1 = new SimpleStringProperty(ta1);
        this.ta2 = new SimpleStringProperty(ta2);
    }
    
    
    public String getSection(){
        return section.get();
    }
    
    public String getInstructor(){
        return instructor.get();
    }
    
    public String getDayTime(){
        return dayTime.get();   
    }
    
    public String getLocation(){
        return location.get();
    }
    
    public String getTa1(){
        return ta1.get();
    }
    
    public void setTa1(String str){
        ta1 = new SimpleStringProperty(str);
    }
    
    public String getTa2(){
        return ta2.get();
    }

    public void setTa2(String tba) {
        ta2 = new SimpleStringProperty(tba);
    }
    
}
