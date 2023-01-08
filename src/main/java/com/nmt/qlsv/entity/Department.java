package com.nmt.qlsv.entity;

public class Department {
    private Integer id;
    private String name;
    private String code;
    private Integer stuQuantity;
    private Integer teacherId;

    public Department(Integer id, String name, String code, Integer stuQuantity, Integer teacherId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.stuQuantity = stuQuantity;
        this.teacherId = teacherId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
