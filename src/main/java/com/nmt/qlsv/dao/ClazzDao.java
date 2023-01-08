package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Clazz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClazzDao {
    private List<Clazz> listClazz;

    public ClazzDao()
    {
        findAll();
    }

    public List<Clazz> findAll()
    {
        String query = "SELECT * FROM Class";
        return query(query);
    }

    public List<Clazz> findBySession(Integer sessionId)
    {
        String query = "SELECT * FROM Class" + "WHERE SessionId = "+ sessionId;
        return query(query);
    }

    private List<Clazz> query(String query)
    {
        listClazz = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Clazz clazz = new Clazz(resultSet.getInt("SessionId"),
                        resultSet.getString("DepartmentCode"), resultSet.getString("Name"),
                        resultSet.getInt("StuQuantity"));
                listClazz.add(clazz);
            }
            return listClazz;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
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
