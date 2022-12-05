package com.nmt.qlsv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
    public static Connection getConnection()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;database=qlsv;integratedSecurity=true;encrypt=true;" +
                    "trustServerCertificate=true";
            String user = "thieu";
            String password = "nmtkma0550";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
