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
public class Schedule {
    
     private final StringProperty type;
     private final StringProperty month;
     private final StringProperty day;
     private final StringProperty title;
     private final StringProperty topic;
     private final StringProperty link;
     private final StringProperty date;
     private final StringProperty time;
     private final StringProperty criteria;
     

 
     /*public Schedule(String type, String date,String title,String topic,String link){
         this.type = new SimpleStringProperty(type);
         String[] parts = date.split("/");
         
         this.month = new SimpleStringProperty(parts[0]);
         this.day = new SimpleStringProperty(parts[1]);
         this.title = new SimpleStringProperty(title);
         this.topic = new SimpleStringProperty(topic);
         this.link = new SimpleStringProperty(link);
         this.date = new SimpleStringProperty(date);
         this.criteria = new SimpleStringProperty("");
         this.time = new SimpleStringProperty("");
     }*/
     
     public Schedule(String type, String date,String title,String topic,String link,String time, String criteria){
         this.type = new SimpleStringProperty(type);
         String[] parts = date.split("/");
         
         this.month = new SimpleStringProperty(parts[0]);
         this.day = new SimpleStringProperty(parts[1]);
         this.title = new SimpleStringProperty(title);
         this.topic = new SimpleStringProperty(topic);
         this.link = new SimpleStringProperty(link);
         this.date = new SimpleStringProperty(date);
         this.criteria = new SimpleStringProperty(criteria);
         this.time = new SimpleStringProperty(time);

     }
  

    public String getType() {
        return type.get();
    }

    public String getMonth() {
        return month.get();
    }

     public String getDay() {
        return day.get();
    }
    
    public String getTitle() {
        return title.get();
    }

    public String getDate() {
        return date.get();
    }
    public String getTopic() {
        return topic.get();
    }

   public String getLink(){
       return link.get();
   }

    public String getTime() {
        return time.get();
    }

    public String getCriteria() {
        return criteria.get();
    }

    
    
    
    
}
