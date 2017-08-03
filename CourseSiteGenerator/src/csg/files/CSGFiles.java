/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.files;

import csg.CSGApp;
import static csg.RecitationProp.MISSING;
import csg.data.CSGData;
import csg.data.Course;
import csg.data.CourseData;
import csg.data.ProjectData;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import csg.data.ReciData;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.ScheData;
import csg.data.Student;
import csg.data.Team;
import csg.data.Work;
import csg.workspace.CourseTab;
import csg.workspace.ProjectTab;
import csg.workspace.RecitationTab;
import csg.workspace.ScheduleTab;
import csg.workspace.TaTab;
import csg.workspace.Workspace;
import djf.AppTemplate;
import djf.ui.AppMessageDialogSingleton;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import javafx.scene.control.ComboBox;
import javax.json.JsonReader;

/**
 *
 * @author Hengqi Zhu
 */
public class CSGFiles implements AppFileComponent{
    
    CSGApp app;
    
    //TA Tab
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "name";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_EMAIL = "email";
    
    //Reci Tab
    static final String JSON_RECITATIONSS = "recitations";
    static final String JSON_SECTION = "section";
    static final String JSON_INSTOR = "instructor";
    static final String JSON_RECI_DAYTIME = "day_time";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA1 = "ta_1";
    static final String JSON_TA2 = "ta_2";
    
    //schedule
    static final String JSON_MONDAY_MONTH = "startingMondayMonth";
    static final String JSON_MONDAY_DAY = "startingMondayDay";
    static final String JSON_FRIDAY_MONTH = "endingFridayMonth";
    static final String JSON_FRIDAY_DAY = "endingFridayDay";
    static final String JSON_HOLIDAY ="holidays";
    
    
    public CSGFiles(AppTemplate initApp){
        app=(CSGApp)initApp;
    
    }
    
    

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        CSGData csgData =  (CSGData) data;
        TAData taDataManager = csgData.getTaData();
        ReciData reciDataManager = csgData.getRecitationData();
        ScheData sechDataManager = csgData.getSechData();
        ProjectData proDataManager = csgData.getProData();
        CourseData courseDataManager = csgData.getCourseData();
        
        
	// NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = taDataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {
            if(ta.getGradValue()){
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .add("undergraduate",ta.getGradValue())
                    .build();
	    taArrayBuilder.add(taJson);}
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();
        
        
        JsonArrayBuilder gradtTaArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> gtas = taDataManager.getTeachingAssistants();
	for (TeachingAssistant ta : gtas) {
            if(!ta.getGradValue()){
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .add("undergraduate",ta.getGradValue())
                    .build();
	    gradtTaArrayBuilder.add(taJson);}
	}
	JsonArray gradTAsArray = gradtTaArrayBuilder.build();
        
        
        
        
        
        // NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
        ArrayList<TimeSlot> officeHours;
        try{
        officeHours = TimeSlot.buildOfficeHoursList(taDataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
        }catch(NullPointerException e){
            officeHours = taDataManager.getTempTS();
            for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
        }
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
        //recitation data
        JsonArrayBuilder reciArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = reciDataManager.getRecitations();
	for (Recitation re : recitations) {	    
	    JsonObject reciJson = Json.createObjectBuilder()
		    .add(JSON_SECTION, re.getSection()+ "("+re.getInstructor()+")")
                    .add(JSON_RECI_DAYTIME,re.getDayTime())
                    .add(JSON_LOCATION,re.getLocation())
                    .add(JSON_TA1,re.getTa1())
                    .add(JSON_TA2,re.getTa2())
                    .build();
	    reciArrayBuilder.add(reciJson);
	}
	JsonArray recitationArray = reciArrayBuilder.build();
        
        //schedulle 
        JsonArrayBuilder holidayArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder lectureArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder referenceArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder recitaArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwArrayBuilder = Json.createArrayBuilder();
	
        ObservableList<Schedule> schedules = sechDataManager.getSchedules();
	for (Schedule sc : schedules) {	
            JsonObject scJson;
            if(sc.getType().equalsIgnoreCase("holidays")){
                scJson = Json.createObjectBuilder()
                    .add("month",sc.getMonth())
                    .add("day",sc.getDay())
                    .add("title",sc.getTitle())
                    .add("link", sc.getLink())
                    .build();
	    holidayArrayBuilder.add(scJson);}
            else if(sc.getType().equalsIgnoreCase("lectures")){
                scJson = Json.createObjectBuilder()
                    .add("month",sc.getMonth())
                    .add("day",sc.getDay())
                    .add("title",sc.getTitle())
                    .add("topic", sc.getTopic())
                     .add("link", sc.getLink())
                    .build();
            lectureArrayBuilder.add(scJson);}
            else if(sc.getType().equalsIgnoreCase("references")){
                scJson = Json.createObjectBuilder()
                    .add("month",sc.getMonth())
                    .add("day",sc.getDay())
                    .add("title",sc.getTitle())
                    .add("topic", sc.getTopic())
                     .add("link",sc.getLink())
                    .build();
            referenceArrayBuilder.add(scJson);
            }
            else if(sc.getType().equalsIgnoreCase("recitations")){
                scJson = Json.createObjectBuilder()
                    .add("month",sc.getMonth())
                    .add("day",sc.getDay())
                    .add("title",sc.getTitle())
                    .add("topic", sc.getTopic())
                    .build();
                recitaArrayBuilder.add(scJson);
            }
            else if(sc.getType().equalsIgnoreCase("hws")){
            scJson = Json.createObjectBuilder()
                    .add("month",sc.getMonth())
                    .add("day",sc.getDay())
                    .add("title",sc.getTitle())
                    .add("topic", sc.getTopic())
                    .add("link", sc.getLink())
                    .add("time",sc.getTime())
                    .add("criteria",sc.getCriteria())
                    .build();
                hwArrayBuilder.add(scJson);
            }
                
	}
        
        
	JsonArray holidayArray = holidayArrayBuilder.build();
        JsonArray lectureArray = lectureArrayBuilder.build();
        JsonArray referArray = referenceArrayBuilder.build();
        JsonArray hwArray = hwArrayBuilder.build();
         JsonArray reciatataiArray = recitaArrayBuilder.build();
        
        //tema and student
        JsonArrayBuilder teamArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = proDataManager.getTeams();
	for (Team team : teams) {	    
	    JsonObject teamJson = Json.createObjectBuilder()
		    .add("name", team.getName())
                    .add("red",team.getRed())
                    .add("green",team.getBlue())
                    .add("blue", team.getBlue())
                    .add("text_color",team.getTextColor() )
                    .add("link", team.getLink())
                    .build();
	    teamArrayBuilder.add(teamJson);
	}
	JsonArray teamArray = teamArrayBuilder.build();
        
        JsonArrayBuilder stuArrayBuilder = Json.createArrayBuilder();
        ObservableList<Student> stus = proDataManager.getStudents();
	for (Student stu : stus) {	    
	    JsonObject stuJson = Json.createObjectBuilder()
		    .add("lastName", stu.getLast())
                    .add("firstName",stu.getFirst())
                    .add("team",stu.getTeam())
                    .add("role", stu.getRole())
                    .build();
	    stuArrayBuilder.add(stuJson);
	}
	JsonArray stuArray = stuArrayBuilder.build();
        
         for(int i=0;i<proDataManager.getTeams().size();i++){
             
             if(proDataManager.getTeams().size()>proDataManager.getWorks().size())
                proDataManager.getWorks().add(new Work(proDataManager.getTeams().get(i),proDataManager.getStudents()));
         }
        //project
        JsonArrayBuilder workArrayBuilder = Json.createArrayBuilder();
        ObservableList<Work> works = proDataManager.getWorks();
	for (Work work : works) {	    
	    JsonObject workJson = Json.createObjectBuilder()
		    .add("name", work.getName())
                    .add("students", work.getStudentName(work.getName()))
                    .add("link",work.getLink())
                    .build();
	    workArrayBuilder.add(workJson);
	}
	JsonArray workArray = workArrayBuilder.build();

         JsonArrayBuilder sbArrayBuilder = Json.createArrayBuilder();
          JsonObject sbJson = Json.createObjectBuilder()
                  .add("semester", proDataManager.getSemseter())
                  .add("projects", workArray)
                  .build();
         sbArrayBuilder.add(sbJson);
         JsonArray sbArray = sbArrayBuilder.build();
            
        // THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add("subject",courseDataManager.getCourse().getSubject())
                .add("semester",courseDataManager.getCourse().getSemester())
                .add("number", courseDataManager.getCourse().getNumber())
                .add("year",courseDataManager.getCourse().getYear())
                .add("title",courseDataManager.getCourse().getTitle())
                .add("name", courseDataManager.getCourse().getName())
                .add("home",courseDataManager.getCourse().getHome())
		.add(JSON_START_HOUR, "" + taDataManager.getStartHour())
		.add(JSON_END_HOUR, "" + taDataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add("grad_tas",gradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                .add("recitation", recitationArray)
                .add("startingMondayMonth",sechDataManager.getStartMonth())
                .add("startingMondayDay",sechDataManager.getStartDay())
                .add("endingFridayMonth", sechDataManager.getEndMonth())
                .add("endingFridayDay",sechDataManager.getEndDay())
                .add("holidays", holidayArray)
                .add("lectures", lectureArray)
                .add("references",referArray)
                .add("recitations",reciatataiArray)
                .add("hws", hwArray)
                .add("teams",teamArray)
                .add("students",stuArray)
                .add("work",sbArray)
		.build();
        
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        
        CSGData csgData =  (CSGData) data;
        TAData taDataManager = csgData.getTaData();
        ReciData reciDataManager = csgData.getRecitationData();
        ScheData sechDataManager = csgData.getSechData();
        ProjectData proDataManager = csgData.getProData();
        CourseData courseDataManager = csgData.getCourseData();
        
        Workspace workspaceComponent = (Workspace)app.getWorkspaceComponent();
        TaTab taWorkspace = workspaceComponent.getTaTab();
        CourseTab courseSpce = workspaceComponent.getCourseTab();
 
        

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);

	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
       ComboBox a=(ComboBox) taWorkspace.getOfficeHoursSubheaderBox().getChildren().get(2);
       ComboBox c=(ComboBox) taWorkspace.getOfficeHoursSubheaderBox().getChildren().get(4);
       a.getSelectionModel().select(Integer.parseInt(startHour)*2);
       c.getSelectionModel().select(Integer.parseInt(endHour)*2);
        taDataManager.initHours(startHour, endHour);

        // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
        app.getWorkspaceComponent().reloadWorkspace(app.getDataComponent());

        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            boolean bol = jsonTA.getBoolean("undergraduate");
            taDataManager.addTA(name, email,bol);
        }
        
        JsonArray jsonGTAArray = json.getJsonArray("grad_tas");
        for (int i = 0; i < jsonGTAArray.size(); i++) {
            JsonObject jsonTA = jsonGTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            boolean bol = jsonTA.getBoolean("undergraduate");
            taDataManager.addTA(name, email,bol);
        }
        
        

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            taDataManager.addOfficeHoursReservation(day, time, name);
          
        }
        
