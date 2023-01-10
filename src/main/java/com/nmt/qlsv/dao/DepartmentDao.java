package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    private List<Department> departmentList;

    public DepartmentDao()
    {
        departmentList = new ArrayList<>();
    }

    public List<Department> findAll()
    {
        departmentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM Department";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department(resultSet.getInt("Id"),
                        resultSet.getString("Name"), resultSet.getString("Code"),
                        resultSet.getInt("StuQuantity"), resultSet.getInt("TeacherId"));
                departmentList.add(department);
            }
            return departmentList;
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
