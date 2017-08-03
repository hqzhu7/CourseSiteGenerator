/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.data.ReciData;
import csg.data.Recitation;
import csg.workspace.RecitationTab;
import jtps.jTPS_Transaction;

/**
 *
 * @author Hengqi
 */
public class editReciTrans implements jTPS_Transaction{
    
    ReciData data;
 
    Object selectedItem;
    Recitation old; 
    Recitation newReci;
    RecitationTab workspace;
    
    String section ;
    String instructor ;
    String dayTime ;
    String loc ;
    String ta1 ;
    String ta2 ;
    
    public editReciTrans(ReciData data,Object selectedItem, RecitationTab workspace,
                            String section,String instructor,String dayTime,String loc ,String ta1 ,String ta2 ){
        this.data = data;
     
        this.selectedItem = selectedItem;
        this.workspace = workspace;
        this.section = section;
        this.instructor = instructor;
        this.dayTime = dayTime;
        this.loc = loc;
        this.ta1 = ta1;
        this.ta2 = ta2;
    }
    public editReciTrans(ReciData data,Recitation old, Recitation newReci){
        this.data = data;
        this.old = old;
        this.newReci = newReci;
    
    }
    
    
    @Override
    public void doTransaction() {
        data.getRecitations().remove(old);
        data.getRecitations().add(newReci);
    }

    @Override
    public void undoTransaction() {
        data.getRecitations().remove(newReci);
        data.getRecitations().add(old);
    }
    
}
