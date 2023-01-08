package com.nmt.qlsv.entity;

public class Subject {
    private Integer id;
    private String name;
    private Integer credit;
    private Integer teacherId;
    private String teacherName;

    public Subject() {
    }

    public Subject(Integer id, String name, Integer credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public Subject(Integer id, String name, Integer credit, Integer teacherId, String teacherName) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
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
