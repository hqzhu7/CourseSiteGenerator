/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.workspace.RecitationTab;
import csg.workspace.Workspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;

/**
 *
 * @author Hengqi Zhu
 */
public class RecitationStyle extends AppStyleComponent{
    private AppTemplate app;
    public static String RECI_INFO = "course_info";
    public static String TOPLABELS = "top_labels";
    public static String BUTTONS = "small_button";
    public static String CLASS_TA_TABLE_COLUMN_HEADER = "ta_table_column_header";
    public static String SMALLTABLE = "small_table";
    public static String RECIPANE = "tab_pane";
    public static String RECIBORDERS = "reci_pane";
    
  
    public RecitationStyle(AppTemplate initApp){
        app = initApp;
        
        app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        initTAWorkspaceStyle();
    }

    private void initTAWorkspaceStyle() {
       Workspace workspaceComponent = (Workspace) app.getWorkspaceComponent();
       RecitationTab reciWorkspace = (RecitationTab)workspaceComponent.getRecitationTab();
        
       reciWorkspace.getReciTitle().getStyleClass().add(RECI_INFO);
       reciWorkspace.getDel().getStyleClass().add(BUTTONS);
       reciWorkspace.getSectionCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
       reciWorkspace.getInstCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
       reciWorkspace.getDayTimeCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
       reciWorkspace.getLocCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
       reciWorkspace.getTaCol1().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
       reciWorkspace.getTaCol2().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
       
       reciWorkspace.getReciTable().getStyleClass().add(SMALLTABLE);
       
       reciWorkspace.getAddEdit().getStyleClass().add(TOPLABELS);
       reciWorkspace.getSection().getStyleClass().add(TOPLABELS);
       reciWorkspace.getInstructor().getStyleClass().add(TOPLABELS);
       reciWorkspace.getDayTime().getStyleClass().add(TOPLABELS);
       reciWorkspace.getLocation().getStyleClass().add(TOPLABELS);
       reciWorkspace.getTa1().getStyleClass().add(TOPLABELS);
       reciWorkspace.getTa2().getStyleClass().add(TOPLABELS);
       reciWorkspace.getAddButton().getStyleClass().add(BUTTONS);
       reciWorkspace.getClearButton().getStyleClass().add(BUTTONS); 
       
       reciWorkspace.getReciPane().getStyleClass().add(RECIPANE); 
       reciWorkspace.getBottom().getStyleClass().add(RECIBORDERS);
    }
    
    
}
