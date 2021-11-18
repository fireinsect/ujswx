package com.ujskylxwechat.kylxwechat.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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



    @Override
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
