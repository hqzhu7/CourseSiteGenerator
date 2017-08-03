/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.CSGApp;
import static csg.RecitationProp.MISSING;
import csg.data.CSGData;
import csg.data.ProjectData;
import csg.data.Student;
import csg.data.Team;
import csg.workspace.ProjectTab;
import csg.workspace.Workspace;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import properties_manager.PropertiesManager;
import jtps.jTPS;

/**
 *
 * @author Hengqi
 */
public class ProjectController {
    
    CSGApp app;
    boolean table1Clicked;
    boolean table2Clicked;
    public static jTPS proJ=new jTPS();
    
    public ProjectController(CSGApp initApp){
        app = initApp;
        table1Clicked = false;
        table2Clicked = false;
    }

    public void handleAdd() {
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TableView table = proTab.getTeamTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        
        if(!table1Clicked){
        String color1 = proTab.getCp1().getValue().toString().substring(2, 8);
        String color2 = proTab.getCp2().getValue().toString().substring(2, 8);
        String name = proTab.getNameText().getText();
        String link = proTab.getLinkText().getText();
        
        if(name.isEmpty()||link.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING), props.getProperty(MISSING));
        }else{
           Team team = new Team(name,color1,color2,link);  
           addTeamTrans add = new addTeamTrans(data,team);
           proJ.addTransaction(add);  
          markWorkAsEdited();
          proTab.getNameText().setText("");
          proTab.getLinkText().setText("");
          proTab.getCp1().setValue(Color.WHITE);
          proTab.getCp2().setValue(Color.WHITE);

       }
       }
        else if(table1Clicked){
         if(selectedItem != null){
            String color1 = proTab.getCp1().getValue().toString().substring(2, 8);
            String color2 = proTab.getCp2().getValue().toString().substring(2, 8);
            String name = proTab.getNameText().getText();
            String link = proTab.getLinkText().getText();
           Team team = (Team) selectedItem;  
           Team newTeam = new Team(name,color1,color2,link);
           editTeamTrans edit = new editTeamTrans(data,team,newTeam);
           proJ.addTransaction(edit);
            markWorkAsEdited();
          proTab.getNameText().setText("");
          proTab.getLinkText().setText("");
          proTab.getCp1().setValue(Color.WHITE);
          proTab.getCp2().setValue(Color.WHITE);

         }
        }
    }

    public void handleClear1() {
        table1Clicked = false;
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
        proTab.getAddUpdate1().setText("  Add  ");
        proTab.getNameText().setText("");
        proTab.getLinkText().setText("");
        proTab.getProjectPane().requestFocus();
    }
    
    public void handleClick1(){
        
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
         
        
        TextField nameText = proTab.getNameText();
        TextField linkText = proTab.getLinkText();
        ColorPicker cp1 = proTab.getCp1();
        ColorPicker cp2 = proTab.getCp2();
        
        TableView table = proTab.getTeamTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            table1Clicked= true;
            proTab.getAddUpdate1().setText("Update");
            Team team = (Team) selectedItem;
            nameText.setText(team.getName());
            linkText.setText(team.getLink());
            Color c = Color.web(team.getColor());
            cp1.setValue(c);
            Color b = Color.web(team.getTextColor());
            cp2.setValue(b);
        }
    }
    
    

    public void handleDelete() {
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
        
        
        TableView table = proTab.getTeamTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            Team team = (Team) selectedItem;
            delTeamTrans delTeam = new delTeamTrans(data,team);
            proJ.addTransaction(delTeam);
            markWorkAsEdited();
          proTab.getNameText().setText("");
          proTab.getLinkText().setText("");
          proTab.getCp1().setValue(Color.WHITE);
          proTab.getCp2().setValue(Color.WHITE);
        }
    }
    
     private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }

    public void handleAdd1() {
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
       
        
        if(!table2Clicked){
        String first =  proTab.getFirstText().getText();
        String last = proTab.getLastText().getText();
        String role = proTab.getRoleText().getText();
        Team team =(Team) proTab.getTeamBox().getValue();
        
        if(first.isEmpty()||last.isEmpty()||role.isEmpty()||team==null){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING), props.getProperty(MISSING));
        }else{
            String teamname = team.getName();
            Student stu = new Student(first,last,teamname,role);
            addStuTrans add = new addStuTrans(data,stu);
            proJ.addTransaction(add);
        }
        markWorkAsEdited();
        proTab.getFirstText().setText("");
        proTab.getLastText().setText("");
        proTab.getRoleText().setText("");
        proTab.getTeamBox().setValue(null);
        proTab.getTeamBox().setPromptText("");
        proTab.getProjectPane().requestFocus();
        }
        else if(table2Clicked){
            TableView table = proTab.getStudentTable();
            Object selectedItem = table.getSelectionModel().getSelectedItem();
            
            if(selectedItem!=null){
            Student stu = (Student) selectedItem;
            String first =  proTab.getFirstText().getText();
            String last = proTab.getLastText().getText();
            String role = proTab.getRoleText().getText();
            String teamName = "";
            if( proTab.getTeamBox().getValue()==null)
               teamName = stu.getTeam();
            else
               teamName = ((Team) proTab.getTeamBox().getSelectionModel().getSelectedItem()).getName();
             
            if(first.isEmpty()||last.isEmpty()||role.isEmpty()){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING), props.getProperty(MISSING));    
            }else{
                Student newStu = new Student(first,last,teamName,role);
                editStuTrans editStu = new editStuTrans(data,stu,newStu);
                proJ.addTransaction(editStu);
                markWorkAsEdited();
                proTab.getFirstText().setText("");
                proTab.getLastText().setText("");
                proTab.getRoleText().setText("");
                proTab.getTeamBox().setValue(null);
                proTab.getTeamBox().setPromptText("");
                proTab.getProjectPane().requestFocus();
            }

            }
        }
        
        
    }

    public void handleDelete1() {
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
        
        
        TableView table = proTab.getStudentTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            Student stu = (Student) selectedItem;
            delStuTrans del = new delStuTrans(data,stu);
            proJ.addTransaction(del);
          markWorkAsEdited();
          markWorkAsEdited();
          proTab.getFirstText().setText("");
          proTab.getLastText().setText("");
          proTab.getRoleText().setText("");
          proTab.getTeamBox().setValue(null);
          proTab.getTeamBox().setPromptText("");
        
        }
    }

    public void handleClick2() {
        
         Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
        
        TableView table = proTab.getStudentTable();
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            proTab.getAddUpdate2().setText("Update");
            table2Clicked = true;
          Student stu = (Student) selectedItem;
          proTab.getFirstText().setText(stu.getFirst());
          proTab.getLastText().setText(stu.getLast());
          proTab.getRoleText().setText(stu.getRole());
          proTab.getTeamBox().setValue(null);
          proTab.getTeamBox().setPromptText(stu.getTeam());
        
        }
    }

    public void handleClear2() {
        table2Clicked =false;
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        ProjectTab proTab = workspace.getProjectTab();
        CSGData datas = (CSGData) app.getDataComponent();
        ProjectData data = datas.getProData();
        proTab.getAddUpdate2().setText("  Add  ");
         proTab.getFirstText().setText("");
          proTab.getLastText().setText("");
          proTab.getTeamBox().setValue(null);
          proTab.getTeamBox().setPromptText("");
          proTab.getRoleText().setText("");
          proTab.getProjectPane().requestFocus();
          
          
          
    }
    
    
    
}
