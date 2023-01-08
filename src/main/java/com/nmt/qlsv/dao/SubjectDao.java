package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    private List<Subject> listSubject;

    public List<Subject> getListSubject() {
        return listSubject;
    }

    public List<Subject> findAll() {
        listSubject = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT sub.Id, sub.Name, sub.Credit, sub.TeacherId, tea.Name TeacherName " +
                    "FROM Subject sub JOIN Teacher tea ON sub.TeacherId = tea.Id";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject subject = new Subject(resultSet.getInt("Id"), resultSet.getString("Name"),
                        resultSet.getInt("Credit"), resultSet.getInt("TeacherId"),
                        resultSet.getString("TeacherName"));
                listSubject.add(subject);
            }
            return listSubject;
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

    public Integer getSubjectIdBySubjectName(String subjectName) {
        findAll();
        for (Subject subject : listSubject) {
            if (subject.getName().equalsIgnoreCase(subjectName.trim())) {
                return subject.getId();
            }
        }
        return null;
    }

    public void save(Subject subject) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        PreparedStatement statement;
        String query;
        if (subject.getId() == null)
            query = "INSERT INTO Subject(Name, Credit, TeacherId) VALUES(?, ?, ?)";
        else
            query = "UPDATE Subject SET Name = ?, Credit = ?, TeacherId = ? WHERE Id = ?";
        statement = con.prepareStatement(query);
        executeUpdate(statement, subject);
        statement.close();
        con.close();
    }

    private void executeUpdate(PreparedStatement statement, Subject subject) throws SQLException {
        statement.setString(1, subject.getName());
        statement.setInt(2, subject.getCredit());
        statement.setInt(3, subject.getTeacherId());
        if (subject.getId() != null)
            statement.setInt(4, subject.getId());
        statement.executeUpdate();
    }

    public void deleteById(Integer id) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        String query = "DELETE FROM Subject WHERE Id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public List<Subject> findByNameContaining(String key) {
        List<Subject> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT sub.Id, sub.Name, sub.Credit, sub.TeacherId, tea.Name TeacherName " +
                    "FROM Subject sub JOIN Teacher tea ON sub.TeacherId = tea.Id " +
                    "WHERE sub.Name LIKE '%" + key + "%'";
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt("Id"), rs.getString("Name"),
                        rs.getInt("Credit"), rs.getInt("TeacherId"),
                        rs.getString("TeacherName"));
                result.add(subject);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                statement.close();
                rs.close();
            } catch (SQLException e1) {
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
            String query = "SELECT sub.Name SubjectName, sub.Credit, tea.Name TeacherName " +
                    "FROM Subject sub JOIN Teacher tea ON sub.TeacherId = tea.Id";
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

}
