package com.nmt.qlsv.entity;

public class Session {
    private Integer id;
    private Integer startYear;
    private Integer endYear;
    private Integer stuQuantity;
    private Integer teacherId;
    private String teacherName;

    public Session(Integer id, Integer startYear, Integer endYear, Integer stuQuantity, Integer teacherId, String teacherName) {
        this.id = id;
        this.startYear = startYear;
        this.endYear = endYear;
        this.stuQuantity = stuQuantity;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
    public Session()
    {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getStuQuantity() {
        return stuQuantity;
    }

    public void setStuQuantity(Integer stuQuantity) {
        this.stuQuantity = stuQuantity;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
