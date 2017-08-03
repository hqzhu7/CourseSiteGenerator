/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.controllers;

import csg.data.ProjectData;
import csg.data.Student;
import jtps.jTPS_Transaction;

/**
 *
 * @author Hengqi
 */
public class addStuTrans implements jTPS_Transaction {
    ProjectData data;
    Student stu;
    
    public addStuTrans(ProjectData data,Student stu){
        this.data =data;
        this.stu = stu;
    }
    
    @Override
    public void doTransaction() {
        data.getStudents().add(stu);
    }

    @Override
    public void undoTransaction() {
       data.getStudents().remove(stu);
    }
    
}
