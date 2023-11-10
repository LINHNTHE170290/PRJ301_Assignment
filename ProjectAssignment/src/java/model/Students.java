/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Students {
    private int stuid;
    private String stucode;
    private String stuname;
    private Date birthday;
    private boolean gender;
    private String stuemail;
    private String stuphone;
    //OneToMany(mappedBy = "student")
    private ArrayList<Points> values = new ArrayList<>();
    private ArrayList<Groups> groups = new ArrayList<>();

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStucode() {
        return stucode;
    }

    public void setStucode(String stucode) {
        this.stucode = stucode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getStuemail() {
        return stuemail;
    }

    public void setStuemail(String stuemail) {
        this.stuemail = stuemail;
    }

    public String getStuphone() {
        return stuphone;
    }

    public void setStuphone(String stuphone) {
        this.stuphone = stuphone;
    }

    public ArrayList<Points> getValues() {
        return values;
    }

    public void setValues(ArrayList<Points> values) {
        this.values = values;
    }

    public ArrayList<Groups> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Groups> groups) {
        this.groups = groups;
    }
    
}
