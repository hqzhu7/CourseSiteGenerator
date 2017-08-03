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
public class editSche implements jTPS_Transaction {
    ScheData data;
    Schedule sche;
    Schedule newSche;
    
    public editSche(ScheData data,Schedule sche,Schedule newSche){
        this.data=data;
        this.sche=sche;
        this.newSche=newSche;
    }
    
    @Override
    public void doTransaction() {
        data.getSchedules().remove(sche);
        data.getSchedules().add(newSche);
    }

    @Override
    public void undoTransaction() {
        data.getSchedules().remove(newSche);
        data.getSchedules().add(sche);
    }
    
}
