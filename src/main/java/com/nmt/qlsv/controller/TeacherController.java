package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.ExcelDao;
import com.nmt.qlsv.dao.TeacherDao;
import com.nmt.qlsv.entity.Teacher;
import com.nmt.qlsv.view.TeacherView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TeacherController {
    private TeacherView teacherView;
    private TeacherDao teacherDao;
    private ExcelDao excelDao;

    public TeacherController()
    {
        teacherView = new TeacherView();
        teacherDao = new TeacherDao();
        excelDao = new ExcelDao();

        showTeacherList();

        teacherView.addAddBtnListener(new AddBtnListener());
        teacherView.addEditBtnListener(new EditBtnListener());
        teacherView.addDeleteBtnListener(new DeleteBtnListener());
        teacherView.addClearBtnListener(new ClearBtnListener());
        teacherView.addSortByNameBtnListener(new SortByNameBtnListener());
        teacherView.addExportExcelBtnListener(new ExportExcelBtnListener());
        teacherView.addSearchFieldListener(new SearchByNameListener());
        teacherView.addChooseRowOnTableListener(new ChooseRowOnTableListener());
    }

    private void showTeacherList()
    {
        List<Teacher> teacherList = teacherDao.findAll();
        teacherView.showTeacherList(teacherList);
    }

    public TeacherView getTeacherView()
    {
        return teacherView;
    }

    class AddBtnListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            Teacher teacher = teacherView.getTeacherInfo();
            try
            {
                teacherDao.save(teacher);
            }
            catch (SQLException e1)
            {
                teacherView.showMessage(e1.getMessage());
            }
            teacherView.showTeacherList(teacherDao.findAll());
            teacherView.showMessage("Đã lưu giảng viên");
        }
    }

    class EditBtnListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            Teacher teacher = teacherView.getTeacherInfo();
            try
            {
                teacherDao.save(teacher);
            }
            catch (SQLException e1)
            {
                teacherView.showMessage(e1.getMessage());
            }
            teacherView.showTeacherList(teacherDao.findAll());
            teacherView.showMessage("Đã lưu giảng viên");
        }
    }

    class DeleteBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Teacher teacher = teacherView.getTeacherInfo();
            try
            {
                teacherDao.deleteById(teacher.getId());
            }
            catch (SQLException e1)
            {
                teacherView.showMessage(e1.getMessage());
            }
            teacherView.showTeacherList(teacherDao.findAll());
            teacherView.clearTeacherInfo();
            teacherView.showMessage("Đã xóa giảng viên");
        }
    }

    class ClearBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            teacherView.clearTeacherInfo();
        }
    }

    class ExportExcelBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Teacher> teacherList = teacherDao.findAll();
            List<String> columnNameList = teacherDao.getExcelColumnNameList();
            try {
                if(teacherList == null)
                    throw new SQLException("Error reading database");
                excelDao.exportTeacherDatabaseToExcel(teacherList, columnNameList);
                teacherView.showMessage("Export thành công vào file -> D:/savedexcel/teacher/TeacherDataSheet.xlsx");
            } catch (IOException ex) {
                teacherView.showMessage("Error writing file");
            } catch (SQLException ex) {
                teacherView.showMessage("Error reading data from database");
            }
        }
    }

    class SortByNameBtnListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Teacher> teacherList = teacherDao.sortByName();
            teacherView.showTeacherList(teacherList);
        }
    }

    class SearchByNameListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                String key = teacherView.getSearchedKey();
                List<Teacher> searchedList = teacherDao.findByNameContaining(key);
                teacherView.showTeacherList(searchedList);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    class ChooseRowOnTableListener implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            teacherView.fillFieldFromSelectedRow();
        }
    }
}
