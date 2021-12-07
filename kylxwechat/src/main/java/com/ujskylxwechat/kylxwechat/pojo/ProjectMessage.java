package com.ujskylxwechat.kylxwechat.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description:
 * @date 2021/12/410:56
 */
@Data
public class ProjectMessage implements Serializable {
    private String projectName;
    private String leaderId;
    /**
     * 参与者id与name集合
     */
    private ArrayList<String> inviteeIds;
    private ArrayList<String> inviteeNames;
}
