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
public class editStuTrans implements jTPS_Transaction{
    ProjectData data;
    Student stu;
    Student newStu;
    
    public editStuTrans(ProjectData data,Student stu,Student newStu){
        this.data = data;
        this.stu= stu;
        this.newStu = newStu;
    }
    
    
    @Override
    public void doTransaction() {
        data.getStudents().add(newStu);
        data.getStudents().remove(stu);
    }

    @Override
    public void undoTransaction() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        data.getStudents().remove(newStu);
        data.getStudents().add(stu);
    }
    
}
