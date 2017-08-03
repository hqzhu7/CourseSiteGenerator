/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CSGApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hengqi Zhu
 */
public class ScheData {
    
    CSGApp app;
     String startMonth;
     String endMonth;
     String startDay;
     String endDay;
    
    ObservableList<Schedule> schedules;
    
    public ScheData(CSGApp initApp){
        app = initApp;

        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        schedules = FXCollections.observableArrayList();
        

    }

    public ObservableList<Schedule> getSchedules() {
        return schedules;
    }
    
     public String getStartMonth() {
        return startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
    
    public void resetData(){
    
     startMonth="1";
      endMonth="12";
      startDay="1";
      endDay="31";
    
    schedules.clear();
    }
    
    public void removeSchedule(String title,String date){
        for(Schedule sche :schedules )
            if(sche.getDate().equals(date)&&sche.getTitle().equals(title)){
                schedules.remove(sche);
                return;
            }
    }
    
}
