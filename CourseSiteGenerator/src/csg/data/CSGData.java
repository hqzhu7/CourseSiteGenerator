/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CSGApp;
import djf.components.AppDataComponent;
import csg.data.TAData;
import djf.AppTemplate;

/**
 *
 * @author Hengqi Zhu
 */
public class CSGData implements AppDataComponent {

    CSGApp app;
    CourseData courseData;
    TAData taData;
    ReciData recitationData;
    ScheData sechData;
    ProjectData proData;
    
    
    public CSGData(AppTemplate initApp){   
        app=(CSGApp)initApp;
        taData = new TAData((CSGApp)initApp);
        recitationData = new ReciData((CSGApp)initApp);
        sechData = new ScheData(app);
        proData = new ProjectData(app);
        courseData = new CourseData(app); 
    }

    @Override
    public void resetData() {
        taData.resetData();
        recitationData.resetData();
        sechData.resetData();
        proData.resetData();
        courseData.resetData();
    }
    
    public TAData getTaData(){
        return taData; 
    }

    public ReciData getRecitationData() {
        return recitationData;
    }

    public ScheData getSechData() {
        return sechData;
    }

    public ProjectData getProData() {
        return proData;
    }

    public CourseData getCourseData() {
        return courseData;
    }
    
    
    
}
