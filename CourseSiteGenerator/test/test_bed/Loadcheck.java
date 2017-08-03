/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import csg.CSGApp;
import csg.data.CSGData;
import csg.data.ProjectData;
import csg.data.ReciData;
import csg.data.Recitation;
import csg.data.ScheData;
import csg.data.Schedule;
import csg.data.Student;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.files.TimeSlot;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Hengqi Zhu
 */
public class Loadcheck {
    
    JsonObject json;
    CSGData csgData;
    CSGData data;
    TAData taDataManager;
    ReciData reciData;
    ScheData scheData;
    ProjectData proData;
     CSGApp app;
    
    
    public Loadcheck() throws IOException {
        
         app = new CSGApp();
        app.loadProperties("app_properties.xml");
         data = new CSGData(app);
         csgData =  (CSGData) data;
         taDataManager = csgData.getTaData();
         reciData = csgData.getRecitationData();
         scheData = csgData.getSechData();
         proData = csgData.getProData();
         json = loadJSONFile(".\\work\\SiteSaveTest.json");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    @Test
    public void checkLoadTaTable() throws IOException{
        
        ObservableList<TeachingAssistant> expect = taDataManager.getTeachingAssistants();
        JsonArray jsonTAArray = json.getJsonArray("undergrad_tas");
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString("name");
            String email = jsonTA.getString("email");
            boolean bol = jsonTA.getBoolean("undergraduate");
            taDataManager.addTA(name, email,bol);
        }
        ObservableList<TeachingAssistant> act  = taDataManager.getTeachingAssistants();
        
        assertEquals(expect,act);
    }
    
   
    @Test
    public void checkLoadTaGrid(){
    
    ArrayList<TimeSlot> expect = taDataManager.getTempTS();
    JsonArray jsonOfficeHoursArray = json.getJsonArray("officeHours");
    ArrayList<TimeSlot> act=new ArrayList();
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString("day");
            String time = jsonOfficeHours.getString("time");
            String name = jsonOfficeHours.getString("name");
            act.add(new TimeSlot(day, time, name));
        }
       
