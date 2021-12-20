package com.ujskylxwechat.kylxwechat.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description:
 * @date 2021/12/412:06
 */
@Data
public class ProjectInfo implements Serializable {
    private Long projectId;
    private String projectName;
    private String sign;
    private String teacherDept;
    private String filePath;
    private String teacherName;
    private final static String URL = "/pages/project/project";

}
