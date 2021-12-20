package com.ujskylxwechat.kylxwechat.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author HP
 */
@Data
public class ProjectDO {
    private Long id;

    private String title;

    private String type;

    private String classs;

    /**
     * 项目类型识别码
     */
    private String sign;

    /**
     * 指导老师名称
     */
    private String teacherName;

    /**
     * 指导老师学院
     */
    private String teacherCollege;

    /**
     * 主持人学号
     */
    private String leaderId;

    /**
     * 文件路径
     */
    private String filePath;

    private Long teacherIndex;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;


}
