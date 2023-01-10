package com.nmt.qlsv.entity;

public class Clazz {
    private Integer id;
    private Integer sessionId;
    private String departmentCode;
    private String name;
    private Integer studentQuantity;

    public Clazz(Integer id, Integer sessionId, String departmentCode, String name, Integer studentQuantity) {
        this.id = id;
        this.sessionId = sessionId;
        this.departmentCode = departmentCode;
        this.name = name;
        this.studentQuantity = studentQuantity;
    }

    public Clazz(Integer sessionId, String departmentCode, String name, Integer studentQuantity) {
        this.sessionId = sessionId;
        this.departmentCode = departmentCode;
        this.name = name;
        this.studentQuantity = studentQuantity;
    }

    public Clazz() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentQuantity() {
        return studentQuantity;
    }

    public void setStudentQuantity(Integer studentQuantity) {
        this.studentQuantity = studentQuantity;
    }
}
