package com.nmt.qlsv.entity;

import java.sql.Date;

public class Student {
    private Integer id;
    private String studentId;
    private String name;
    private Integer age;
    private Date birthday;
    private String studentClass;
    private String address;
    private String hometown;

    public Student(String studentId, String name, Integer age, Date birthday,
                   String studentClass, String address, String hometown) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.studentClass = studentClass;
        this.address = address;
        this.hometown = hometown;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

}
