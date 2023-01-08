package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.ClazzDao;
import com.nmt.qlsv.dao.ExcelDao;
import com.nmt.qlsv.dao.StudentDao;
import com.nmt.qlsv.entity.Clazz;
import com.nmt.qlsv.entity.Student;
import com.nmt.qlsv.view.StudentView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private StudentDao studentDao;
    private StudentView studentView;
    private ClazzDao clazzDao;
    private ExcelDao excelDao;

    public StudentController() {
        studentDao = new StudentDao();
        studentView = new StudentView();
        clazzDao = new ClazzDao();
        excelDao = new ExcelDao();

        studentView.addAddStudentListener(new AddStudentListener());
        studentView.addEdiStudentListener(new EditStudentListener());
        studentView.addDeleteStudentListener(new DeleteStudentListener());
        studentView.addClearListener(new ClearStudentListener());
        studentView.addSortStudentNameListener(new SortStudentNameListener());
        studentView.addListStudentSelectionListener(new ListStudentSelectionListener());
        studentView.addChooseExcelFileListener(new ChooseExcelFileListener());
        studentView.addClassComboBoxListener(new ClassComboBoxListener());
        studentView.addExportToExcelListener(new ExportToExcelListener());
//        studentView.addShowPointListener(new ShowPointListener());
        studentView.addSearchFieldListener(new SearchFieldListener());

        setClassComboBoxData();
        showStudentView();
    }

    public StudentView getStudentView()
    {
        return studentView;
    }

    private void setClassComboBoxData()
    {
        List<Clazz> listClazz = clazzDao.findAll();
        List<String> listClassName = new ArrayList<>();
        for(Clazz clazz: listClazz)
        {
            listClassName.add(clazz.getName());
        }
        studentView.setDataForComboBox(listClassName);
    }

    private void showStudentView() {
        List<Student> studentList = studentDao.findAll();
        studentView.setVisible(true);
        studentView.showListStudents(studentList);
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                try
                {
                    studentDao.add(student);
                    studentView.showStudentToTextField(student);
                    studentView.showListStudents(studentDao.findAll());
                    studentView.showMessage("Thêm thành công!");
                }
                catch (SQLException e1)
                {
                    studentView.showMessage("Lỗi khi thêm sinh viên: trùng mã sinh viên hoặc lỗi hệ thống");
                    e1.printStackTrace();
                }
            }
        }
    }

    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                try
                {
                    studentDao.edit(student);
                    studentView.showStudentToTextField(student);
                    studentView.showListStudents(studentDao.findAll());
                    studentView.showMessage("Cập nhật thành công!");
                }
                catch (SQLException e1)
                {
                    studentView.showMessage("Lỗi khi cập nhật thông tin sinh viên");
                }
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                try
                {
                    studentDao.delete(student);
                    studentView.clearStudentInfo();
                    studentView.showListStudents(studentDao.findAll());
                    studentView.showMessage("Xóa thành công!");
                }
                catch (SQLException e1)
                {
                    studentView.showMessage("Lỗi khi cập nhật thông tin sinh viên");
                }
            }
        }
    }

    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.clearStudentInfo();
        }
    }

    class SortStudentNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortStudentByName();
            studentView.showListStudents(studentDao.getListStudent());
        }
    }

    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }

    class ChooseExcelFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String filePath = studentView.chooseExcelFile();
            if(filePath != null)
            {
                try
                {
                    excelDao.importStudentExcelToDatabase(filePath);
                    studentView.showListStudents(studentDao.findAll());
                    studentView.showMessage("Import thành công");
                }
                catch (ParseException e1)
                {
                    studentView.showMessage("Error importing file");
                }
            }
        }
    }

    class ExportToExcelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Student> listStudent = studentDao.findAll();
            List<String> listColumn = studentDao.getListColumnName();
            try {
                excelDao.exportStudentDatabaseToExcel(listStudent, listColumn);
                studentView.showMessage("Export thành công vào file -> D:/savedexcel/student/StudentDataSheet.xlsx");
            } catch (IOException ex) {
                studentView.showMessage("Error writing file");
            } catch (SQLException ex) {
                studentView.showMessage("Error reading data from database");
            }
        }
    }

    class ClassComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedClass = studentView.getClassSelectedFromComboBox();
            List<Student> listStudentByClass = studentDao.findAll(selectedClass);
            studentView.showListStudents(listStudentByClass);
        }
    }

//    class ShowPointListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            pointController = new PointController();
//            pointView.setVisible(true);
//        }
//    }

    class SearchFieldListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                String key = studentView.getSearchedKey();
                String classKey = studentView.getClassSelectedFromComboBox();
                List<Student> listSearchedStudent = studentDao.searchByNameOrStudentId(key, classKey);
                studentView.showListStudents(listSearchedStudent);
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
