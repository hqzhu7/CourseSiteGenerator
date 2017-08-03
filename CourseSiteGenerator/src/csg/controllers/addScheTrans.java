/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.data.ScheData;
import csg.data.Schedule;
import jtps.jTPS_Transaction;

/**
 *
 * @author Hengqi
 */
public class addScheTrans implements jTPS_Transaction{
    
    ScheData data;
    String type,date,title,topic,link,time,criteria;
    

    public addScheTrans(String type,String date,String title,String topic, 
            String link,String time,String criteria, ScheData data){
        this.type=type;
        this.date= date;
        this.title = title;
        this.topic=topic;
        this.link = link;
        this.time = time;
        this.criteria = criteria;
        this.data= data;
    
    }
    
    @Override
    public void doTransaction() {
        data.getSchedules().add(new Schedule(type,date,title,topic,link,time,criteria));
    }

    @Override
    public void undoTransaction() {
        data.removeSchedule(title, date);
    }
    
}
