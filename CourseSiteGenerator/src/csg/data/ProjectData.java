/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

/**
 *
 * @author Hengqi Zhu
 */
import csg.CSGApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class ProjectData {
    
    CSGApp app;

    ObservableList<Team> teams;
    ObservableList<Student> students;
    ObservableList<Work> works;
    String semseter;
    
    public ProjectData(CSGApp initApp){
       
        app = initApp;

        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        teams = FXCollections.observableArrayList();
        students = FXCollections.observableArrayList();
        works = FXCollections.observableArrayList();
       
    }

    public ObservableList<Team> getTeams() {
        return teams;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }
    
    public ObservableList<Work> getWorks() {
        return works;
    }

    public String getSemseter() {
        return semseter;
    }

    public void setSemseter(String semseter) {
        this.semseter = semseter;
    }
    
    public void resetData(){
         teams.clear();
     students.clear();
      works.clear();
        semseter="";
    }
    
}
