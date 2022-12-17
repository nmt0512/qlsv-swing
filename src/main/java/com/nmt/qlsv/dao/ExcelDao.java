package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.Point;
import com.nmt.qlsv.entity.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class ExcelDao {
    public static void importStudentExcelToDatabase(String filePath) {
        String excelFilePath = filePath;
        try {
            Connection con = ConnectionDao.getConnection();
            con.setAutoCommit(false);
            String query = "INSERT INTO Student(StudentId, Name, Age, Birthday, Class, Address, Hometown) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);

            FileInputStream inputStream = new FileInputStream(excelFilePath);

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            rowIterator.next(); // skip the header row
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            String studentId = nextCell.getStringCellValue();
                            statement.setString(1, studentId);
                            break;
                        case 1:
                            String name = nextCell.getStringCellValue();
                            statement.setString(2, name);
                            break;
                        case 2:
                            Integer age = (int) nextCell.getNumericCellValue();
                            statement.setInt(3, age);
                            break;
                        case 3:
                            Date birthday = nextCell.getDateCellValue();
                            statement.setTimestamp(4, new Timestamp(birthday.getTime()));
                            break;
                        case 4:
                            String clazz = nextCell.getStringCellValue();
                            statement.setString(5, clazz);
                            break;
                        case 5:
                            String address = nextCell.getStringCellValue();
                            statement.setString(6, address);
                            break;
                        case 6:
                            String hometown = nextCell.getStringCellValue();
                            statement.setString(7, hometown);
                            break;
                    }
                }
                statement.addBatch();
                statement.executeBatch();
            }
            workbook.close();
            con.commit();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Add to database error");
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "Error reading file");
        } catch (IOException e2) {
            JOptionPane.showMessageDialog(null, "Error reading file");
        }
    }

    public static void exportStudentDatabaseToExcel(List<Student> listStudent, List<String> listColumn)
            throws IOException, SQLException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");

        XSSFRow row = spreadsheet.createRow(0);
        for(int cellId = 0; cellId < listColumn.size(); cellId ++)
        {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(listColumn.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Student student : listStudent) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < Student.class.getDeclaredFields().length; cellId ++) {
                Cell cell = row.createCell(cellId);
                switch (cellId) {
                    case 0:
                        cell.setCellValue(student.getId());
                        break;
                    case 1:
                        cell.setCellValue(student.getStudentId());
                        break;
                    case 2:
                        cell.setCellValue(student.getName());
                        break;
                    case 3:
                        cell.setCellValue(student.getAge());
                        break;
                    case 4:
                        cell.setCellValue(student.getBirthday());
                        break;
                    case 5:
                        cell.setCellValue(student.getStudentClass());
                        break;
                    case 6:
                        cell.setCellValue(student.getAddress());
                        break;
                    case 7:
                        cell.setCellValue(student.getHometown());
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream(new File("D:/savedexcel/student/StudentDataSheet.xlsx"));
        workbook.write(out);
        out.close();
    }

    public static void importPointExcelToDatabase(String filePath) {
        SubjectDao subjectDao = new SubjectDao();
        String excelFilePath = filePath;
        try {
            Connection con = ConnectionDao.getConnection();
            con.setAutoCommit(false);
            String query = "INSERT INTO Point(StudentId, SubjectId, Point1, Point2, PointFinal, TotalPoint) " +
                    "VALUES(?, ?, ? , ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);

            FileInputStream inputStream = new FileInputStream(excelFilePath);

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            rowIterator.next(); // skip the header row
            while (rowIterator.hasNext()) {
                Float point1 = Float.valueOf(0);
                Float point2 = Float.valueOf(0);
                Float pointFinal = Float.valueOf(0);
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            String studentId = nextCell.getStringCellValue();
                            statement.setString(1, studentId);
                            break;
                        case 2:
                            Integer subjectId = subjectDao.getSubjectIdBySubjectName(nextCell.getStringCellValue());
                            statement.setInt(2, subjectId);
                            break;
                        case 3:
                            point1 = (float) nextCell.getNumericCellValue();
                            statement.setFloat(3, point1);
                            break;
                        case 4:
                            point2 = (float) nextCell.getNumericCellValue();
                            statement.setFloat(4, point2);
                            break;
                        case 5:
                            pointFinal = (float) nextCell.getNumericCellValue();
                            statement.setFloat(5, pointFinal);
                            break;
                    }
                }
                statement.setFloat(6, (float)(point1 * 0.1 + point2 * 0.2 + pointFinal * 0.7));
                statement.addBatch();
                statement.executeBatch();
            }
            workbook.close();
            con.commit();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Add to database error");
            e.printStackTrace();
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "Error reading file");
            e1.printStackTrace();
        } catch (IOException e2) {
            JOptionPane.showMessageDialog(null, "Error reading file");
            e2.printStackTrace();
        }
        catch(Exception e3)
        {
            JOptionPane.showMessageDialog(null, "Error reading excel file");
            e3.printStackTrace();
        }
    }

    public static void exportPointDatabaseToExcel(List<Point> listPoint, List<String> listColumn)
            throws IOException, SQLException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet(" Point Data ");

        XSSFRow row = spreadsheet.createRow(0);
        for(int cellId = 0; cellId < listColumn.size(); cellId ++)
        {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(listColumn.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Point point : listPoint) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < Student.class.getDeclaredFields().length; cellId ++) {
                Cell cell = row.createCell(cellId);
                switch (cellId) {
                    case 0:
                        cell.setCellValue(point.getStudentId());
                        break;
                    case 1:
                        cell.setCellValue(point.getStudentName());
                        break;
                    case 2:
                        cell.setCellValue(point.getSubjectName());
                        break;
                    case 3:
                        cell.setCellValue(point.getPoint1());
                        break;
                    case 4:
                        cell.setCellValue(point.getPoint2());
                        break;
                    case 5:
                        cell.setCellValue(point.getPointFinal());
                        break;
                    case 6:
                        cell.setCellValue(point.getTotalPoint());
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream(new File("D:/savedexcel/point/PointDataSheet.xlsx"));
        workbook.write(out);
        out.close();
    }
}
