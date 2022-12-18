package com.nmt.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.nmt.qlsv.dao.ExcelDao;
import com.nmt.qlsv.dao.StudentDao;
import com.nmt.qlsv.view.PointView;
import com.nmt.qlsv.view.StudentView;
import com.nmt.qlsv.entity.Student;

public class StudentController {
    private StudentDao studentDao;
    private StudentView studentView;
    private PointView pointView;
    private PointController pointController;

    public StudentController(StudentView view) {
        this.studentView = view;
        studentDao = new StudentDao();

        view.addAddStudentListener(new AddStudentListener());
        view.addEdiStudentListener(new EditStudentListener());
        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addClearListener(new ClearStudentListener());
        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
        view.addChooseExcelFileListener(new ChooseExcelFileListener());
        view.addClassComboBoxListener(new ClassComboBoxListener());
        view.addExportToExcelListener(new ExportToExcelListener());
        view.addShowPointListener(new ShowPointListener());
        view.addSearchFieldListener(new SearchFieldListener());
    }

    public void showStudentView() {
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
                ExcelDao.importStudentExcelToDatabase(filePath);
                studentView.showListStudents(studentDao.findAll());
                studentView.showMessage("Import thành công");
            }
        }
    }

    class ExportToExcelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Student> listStudent = studentDao.findAll();
            List<String> listColumn = studentDao.getListColumnName();
            try {
                ExcelDao.exportStudentDatabaseToExcel(listStudent, listColumn);
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

    class ShowPointListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pointView = new PointView();
            pointController = new PointController(pointView);
            pointController.showPointListAndSubjectList();
            pointView.setVisible(true);
        }
    }

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