       for(int i=0;i<expect.size();i++){
            assertEquals(expect.get(i).getDay(),act.get(i).getDay());
            assertEquals(expect.get(i).getName(),act.get(i).getName());
            assertEquals(expect.get(i).getTime(),act.get(i).getTime());
       }
           
    }
    
    @Test
    public void checkLoadRecitation(){
        ObservableList<Recitation> expect = FXCollections.observableArrayList();
        expect.add(new Recitation("R02","McKenna","Mondays, 3:30-4:23pm",
                                                        "Old Computer Science 2114","Joe Shmo","Jane Doe"));
        expect.add(new Recitation("R02","McKenna","Mondays, 3:30-4:23pm",
                                                        "Old Computer Science 2114","Joe Shmo","Jane Doe"));
        JsonArray jsonRecitArray = json.getJsonArray("recitation");
        for (int i = 0; i < jsonRecitArray.size(); i++) {
            JsonObject jsonRe = jsonRecitArray.getJsonObject(i);
            String section = jsonRe.getString("section").substring(0, 3);
            String instruc = jsonRe.getString("section").substring(4,jsonRe.getString("section").length()-1);
            String day_time = jsonRe.getString("day_time");
            String location = jsonRe.getString("location");
            String ta1 = jsonRe.getString("ta_1");
            String ta2 = jsonRe.getString("ta_2");
              reciData.getRecitations().add(new Recitation(section,instruc,day_time,location,ta1,ta2));
        }
        ObservableList<Recitation> act = data.getRecitationData().getRecitations();
        
        for(int i=0;i<expect.size();i++){
            assertEquals(expect.get(i).getSection(),act.get(i).getSection());
            assertEquals(expect.get(i).getInstructor(),act.get(i).getInstructor());
             assertEquals(expect.get(i).getDayTime(),act.get(i).getDayTime());
              assertEquals(expect.get(i).getLocation(),act.get(i).getLocation());
               assertEquals(expect.get(i).getTa1(),act.get(i).getTa1());
                assertEquals(expect.get(i).getTa2(),act.get(i).getTa2());
            
       }
        
  

}
    
    
    @Test
    public void checkLoadSchedule(){
     ObservableList<Schedule> expect1 = FXCollections.observableArrayList();
     // expect1.add(new Schedule("holidays","2/9","snowday","","www.funnny?"));
       ObservableList<Schedule> act1 =FXCollections.observableArrayList();
      JsonArray holiRecitArray = json.getJsonArray("holidays");
        for (int i = 0; i < holiRecitArray.size(); i++) {
            JsonObject jsonholi = holiRecitArray.getJsonObject(i);
            String type = "holidays";
            String date = jsonholi.getString("month")+"/"+jsonholi.getString("day"); 
            String title = jsonholi.getString("title");
            String link = jsonholi.getString("link");
             // act1.add(new Schedule(type,date,title,"",link));
        }
      
       
        for(int i=0;i<expect1.size();i++){
            //System.out.println(expect1.get(i).getTopic()+" "+act1.get(i).getTopic());
            assertEquals(expect1.get(i).getDate(),act1.get(i).getDate());
            assertEquals(expect1.get(i).getTitle(),act1.get(i).getTitle());
            assertEquals(expect1.get(i).getType(),act1.get(i).getType());
            assertEquals(expect1.get(i).getTopic(),act1.get(i).getTopic());
       }
       
       
        ObservableList<Schedule> expect2 = FXCollections.observableArrayList();
        //expect2.add(new Schedule("lectures","1/24","lectures1","javascript","www.google.com"));
        ObservableList<Schedule> act2 = FXCollections.observableArrayList();
        JsonArray lecRecitArray = json.getJsonArray("lectures");
        for (int i = 0; i < lecRecitArray.size(); i++) {
            JsonObject jsonholec = lecRecitArray.getJsonObject(i);
            String type = "lectures";
            String date = jsonholec.getString("month")+"/"+jsonholec.getString("day"); 
               
            String title = jsonholec.getString("title");
            String topic = jsonholec.getString("topic");
            String link = jsonholec.getString("link");
              //act2.add(new Schedule(type,date,title,topic,link));
        }
        for(int i=0;i<expect1.size();i++){
            assertEquals(expect2.get(i).getDate(),act2.get(i).getDate());
            assertEquals(expect2.get(i).getTitle(),act2.get(i).getTitle());
            assertEquals(expect2.get(i).getType(),act2.get(i).getType());
            assertEquals(expect2.get(i).getTopic(),act2.get(i).getTopic());
       }
        
        
         ObservableList<Schedule> expect3 = FXCollections.observableArrayList();
         //expect3.add(new Schedule("references","2/14","reference","javafx","www.google.com"));
         ObservableList<Schedule> act3 = FXCollections.observableArrayList();
        JsonArray refeRecitArray = json.getJsonArray("references");
        for (int i = 0; i < refeRecitArray.size(); i++) {
            JsonObject jsonre = refeRecitArray.getJsonObject(i);
            String type = "references";
            String date = jsonre.getString("month")+"/"+jsonre.getString("day");
            String title = jsonre.getString("title");
            String topic = jsonre.getString("topic");
            String link = jsonre.getString("link");
             // act3.add(new Schedule(type,date,title,topic,link));
        }

        for(int i=0;i<expect1.size();i++){
            assertEquals(expect3.get(i).getDate(),act3.get(i).getDate());
            assertEquals(expect3.get(i).getTitle(),act3.get(i).getTitle());
            assertEquals(expect3.get(i).getType(),act3.get(i).getType());
            assertEquals(expect3.get(i).getTopic(),act3.get(i).getTopic());
            assertEquals(expect3.get(i).getLink(),act3.get(i).getLink());
       }
        
        /*
        JsonArray RecitsArray = json.getJsonArray("recitations");
        for (int i = 0; i < RecitsArray.size(); i++) {
            JsonObject jsonreci = RecitsArray.getJsonObject(i);
            String type = "recitations";
            String date = jsonreci.getString("month")+"/"+jsonreci.getString("day"); 
            String title = jsonreci.getString("title");
            String topic = jsonreci.getString("topic");
              scheData.getSchedules().add(new Schedule(type,date,title,topic));
        }
        JsonArray hwArray = json.getJsonArray("hws");
        for (int i = 0; i < hwArray.size(); i++) {
            JsonObject jsonhw = hwArray.getJsonObject(i);
            String type = "hws";
            String date = jsonhw.getString("month")+"/"+jsonhw.getString("day"); 
            String title = jsonhw.getString("title");
            String topic = jsonhw.getString("topic");
              scheData.getSchedules().add(new Schedule(type,date,title,topic));
        }*/
    }
    
    @Test
    public void checkLoadProject(){
        Team aqua = new Team("Aqua","123456","black","www.google.com");
        ObservableList<Team> expect1 = FXCollections.observableArrayList();
        expect1.add(aqua);
         ObservableList<Team> act1 = FXCollections.observableArrayList();
        JsonArray teamArray = json.getJsonArray("teams");
        for (int i = 0; i < teamArray.size(); i++) {
            JsonObject jsonteam = teamArray.getJsonObject(i);
            String name = jsonteam.getString("name");
            String color = jsonteam.getString("red")+jsonteam.getString("green")+jsonteam.getString("blue");
            String text_color = jsonteam.getString("text_color");
            String link = jsonteam.getString("link");
              act1.add(new Team(name,color,text_color,link));
        }
        for(int i=0;i<expect1.size();i++){
            assertEquals(expect1.get(i).getName(),act1.get(i).getName());
            assertEquals(expect1.get(i).getColor(),act1.get(i).getColor());
            assertEquals(expect1.get(i).getTextColor(),act1.get(i).getTextColor());
            assertEquals(expect1.get(i).getLink(),act1.get(i).getLink());
       }

        
        //data.getProData().getWorks().add(new Work(aqua,data.getProData().getStudents()));
        //data.getProData().getWorks().add(new Work(abc,data.getProData().getStudents()));
       ObservableList<Student> expect2 = FXCollections.observableArrayList();
        expect2.add(new Student("verma","rahul","Aqua","Data Designer"));
        expect2.add(new Student("sf","sfd","Aqua","Lead Designer"));
        expect2.add(new Student("dsf","sdgd","Aqua","Project Manager"));
        expect2.add(new Student("132","rahul","Aqua","Lead Programmer"));
        
         ObservableList<Student> act2 = FXCollections.observableArrayList(); 
          JsonArray stuArray = json.getJsonArray("students");
        for (int i = 0; i < stuArray.size(); i++) {
            JsonObject jsonstu = stuArray.getJsonObject(i);
            String lastname = jsonstu.getString("lastName");
            String firstname = jsonstu.getString("firstName");
            String team = jsonstu.getString("team");
            String role = jsonstu.getString("role");
              act2.add(new Student(firstname,lastname,team,role));
        }
        
        for(int i=0;i<expect2.size();i++){
            assertEquals(expect2.get(i).getLast(),act2.get(i).getLast());
            assertEquals(expect2.get(i).getFirst(),act2.get(i).getFirst());
            assertEquals(expect2.get(i).getTeam(),act2.get(i).getTeam());
            assertEquals(expect2.get(i).getRole(),act2.get(i).getRole());
       }
        
    }

}
