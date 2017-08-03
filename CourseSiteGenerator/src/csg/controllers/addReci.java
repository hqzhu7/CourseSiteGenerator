/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.data.ReciData;
import csg.data.Recitation;
import jtps.jTPS_Transaction;

/**
 *
 * @author Hengqi
 */
public class addReci implements jTPS_Transaction{
    
    ReciData reciData;
    
    String section;
    String instructor;
    String dayTime;
    String location;
    String ta1;
    String ta2;
    
    public addReci(String sec, String inst, String dt, String loc, String ta1, String ta2, ReciData data ){
        section = sec;
        instructor = inst;
        dayTime = dt;
        location = loc;
        this.ta1 = ta1;
        this.ta2 = ta2;
        reciData = data;  
    }
    
    

    @Override
    public void doTransaction() {
        reciData.getRecitations().add(new Recitation(section,instructor,dayTime,location,ta1,ta2));
    }

    @Override
    public void undoTransaction() {
        reciData.removeRec(section);
    }
    
}
