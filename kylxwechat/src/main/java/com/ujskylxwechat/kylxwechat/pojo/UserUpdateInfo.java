package com.ujskylxwechat.kylxwechat.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateInfo implements Serializable {
    String studentId;
    String email;
    String mobileNumber;
    String college;
    String major;
    String classs;
}
