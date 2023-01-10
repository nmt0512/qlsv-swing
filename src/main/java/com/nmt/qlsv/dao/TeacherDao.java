package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Teacher;

import java.sql.*;
import java.util.*;

public class TeacherDao {
    private List<Teacher> teacherList;

    public TeacherDao()
    {
        teacherList = new ArrayList<>();
    }

    public List<Teacher> getTeacherList()
    {
        return teacherList;
    }

    public List<Teacher> findAll()
    {
        teacherList = new ArrayList<>();
        Connection con = ConnectionDao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            String query = "SELECT * FROM Teacher";
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next())
            {
                Teacher teacher = new Teacher(rs.getInt("Id"), rs.getString("Name"), rs.getInt("Age"),
                        rs.getString("Phone"), rs.getString("Email"), rs.getDate("Birthday"),
                        rs.getDate("StartWorking"), rs.getString("Hometown"));
                teacherList.add(teacher);
            }
            return teacherList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                con.close();
                statement.close();
                rs.close();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public void save(Teacher teacher) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        PreparedStatement statement;
        if(teacher.getId() == null)
        {
            String query = "INSERT INTO Teacher(Name, Age, Phone, Email, Birthday, StartWorking, Hometown) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(query);
            executeUpdate(statement, teacher);
        }
        else
        {
            String query = "UPDATE Teacher SET Name = ?, Age = ?, Phone = ?, Email = ?, Birthday = ?, StartWorking = ?, Hometown = ? " +
                    "WHERE Id = ?";
            statement = con.prepareStatement(query);
            executeUpdate(statement, teacher);
        }
        statement.close();
        con.close();
    }

    private void executeUpdate(PreparedStatement statement, Teacher teacher) throws SQLException {
        statement.setString(1, teacher.getName());
        statement.setInt(2, teacher.getAge());
        statement.setString(3, teacher.getPhone());
        statement.setString(4, teacher.getEmail());
        statement.setDate(5, teacher.getBirthday());
        statement.setDate(6, teacher.getStartWorking());
        statement.setString(7, teacher.getHometown());
        if(teacher.getId() != null)
            statement.setInt(8, teacher.getId());
        statement.executeUpdate();
    }

    public void deleteById(Integer id) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        String query = "DELETE FROM Teacher WHERE Id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public List<Teacher> findByNameContaining(String key)
    {
        List<Teacher> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Teacher WHERE Name LIKE N'%"+ key +"%'";
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            while(rs.next())
            {
                Teacher teacher = new Teacher(rs.getInt("Id"), rs.getString("Name"),
                        rs.getInt("Age"), rs.getString("Phone"),
                        rs.getString("Email"), rs.getDate("Birthday"), rs.getDate("StartWorking"),
                        rs.getString("Hometown"));
                result.add(teacher);
            }
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                con.close();
                statement.close();
                rs.close();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public List<String> getExcelColumnNameList() {
        List<String> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Teacher";
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

    public List<Teacher> sortByName()
    {
        List<Teacher> result = teacherList;
        result.sort(Comparator.comparing(Teacher::getName));
        return result;
    }
}
