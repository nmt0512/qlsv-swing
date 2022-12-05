package com.nmt.qlsv.entity;

import java.sql.Timestamp;

public class Student {
    private Integer id;
    private String studentId;
    private String name;
    private Integer age;
    private Timestamp birthday;
    private String studentClass;
    private String address;
    private String hometown;
    private Float gpa;

    public Student(String studentId, String name, Integer age, Timestamp birthday,
                   String studentClass, String address, String hometown, Float gpa) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.studentClass = studentClass;
        this.address = address;
        this.hometown = hometown;
        this.gpa = gpa;
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

    public void setStudentId(String stuId) {
        this.studentId = stuId;
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

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
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

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }
}
