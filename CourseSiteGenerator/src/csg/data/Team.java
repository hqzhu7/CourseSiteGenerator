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
public class Team {
    
    private final StringProperty name;
    // private final StringProperty color;
   private final StringProperty color;
    private final StringProperty link;
   
     private final StringProperty textColor;
     //private final StringProperty link;
     
     public Team(String name, String color, String textColor,String link){
         this.name = new SimpleStringProperty(name); 
         this.color = new SimpleStringProperty(color); 
         this.textColor = new SimpleStringProperty(textColor); 
         this.link = new SimpleStringProperty(link); 
        
     
     }

    public String getName() {
        return name.get();
    }

    public String getColor() {
        return color.get();
    }

    public String getLink() {
        return link.get();
    }

    public String getRed(){
       String red = color.get().substring(0, 2);
       int temp = hex2Decimal(red.charAt(0))*16 +  hex2Decimal(red.charAt(1));
       return temp+"";
       
    }
    public String getGreen(){
        
        String red = color.get().substring(2, 4);
       int temp = hex2Decimal(red.charAt(0))*16 +  hex2Decimal(red.charAt(1));
       return temp+"";
    }
    public String getBlue(){
       
       String red = color.get().substring(4, 6);
       int temp = hex2Decimal(red.charAt(0))*16 +  hex2Decimal(red.charAt(1));
       return temp+"";
    }

    public int hex2Decimal(char c) {
        String digits = "0123456789abcdef";
        return digits.indexOf(c);

    }
    
    public String getTextColor() {
        return textColor.get();
    }

   public String toString(){
       return getName();
   }
    
     
}
