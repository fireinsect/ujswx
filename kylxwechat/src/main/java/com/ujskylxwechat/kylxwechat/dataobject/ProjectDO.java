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
    private String teachCollege;

    /**
     * 主持人id
     */
    private Long leaderId;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 参与者1的id
     */
    private Long invitee1Id;

    /**
     * 参与者2的id
     */
    private Long invitee2Id;

    /**
     * 参与者3的id
     */
    private Long invitee3Id;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;


}
