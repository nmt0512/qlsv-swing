package com.nmt.qlsv.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.nmt.qlsv.entity.Student;

public class StudentDao {
    private List<Student> listStudent;

    public StudentDao() {
        findAll();
    }

    private PreparedStatement mapStudentToQuery(PreparedStatement statement, Student student) throws SQLException {
        statement.setString(1, student.getStudentId());
        statement.setString(2, student.getName());
        statement.setInt(3, student.getAge());
        statement.setTimestamp(4, student.getBirthday());
        statement.setString(5, student.getStudentClass());
        statement.setString(6, student.getAddress());
        statement.setString(7, student.getHometown());
        if (student.getId() != null)
            statement.setInt(8, student.getId());
        return statement;
    }

    private void save(String query, Student... student) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionDao.getConnection();
        statement = con.prepareStatement(query);
        if (student != null)
            statement = mapStudentToQuery(statement, student[0]);
        statement.executeUpdate();
        try {
            statement.close();
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }


    public List<Student> getListStudent() {
        return this.listStudent;
    }

    public List<Student> findAll(String... classParam) {
        this.listStudent = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Student";
            if (classParam.length != 0 && !classParam[0].equals("All")) {
                query += " WHERE Class = ?";
                statement = con.prepareStatement(query);
                statement.setString(1, classParam[0]);
            } else {
                statement = con.prepareStatement(query);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("Id"));
                student.setStudentId(resultSet.getString("StudentId"));
                student.setName(resultSet.getString("Name"));
                student.setAge(resultSet.getInt("Age"));
                student.setBirthday(resultSet.getTimestamp("Birthday"));
                student.setStudentClass(resultSet.getString("Class"));
                student.setAddress(resultSet.getString("Address"));
                student.setHometown(resultSet.getString("Hometown"));
                listStudent.add(student);
            }
            return listStudent;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public List<String> getListColumnName() {
        List<String> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Student";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                results.add(rsmd.getColumnName(i));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public void add(Student student) throws SQLException {
        String query = "INSERT INTO Student(StudentId, Name, Age, Birthday, Class, Address, Hometown) " +
                "VALUES(?,?,?,?,?,?,?)";
        save(query, student);
    }

    public void edit(Student student) throws SQLException {
        String query = "UPDATE Student SET StudentId = ?, Name = ?, Age = ?, Birthday = ?, Class = ?, Address = ?, " +
                "Hometown = ? WHERE Id = ?";
        save(query, student);
    }

    public void delete(Student student) throws SQLException {
        String query = "DELETE FROM Student WHERE Id = " + student.getId();
        save(query, null);
    }

    //sort student list by name ascend
    public void sortStudentByName() {
        Collections.sort(listStudent, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                int lastIndexStudent1 = student1.getName().trim().lastIndexOf(" ") + 1;
                int lastIndexStudent2 = student2.getName().trim().lastIndexOf(" ") + 1;
                return student1.getName().substring(lastIndexStudent1)
                        .compareTo(student2.getName().substring(lastIndexStudent2));
            }
        });
    }

}