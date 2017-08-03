/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.data.ProjectData;
import csg.data.Team;
import jtps.jTPS_Transaction;

/**
 *
 * @author Hengqi
 */
public class delTeamTrans implements jTPS_Transaction{
    ProjectData data;
    Team team;
    
    public delTeamTrans(ProjectData data,Team team){
        this.data =data;
        this.team = team;
    }
    @Override
    public void doTransaction() {
        data.getTeams().remove(team);
        
    }

    @Override
    public void undoTransaction() {
         data.getTeams().add(team);
    }
    
    
    
}
