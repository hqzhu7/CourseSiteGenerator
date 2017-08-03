/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CSGApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hengqi Zhu
 */
public class ReciData {
    CSGApp app;

    ObservableList<Recitation> recitations;
    
    public ReciData(CSGApp initApp){
        app = initApp;

        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        recitations = FXCollections.observableArrayList();
        

    }

    public ObservableList<Recitation> getRecitations() {
        return recitations;
    }
    
    public void resetData(){
        recitations.clear();
    }
    
    public void removeRec(String str){
        for(Recitation r : recitations)
            if(r.getSection().equals(str))
            {recitations.remove(r);
            return;}
    
    }
    
    
}
