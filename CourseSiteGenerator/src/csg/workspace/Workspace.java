/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CSGApp;
import djf.AppTemplate;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import javafx.scene.control.TabPane;
import static csg.controllers.ReciController.reciJ;
import static csg.controllers.TAController.TaJ;
import static csg.controllers.ScheController.scheJ;
import static csg.controllers.ProjectController.proJ;
import djf.ui.AppGUI;



/**
 *
 * @author Hengqi Zhu
 */
public class Workspace extends AppWorkspaceComponent {
    
     CSGApp app;
     CourseTab courseTab;
     TaTab taTab;
     RecitationTab recitationTab;
     ScheduleTab scheduleTab;
     ProjectTab projectTab;
    
     public Workspace(AppTemplate initApp){
     
        app=(CSGApp)initApp;
        workspace = new TabPane();
  
        courseTab = new CourseTab((CSGApp)initApp);
        taTab = new TaTab((CSGApp)initApp);
        recitationTab = new RecitationTab((CSGApp)initApp);
        scheduleTab = new ScheduleTab((CSGApp)initApp);
        projectTab = new ProjectTab((CSGApp)initApp);
        workspace.getTabs().add(courseTab);
        workspace.getTabs().add(taTab);
        workspace.getTabs().add(recitationTab);
        workspace.getTabs().add(scheduleTab);
        workspace.getTabs().add(projectTab);
        
        app.getGUI().getUndoButton().setOnAction(e->{
            if(workspace.getSelectionModel().getSelectedIndex()==1)
                TaJ.undoTransaction();
            else if (workspace.getSelectionModel().getSelectedIndex()==2)
                reciJ.undoTransaction();
            else if (workspace.getSelectionModel().getSelectedIndex()==3)
                scheJ.undoTransaction();
            else if(workspace.getSelectionModel().getSelectedIndex()==4)
                proJ.undoTransaction();
            
            AppGUI gui = app.getGUI();
            gui.getFileController().markAsEdited(gui);
            
        });
        
        app.getGUI().getRedoButton().setOnAction(e->{
            if (workspace.getSelectionModel().getSelectedIndex()==1)
                TaJ.doTransaction();
            else if (workspace.getSelectionModel().getSelectedIndex()==2)
                reciJ.doTransaction();
            else if (workspace.getSelectionModel().getSelectedIndex()==3)
                scheJ.doTransaction();
            else if(workspace.getSelectionModel().getSelectedIndex()==4)
                proJ.doTransaction();
            
            AppGUI gui = app.getGUI();
            gui.getFileController().markAsEdited(gui);
        });
 
     }
    
     
     
    public TaTab getTaTab(){
        return taTab;
    }
    
     public CourseTab getCourseTab(){
        return courseTab;
    }

    public RecitationTab getRecitationTab() {
        return recitationTab;
    }

    public ScheduleTab getScheduleTab() {
        return scheduleTab;
    }

    public ProjectTab getProjectTab() {
        return projectTab;
    }
    
     
     
     
    @Override
    public void resetWorkspace() {
        taTab.resetWorkspace();
        //recitationTab.resetWorkspace();
        //scheduleTab.resetWorkspace();
       // projectTab.resetWorkspace();
        
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        taTab.reloadWorkspace(dataComponent);
        
    }
    
}
