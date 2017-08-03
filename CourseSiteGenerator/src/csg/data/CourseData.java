/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;


import csg.CSGApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Hengqi Zhu
 */
public class CourseData {
    
    CSGApp app;
    Course course;
    
    
    public CourseData(CSGApp initApp){
        app = initApp;
       
        
    
    }

    public void setCourse(Course course){
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
    
    public void resetData(){
        
    }
    
}
