/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.workspace.CourseTab;
import csg.workspace.Workspace;
import djf.AppTemplate;
import djf.components.AppStyleComponent;


/**
 *
 * @author Hengqi Zhu
 */
public class CourseStyle extends AppStyleComponent{
    
    public static String COURSE_INFO = "course_info";
    public static String TOPLABELS = "top_labels";
    public static String BUTTONS = "small_button";
    public static String BORDERS = "pane_borders";
    public static String CLASS_TA_TABLE_COLUMN_HEADER = "ta_table_column_header";
    public static String SMALLTABLE = "small_table";
    public static String TABPANE = "tab_pane";
    
     private AppTemplate app;

     
     public CourseStyle(AppTemplate initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        // LET'S USE THE DEFAULT STYLESHEET SETUP
       // super.initStylesheet(app);

        // INIT THE STYLE FOR THE FILE TOOLBAR
        app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        initTAWorkspaceStyle();
    }

    private void initTAWorkspaceStyle() {
        Workspace workspaceComponent = (Workspace) app.getWorkspaceComponent();
        CourseTab courseWorkspace = (CourseTab)workspaceComponent.getCourseTab(); 
        
        courseWorkspace.getCourseInfo().getStyleClass().add(COURSE_INFO);
        courseWorkspace.getSubject().getStyleClass().add(TOPLABELS);
        courseWorkspace.getSemester().getStyleClass().add(TOPLABELS);
        courseWorkspace.getTitle().getStyleClass().add(TOPLABELS);
        courseWorkspace.getInstrHome().getStyleClass().add(TOPLABELS);
        courseWorkspace.getInstrName().getStyleClass().add(TOPLABELS);
        courseWorkspace.getDir().getStyleClass().add(TOPLABELS);
        courseWorkspace.getNumber().getStyleClass().add(TOPLABELS);
        courseWorkspace.getYear().getStyleClass().add(TOPLABELS);
        courseWorkspace.getChangeTop().getStyleClass().add(BUTTONS);
        courseWorkspace.getTop().getStyleClass().add(BORDERS);
        
        courseWorkspace.getSiteTemp().getStyleClass().add(COURSE_INFO);
        courseWorkspace.getSelect().getStyleClass().add(BUTTONS);
        courseWorkspace.getSitePage().getStyleClass().add(TOPLABELS);
        
        courseWorkspace.getUseCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        courseWorkspace.getNavbarCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        courseWorkspace.getFilenameCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        courseWorkspace.getScriptCol().getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER); 
        
        courseWorkspace.getSiteFiles().getStyleClass().add(SMALLTABLE);
        courseWorkspace.getMiddle().getStyleClass().add(BORDERS);
        
        courseWorkspace.getPageStyle().getStyleClass().add(COURSE_INFO);
        courseWorkspace.getSchool().getStyleClass().add(TOPLABELS);
        courseWorkspace.getLeftFooter().getStyleClass().add(TOPLABELS);
        courseWorkspace.getRightFooter().getStyleClass().add(TOPLABELS);
        courseWorkspace.getStylesheet().getStyleClass().add(TOPLABELS);
        courseWorkspace.getChange1().getStyleClass().add(BUTTONS);
        courseWorkspace.getChange2().getStyleClass().add(BUTTONS);
        courseWorkspace.getChange3().getStyleClass().add(BUTTONS);
        courseWorkspace.getBottom().getStyleClass().add(BORDERS);
        courseWorkspace.getCourseTabPane().getStyleClass().add(TABPANE);
        
        
    }

    
}
