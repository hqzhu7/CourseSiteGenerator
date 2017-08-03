/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.CSGApp;
import csg.data.ReciData;
import csg.data.Recitation;
import csg.workspace.RecitationTab;
import jtps.jTPS_Transaction;

/**
 *
 * @author Hengqi
 */
public class delReciTrans implements jTPS_Transaction{
    
    ReciData data;
 
    Object selectedItem;
    RecitationTab workspace;
    
    String section ;
    String instructor ;
    String dayTime ;
    String loc ;
    String ta1 ;
    String ta2 ;
    
    public delReciTrans(ReciData data,Object selectedItem, RecitationTab workspace){
        this.data = data;
     
        this.selectedItem = selectedItem;
        this.workspace = workspace;

    }
    
    
    @Override
    public void doTransaction() {
        Recitation reci = (Recitation) selectedItem;
        
        section = reci.getSection();
        instructor = reci.getInstructor();
        dayTime = reci.getDayTime();
        loc = reci.getLocation();
        ta1 = reci.getTa1();
        ta2 = reci.getTa2();
        
        data.removeRec(reci.getSection());

    }

    @Override
    public void undoTransaction() {
        data.getRecitations().add(new Recitation(section,instructor,dayTime,loc,ta1,ta2));
    }
    
}