        //load recitataion
        JsonArray jsonRecitArray = json.getJsonArray("recitation");
        for (int i = 0; i < jsonRecitArray.size(); i++) {
            JsonObject jsonRe = jsonRecitArray.getJsonObject(i);
            String section = jsonRe.getString("section").substring(0, 3);
            String instruc = jsonRe.getString("section").substring(4,jsonRe.getString("section").length()-1);
            String day_time = jsonRe.getString("day_time");
            String location = jsonRe.getString("location");
            String ta1 = jsonRe.getString("ta_1");
            String ta2 = jsonRe.getString("ta_2");
              reciDataManager.getRecitations().add(new Recitation(section,instruc,day_time,location,ta1,ta2));
        }
        
        //load schedulesssssssssssss
         JsonArray holiRecitArray = json.getJsonArray("holidays");
        for (int i = 0; i < holiRecitArray.size(); i++) {
            JsonObject jsonholi = holiRecitArray.getJsonObject(i);
            String type = "holidays";
            String date = jsonholi.getString("month")+"/"+jsonholi.getString("day"); 
            String title = jsonholi.getString("title");
            String link = jsonholi.getString("link");
              sechDataManager.getSchedules().add(new Schedule(type,date,title,"",link,"",""));
        }
        JsonArray lecRecitArray = json.getJsonArray("lectures");
        for (int i = 0; i < lecRecitArray.size(); i++) {
            JsonObject jsonholec = lecRecitArray.getJsonObject(i);
            String type = "lectures";
            String date = jsonholec.getString("month")+"/"+jsonholec.getString("day"); 
               
            String title = jsonholec.getString("title");
            String topic = jsonholec.getString("topic");
            String link = jsonholec.getString("link");
              sechDataManager.getSchedules().add(new Schedule(type,date,title,topic,link,"",""));
        }
        JsonArray refeRecitArray = json.getJsonArray("references");
        for (int i = 0; i < refeRecitArray.size(); i++) {
            JsonObject jsonre = refeRecitArray.getJsonObject(i);
            String type = "references";
            String date = jsonre.getString("month")+"/"+jsonre.getString("day");
            String title = jsonre.getString("title");
            String topic = jsonre.getString("topic");
            String link = jsonre.getString("link");
              sechDataManager.getSchedules().add(new Schedule(type,date,title,topic,link,"",""));
        }
        JsonArray RecitsArray = json.getJsonArray("recitations");
        for (int i = 0; i < RecitsArray.size(); i++) {
            JsonObject jsonreci = RecitsArray.getJsonObject(i);
            String type = "recitations";
            String date = jsonreci.getString("month")+"/"+jsonreci.getString("day"); 
            String title = jsonreci.getString("title");
            String topic = jsonreci.getString("topic");
              sechDataManager.getSchedules().add(new Schedule(type,date,title,topic,"","",""));
        }
        JsonArray hwArray = json.getJsonArray("hws");
        for (int i = 0; i < hwArray.size(); i++) {
            JsonObject jsonhw = hwArray.getJsonObject(i);
            String type = "hws";
            String date = jsonhw.getString("month")+"/"+jsonhw.getString("day"); 
            String title = jsonhw.getString("title");
            String topic = jsonhw.getString("topic");
            String link = jsonhw.getString("link");
            String time = jsonhw.getString("time");
            String criteria = jsonhw.getString("criteria");
              sechDataManager.getSchedules().add(new Schedule(type,date,title,topic,link,time,criteria));
        }
        
