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
public class deleteScheTrans implements jTPS_Transaction{

    ScheData data;
    Schedule sche;
    
    public deleteScheTrans(Schedule sche,ScheData data){
        this.data= data;
        this.sche = sche;
    
    }
    
    @Override
    public void doTransaction() {
        data.getSchedules().remove(sche);
    }

    @Override
    public void undoTransaction() {
       data.getSchedules().add(sche);
    }
    
}
