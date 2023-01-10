package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.ClazzDao;
import com.nmt.qlsv.dao.DepartmentDao;
import com.nmt.qlsv.dao.ExcelDao;
import com.nmt.qlsv.dao.SessionDao;
import com.nmt.qlsv.entity.Clazz;
import com.nmt.qlsv.entity.Department;
import com.nmt.qlsv.entity.Session;
import com.nmt.qlsv.view.ClassView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClassController {
    private ClassView classView;
    private ClazzDao clazzDao;
    private ExcelDao excelDao;
    private SessionDao sessionDao;
    private DepartmentDao departmentDao;

    public ClassController()
    {
        classView = new ClassView();
        clazzDao = new ClazzDao();
        excelDao = new ExcelDao();
        sessionDao = new SessionDao();
        departmentDao = new DepartmentDao();

        showClassList();
        setSessionComboBoxData();
        setDepartmentComboBoxData();

        classView.addAddBtnListener(new AddBtnListener());
        classView.addEditBtnListener(new EditBtnListener());
        classView.addDeleteBtnListener(new DeleteBtnListener());
        classView.addClearBtnListener(new ClearBtnListener());
        classView.addChooseRowOnTableListener(new ChooseRowOnTableListener());
        classView.addExportExcelBtnListener(new ExportExcelBtnListener());
    }

    public ClassView getClassView()
    {
        return classView;
    }

    public void showClassList()
    {
        List<Clazz> clazzList = clazzDao.findAll();
        classView.showClassList(clazzList);
    }

    public void setSessionComboBoxData()
    {
        List<Session> sessionList = sessionDao.findAll();
        classView.setSessionComboBoxData(sessionList);
    }

    public void setDepartmentComboBoxData()
    {
        List<Department> departmentList = departmentDao.findAll();
        classView.setDepartmentComboBoxData(departmentList);
    }

    class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clazz clazz = classView.getClassInfo();
            if(clazz != null)
            {
                try
                {
                    clazzDao.save(clazz);
                    classView.showClassList(clazzDao.findAll());
                    classView.showMessage("Đã lưu lớp học");
                }
                catch (SQLException e1)
                {
                    classView.showMessage("Lỗi khi cập nhật lớp học: tên lớp học đã tồn tại");
                }
            }
        }
    }

    class EditBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clazz clazz = classView.getClassInfo();
            if(clazz != null)
            {
                try
                {
                    clazzDao.save(clazz);
                    classView.showClassList(clazzDao.findAll());
                    classView.showMessage("Đã lưu lớp học");
                }
                catch (SQLException e1)
                {
                    classView.showMessage("Lỗi khi cập nhật lớp học: tên lớp học đã tồn tại");
                }
            }
        }
    }

    class DeleteBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clazz clazz = classView.getClassInfo();
            if(clazz != null)
            {
                try
                {
                    clazzDao.deleteById(clazz.getId());
                    classView.showClassList(clazzDao.findAll());
                    classView.clearClassInfo();
                    classView.showMessage("Đã xóa lớp học");
                }
                catch (SQLException e1)
                {
                    classView.showMessage("Error! Lớp học đang có sinh viên không thể xóa");
                }
            }
        }
    }

    class ClearBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            classView.clearClassInfo();
        }
    }

    class ExportExcelBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Clazz> clazzList = clazzDao.findAll();
            List<String> columnNameList = clazzDao.getExcelColumnNameList();
            try {
                if(clazzList == null)
                    throw new SQLException("Error reading database");
                excelDao.exportClassDatabaseToExcel(clazzList, columnNameList);
                classView.showMessage("Export thành công vào file -> D:/savedexcel/class/ClassDataSheet.xlsx");
            } catch (IOException ex) {
                classView.showMessage("Error writing file");
            } catch (SQLException ex) {
                classView.showMessage("Error reading data from database");
            }
        }
    }

    class ChooseRowOnTableListener implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            classView.fillFieldFromSelectedRow();
        }
    }
}
