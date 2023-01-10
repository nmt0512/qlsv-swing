package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Clazz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClazzDao {
    private List<Clazz> clazzList;

    public ClazzDao() {
        clazzList = new ArrayList<>();
    }
    public List<Clazz> getClazzList()
    {
        return clazzList;
    }

    public List<Clazz> findAll() {
        clazzList = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            statement = con.prepareStatement("SELECT * FROM Class");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Clazz clazz = new Clazz(resultSet.getInt("Id"), resultSet.getInt("SessionId"),
                        resultSet.getString("DepartmentCode"), resultSet.getString("Name"),
                        resultSet.getInt("StuQuantity"));
                clazzList.add(clazz);
            }
            return clazzList;
        } catch (SQLException e) {

        } finally {
            try {
                resultSet.close();
                statement.close();
                con.close();
            } catch (SQLException e1) {

            }
        }
        return null;
    }

    public void save(Clazz clazz) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        PreparedStatement statement;
        String query;
        if (clazz.getId() == null)
            query = "INSERT INTO Class(SessionId, DepartmentCode, Name, StuQuantity) VALUES(?, ?, ?, ?)";
        else
            query = "UPDATE Class SET SessionId = ?, DepartmentCode = ?, Name = ?, StuQuantity = ? WHERE Id = ?";
        statement = con.prepareStatement(query);
        executeUpdate(statement, clazz);
        statement.close();
        con.close();
    }

    public void deleteById(Integer id) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        String query = "DELETE FROM Class WHERE Id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public void updateStuQuantityByClassName(String className, Boolean addStuQuantity) throws SQLException, NullPointerException {
        Integer oldQuantity = null;
        for (Clazz clazz : clazzList) {
            if (clazz.getName().equals(className))
                oldQuantity = clazz.getStudentQuantity();
        }
        Connection con = ConnectionDao.getConnection();
        String query = "UPDATE Class SET StuQuantity = ? WHERE Name = ?";
        PreparedStatement statement = con.prepareStatement(query);
        if (addStuQuantity)
            statement.setInt(1, oldQuantity + 1);
        else
            statement.setInt(1, oldQuantity - 1);
        statement.setString(2, className);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public Integer findSessionIdByClassName(String className) throws SQLException {
        Integer sessionId = null;
        Connection con = ConnectionDao.getConnection();
        String query = "SELECT SessionId FROM Class WHERE Name = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, className);
        ResultSet rs = statement.executeQuery();
        if(rs.next())
            sessionId = rs.getInt("SessionId");
        statement.close();
        con.close();
        return sessionId;
    }

    private void executeUpdate(PreparedStatement statement, Clazz clazz) throws SQLException {
        statement.setInt(1, clazz.getSessionId());
        statement.setString(2, clazz.getDepartmentCode());
        statement.setString(3, clazz.getName());
        statement.setInt(4, clazz.getStudentQuantity());
        if (clazz.getId() != null)
            statement.setInt(5, clazz.getId());
        statement.executeUpdate();
    }

    public Integer findIdByName(String name)
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer id = null;
        try {
            con = ConnectionDao.getConnection();
            statement = con.prepareStatement("SELECT Id FROM Class WHERE Name = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet.next())
                id = resultSet.getInt("Id");
        }
        catch (SQLException e) {

        }
        finally {
            try {
                resultSet.close();
                statement.close();
                con.close();
            } catch (SQLException e1) {

            }
        }
        return id;
    }


    public List<String> getExcelColumnNameList() {
        List<String> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Class";
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
