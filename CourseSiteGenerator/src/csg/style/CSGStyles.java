/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import djf.AppTemplate;
import djf.components.AppStyleComponent;

/**
 *
 * @author Hengqi Zhu
 */
public class CSGStyles extends AppStyleComponent{
    
    private AppTemplate app;
    
    TAStyle taStyle;
    CourseStyle courseStyle;
    RecitationStyle reciStyle;
    ScheduleStyle scheStyle;
    ProjectStyle proStyle;
    
    public CSGStyles(AppTemplate initApp){
        app = initApp;

        // LET'S USE THE DEFAULT STYLESHEET SETUP
        super.initStylesheet(app);

        // INIT THE STYLE FOR THE FILE TOOLBAR
        app.getGUI().initFileToolbarStyle();
        
        taStyle = new TAStyle(initApp);
        courseStyle = new CourseStyle(initApp);
        reciStyle = new RecitationStyle(initApp);
        scheStyle= new ScheduleStyle(initApp);
        proStyle = new ProjectStyle(initApp);
    }
    
    public TAStyle getTaStyle(){
        return taStyle;
    }
    
}
