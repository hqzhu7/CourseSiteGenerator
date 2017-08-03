/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import jtps.jTPS_Transaction;
import csg.CSGApp;
import csg.data.CSGData;
import csg.data.ReciData;
import csg.data.Recitation;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.workspace.RecitationTab;
import csg.workspace.TaTab;
import csg.workspace.Workspace;

/**
 *
 * @author moham_000
 */
public class deleteTA  implements jTPS_Transaction{
       CSGApp app;
       TAController controller;
       Object selectedItem;
       TaTab workspace;
       String email;
       String name;
       ArrayList<StringProperty> list;
    deleteTA(CSGApp app,TAController controller,Object selectedItem, TaTab workspace){
        this.app=app;
        this.controller=controller;
        this.selectedItem=selectedItem;
        this.workspace=workspace;
       // this.selectedItem=selectedItem
    }
    @Override
    public void doTransaction() {
       // GET THE TA AND REMOVE IT
                TeachingAssistant ta = (TeachingAssistant)selectedItem;
                String taName = ta.getName();
                this.name=ta.getName();
                this.email=ta.getEmail();
                CSGData csgData =  (CSGData) app.getDataComponent();
            TAData data = csgData.getTaData();
            ReciData reciData = csgData.getRecitationData();
                data.removeTA(taName);
                ArrayList<StringProperty> list=new ArrayList<StringProperty>();
                // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
                HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();
                for (Label label : labels.values()) {
                    if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                        data.removeTAFromCell(label.textProperty(), taName);
                        list.add(label.textProperty());
                    }
                }
                this.list=list;
                
                for(Recitation r :reciData.getRecitations()){
                    if(r.getTa1().equals(taName))
                        r.setTa1("TBA");
                    if(r.getTa2().equals(taName))
                       r.setTa2("TBA");
                }
                Workspace workspace = (Workspace) app.getWorkspaceComponent();
                RecitationTab reciTab = workspace.getRecitationTab();
                reciTab.getReciTable().refresh();
                // WE'VE CHANGED STUFF
                controller.markWorkAsEdited();
    }

    @Override
    public void undoTransaction() {
         CSGData csgData =  (CSGData) app.getDataComponent();
            TAData data = csgData.getTaData();
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
        data.addTA(name, email);
        for(int i=0;i<list.size();i++){
            list.get(i).setValue(list.get(i).getValue()+(list.get(i).getValue().equals("")?name:"\n"+name));
        }
        ReciData reciData = csgData.getRecitationData();
        for(Recitation r :reciData.getRecitations()){
                    if(r.getTa1().equals("TBA"))
                        r.setTa1(ta.getName());
                    if(r.getTa2().equals("TBA"))
                       r.setTa2(ta.getName());
                }
                Workspace workspace = (Workspace) app.getWorkspaceComponent();
                RecitationTab reciTab = workspace.getRecitationTab();
                reciTab.getReciTable().refresh();
        
    }
    
}
