package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User loggingInUser;
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
                {
                    loggingInUser = new User(user.getUsername(), user.getPassword(), resultSet.getString("Fullname"));
                    return true;
                }
            }
            catch(SQLException e)
            {
            }
            finally {
                try{
                    resultSet.close();
                    statement.close();
                    con.close();
                }
                catch(SQLException e1)
                {
                }
            }
        }
        return false;
    }

    public void updatePassword(User user) throws SQLException {
        Connection con = ConnectionDao.getConnection();
        String query = "UPDATE DBUser SET Password = ? WHERE Username = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, user.getPassword());
        statement.setString(2, user.getUsername());
        statement.executeUpdate();
        statement.close();
        con.close();
    }
}
