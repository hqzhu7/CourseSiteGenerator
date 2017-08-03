/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
/**
 *
 * @author Hengqi Zhu
 */
public class Work {
    String semester;
    private final StringProperty name;
    private final StringProperty link;
    ObservableList<Student> students;
   Team team;
    String semseter;
    
    public Work(Team t, ObservableList<Student> stu){
        team = t;
        this.name = new SimpleStringProperty(t.getName());
        this.link = new SimpleStringProperty(t.getLink());
        students = stu;
    
    }

    public String getSemester() {
        return semester;
    }

    public String getName() {
        return name.get();
    }

    public String getLink() {
        return link.get();
    }

    public ObservableList<Student> getStudents() {
        return students;
    }
     
    public JsonArray getStudentName(String team) {
        JsonArrayBuilder sutNameArrayBuilder = Json.createArrayBuilder();
        for(Student stu:students)
            if( stu.getTeam().equals(team))
                sutNameArrayBuilder.add(stu.getFirst()+' '+stu.getLast());
        return sutNameArrayBuilder.build();
        
        /*String temp = "";
        for(Student stu : students)
            temp += '"'+stu.getFirst()+' '+stu.getLast()+ "\",";
        temp = temp.substring(0, temp.length()-1);
        temp = '['+temp+']';
        return temp;   */
    }

    
    
}
