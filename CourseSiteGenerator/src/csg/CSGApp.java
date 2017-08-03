/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import csg.data.CSGData;
import csg.files.CSGFiles;
import csg.style.CSGStyles;
import csg.workspace.Workspace;
import djf.AppTemplate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Hengqi Zhu
 */
import java.util.Locale;
import static javafx.application.Application.launch;
public class CSGApp extends AppTemplate {
    
    
     @Override
    public void buildAppComponentsHook() {
        // CONSTRUCT ALL FOUR COMPONENTS. NOTE THAT FOR THIS APP
        // THE WORKSPACE NEEDS THE DATA COMPONENT TO EXIST ALREADY
        // WHEN IT IS CONSTRUCTED, SO BE CAREFUL OF THE ORDER
        dataComponent = new CSGData(this);
        workspaceComponent = new Workspace(this);
        fileComponent = new CSGFiles(this);
        styleComponent = new CSGStyles(this);
    }
   
      public static void main(String[] args) {
        Locale.setDefault(Locale.US);
	launch(args);
    }    
        
    

   
    
}
