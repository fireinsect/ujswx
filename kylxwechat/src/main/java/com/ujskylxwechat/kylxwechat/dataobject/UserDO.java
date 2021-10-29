package com.ujskylxwechat.kylxwechat.dataobject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDO {
    private String id;

    private String name;

    private String email;

    /*所属学院*/
    private String college;

    /*所属专业*/
    private String major;

    /*班级*/
    private String classs;

    private List<ProjectDO> projects;

    private int permission;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

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

    public List<ProjectDO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDO> projects) {
        this.projects = projects;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
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
}
