package com.nmt.qlsv.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.nmt.qlsv.entity.Student;

public class StudentDao {
    private List<Student> listStudent;
    private List<Student> listSearchedStudent;

    public StudentDao() {
        findAll();
        this.listSearchedStudent = new ArrayList<>();
    }

    public List<Student> getListStudent() {
        return this.listStudent;
    }

    public List<Student> getListSearchedStudent() {
        return this.listSearchedStudent;
    }

    private void executeUpdate(PreparedStatement statement, Student student) throws SQLException {
        statement.setString(1, student.getStudentId());
        statement.setString(2, student.getName());
        statement.setInt(3, student.getAge());
        statement.setDate(4, student.getBirthday());
        statement.setString(5, student.getAddress());
        statement.setString(6, student.getHometown());
        statement.setInt(7, student.getClassId());
        if (student.getId() != null)
            statement.setInt(8, student.getId());
        statement.executeUpdate();
    }

    public void save(Student student) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionDao.getConnection();
        String query;
        if (student.getId() == null)
            query = "INSERT INTO Student(StudentId, Name, Age, Birthday, Address, Hometown, ClassId) " +
                    "VALUES(?,?,?,?,?,?,?)";
        else
            query = "UPDATE Student SET StudentId = ?, Name = ?, Age = ?, Birthday = ?, Address = ?, " +
                    "Hometown = ?, ClassId = ? WHERE Id = ?";
        statement = con.prepareStatement(query);
        executeUpdate(statement, student);
        statement.close();
        con.close();
    }

    public List<Student> findAll(String... classParam) {
        this.listStudent = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT stu.Id, stu.StudentId, stu.Name, stu.Age, stu.Birthday, stu.Address, stu.Hometown, " +
                    "stu.ClassId, cla.Name ClassName FROM Student stu JOIN Class cla ON stu.ClassId = cla.Id";
            if (classParam.length != 0 && !classParam[0].equals("All")) {
                query += " WHERE cla.Name = ?";
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
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setAddress(resultSet.getString("Address"));
                student.setHometown(resultSet.getString("Hometown"));
                student.setClassId(resultSet.getInt("ClassId"));
                student.setClassName(resultSet.getString("ClassName"));
                listStudent.add(student);
            }
            return listStudent;
        }
        catch (SQLException e) {
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                con.close();
            }
            catch (SQLException | NullPointerException e1) {
            }
        }
        return null;
    }

    public String findClassNameByStudentId(String studentId) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String result = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT cla.Name ClassName FROM Student stu JOIN Class cla ON stu.ClassId = cla.Id " +
                    "WHERE stu.StudentId = '" + studentId + "'";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("ClassName");
            }
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
        return result;
    }

    public String findClassNameById(Integer id) throws SQLException {
        String studentClass = null;
        Connection con = ConnectionDao.getConnection();
        String query = "SELECT cla.Name ClassName FROM Student stu JOIN Class cla ON stu.ClassId = cla.Id " +
                "WHERE stu.Id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next())
            studentClass = rs.getString("ClassName");
        rs.close();
        statement.close();
        con.close();
        return studentClass;
    }

    public List<String> getListColumnName() {
        List<String> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT stu.Id, stu.StudentId, stu.Name, stu.Age, stu.Birthday, stu.Address, stu.Hometown, " +
                    "cla.Name Class FROM Student stu JOIN Class cla ON stu.ClassId = cla.Id";
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

    public void deleteById(Integer id) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionDao.getConnection();
        String query = "DELETE FROM Student WHERE Id = ?";
        statement = con.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
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

    public List<Student> searchByNameOrStudentId(String key, String classKey) {
        this.listSearchedStudent = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT stu.Id, stu.StudentId, stu.Name, stu.Age, stu.Birthday, stu.Address, stu.Hometown, " +
                    "stu.ClassId, cla.Name ClassName FROM Student stu JOIN Class cla ON stu.ClassId = cla.Id " +
                    "WHERE (stu.StudentId LIKE '%" + key + "%' OR stu.Name LIKE N'%" + key + "%')";
            if (!classKey.equals("All"))
                query += " AND cla.Name = '" + classKey + "'";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("Id"));
                student.setStudentId(resultSet.getString("StudentId"));
                student.setName(resultSet.getString("Name"));
                student.setAge(resultSet.getInt("Age"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setAddress(resultSet.getString("Address"));
                student.setHometown(resultSet.getString("Hometown"));
                student.setClassId(resultSet.getInt("ClassId"));
                student.setClassName(resultSet.getString("ClassName"));
                listSearchedStudent.add(student);
            }
            return listSearchedStudent;
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

}