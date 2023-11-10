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
public class Courses {
    private int cid;
    private String ccode;
    private String cname;
    //OneToMany(mappedBy = "course")
    private ArrayList<Tests> tests; // Danh sách các bài kiểm tra trong khóa học
    //OneToMany(mappedBy = "course")
    private ArrayList<Groups> groups;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public ArrayList<Tests> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Tests> tests) {
        this.tests = tests;
    }

    public ArrayList<Groups> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Groups> groups) {
        this.groups = groups;
    }

    
    
}
