package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Point;

import java.sql.*;
import java.util.*;

public class PointDao {
    private List<Point> pointList;

    public PointDao() {
        this.pointList = new ArrayList<>();
    }

    private PreparedStatement mapPointToQuery(PreparedStatement statement, Boolean updateFlag, Point point)
            throws SQLException {
        statement.setString(1, point.getStudentId());
        statement.setInt(2, point.getSubjectId());
        statement.setFloat(3, point.getPoint1());
        statement.setFloat(4, point.getPoint2());
        statement.setFloat(5, point.getPointFinal());
        statement.setFloat(6, point.getTotalPoint());
        if (updateFlag) {
            statement.setString(7, point.getStudentId());
            statement.setInt(8, point.getSubjectId());
        }
        return statement;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    private void save(String query, Boolean updateFunction, Point... points) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionDao.getConnection();
        statement = con.prepareStatement(query);
        if (points != null)
            statement = mapPointToQuery(statement, updateFunction, points[0]);
        statement.executeUpdate();
        try {
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPoint(Point point) throws SQLException {
        String query = "INSERT INTO Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) " +
                "VALUES(?, ?, ? ,?, ?, ?)";
        save(query, false, point);
    }

    public void updatePoint(Point point) throws SQLException {
        String query = "UPDATE Point SET StudentId = ?, SubjectId = ?, Point1 = ?, Point2 = ?, PointFinal = ?, " +
                "TotalPoint = ? WHERE StudentId = ? AND SubjectId = ?";
        save(query, true, point);
    }

    public void deletePoint(Point point) throws SQLException {
        String query = "DELETE FROM Point WHERE StudentId = '" + point.getStudentId()
                + "' AND SubjectId = " + point.getSubjectId();
        save(query, false, null);
    }

    public List<Point> findAll(Integer... subjectId) {
        this.pointList = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectionDao.getConnection();
            String query = "SELECT stu.StudentId, stu.Name, sub.Name SubjectName, p.Point1, p.Point2, p.PointFinal, p.TotalPoint, " +
                    "sub.Id SubjectId FROM Student stu JOIN Point p ON stu.StudentId = p.StudentId " +
                    "JOIN Subject sub ON p.SubjectId = sub.Id";
            if(subjectId.length != 0 && subjectId[0] != null)
                query += " WHERE sub.Id = " + subjectId[0];
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Point point = new Point(rs.getString("StudentId"), rs.getString("Name"),
                        rs.getString("SubjectName"), rs.getFloat("Point1"),
                        rs.getFloat("Point2"), rs.getFloat("PointFinal"),
                        rs.getFloat("TotalPoint"), rs.getInt("SubjectId"));
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
                if (point1.getTotalPoint() > point2.getTotalPoint()) {
                    return 1;
                }
                return -1;
            }
        });
    }
}
