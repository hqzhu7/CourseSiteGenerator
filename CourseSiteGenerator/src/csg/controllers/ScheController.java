/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.CSGApp;
import static csg.RecitationProp.MISSING;
import csg.data.CSGData;
import csg.data.ScheData;
import csg.data.Schedule;
import csg.workspace.ScheduleTab;
import csg.workspace.Workspace;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jtps.jTPS;
import properties_manager.PropertiesManager;

/**
 *
 * @author Hengqi
 */
public class ScheController {
    CSGApp app;
    public static jTPS scheJ=new jTPS();
    boolean tableClicked;
    
    public ScheController(CSGApp initApp){
        app = initApp;
        tableClicked = false;
    }

    public void handleDate() {
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ScheduleTab scheTab = workspace.getScheduleTab();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData datas = (CSGData) app.getDataComponent();
        ScheData reciData = datas.getSechData();
        
        String endDate = "";
        String startDate="";
        if(scheTab.getStartDate().getValue()!=null&&scheTab.getEndDate().getValue()!=null){
            startDate = scheTab.getStartDate().getValue().toString();
            String[] parts1 = startDate.split("-");
            endDate = scheTab.getEndDate().getValue().toString();
            String[] parts2 = endDate.split("-");
            
            
            if(scheTab.getStartDate().getValue().getDayOfYear()>=scheTab.getEndDate().getValue().getDayOfYear()){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("time wrong", "check time");
            }else{
                reciData.setStartMonth(parts1[1]);
                reciData.setStartDay(parts1[2]);
                reciData.setEndMonth(parts2[1]);
                reciData.setEndDay(parts2[2]);
            }
        }else{
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING), props.getProperty(MISSING));
            
        }
        
       
        
        
      
    }

    public void handleAdd() {
         Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ScheduleTab scheTab = workspace.getScheduleTab();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData datas = (CSGData) app.getDataComponent();
        ScheData scheData = datas.getSechData();
        
        if(!tableClicked){
        ComboBox type = scheTab.getTypeBox();
        DatePicker date = scheTab.getDatePicker();
        TextField time = scheTab.getTimeText();
        TextField title = scheTab.getTitleText();
        TextField topic = scheTab.getTopicText();
        TextField link = scheTab.getLinkText();
        TextField criteria = scheTab.getCriteriaText();
        
        int month = 1;
        int day = 1;
       
        String dateValue = "";
        if(date.getValue()!=null){
            month = date.getValue().getMonthValue();
            day = date.getValue().getDayOfMonth();
            dateValue = month+"/"+day;
          
        }
        
        String typeValue = "";
        if(type.getValue()!=null)
            typeValue = (String)type.getValue();
       
           
        
        if(typeValue.equals("holidays")){
            String titleVaue;
            String linkValue;
            
            if(title.getText().equals("")||link.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               linkValue = link.getText();
               addScheTrans add = new addScheTrans("holidays",dateValue,titleVaue,"",linkValue,"","",scheData);
               scheJ.addTransaction(add);
            }
        }
        else if(typeValue.equals("lectures")) {
            String titleVaue;
            String linkValue;
            String topicValue;
            
            if(title.getText().equals("")||link.getText().equals("")||topic.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               linkValue = link.getText();
               topicValue = topic.getText();
               addScheTrans add = new addScheTrans("lectures",dateValue,titleVaue,topicValue,linkValue,"","",scheData);
               scheJ.addTransaction(add);
            }
        }
        else if(typeValue.equals("recitations")){
            String titleVaue;
            String topicValue;
            
            if(title.getText().equals("")||topic.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               topicValue = topic.getText();
               addScheTrans add = new addScheTrans("recitations",dateValue,titleVaue,topicValue,"","","",scheData);
               scheJ.addTransaction(add);
            }
        }
        else if(typeValue.equals("hws")){
            String titleVaue;
            String topicValue;
            String linkValue;
            String criteriaValue;
            String timeValue;
            
            if(title.getText().equals("")||topic.getText().equals("")||link.getText().equals("")||criteria.getText().equals("")||time.getText().equals("")){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               topicValue = topic.getText();
               linkValue = link.getText();
               criteriaValue = criteria.getText();
               timeValue= time.getText();
               addScheTrans add = new addScheTrans("hws",dateValue,titleVaue,topicValue,linkValue,timeValue,criteriaValue,scheData);
               scheJ.addTransaction(add);
            }  
        }
        else if(typeValue.equals("references")){
            String titleVaue;
            String topicValue;
            String linkValue;

            
            if(title.getText().equals("")||topic.getText().equals("")||link.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               topicValue = topic.getText();
               linkValue = link.getText();

               addScheTrans add = new addScheTrans("references",dateValue,titleVaue,topicValue,linkValue,"","",scheData);
               scheJ.addTransaction(add);
            }
        }
        
        scheTab.getTypeBox().setPromptText("");
        scheTab.getDatePicker().setValue(null);
        scheTab.getTimeText().setText("");
        scheTab.getTopicText().setText("");
        scheTab.getLinkText().setText("");
        scheTab.getCriteriaText().setText("");
        scheTab.getTitleText().setText("");
        markWorkAsEdited();
        }
        else if(tableClicked){
        ComboBox type = scheTab.getTypeBox();
        DatePicker date = scheTab.getDatePicker();
        TextField time = scheTab.getTimeText();
        TextField title = scheTab.getTitleText();
        TextField topic = scheTab.getTopicText();
        TextField link = scheTab.getLinkText();
        TextField criteria = scheTab.getCriteriaText();
        TableView table = scheTab.getScheTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Schedule sche = (Schedule)selectedItem; 
        int month = 1;
        int day = 1;
        String dateValue = "";
        if(date.getValue()!=null){
            month = date.getValue().getMonthValue();
            day = date.getValue().getDayOfMonth();
            dateValue = month+"/"+day;
        }
        String typeValue = "";
        if(type.getValue()!=null)
            typeValue = (String)type.getValue();
        else
            typeValue = sche.getType();
        
         
         if(typeValue.equals("holidays")){
            String titleVaue;
            String linkValue;
            
            if(title.getText().equals("")||link.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               linkValue = link.getText();
               Schedule newSche = new Schedule("holidays",dateValue,titleVaue,"",linkValue,"","");
               editSche edit = new editSche(scheData,sche,newSche);
               scheJ.addTransaction(edit);
            }
        }
        else if(typeValue.equals("lectures")) {
            String titleVaue;
            String linkValue;
            String topicValue;
            
            if(title.getText().equals("")||link.getText().equals("")||topic.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               linkValue = link.getText();
               topicValue = topic.getText();
               Schedule newSche = new Schedule("lectures",dateValue,titleVaue,topicValue,linkValue,"","");
               editSche edit = new editSche(scheData,sche,newSche);
               scheJ.addTransaction(edit);
            }
        }
        else if(typeValue.equals("recitations")){
            String titleVaue;
            String topicValue;
            
            if(title.getText().equals("")||topic.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               topicValue = topic.getText();
               //addScheTrans add = new addScheTrans("recitations",dateValue,titleVaue,topicValue,"","","",scheData);
               Schedule newSche = new Schedule("recitations",dateValue,titleVaue,topicValue,"","","");
               editSche edit = new editSche(scheData,sche,newSche);
               scheJ.addTransaction(edit);
            }
        }
        else if(typeValue.equals("hws")){
            String titleVaue;
            String topicValue;
            String linkValue;
            String criteriaValue;
            String timeValue;
            
            if(title.getText().equals("")||topic.getText().equals("")||link.getText().equals("")||criteria.getText().equals("")||time.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               topicValue = topic.getText();
               linkValue = link.getText();
               criteriaValue = criteria.getText();
               timeValue= time.getText();
               //addScheTrans add = new addScheTrans("hws",dateValue,titleVaue,topicValue,linkValue,timeValue,criteriaValue,scheData);
               Schedule newSche = new Schedule("hws",dateValue,titleVaue,topicValue,linkValue,timeValue,criteriaValue);
               editSche edit = new editSche(scheData,sche,newSche);
               scheJ.addTransaction(edit);
            }  
        }
        else if(typeValue.equals("references")){
            String titleVaue;
            String topicValue;
            String linkValue;

            
            if(title.getText().equals("")||topic.getText().equals("")||link.getText().equals("")||date.getValue()==null){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING)); 
            }else{
               titleVaue = title.getText();
               topicValue = topic.getText();
               linkValue = link.getText();

               //addScheTrans add = new addScheTrans("references",dateValue,titleVaue,topicValue,linkValue,"","",scheData);
               Schedule newSche = new Schedule("references",dateValue,titleVaue,topicValue,linkValue,"","");
               editSche edit = new editSche(scheData,sche,newSche);
               scheJ.addTransaction(edit);
            }
        }    
         //scheData.getSchedules().sorted();
         
        } 
        scheTab.getTypeBox().setPromptText("");
        scheTab.getDatePicker().setValue(null);
        scheTab.getTimeText().setText("");
        scheTab.getTopicText().setText("");
        scheTab.getLinkText().setText("");
        scheTab.getCriteriaText().setText("");
        scheTab.getTitleText().setText("");
        markWorkAsEdited();   
            
        }
    }
    
    public void handleclick(){
      
        Workspace workspace  = (Workspace) app.getWorkspaceComponent();
        ScheduleTab scheTab = workspace.getScheduleTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ScheData scheData = datas.getSechData();
        
        
        
        ComboBox type = scheTab.getTypeBox();
        DatePicker date = scheTab.getDatePicker();
        TextField time = scheTab.getTimeText();
        TextField title = scheTab.getTitleText();
        TextField topic = scheTab.getTopicText();
        TextField link = scheTab.getLinkText();
        TextField criteria = scheTab.getCriteriaText();
        
        TableView table = scheTab.getScheTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            scheTab.getAddButton().setText("Update");
              tableClicked = true;
            Schedule sche = (Schedule)selectedItem;
            String typeValue = sche.getType();
            String dateValue = sche.getDate();
            String timeValue = sche.getTime();
            String titleValue  = sche.getTitle();
            String topicValue = sche.getTopic();
            String linkValue = sche.getLink();
            String criteriaValue = sche.getCriteria();
        
            type.setPromptText(typeValue);
           
            String month;
            if(Integer.parseInt(sche.getMonth())<10)
                month = "0"+sche.getMonth();
            else
                month = sche.getMonth();
            
            String day;
            if(Integer.parseInt(sche.getDay())<10)
                day = "0"+sche.getDay();
            else
                day = sche.getDay();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.US); 
            LocalDate date123 = LocalDate.parse("2017-"+month+"-"+day, formatter);
            date.setValue(date123);
            time.setText(timeValue);
            title.setText(titleValue);
            topic.setText(topicValue);
            link.setText(linkValue);
            criteria.setText(criteriaValue);
        }    
    }

    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }

    public void handleDelete() {
      
        Workspace workspace  = (Workspace) app.getWorkspaceComponent();
        ScheduleTab scheTab = workspace.getScheduleTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ScheData scheData = datas.getSechData(); 
        
        TableView table = scheTab.getScheTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            Schedule sche = (Schedule)selectedItem;
            deleteScheTrans delete = new deleteScheTrans(sche,scheData);
            scheJ.addTransaction(delete);
            //scheData.getSchedules().remove(sche);
            
            scheTab.getTypeBox().setPromptText("");
            scheTab.getDatePicker().setValue(null);
            scheTab.getTimeText().setText("");
            scheTab.getTopicText().setText("");
            scheTab.getLinkText().setText("");
            scheTab.getCriteriaText().setText("");
            scheTab.getTitleText().setText("");
            markWorkAsEdited();
         }
        
        
        
        
        
    }

    public void handleClear() {
        tableClicked = false;
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ScheduleTab scheTab = workspace.getScheduleTab();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CSGData datas = (CSGData) app.getDataComponent();
        ScheData scheData = datas.getSechData();
        scheTab.getAddButton().setText("  Add  ");
        scheTab.getTypeBox().setPromptText("");
        scheTab.getDatePicker().setValue(null);
        scheTab.getTimeText().setText("");
        scheTab.getTopicText().setText("");
        scheTab.getLinkText().setText("");
        scheTab.getCriteriaText().setText("");
        scheTab.getTitleText().setText("");
        markWorkAsEdited(); 
        
        scheTab.getSchedulePane().requestFocus();
        
        
    }
    
    
    
    
}
