/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.CSGApp;
import static csg.RecitationProp.MISSING;
import csg.data.CSGData;
import csg.data.Course;
import csg.data.CourseData;
import csg.workspace.CourseTab;
import csg.workspace.Workspace;
import static djf.settings.AppStartupConstants.PATH_IMAGES;
import static djf.settings.AppStartupConstants.PATH_WORK;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;

/**
 *
 * @author Hengqi
 */
public class CourseController {
    
    CSGApp app;
    
    public CourseController(CSGApp initApp){
        app = initApp;
    }

    public void handleChange1() throws IOException {
       PropertiesManager props = PropertiesManager.getPropertiesManager();
	
        // AND NOW ASK THE USER FOR THE FILE TO OPEN
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_IMAGES));
        fc.setTitle(props.getProperty("choose you picture"));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
        System.out.println(selectedFile.toURI());
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        CourseTab courseTab = workspace.getCourseTab();
        
        ImageView imageView = new ImageView(new Image(selectedFile.toURI().toString()));
        courseTab.getSchoolContent().setGraphic(imageView);
        
        Files.copy(selectedFile.toPath(), Paths.get("../cse308/public_html/images/SBUDarkRedShieldLogo.png"),StandardCopyOption.REPLACE_EXISTING);  
    }
    
    public void handleChange2() throws IOException {
       PropertiesManager props = PropertiesManager.getPropertiesManager();
	
        // AND NOW ASK THE USER FOR THE FILE TO OPEN
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_IMAGES));
        fc.setTitle(props.getProperty("choose you picture"));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
        System.out.println(selectedFile.toURI());
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        CourseTab courseTab = workspace.getCourseTab();
        
        ImageView imageView = new ImageView(new Image(selectedFile.toURI().toString()));
        courseTab.getLeftContent().setGraphic(imageView);
        
        Files.copy(selectedFile.toPath(), Paths.get("../cse308/public_html/images/SBUWhiteShieldLogo.jpg"),StandardCopyOption.REPLACE_EXISTING);  
    }
    
    public void handleChange3() throws IOException {
       PropertiesManager props = PropertiesManager.getPropertiesManager();
	
        // AND NOW ASK THE USER FOR THE FILE TO OPEN
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_IMAGES));
        fc.setTitle(props.getProperty("choose you picture"));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
        System.out.println(selectedFile.toURI());
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        CourseTab courseTab = workspace.getCourseTab();
        
        ImageView imageView = new ImageView(new Image(selectedFile.toURI().toString()));
        courseTab.getRightContent().setGraphic(imageView);
        
        Files.copy(selectedFile.toPath(), Paths.get("../cse308/public_html/images/CSLogo"),StandardCopyOption.REPLACE_EXISTING);  
    }

    public void handleChangeEx() {
        AppGUI gui = app.getGUI();
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        CourseTab ct = workspace.getCourseTab();
        CSGData datas = (CSGData) app.getDataComponent();
        CourseData data = datas.getCourseData();
        
        ComboBox sub = ct.getSubjectBox();
        ComboBox number = ct.getNumberBox();
        ComboBox se = ct.getSemesterBox();
        ComboBox year=ct.getYearBox();
        TextField tit  = ct.getTitleText();
        TextField name = ct.getInstrNameText();
        TextField home = ct.getInstrHomeText();
        String subb,num,see,yearr,titt,namee,homee;
        
        Course c =data.getCourse();
        if(c==null){
            if(sub.getSelectionModel().getSelectedItem()==null||number.getSelectionModel().getSelectedItem()==null||
                  se.getSelectionModel().getSelectedItem()==null||year.getSelectionModel().getSelectedItem()==null||
                     tit.getText().isEmpty()||name.getText().isEmpty()||home.getText().isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("information missing", "missing some course information");
            }
            else{
            subb = sub.getSelectionModel().getSelectedItem().toString();
            num = number.getSelectionModel().getSelectedItem().toString();
            see = se.getSelectionModel().getSelectedItem().toString();
            yearr = year.getSelectionModel().getSelectedItem().toString();
            titt=tit.getText();namee=name.getText();homee=home.getText();
            data.setCourse(new Course(subb,num,see,yearr,titt,namee,homee));
            DirectoryChooser dc = new DirectoryChooser();
       dc.setInitialDirectory(new File(PATH_WORK));         
       dc.setTitle("Select or new a folder");
       
       File selectedDir = dc.showDialog(app.getGUI().getWindow());
       ct.getDirAddress().setText(selectedDir.toString());
            }
        
        }else{
            if(sub.getSelectionModel().getSelectedItem()==null)
                subb = c.getSubject();
            else
                subb = sub.getSelectionModel().getSelectedItem().toString();
            if(number.getSelectionModel().getSelectedItem()==null)
                num = c.getSubject();
            else
                num = number.getSelectionModel().getSelectedItem().toString();
            if(se.getSelectionModel().getSelectedItem()==null)
                see = c.getSubject();
            else
                see = se.getSelectionModel().getSelectedItem().toString();
            if(year.getSelectionModel().getSelectedItem()==null)
                yearr = c.getSubject();
            else
                yearr = year.getSelectionModel().getSelectedItem().toString();
            if(tit.getText().isEmpty()||name.getText().isEmpty()||home.getText().isEmpty()){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("information missing", "missing information");
            }else{
                titt=tit.getText();namee=name.getText();homee=home.getText();
                data.setCourse(new Course(subb,num,see,yearr,titt,namee,homee));
                DirectoryChooser dc = new DirectoryChooser();
       dc.setInitialDirectory(new File(PATH_WORK));         
       dc.setTitle("Select or new a folder");
       
       File selectedDir = dc.showDialog(app.getGUI().getWindow());
       if(selectedDir!=null){
            ct.getDirAddress().setText(selectedDir.toString());
            gui.getFileController().setExportDir(selectedDir);
       }
            
            }
             
        }
        
        
        
        
        
        
        markWorkAsEdited();
       
        
    }
    
    
    
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }

    public void handleSelectTemplate() {
       DirectoryChooser dc = new DirectoryChooser();
       File selectedDir = dc.showDialog(app.getGUI().getWindow());
       Workspace workspace = (Workspace) app.getWorkspaceComponent();
        CourseTab ct = workspace.getCourseTab();
        
       if(selectedDir != null){
           System.out.println(selectedDir.toString());
           if(new File(selectedDir+"\\index.html").exists())
           {System.out.println("dsfs");
            ct.getTemplateList().get(0).setChecked(Boolean.TRUE);
           }
       }
    }

}
