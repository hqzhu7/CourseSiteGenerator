/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Hengqi Zhu
 */
public class Student {
    private final StringProperty first;
     private final StringProperty last;
     private final StringProperty team;
     private final StringProperty role;
     
     public Student(String first,String last,String team,String role){
         this.first = new SimpleStringProperty(first);
         this.last = new SimpleStringProperty(last);
         this.team = new SimpleStringProperty(team);
         this.role = new SimpleStringProperty(role);
       
     }

    public String getFirst() {
        return first.get();
    }

    public String getLast() {
        return last.get();
    }

    public String getTeam() {
        return team.get();
    }

    public String getRole() {
        return role.get();
    }
     
     
     
     
}
