package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDao {
    private List<Session> sessionList;

    public SessionDao()
    {
        sessionList = new ArrayList<>();
    }

    public List<Session> findAll()
    {
        sessionList = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT ses.Id, ses.StartYear, ses.EndYear, ses.StuQuantity, ses.TeacherId, tea.Name TeacherName " +
                    "FROM Session ses JOIN Teacher tea ON ses.TeacherId = tea.Id";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                Session session = new Session(resultSet.getInt("Id"), resultSet.getInt("StartYear"),
                        resultSet.getInt("EndYear"), resultSet.getInt("StuQuantity"),
                        resultSet.getInt("TeacherId"), resultSet.getString("TeacherName"));
                sessionList.add(session);
            }
            return sessionList;
        }
        catch (SQLException e)
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

    public void save(Session session) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        String query;

        String ifExistQuery = "SELECT * FROM Session WHERE Id = ?";
        statement = con.prepareStatement(ifExistQuery);
        statement.setInt(1, session.getId());
        resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            query = "UPDATE Session SET Id = ?, StartYear = ?, EndYear = ?, StuQuantity = ?, TeacherId = ? WHERE Id = ?";
            statement = con.prepareStatement(query);
            executeUpdate(statement, session, true);
        }
        else
        {
            query = "INSERT INTO Session VALUES(?, ?, ?, ?, ?)";
            statement = con.prepareStatement(query);
            executeUpdate(statement, session, false);
        }

        resultSet.close();
        statement.close();
        con.close();
    }

    private void executeUpdate(PreparedStatement statement, Session session, Boolean updateFlag) throws SQLException {
        statement.setInt(1, session.getId());
        statement.setInt(2, session.getStartYear());
        statement.setInt(3, session.getEndYear());
        statement.setInt(4, session.getStuQuantity());
        statement.setInt(5, session.getTeacherId());
        if(updateFlag)
            statement.setInt(6, session.getId());
        statement.executeUpdate();
    }

    public void deleteById(Integer id) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        String query = "DELETE FROM Session WHERE Id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public List<String> getExcelColumnNameList()
    {
        List<String> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT ses.Id, ses.StartYear, ses.EndYear, ses.StuQuantity, tea.Name TeacherName " +
                    "FROM Session ses JOIN Teacher tea ON ses.TeacherId = tea.Id";
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
