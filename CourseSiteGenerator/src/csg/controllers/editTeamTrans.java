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
public class editTeamTrans implements jTPS_Transaction{
    ProjectData data;
    Team team;
    Team newTeam;
    
    public editTeamTrans(ProjectData data,Team team,Team newTeam){
        this.data= data;
        this.team =team;
        this.newTeam=newTeam;
    }

    @Override
    public void doTransaction() {
        data.getTeams().remove(team);
        data.getTeams().add(newTeam);
    }

    @Override
    public void undoTransaction() {
        data.getTeams().add(team);
        data.getTeams().remove(newTeam);
    }
    
}
