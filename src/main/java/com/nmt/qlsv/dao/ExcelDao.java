package com.nmt.qlsv.dao;

import com.nmt.qlsv.entity.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class ExcelDao {
    public void importStudentExcelToDatabase(String filePath) throws ParseException, SQLException, IOException {
        ClazzDao clazzDao = new ClazzDao();
        String excelFilePath = filePath;
        Connection con = ConnectionDao.getConnection();
        con.setAutoCommit(false);
        String query = "INSERT INTO Student(StudentId, Name, Age, Birthday, Address, Hometown, ClassId) " +
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
                        String birthday = nextCell.getStringCellValue();
                        statement.setDate(4, stringToDate(birthday));
                        break;
                    case 4:
                        String address = nextCell.getStringCellValue();
                        statement.setString(5, address);
                        break;
                    case 5:
                        String hometown = nextCell.getStringCellValue();
                        statement.setString(6, hometown);
                        break;
                    case 6:
                        String clazz = nextCell.getStringCellValue();
                        Integer classId = clazzDao.findIdByName(clazz);
                        statement.setInt(7, classId);
                        break;
                }
            }
            statement.addBatch();
            statement.executeBatch();
        }
        workbook.close();
        con.commit();
        con.close();
    }

    public void exportStudentDatabaseToExcel(List<Student> listStudent, List<String> listColumn)
            throws IOException, SQLException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");

        XSSFRow row = spreadsheet.createRow(0);
        for (int cellId = 0; cellId < listColumn.size(); cellId++) {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(listColumn.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Student student : listStudent) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < Student.class.getDeclaredFields().length; cellId++) {
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
                        cell.setCellValue(dateToString(student.getBirthday()));
                        break;
                    case 5:
                        cell.setCellValue(student.getAddress());
                        break;
                    case 6:
                        cell.setCellValue(student.getHometown());
                        break;
                    case 7:
                        cell.setCellValue(student.getClassName());
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream(new File("D:/savedexcel/student/StudentDataSheet.xlsx"));
        workbook.write(out);
        out.close();
    }

    public void importPointExcelToDatabase(String filePath) throws SQLException, Exception {
        SubjectDao subjectDao = new SubjectDao();
        String excelFilePath = filePath;
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
            float point1 = (float) 0;
            float point2 = (float) 0;
            float pointFinal = (float) 0;
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
                        Integer subjectId = subjectDao.getSubjectIdBySubjectName(nextCell.getStringCellValue());
                        statement.setInt(2, subjectId);
                        break;
                    case 2:
                        point1 = (float) nextCell.getNumericCellValue();
                        statement.setFloat(3, point1);
                        break;
                    case 3:
                        point2 = (float) nextCell.getNumericCellValue();
                        statement.setFloat(4, point2);
                        break;
                    case 4:
                        pointFinal = (float) nextCell.getNumericCellValue();
                        statement.setFloat(5, pointFinal);
                        break;
                }
            }
            statement.setFloat(6, (float) (point1 * 0.1 + point2 * 0.2 + pointFinal * 0.7));
            statement.addBatch();
            statement.executeBatch();
        }
        workbook.close();
        con.commit();
        con.close();

    }

    public void exportPointDatabaseToExcel(List<Point> listPoint, List<String> listColumn)
            throws IOException, SQLException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet(" Point Data ");

        XSSFRow row = spreadsheet.createRow(0);
        for (int cellId = 0; cellId < listColumn.size(); cellId++) {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(listColumn.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Point point : listPoint) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < listColumn.size(); cellId++) {
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

    public void exportSubjectDatabaseToExcel(List<Subject> subjectList, List<String> columnList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" Subject Data ");
        XSSFRow row = spreadsheet.createRow(0);

        for (int cellId = 0; cellId < columnList.size(); cellId++) {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(columnList.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Subject subject : subjectList) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < columnList.size(); cellId++) {
                Cell cell = row.createCell(cellId);
                switch (cellId) {
                    case 0:
                        cell.setCellValue(subject.getName());
                        break;
                    case 1:
                        cell.setCellValue(subject.getCredit());
                        break;
                    case 2:
                        cell.setCellValue(subject.getTeacherName());
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream(new File("D:/savedexcel/subject/SubjectDataSheet.xlsx"));
        workbook.write(out);
        out.close();
    }

    public void exportTeacherDatabaseToExcel(List<Teacher> teacherList, List<String> columnList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" Teacher Data ");
        XSSFRow row = spreadsheet.createRow(0);

        for (int cellId = 0; cellId < columnList.size(); cellId++) {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(columnList.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Teacher teacher : teacherList) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < columnList.size(); cellId++) {
                Cell cell = row.createCell(cellId);
                switch (cellId) {
                    case 0:
                        cell.setCellValue(teacher.getName());
                        break;
                    case 1:
                        cell.setCellValue(teacher.getAge());
                        break;
                    case 2:
                        cell.setCellValue(teacher.getPhone());
                        break;
                    case 3:
                        cell.setCellValue(teacher.getEmail());
                        break;
                    case 4:
                        cell.setCellValue(teacher.getBirthday());
                        break;
                    case 5:
                        cell.setCellValue(teacher.getStartWorking());
                        break;
                    case 6:
                        cell.setCellValue(teacher.getHometown());
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream(new File("D:/savedexcel/teacher/TeacherDataSheet.xlsx"));
        workbook.write(out);
        out.close();
    }

    public void exportSessionDatabaseToExcel(List<Session> sessionList, List<String> columnList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" Session Data ");
        XSSFRow row = spreadsheet.createRow(0);

        for (int cellId = 0; cellId < columnList.size(); cellId++) {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(columnList.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Session session : sessionList) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < columnList.size(); cellId++) {
                Cell cell = row.createCell(cellId);
                switch (cellId) {
                    case 0:
                        cell.setCellValue(session.getId());
                        break;
                    case 1:
                        cell.setCellValue(session.getStartYear());
                        break;
                    case 2:
                        cell.setCellValue(session.getEndYear());
                        break;
                    case 3:
                        cell.setCellValue(session.getStuQuantity());
                        break;
                    case 4:
                        cell.setCellValue(session.getTeacherName());
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream(new File("D:/savedexcel/session/SessionDataSheet.xlsx"));
        workbook.write(out);
        out.close();
    }

    public void exportClassDatabaseToExcel(List<Clazz> clazzList, List<String> columnList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" Class Data ");
        XSSFRow row = spreadsheet.createRow(0);

        for (int cellId = 0; cellId < columnList.size(); cellId++) {
            Cell cellColumn = row.createCell(cellId);
            cellColumn.setCellValue(columnList.get(cellId));
        }

        int rowid = 1;
        // writing the data into the sheets...
        for (Clazz clazz : clazzList) {
            row = spreadsheet.createRow(rowid++);
            for (int cellId = 0; cellId < columnList.size(); cellId++) {
                Cell cell = row.createCell(cellId);
                switch (cellId) {
                    case 0:
                        cell.setCellValue(clazz.getId());
                        break;
                    case 1:
                        cell.setCellValue(clazz.getSessionId());
                        break;
                    case 2:
                        cell.setCellValue(clazz.getDepartmentCode());
                        break;
                    case 3:
                        cell.setCellValue(clazz.getName());
                        break;
                    case 4:
                        cell.setCellValue(clazz.getStudentQuantity());
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream(new File("D:/savedexcel/class/ClassDataSheet.xlsx"));
        workbook.write(out);
        out.close();
    }

    private String dateToString(java.sql.Date date) {
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(date);
        }
        return "";
    }

    private java.sql.Date stringToDate(String strDate) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
// because PreparedStatement#setDate(..) expects a java.sql.Date argument
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
