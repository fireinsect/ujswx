package com.ujskylxwechat.kylxwechat.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectFileDO {
    private Long projectId;

    /*申请表Url地址*/
    private String applicationUrl;

    /*结题文档地址*/
    private String overUrl;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

}
