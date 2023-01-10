package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.ExcelDao;
import com.nmt.qlsv.dao.SubjectDao;
import com.nmt.qlsv.dao.TeacherDao;
import com.nmt.qlsv.entity.Subject;
import com.nmt.qlsv.entity.Teacher;
import com.nmt.qlsv.view.SubjectView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SubjectController {
    private SubjectView subjectView;
    private SubjectDao subjectDao;
    private TeacherDao teacherDao;
    private ExcelDao excelDao;

    public SubjectController()
    {
        subjectView = new SubjectView();
        subjectDao = new SubjectDao();
        teacherDao = new TeacherDao();
        excelDao = new ExcelDao();

        showSubjectList();
        setTeacherComboBoxData();

        subjectView.addAddBtnListener(new AddBtnListener());
        subjectView.addEditBtnListener(new EditBtnListener());
        subjectView.addDeleteBtnListener(new DeleteBtnListener());
        subjectView.addClearBtnListener(new ClearBtnListener());
        subjectView.addSearchFieldListener(new SearchFieldListener());
        subjectView.addChooseRowOnTableListener(new ChooseRowOnTableListener());
        subjectView.addExportExcelBtnListener(new ExportExcelBtnListener());
    }

    public void showSubjectList()
    {
        List<Subject> subjectList = subjectDao.findAll();
        subjectView.showSubjectList(subjectList);
    }
    public void setTeacherComboBoxData()
    {
        List<Teacher> teacherList = teacherDao.findAll();
        subjectView.setTeacherComboBoxData(teacherList);
    }

    public SubjectView getSubjectView()
    {
        return subjectView;
    }

    class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Subject subject = subjectView.getSubjectInfo();
            try
            {
                subjectDao.save(subject);
            }
            catch (SQLException e1)
            {
                subjectView.showMessage(e1.getMessage());
            }
            subjectView.showSubjectList(subjectDao.findAll());
            subjectView.showMessage("Đã lưu môn học");
        }
    }

    class EditBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Subject subject = subjectView.getSubjectInfo();
            try
            {
                subjectDao.save(subject);
            }
            catch (SQLException e1)
            {
                subjectView.showMessage(e1.getMessage());
            }
            subjectView.showSubjectList(subjectDao.findAll());
            subjectView.showMessage("Đã lưu môn học");
        }
    }

    class DeleteBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Subject subject = subjectView.getSubjectInfo();
            try
            {
                subjectDao.deleteById(subject.getId());
            }
            catch (SQLException e1)
            {
                subjectView.showMessage(e1.getMessage());
            }
            subjectView.showSubjectList(subjectDao.findAll());
            subjectView.clearSubjectInfo();
            subjectView.showMessage("Đã xóa môn học");
        }
    }

    class ClearBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            subjectView.clearSubjectInfo();
        }
    }

    class ExportExcelBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Subject> subjectList = subjectDao.findAll();
            List<String> columnNameList = subjectDao.getExcelColumnNameList();
            try {
                if(subjectList == null)
                    throw new SQLException("Error reading database");
                excelDao.exportSubjectDatabaseToExcel(subjectList, columnNameList);
                subjectView.showMessage("Export thành công vào file -> D:/savedexcel/subject/SubjectDataSheet.xlsx");
            } catch (IOException ex) {
                subjectView.showMessage("Error writing file");
            } catch (SQLException ex) {
                subjectView.showMessage("Error reading data from database");
            }
        }
    }

    class ChooseRowOnTableListener implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            subjectView.fillFieldFromSelectedRow();
        }
    }

    class SearchFieldListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                String key = subjectView.getSearchedKey();
                List<Subject> searchedSubjectList = subjectDao.findByNameContaining(key);
                subjectView.showSubjectList(searchedSubjectList);
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
