package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.ExcelDao;
import com.nmt.qlsv.dao.SessionDao;
import com.nmt.qlsv.dao.TeacherDao;
import com.nmt.qlsv.entity.Session;
import com.nmt.qlsv.entity.Teacher;
import com.nmt.qlsv.view.SessionView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SessionController {
    private SessionView sessionView;
    private SessionDao sessionDao;
    private ExcelDao excelDao;
    private TeacherDao teacherDao;

    public SessionController()
    {
        sessionView = new SessionView();
        sessionDao = new SessionDao();
        excelDao = new ExcelDao();
        teacherDao = new TeacherDao();

        showSessionList();
        setTeacherComboBoxData();

        sessionView.addAddBtnListener(new AddBtnListener());
        sessionView.addEditBtnListener(new EditBtnListener());
        sessionView.addDeleteBtnListener(new DeleteBtnListener());
        sessionView.addClearBtnListener(new ClearBtnListener());
        sessionView.addChooseRowOnTableListener(new ChooseRowOnTableListener());
        sessionView.addExportExcelBtnListener(new ExportExcelBtnListener());
    }

    public void showSessionList()
    {
        List<Session> sessionList = sessionDao.findAll();
        sessionView.showSessionList(sessionList);
    }

    public void setTeacherComboBoxData()
    {
        List<Teacher> teacherList = teacherDao.findAll();
        sessionView.setTeacherComboBoxData(teacherList);
    }

    public SessionView getSessionView()
    {
        return sessionView;
    }

    class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Session session = sessionView.getSessionInfo();
            if(session != null)
            {
                try
                {
                    sessionDao.save(session);
                }
                catch (SQLException e1)
                {
                    sessionView.showMessage(e1.getMessage());
                }
                sessionView.showSessionList(sessionDao.findAll());
                sessionView.showMessage("Đã lưu niên khóa");
            }
        }
    }

    class EditBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Session session = sessionView.getSessionInfo();
            if(session != null)
            {
                try
                {
                    sessionDao.save(session);
                    sessionView.showSessionList(sessionDao.findAll());
                    sessionView.showMessage("Đã lưu niên khóa");
                }
                catch (SQLException | NullPointerException e1)
                {
                    sessionView.showMessage("Lỗi hệ thống");
                }
            }
        }
    }

    class DeleteBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Session session = sessionView.getSessionInfo();
            if(session != null)
            {
                try
                {
                    sessionDao.deleteById(session.getId());
                    sessionView.showSessionList(sessionDao.findAll());
                    sessionView.clearSessionInfo();
                    sessionView.showMessage("Đã xóa niên khóa");
                }
                catch (SQLException e1)
                {
                    sessionView.showMessage("Error! Không thể xóa niên khóa đang có sinh viên");
                }
            }
        }
    }

    class ClearBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionView.clearSessionInfo();
        }
    }

    class ExportExcelBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Session> sessionList = sessionDao.findAll();
            List<String> columnNameList = sessionDao.getExcelColumnNameList();
            try {
                if(sessionList == null)
                    throw new SQLException("Error reading database");
                excelDao.exportSessionDatabaseToExcel(sessionList, columnNameList);
                sessionView.showMessage("Export thành công vào file -> D:/savedexcel/session/SessionDataSheet.xlsx");
            } catch (IOException ex) {
                sessionView.showMessage("Error writing file");
            } catch (SQLException ex) {
                sessionView.showMessage("Error reading data from database");
            }
        }
    }

    class ChooseRowOnTableListener implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            sessionView.fillFieldFromSelectedRow();
        }
    }
}
