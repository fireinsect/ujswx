package com.ujskylxwechat.kylxwechat.dataobject;

public class ProjectDO {
    private long id;

    private String title;

    private String type;

    private String classs;

    /*项目类型识别码*/
    private String sign;

    /*指导老师名称*/
    private String teacherName;

    /*指导老师学院*/
    private String teachColl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeachColl() {
        return teachColl;
    }

    public void setTeachColl(String teachColl) {
        this.teachColl = teachColl;
    }

}
