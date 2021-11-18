package com.ujskylxwechat.kylxwechat.dataobject;

import java.time.LocalDateTime;

public class UserDO {
    private String id;

    private String openid;

    private String name;

    private String studentId;

    private String mobileNumber;

    private String email;

    /*所属学院*/
    private String college;

    /*所属专业*/
    private String major;

    /*班级*/
    private String classs;

    private String projectId;

    private int permission=1;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getPermission() {
        return permission;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String toString(){
        return "name:" +this.getName()
                +"\nopenid:"+this.getOpenid()
                +"\nstudent_id:"+this.getStudentId()
                +"\nmobile_number:"+this.getMobileNumber()
                +"\nemail:"+this.getEmail()
                +"\ncollege:"+this.getCollege()
                +"\nmajor:"+this.getMajor()
                +"\nclasss:"+this.getClasss();
    }

}