        String startingmondaymonth = json.getString("startingMondayMonth");
        String startingmondayday = json.getString("startingMondayDay");
        String endingfridaymonth = json.getString("endingFridayMonth");
        String endingfridayday = json.getString("endingFridayDay");
        
        sechDataManager.setEndDay(endingfridayday);
        sechDataManager.setEndMonth(endingfridaymonth);
        sechDataManager.setStartDay(startingmondayday);
        sechDataManager.setStartMonth(startingmondaymonth);
        

        //load team
        JsonArray teamArray = json.getJsonArray("teams");
        for (int i = 0; i < teamArray.size(); i++) {
            JsonObject jsonteam = teamArray.getJsonObject(i);
            String name = jsonteam.getString("name");
            String red = Integer.toHexString(Integer.parseInt(jsonteam.getString("red")));
            String green = Integer.toHexString(Integer.parseInt(jsonteam.getString("green")));
            String blue = Integer.toHexString(Integer.parseInt(jsonteam.getString("blue")));
            String color = red+green+blue;
            String title = jsonteam.getString("text_color");
            String topic = jsonteam.getString("link");
              proDataManager.getTeams().add(new Team(name,color,title,topic));
        }
        
        JsonArray stuArray = json.getJsonArray("students");
        for (int i = 0; i < stuArray.size(); i++) {
            JsonObject jsonstu = stuArray.getJsonObject(i);
            String lastname = jsonstu.getString("lastName");
            String firstname = jsonstu.getString("firstName");
            String team = jsonstu.getString("team");
            String role = jsonstu.getString("role");
              proDataManager.getStudents().add(new Student(lastname,firstname,team,role));
        }
        
       //load course
        String subject = json.getString("subject");
        String semester = json.getString("semester");
        String number = json.getString("number");
        String year= json.getString("year");
        String title = json.getString("title");
        String name = json.getString("name");
        String home = json.getString("home");
        
        courseDataManager.setCourse(new Course(subject,number,semester,year,title,name,home));
        courseSpce.reloadWorkspace();
      
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
