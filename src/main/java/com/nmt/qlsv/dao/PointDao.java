package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PointDao {
    private List<Point> pointList;
    private List<Point> searchedPointList;

    public PointDao() {
        this.pointList = new ArrayList<>();
    }

    private void executeUpdate(PreparedStatement statement, Point point)
            throws SQLException {
        statement.setFloat(1, point.getPoint1());
        statement.setFloat(2, point.getPoint2());
        statement.setFloat(3, point.getPointFinal());
        statement.setFloat(4, point.getTotalPoint());
        statement.setString(5, point.getStudentId());
        statement.setInt(6, point.getSubjectId());
        statement.executeUpdate();
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void save(Point point, Boolean isUpdatingProcess) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        String query;
        con = ConnectionDao.getConnection();
        if (!isUpdatingProcess)
            query = "INSERT INTO Point(Point1, Point2, PointFinal, TotalPoint, StudentId, SubjectId) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
        else
            query = "UPDATE Point SET Point1 = ?, Point2 = ?, PointFinal = ?, " +
                    "TotalPoint = ? WHERE StudentId = ? AND SubjectId = ?";
        statement = con.prepareStatement(query);
        executeUpdate(statement, point);
        statement.close();
        con.close();
    }

    public void deletePoint(Point point) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionDao.getConnection();
        String query = "DELETE FROM Point WHERE StudentId = '" + point.getStudentId()
                + "' AND SubjectId = " + point.getSubjectId();
        statement = con.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public List<Point> findAll(String studentClass, Integer subjectId) {
        this.pointList = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT stu.StudentId, stu.Name, sub.Name SubjectName, p.Point1, p.Point2, p.PointFinal, p.TotalPoint, " +
                    "sub.Id SubjectId, cla.Name StudentClass FROM Student stu JOIN Point p ON stu.StudentId = p.StudentId " +
                    "JOIN Subject sub ON p.SubjectId = sub.Id JOIN Class cla ON stu.ClassId = cla.Id";
            if (studentClass != null || subjectId != null) {
                query += " WHERE ";
                if (studentClass != null && subjectId != null)
                    query += "cla.Name = '" + studentClass + "' AND sub.Id = " + subjectId;
                else {
                    if (studentClass != null)
                        query += "cla.Name = '" + studentClass + "'";
                    else
                        query += "sub.Id = " + subjectId;
                }
            }
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Point point = new Point(rs.getString("StudentId"), rs.getString("Name"),
                        rs.getString("SubjectName"), rs.getFloat("Point1"),
                        rs.getFloat("Point2"), rs.getFloat("PointFinal"),
                        rs.getFloat("TotalPoint"), rs.getInt("SubjectId"),
                        rs.getString("StudentClass"));
                pointList.add(point);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return pointList;
    }

    public List<String> getExcelListColumnName() {
        List<String> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT stu.StudentId, stu.Name, sub.Name SubjectName, p.Point1, p.Point2, p.PointFinal, p.TotalPoint " +
                    "FROM Student stu JOIN Point p ON stu.StudentId = p.StudentId " +
                    "JOIN Subject sub ON p.SubjectId = sub.Id";
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

    public void sortPointByName() {
        Collections.sort(pointList, new Comparator<Point>() {
            public int compare(Point point1, Point point2) {
                int lastIndexPoint1 = point1.getStudentName().trim().lastIndexOf(" ") + 1;
                int lastIndexPoint2 = point2.getStudentName().trim().lastIndexOf(" ") + 1;
                return point1.getStudentName().substring(lastIndexPoint1)
                        .compareTo(point2.getStudentName().substring(lastIndexPoint2));
            }
        });
    }

    public void sortByTotalPoint() {
        Collections.sort(pointList, new Comparator<Point>() {
            public int compare(Point point1, Point point2) {
                if (point1.getTotalPoint() < point2.getTotalPoint()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public void deleteByStudentId(String studentId) throws SQLException
    {
        Connection con = ConnectionDao.getConnection();
        String query = "DELETE FROM Point WHERE StudentId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, studentId);
        statement.executeUpdate();
        statement.close();
        con.close();
    }

    public List<Point> searchByNameOrStudentId(String key, String classKey, String subjectKey) {
        this.searchedPointList = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT stu.StudentId, stu.Name, sub.Name SubjectName, p.Point1, p.Point2, p.PointFinal, p.TotalPoint, " +
                    "sub.Id SubjectId, cla.Name StudentClass FROM Student stu JOIN Point p ON stu.StudentId = p.StudentId " +
                    "JOIN Subject sub ON p.SubjectId = sub.Id JOIN Class cla ON stu.ClassId = cla.Id " +
                    "WHERE (stu.StudentId LIKE '%" + key + "%' OR stu.Name LIKE N'%" + key + "%')";
            if (!classKey.equals("All")) {
                query += " AND cla.Name = '" + classKey + "'";
            }
            if (!subjectKey.equals("All")) {
                query += " AND sub.Name = '" + subjectKey + "'";
            }
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Point point = new Point(rs.getString("StudentId"), rs.getString("Name"),
                        rs.getString("SubjectName"), rs.getFloat("Point1"),
                        rs.getFloat("Point2"), rs.getFloat("PointFinal"),
                        rs.getFloat("TotalPoint"), rs.getInt("SubjectId"),
                        rs.getString("StudentClass"));
                searchedPointList.add(point);
            }
            return searchedPointList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
