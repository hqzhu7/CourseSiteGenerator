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
public class Course {
    private final  StringProperty subject;
    private final  StringProperty number;
    private final  StringProperty semester;
    private final  StringProperty year;
    private final StringProperty title;
    private final StringProperty name;
    private final StringProperty home;

public Course(String sub, String num, String seme, String yea, String tit, String nam, String hom){
        subject = new SimpleStringProperty(sub);
        number = new SimpleStringProperty(num);
        semester = new SimpleStringProperty(seme);
        year = new SimpleStringProperty(yea);
        title = new SimpleStringProperty(tit);
        name = new SimpleStringProperty(nam);
        home = new SimpleStringProperty(hom);
        }


    public String getSubject() {
        return subject.get();
    }

    public String getNumber() {
        return number.get();
    }

    public String getSemester() {
        return semester.get();
    }

    public String getYear() {
        return year.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getName() {
        return name.get();
    }

    public String getHome() {
        return home.get();
    }


}
