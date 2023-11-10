/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Groups {
    private int gid;
    private String gname;
    //ManyToOne   //JoinColumn(name = "cid")
    private Courses course;
    //OneToOne(mappedBy = "group")
    private Instructors instructor;
    //ManyToMany  //JoinTable(name = "GroupStudent", 
    //joinColumns = @JoinColumn(name = "gid"), inverseJoinColumns = @JoinColumn(name = "stuid"))
    public ArrayList<Students> students;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Instructors getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructors instructor) {
        this.instructor = instructor;
    }

    public ArrayList<Students> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Students> students) {
        this.students = students;
    }
    
    

}
