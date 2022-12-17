package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    private List<Subject> listSubject;

    public List<Subject> getListSubject() {
        return listSubject;
    }

    public List<Subject> findAll()
    {
        listSubject = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Subject";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                Subject subject = new Subject(resultSet.getInt("Id"), resultSet.getString("Name"));
                listSubject.add(subject);
            }
            return listSubject;
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

    public Integer getSubjectIdBySubjectName(String subjectName)
    {
        findAll();
        for(Subject subject: listSubject)
        {
            if(subject.getName().equalsIgnoreCase(subjectName.trim()))
            {
                return subject.getId();
            }
        }
        return null;
    }

}
