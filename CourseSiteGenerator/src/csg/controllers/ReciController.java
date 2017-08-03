/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.CSGApp;
import static csg.RecitationProp.MISSING;

import csg.data.CSGData;
import csg.data.ReciData;
import csg.data.Recitation;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.workspace.RecitationTab;
import csg.workspace.Workspace;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author Hengqi Zhu
 */
public class ReciController {
  
    CSGApp app;
    public static jTPS reciJ=new jTPS();
    boolean tableClicked;
 

    /**
     * Constructor, note that the app must already be constructed.
     */
    public ReciController(CSGApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        tableClicked = false;
 
    }
    
    public void handleAddRecitation(){
        
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        RecitationTab reciTab = workspace.getRecitationTab();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData datas = (CSGData) app.getDataComponent();
        ReciData reciData = datas.getRecitationData();
        
        //add ta
    if(!tableClicked){
        String section = reciTab.getSectionText().getText();
        String instructor = reciTab.getInstorText().getText();
        String dayTime = reciTab.getDayTimeText().getText();
        String loc = reciTab.getLocationText().getText();
        TeachingAssistant ta1 =  (TeachingAssistant)  reciTab.getTaBox1().getSelectionModel().getSelectedItem();
        
        TeachingAssistant ta2 =  (TeachingAssistant)  reciTab.getTaBox2().getSelectionModel().getSelectedItem();
        
        if(!section.equals("")&&!instructor.equals("")&&!dayTime.equals("")&&!loc.equals("")&&ta1!=null&&ta2!=null){
            
            String ta1Name = ta1.getName();
            String ta2Name = ta2.getName();
            
            addReci addReciTran = new addReci(section,instructor,dayTime,loc,ta1Name,ta2Name,reciData);
            reciJ.addTransaction(addReciTran);
            
            reciTab.getSectionText().setText("");
            reciTab.getInstorText().setText("");
            reciTab.getDayTimeText().setText("");
            reciTab.getLocationText().setText("");
            markWorkAsEdited();
        }else{AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING), props.getProperty(MISSING));       
        }
       
        }
    
    //edit ta
    else if(tableClicked){
        
        TableView table = reciTab.getReciTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Recitation oldReci = (Recitation) selectedItem;
        
        String section = reciTab.getSectionText().getText();
        String instructor = reciTab.getInstorText().getText();
        String dayTime = reciTab.getDayTimeText().getText();
        String loc = reciTab.getLocationText().getText();
        String ta1Name,ta2Name;
        TeachingAssistant ta1 =  (TeachingAssistant)  reciTab.getTaBox1().getSelectionModel().getSelectedItem();
        if(ta1 ==null)  
            ta1Name = oldReci.getTa1();
        else
            ta1Name = ta1.getName();
        
        TeachingAssistant ta2 =  (TeachingAssistant)  reciTab.getTaBox2().getSelectionModel().getSelectedItem();
        if(ta2==null)
            ta2Name = oldReci.getTa2();
        else
            ta2Name = ta2.getName();
        
        Recitation newReci = new Recitation(section,instructor,dayTime,loc,ta1Name,ta2Name);
        
        
        editReciTrans editReci = new editReciTrans(reciData,oldReci,newReci);
        reciJ.addTransaction(editReci);
        reciTab.getSectionText().setText("");
        reciTab.getInstorText().setText("");
        reciTab.getDayTimeText().setText("");
        reciTab.getLocationText().setText("");
        reciTab.getTaBox1().setPromptText("");
        reciTab.getTaBox2().setPromptText("");
        reciTab.getSectionText().requestFocus();
        markWorkAsEdited();
        
        }
        }
    }

    public void handleClick() {
     
       
       Workspace workspace = (Workspace) app.getWorkspaceComponent();
        RecitationTab reciTab = workspace.getRecitationTab();
        
        CSGData datas = (CSGData) app.getDataComponent();
      
        
        TextField sectionT = reciTab.getSectionText();
        TextField instructorT = reciTab.getInstorText();
        TextField dayTimeT = reciTab.getDayTimeText();
        TextField locT = reciTab.getLocationText();
        ComboBox ta1Box = reciTab.getTaBox1();
        ComboBox ta2Box = reciTab.getTaBox2();
        
        TableView table = reciTab.getReciTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
             reciTab.getAddButton().setText("Update");
              tableClicked = true;
            Recitation reci = (Recitation)selectedItem;
            String section = reci.getSection();
            String instructor = reci.getInstructor();
            String dayTime = reci.getDayTime();
            String loc = reci.getLocation();
            String ta1 = reci.getTa1();
            String ta2 = reci.getTa2();
            
            sectionT.setText(section);
            instructorT.setText(instructor);
            dayTimeT.setText(dayTime);
            locT.setText(loc);
            ta1Box.setPromptText(ta1);
            ta2Box.setPromptText(ta2);
        }
        
       
    }

    public void handleClear() {
       tableClicked = false;
       
       Workspace workspace = (Workspace) app.getWorkspaceComponent();
        RecitationTab reciTab = workspace.getRecitationTab();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData datas = (CSGData) app.getDataComponent();
        
        reciTab.getAddButton().setText("  Add  ");
        TextField sectionT = reciTab.getSectionText();
        TextField instructorT = reciTab.getInstorText();
        TextField dayTimeT = reciTab.getDayTimeText();
        TextField locT = reciTab.getLocationText();
        ComboBox ta1Box = reciTab.getTaBox1();
        ComboBox ta2Box = reciTab.getTaBox2();
        
        sectionT.setText("");
        instructorT.setText("");
        dayTimeT.setText("");
        locT.setText("");
        ta1Box.setPromptText("");
        ta2Box.setPromptText("");
            
        sectionT.requestFocus();
    }

    public void handleDelete() {
         Workspace workspace = (Workspace) app.getWorkspaceComponent();
        RecitationTab reciTab = workspace.getRecitationTab();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData datas = (CSGData) app.getDataComponent();
        ReciData reciData = datas.getRecitationData();
        
        TextField sectionT = reciTab.getSectionText();
        TextField instructorT = reciTab.getInstorText();
        TextField dayTimeT = reciTab.getDayTimeText();
        TextField locT = reciTab.getLocationText();
        ComboBox ta1Box = reciTab.getTaBox1();
        ComboBox ta2Box = reciTab.getTaBox2();
        
        TableView table = reciTab.getReciTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            delReciTrans delReci = new delReciTrans( reciData, selectedItem,  reciTab);
            reciJ.addTransaction(delReci);
            sectionT.setText("");
            instructorT.setText("");
            dayTimeT.setText("");
            locT.setText("");
            ta1Box.setPromptText("");
            ta2Box.setPromptText("");
            
            sectionT.requestFocus();
            markWorkAsEdited();
        }
        
        
    }
    
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }
    
    
    
}
