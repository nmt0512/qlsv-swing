package com.nmt.qlsv.entity;

import java.sql.Date;

public class Teacher {
    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
    private Date birthday;
    private Date startWorking;
    private String hometown;

    public Teacher(Integer id, String name, Integer age, String phone, String email, Date birthday, Date startWorking, String hometown) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.startWorking = startWorking;
        this.hometown = hometown;
    }

    public Teacher() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(Date startWorking) {
        this.startWorking = startWorking;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
