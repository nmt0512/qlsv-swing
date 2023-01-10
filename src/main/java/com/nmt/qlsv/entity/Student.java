package com.nmt.qlsv.entity;

import java.sql.Date;

public class Student {
    private Integer id;
    private String studentId;
    private String name;
    private Integer age;
    private Date birthday;
    private String address;
    private String hometown;
    private Integer classId;
    private String className;

    public Student(String studentId, String name, Integer age, Date birthday,
                   String address, String hometown, Integer classId, String className) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.address = address;
        this.hometown = hometown;
        this.classId = classId;
        this.className = className;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
