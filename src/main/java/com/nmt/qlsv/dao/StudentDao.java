package com.nmt.qlsv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.nmt.qlsv.entity.Student;

public class StudentDao {
    private List<Student> listStudent;

    public StudentDao()
    {
        this.listStudent = getListStudent();
    }

    private PreparedStatement mapStudentToQuery(PreparedStatement statement, Student student) throws SQLException {
        statement.setString(1, student.getStudentId());
        statement.setString(2, student.getName());
        statement.setInt(3, student.getAge());
        statement.setTimestamp(4, student.getBirthday());
        statement.setString(5, student.getStudentClass());
        statement.setString(6, student.getAddress());
        statement.setString(7, student.getHometown());
        statement.setFloat(8, student.getGpa());
        if(student.getId() != null)
            statement.setInt(9, student.getId());
        return statement;
    }

    private void save(String query, Student... student) {
        Connection con = null;
        PreparedStatement statement = null;
        try{
            con = ConnectionDao.getConnection();
            statement = con.prepareStatement(query);
            if(student != null)
                statement = mapStudentToQuery(statement, student[0]);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                statement.close();
                con.close();
            }
            catch(SQLException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    public List<Student> getCurrentListStudent()
    {
        return this.listStudent;
    }

    public List<Student> getListStudent()
    {
        List<Student> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Student";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                Student student = new Student();
                student.setId(resultSet.getInt("Id"));
                student.setStudentId(resultSet.getString("StudentId"));
                student.setName(resultSet.getString("Name"));
                student.setAge(resultSet.getInt("Age"));
                student.setBirthday(resultSet.getTimestamp("Birthday"));
                student.setStudentClass(resultSet.getString("Class"));
                student.setAddress(resultSet.getString("Address"));
                student.setHometown(resultSet.getString("Hometown"));
                student.setGpa(resultSet.getFloat("Gpa"));
                results.add(student);
            }
            return results;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                resultSet.close();
                statement.close();
                con.close();
            }
            catch(SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public void add(Student student)
    {
        String query = "INSERT INTO Student(StudentId, Name, Age, Birthday, Class, Address, Hometown, Gpa) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        save(query, student);
    }

    public void edit(Student student) {
        String query = "UPDATE Student SET StudentId = ?, Name = ?, Age = ?, Birthday = ?, Class = ?, Address = ?" +
                "Hometown = ?, Gpa = ? WHERE Id = ?";
        save(query, student);
    }

    public void delete(Student student) {
        String query = "DELETE FROM Student WHERE Id = " + student.getId();
        save(query, null);
    }

    //sort student list by name ascend
    public void sortStudentByName() {
        Collections.sort(listStudent, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                return student1.getName().compareTo(student2.getName());
            }
        });
    }

    //sort student list by GPA ascend
    public void sortStudentByGPA() {
        Collections.sort(listStudent, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                if (student1.getGpa() > student2.getGpa()) {
                    return 1;
                }
                return -1;
            }
        });
    }

}