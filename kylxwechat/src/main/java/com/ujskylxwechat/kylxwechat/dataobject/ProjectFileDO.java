package com.ujskylxwechat.kylxwechat.dataobject;

public class ProjectFileDO {
    private String id;

    /*申请表Url地址*/
    private String fileUrl;

    /*结题文档地址*/
    private String overUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getOverUrl() {
        return overUrl;
    }

    public void setOverUrl(String overUrl) {
        this.overUrl = overUrl;
    }
}
