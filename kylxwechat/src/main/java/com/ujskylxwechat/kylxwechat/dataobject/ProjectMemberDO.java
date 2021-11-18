package com.ujskylxwechat.kylxwechat.dataobject;

import lombok.Data;

@Data
public class ProjectMemberDO {
    private long id;

    /*队长学号*/
    private String leaderId;

    /*队员学号*/
    private String join2one;

    private String join2two;

    private String join2three;
}
