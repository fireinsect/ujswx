package com.ujskylxwechat.kylxwechat.dataobject;

import lombok.Data;

@Data
public class ProjectFileDO {
    private String id;

    /*申请表Url地址*/
    private String fileUrl;

    /*结题文档地址*/
    private String overUrl;


}
