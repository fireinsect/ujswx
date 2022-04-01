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

    private String educationBackground;

    /*所属学院*/
    private String college;

//    /*所属专业*/
//    private String major;


    /*专业班级*/
    private String classs;

    private Long projectId;

    private int permission=1;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;


}
