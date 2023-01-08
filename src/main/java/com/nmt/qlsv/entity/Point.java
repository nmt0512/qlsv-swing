package com.nmt.qlsv.entity;

import java.util.HashMap;
import java.util.Map;

public class Point {
    private String studentId;
    private String studentName;
    private String subjectName;
    private Float point1;
    private Float point2;
    private Float pointFinal;
    private Float totalPoint;
    private Integer subjectId;
    private String studentClass;

    public Point(String studentId, String studentName, String subjectName, Float point1, Float point2,
                 Float pointFinal, Float totalPoint, Integer subjectId, String studentClass) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.point1 = point1;
        this.point2 = point2;
        this.pointFinal = pointFinal;
        this.totalPoint = totalPoint;
        this.subjectId = subjectId;
        this.studentClass = studentClass;
    }

    public Point() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Float getPoint1() {
        return point1;
    }

    public void setPoint1(Float point1) {
        this.point1 = point1;
    }

    public Float getPoint2() {
        return point2;
    }

    public void setPoint2(Float point2) {
        this.point2 = point2;
    }

    public Float getPointFinal() {
        return pointFinal;
    }

    public void setPointFinal(Float pointFinal) {
        this.pointFinal = pointFinal;
    }

    public Float getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Float totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void calculateTotalPoint()
    {
        this.totalPoint = (float)(point1 * 0.1 + point2 * 0.2 + pointFinal * 0.7);
    }
}
