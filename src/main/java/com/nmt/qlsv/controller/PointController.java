package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.ExcelDao;
import com.nmt.qlsv.dao.PointDao;
import com.nmt.qlsv.dao.SubjectDao;
import com.nmt.qlsv.entity.Point;
import com.nmt.qlsv.entity.Subject;
import com.nmt.qlsv.view.PointView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointController {
    private PointDao pointDao;
    private PointView pointView;
    private SubjectDao subjectDao;

    public PointController(PointView pointView)
    {
        this.pointView = pointView;
        pointDao = new PointDao();
        subjectDao = new SubjectDao();
        pointView.addChooseRowOnTableListener(new ChooseRowOnTableListener());
        pointView.addAddPointListener(new AddBtnListener());
        pointView.addEditPointListener(new EditBtnListener());
        pointView.addDeletePointListener(new DeleteBtnListener());
        pointView.addClearPointListener(new ClearPointListener());
        pointView.addSortByTotalPoint(new SortPointByTotalPointListener());
        pointView.addSortByNameListener(new SortPointByNameListener());
        pointView.addSubjectComboxBoxListener(new SubjectComboBoxListener());
        pointView.addChooseExcelFileListener(new ChooseExcelFileListener());
        pointView.addExportExcelListener(new ExportExcelListener());
    }

    public void showPointListAndSubjectList()
    {
        List<Point> pointList = pointDao.findAll();
        List<String> subjectName = new ArrayList<>();
        subjectName.add("All");
        pointView.showListPoint(pointList);
        for(Subject subject: subjectDao.findAll())
        {
            subjectName.add(subject.getName());
        }
        pointView.setDataSubjectComboBox(subjectName);
    }

    class AddBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = pointView.getPointInfo();
            if(point != null)
            {
                if(point.getSubjectName().isBlank())
                {
                    pointView.showMessage("Vui lòng chọn tên môn học");
                }
                else
                {
                    point.setSubjectId(subjectDao.getSubjectIdBySubjectName(point.getSubjectName()));
                    point.calculateTotalPoint();
                    try
                    {
                        pointDao.addPoint(point);
                        pointView.showPointToTextField(point);
                        pointView.showListPoint(pointDao.findAll());
                        pointView.showMessage("Thêm thành công");
                    }
                    catch (SQLException e1)
                    {
                        pointView.showMessage("Sinh viên này đã có điểm môn " + point.getSubjectName());
                    }
                }
            }
        }
    }

    class EditBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = pointView.getPointInfo();
            if(point != null)
            {
                point.setSubjectId(subjectDao.getSubjectIdBySubjectName(point.getSubjectName()));
                point.calculateTotalPoint();
                try
                {
                    pointDao.updatePoint(point);
                    pointView.showPointToTextField(point);
                    pointView.showListPoint(pointDao.findAll());
                    pointView.showMessage("Chỉnh sửa thành công");
                }
                catch (SQLException e1)
                {
                    pointView.showMessage("Lỗi khi cập nhật điểm sinh viên");
                }
            }
        }
    }

    class DeleteBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = pointView.getPointInfo();
            if(point != null)
            {
                point.setSubjectId(subjectDao.getSubjectIdBySubjectName(point.getSubjectName()));
                try
                {
                    pointDao.deletePoint(point);
                    pointView.clearPointInfo();
                    pointView.showListPoint(pointDao.findAll());
                    pointView.showMessage("Xóa thành công");
                }
                catch (SQLException e1) {
                    pointView.showMessage("Lỗi khi cập nhật điểm sinh viên");
                }
            }
        }
    }

    class ChooseRowOnTableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            pointView.fillFieldFromSelectedRow();
        }
    }

    class ClearPointListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pointView.clearPointInfo();
        }
    }

    class SortPointByNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pointDao.sortPointByName();
            pointView.showListPoint(pointDao.getPointList());
        }
    }

    class SortPointByTotalPointListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pointDao.sortByTotalPoint();
            pointView.showListPoint(pointDao.getPointList());
        }
    }

    class SubjectComboBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String subjectName = pointView.getSubjectComboBoxSelectedItem();
            Integer subjectId = null;
            subjectId = subjectDao.getSubjectIdBySubjectName(subjectName);
            if(subjectId != null)
            {
                pointDao.findAll(subjectId);
            }
            else
            {
                pointDao.findAll();
            }
            pointView.showListPoint(pointDao.getPointList());
        }
    }

    class ChooseExcelFileListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String filePath = pointView.chooseExcelFile();
            if(filePath != null)
            {
                ExcelDao.importPointExcelToDatabase(filePath);
                pointView.showListPoint(pointDao.findAll());
                pointView.showMessage("Import thành công");
            }
        }
    }

    class ExportExcelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Point> listPoint = pointDao.findAll();
            List<String> listColumn = pointDao.getExcelListColumnName();
            try {
                ExcelDao.exportPointDatabaseToExcel(listPoint, listColumn);
                pointView.showMessage("Export thành công vào file -> D:/savedexcel/point/PointDataSheet.xlsx");
            } catch (IOException ex) {
                pointView.showMessage("Error writing file");
            } catch (SQLException ex) {
                pointView.showMessage("Error reading data from database");
            }
        }
    }

}
