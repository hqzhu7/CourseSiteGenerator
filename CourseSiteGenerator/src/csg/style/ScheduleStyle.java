/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.workspace.ScheduleTab;
import csg.workspace.Workspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;

/**
 *
 * @author Hengqi Zhu
 */
public class ScheduleStyle extends AppStyleComponent{
    private AppTemplate app;
    public static String RECI_INFO = "course_info";
    public static String TOPLABELS = "top_labels";
    public static String BUTTONS = "small_button";
    public static String COLUMN = "ta_table_column_header";
    public static String SMALLTABLE = "small_table";
    public static String RECIPANE = "tab_pane";
    public static String RECIBORDERS = "reci_pane";
    
    public ScheduleStyle(AppTemplate initApp)
    {
        app = initApp;
        
        app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        initTAWorkspaceStyle();
    }

    private void initTAWorkspaceStyle() {
        Workspace workspaceComponent = (Workspace) app.getWorkspaceComponent();
        ScheduleTab scheWorkspace = (ScheduleTab)workspaceComponent.getScheduleTab();
        
        scheWorkspace.getScheduleTitle().getStyleClass().add(RECI_INFO);
        
        scheWorkspace.getCalendarBound().getStyleClass().add(TOPLABELS);
        scheWorkspace.getStart().getStyleClass().add(TOPLABELS);
        scheWorkspace.getEnd().getStyleClass().add(TOPLABELS);
        scheWorkspace.getScheItem().getStyleClass().add(TOPLABELS);
        scheWorkspace.getAddEdit().getStyleClass().add(TOPLABELS);
        scheWorkspace.getType().getStyleClass().add(TOPLABELS);
        scheWorkspace.getDate().getStyleClass().add(TOPLABELS);
        scheWorkspace.getTime().getStyleClass().add(TOPLABELS);
        scheWorkspace.getTitle().getStyleClass().add(TOPLABELS);
        scheWorkspace.getTopic().getStyleClass().add(TOPLABELS);
        scheWorkspace.getLink().getStyleClass().add(TOPLABELS);
        
        scheWorkspace.getAddButton().getStyleClass().add(BUTTONS);
        scheWorkspace.getClear().getStyleClass().add(BUTTONS);
        
        scheWorkspace.getTypeCol().getStyleClass().add(COLUMN);
        scheWorkspace.getDateCol().getStyleClass().add(COLUMN);
        scheWorkspace.getTitleCol().getStyleClass().add(COLUMN);
        scheWorkspace.getTopicCol().getStyleClass().add(COLUMN);
        
        scheWorkspace.getScheTable().getStyleClass().add(SMALLTABLE);
        scheWorkspace.getSchedulePane().getStyleClass().add(RECIPANE);
        scheWorkspace.getTop().getStyleClass().add(RECIBORDERS);
        scheWorkspace.getBottom().getStyleClass().add(RECIBORDERS);
        scheWorkspace.getCriteria().getStyleClass().add(TOPLABELS);
        scheWorkspace.getDel().getStyleClass().add(BUTTONS);
      
        
        
    }
    
    
    
}
