/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Hengqi Zhu
 */
public class Template {
    
    private final StringProperty navbar;
    private final StringProperty name;
    private final StringProperty script;
    private BooleanProperty checked;
    
    public Template(String navbar, String name, String script, boolean checeked){
        this.navbar=new SimpleStringProperty(navbar);
        this.name = new SimpleStringProperty(name);
        this.script = new SimpleStringProperty(script);
        this.checked = new SimpleBooleanProperty(checeked);

    }
    
    public String getNavbar(){return navbar.get();}
    public String getName() {return name.get();}
    public String getScript() {return script.get();}
    public ObservableValue<Boolean> getChecked() {return checked;}

    public void setChecked(Boolean bool) {
        checked.set(bool);
    }

    /*public ObservableValue<Boolean>  setChecked() {
       if(checked.get()){
           setChecked(false);
           System.out.println(checked.get());
       return new SimpleBooleanProperty(false);}
       else
       { setChecked(true);
       System.out.println(checked.get());
          return new  SimpleBooleanProperty(true);}
    }*/
    
}
