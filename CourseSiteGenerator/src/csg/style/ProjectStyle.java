/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.workspace.ProjectTab;
import csg.workspace.Workspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;

/**
 *
 * @author Hengqi Zhu
 */
public class ProjectStyle extends AppStyleComponent{
    private AppTemplate app;
    public static String RECI_INFO = "course_info";
    public static String TOPLABELS = "top_labels";
    public static String BUTTONS = "small_button";
    public static String COLUMN = "ta_table_column_header";
    public static String SMALLTABLE = "small_table";
    public static String RECIPANE = "tab_pane";
    public static String RECIBORDERS = "reci_pane";
    
    public ProjectStyle(AppTemplate initApp){
        app = initApp;
    
         app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        initTAWorkspaceStyle();
    }

    private void initTAWorkspaceStyle() {
        Workspace workspaceComponent = (Workspace) app.getWorkspaceComponent();
        ProjectTab proWorkspace = (ProjectTab)workspaceComponent.getProjectTab();
        
        proWorkspace.getProjectTitle().getStyleClass().add(RECI_INFO);
        proWorkspace.getTeamTitle().getStyleClass().add(TOPLABELS);
        proWorkspace.getAddEdit1().getStyleClass().add(TOPLABELS);
        proWorkspace.getName().getStyleClass().add(TOPLABELS);
        proWorkspace.getColor().getStyleClass().add(TOPLABELS);
        proWorkspace.getTextColor().getStyleClass().add(TOPLABELS);
        proWorkspace.getLink().getStyleClass().add(TOPLABELS);
        proWorkspace.getStudentTitle().getStyleClass().add(TOPLABELS);
        proWorkspace.getAddEdit2().getStyleClass().add(TOPLABELS);
        proWorkspace.getFirst().getStyleClass().add(TOPLABELS);
        proWorkspace.getLast().getStyleClass().add(TOPLABELS);
        proWorkspace.getTeam().getStyleClass().add(TOPLABELS);
        proWorkspace.getRole().getStyleClass().add(TOPLABELS);
        
        proWorkspace.getDel1().getStyleClass().add(BUTTONS);
        proWorkspace.getDel2().getStyleClass().add(BUTTONS);
        proWorkspace.getAddUpdate1().getStyleClass().add(BUTTONS);
        proWorkspace.getAddUpdate2().getStyleClass().add(BUTTONS);
        proWorkspace.getClear1().getStyleClass().add(BUTTONS);
        proWorkspace.getClear2().getStyleClass().add(BUTTONS);
        
        proWorkspace.getNameCol().getStyleClass().add(COLUMN);
         proWorkspace.getColorCol().getStyleClass().add(COLUMN);
         proWorkspace.getTextColorCol().getStyleClass().add(COLUMN);
         proWorkspace.getLinkCol().getStyleClass().add(COLUMN);
         
          proWorkspace.getFirstCol().getStyleClass().add(COLUMN);
           proWorkspace.getLastCol().getStyleClass().add(COLUMN);
         proWorkspace.getTeamCol().getStyleClass().add(COLUMN);
        proWorkspace.getRoleCol().getStyleClass().add(COLUMN);
        
         proWorkspace.getProjectPane().getStyleClass().add(RECIPANE);
         proWorkspace.getProjectPane().getStyleClass().add(RECIBORDERS);
         proWorkspace.getLeftPane().getStyleClass().add(RECIBORDERS);
    proWorkspace.getRightPane().getStyleClass().add(RECIBORDERS);
    //proWorkspace.getLeft().getStyleClass().add(RECIPANE);
    //proWorkspace.getRight().getStyleClass().add(RECIPANE);
    
    
    }
    
}
