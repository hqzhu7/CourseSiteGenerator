package test_bed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import csg.CSGApp;
import csg.data.CSGData;
import csg.data.Course;

import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.Student;

import csg.data.Team;
import csg.data.Work;
import csg.files.CSGFiles;
import csg.files.TimeSlot;

import java.io.IOException;


/**
 *
 * @author Hengqi Zhu
 */
public class TestSave{
    

    
    public  static void main(String[] args) throws IOException{
        CSGApp app = new CSGApp();
        app.loadProperties("app_properties.xml");
        
        
        CSGData data = new CSGData(app);
        CSGFiles file = new CSGFiles(app);
        
        data.getTaData().getTempTS().add(new TimeSlot("MONDAY","7_00am","Hsiang-Ju Lai"));
        data.getTaData().addTA("hq", "hq.zhu@outlook.com",true);
        data.getTaData().addTA("sfdas", "hq.zhsdfu@outlook.com",false);

        data.getRecitationData().getRecitations().add(new Recitation("R01","McKenna","Mondays, 3:30-4:23pm",
                                                        "Old Computer Science 2114","Joe Shmo","Jane Doe"));
        data.getRecitationData().getRecitations().add(new Recitation("R02","McKenna","Mondays, 3:30-4:23pm",
                                                        "Old Computer Science 2114","Joe Shmo","Jane Doe"));
        
        
        
        data.getSechData().getSchedules().add(new Schedule("holidays","2/9","snowday","","www.funnny?","",""));
         data.getSechData().getSchedules().add(new Schedule("hws","2/22","hw1","javafx","www.google.com","11:59pm","ht tps://docs.google.com/spreadsheets/d"));
          data.getSechData().getSchedules().add(new Schedule("lectures","1/24","lectures1","javascript","www.google.com","",""));
          data.getSechData().getSchedules().add(new Schedule("recitations","1/30","rectition1","netbean","","",""));
          data.getSechData().getSchedules().add(new Schedule("references","2/14","reference","javafx","www.google.com","",""));
        data.getSechData().setEndDay("19");
        data.getSechData().setEndMonth("05");
        data.getSechData().setStartDay("23");
        data.getSechData().setStartMonth("01");
        
        Team aqua = new Team("Aqua","cc3333","000000","www.google.com");

        data.getProData().getTeams().add(aqua);
       
        data.getProData().getStudents().add(new Student("verma","rahul","Aqua","Data Designer"));
        data.getProData().getStudents().add(new Student("sf","sfd","Aqua","Lead Designer"));
        data.getProData().getStudents().add(new Student("dsf","sdgd","Aqua","Project Manager"));
        data.getProData().getStudents().add(new Student("132","rahul","Aqua","Lead Programmer"));
 
        data.getProData().getWorks().add(new Work(aqua,data.getProData().getStudents()));
        //data.getProData().getWorks().add(new Work(abc,data.getProData().getStudents()));
        data.getProData().setSemseter("Spring 2016");
        
        //Course(String sub, String num, String seme, String yea, String tit, String nam, String hom)
        data.getCourseData().setCourse(new Course("cse","220","spring","2017","computer science","abc","www.google.com"));
        

        file.saveData(data, ".\\work\\SiteSaveTest.json");
    }
}


