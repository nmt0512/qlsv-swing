package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public Boolean checkUser(User user) {
        if (user != null) {
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try{
                con = ConnectionDao.getConnection();
                String query = "SELECT * FROM DBUser WHERE Username = '" + user.getUsername() + "' " +
                        " AND Password = '" + user.getPassword() +"'";
                statement = con.prepareStatement(query);
                resultSet = statement.executeQuery();
                if(resultSet.next())
                    return true;
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
        }
        return false;
    }
}
