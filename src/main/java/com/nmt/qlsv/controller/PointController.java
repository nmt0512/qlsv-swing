package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.*;
import com.nmt.qlsv.entity.Clazz;
import com.nmt.qlsv.entity.Point;
import com.nmt.qlsv.entity.Subject;
import com.nmt.qlsv.view.PointView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointController {
    private PointDao pointDao;
    private PointView pointView;
    private SubjectDao subjectDao;
    private ClazzDao clazzDao;
    private StudentDao studentDao;
    private ExcelDao excelDao;

    public PointController() {
        this.pointView = new PointView();
        pointDao = new PointDao();
        subjectDao = new SubjectDao();
        clazzDao = new ClazzDao();
        studentDao = new StudentDao();
        excelDao = new ExcelDao();

        showPointList();
        setClassAndSubjectComboBoxData();

        pointView.addChooseRowOnTableListener(new ChooseRowOnTableListener());
        pointView.addAddPointListener(new AddBtnListener());
        pointView.addEditPointListener(new EditBtnListener());
        pointView.addDeletePointListener(new DeleteBtnListener());
        pointView.addClearPointListener(new ClearPointListener());
        pointView.addSortByTotalPoint(new SortPointByTotalPointListener());
        pointView.addSortByNameListener(new SortPointByNameListener());
        pointView.addSubjectComboxBoxListener(new ClassAndSubjectComboBoxListener());
        pointView.addClassComboBoxListener(new ClassAndSubjectComboBoxListener());
        pointView.addChooseExcelFileListener(new ChooseExcelFileListener());
        pointView.addExportExcelListener(new ExportExcelListener());
        pointView.addSearchFieldListener(new SearchFieldListener());
    }

    public PointView getPointView() {
        return pointView;
    }

    public void showPointList() {
        List<Point> pointList = pointDao.findAll(null, null);
        pointView.showListPoint(pointList);
    }

    public void setClassAndSubjectComboBoxData()
    {
        List<String> subjectName = new ArrayList<>();
        List<String> clazzName = new ArrayList<>();

        for (Subject subject : subjectDao.findAll()) {
            subjectName.add(subject.getName());
        }
        pointView.setSubjectComboBoxData(subjectName);

        for(Clazz clazz: clazzDao.findAll())
        {
            clazzName.add(clazz.getName());
        }
        pointView.setClassComboBoxData(clazzName);
    }

    class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = pointView.getPointInfo();
            if (point != null) {
                if (point.getSubjectName().isBlank()) {
                    pointView.showMessage("Vui lòng chọn tên môn học");
                }
                else {
                    point.setSubjectId(subjectDao.getSubjectIdBySubjectName(point.getSubjectName()));
                    point.calculateTotalPoint();
                    try {
                        pointDao.save(point, false);
                        pointView.showPointToTextField(point);
                        String studentClass = studentDao.findClassNameByStudentId(point.getStudentId());
                        pointView.showListPoint(pointDao.findAll(studentClass, null));
                        pointView.showMessage("Thêm thành công");
                    } catch (SQLException e1) {
                        pointView.showMessage("Sinh viên không tồn tại hoặc đã có điểm môn " + point.getSubjectName());
                    }
                }
            }
        }
    }

    class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = pointView.getPointInfo();
            if (point != null) {
                point.setSubjectId(subjectDao.getSubjectIdBySubjectName(point.getSubjectName()));
                point.calculateTotalPoint();
                try {
                    pointDao.save(point, true);
                    pointView.showPointToTextField(point);
                    String studentClass = studentDao.findClassNameByStudentId(point.getStudentId());
                    pointView.showListPoint(pointDao.findAll(studentClass, null));
                    pointView.showMessage("Chỉnh sửa thành công");
                } catch (SQLException e1) {
                    pointView.showMessage("Lỗi khi cập nhật điểm sinh viên");
                }
            }
        }
    }

    class DeleteBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = pointView.getPointInfo();
            if (point != null) {
                point.setSubjectId(subjectDao.getSubjectIdBySubjectName(point.getSubjectName()));
                try {
                    pointDao.deletePoint(point);
                    pointView.clearPointInfo();
                    pointView.showListPoint(pointDao.findAll(null, null));
                    pointView.showMessage("Xóa thành công");
                } catch (SQLException e1) {
                    pointView.showMessage("Lỗi khi xóa điểm sinh viên");
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

    class ClearPointListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pointView.clearPointInfo();
        }
    }

    class SortPointByNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pointDao.sortPointByName();
            pointView.showListPoint(pointDao.getPointList());
        }
    }

    class SortPointByTotalPointListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pointDao.sortByTotalPoint();
            pointView.showListPoint(pointDao.getPointList());
        }
    }

    class ClassAndSubjectComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String subjectName = pointView.getSubjectComboBoxSelectedItem();
            String className = pointView.getClassComboBoxSelectedItem();
            if(subjectName != null && className != null)
            {
                Integer subjectId = subjectDao.getSubjectIdBySubjectName(subjectName);
                if(className != "All" || subjectId != null)
                {
                    if(className != "All" && subjectId != null)
                        pointDao.findAll(className, subjectId);
                    else
                    {
                        if(className != "All")
                            pointDao.findAll(className, null);
                        else
                            pointDao.findAll(null, subjectId);
                    }
                }
                else
                    pointDao.findAll(null, null);
                pointView.showListPoint(pointDao.getPointList());
            }
        }
    }

    class ChooseExcelFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String filePath = pointView.chooseExcelFile();
            if (filePath != null) {
                excelDao.importPointExcelToDatabase(filePath);
                pointView.showListPoint(pointDao.findAll(null, null));
                pointView.showMessage("Import thành công");
            }
        }
    }

    class ExportExcelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Point> listPoint = pointDao.findAll(null, null);
            List<String> listColumn = pointDao.getExcelListColumnName();
            try {
                excelDao.exportPointDatabaseToExcel(listPoint, listColumn);
                pointView.showMessage("Export thành công vào file -> D:/savedexcel/point/PointDataSheet.xlsx");
            } catch (IOException ex) {
                pointView.showMessage("Error writing file");
            } catch (SQLException ex) {
                pointView.showMessage("Error reading data from database");
            }
        }
    }

    class SearchFieldListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String key = pointView.getSearchedKey();
                String classKey = pointView.getClassComboBoxSelectedItem();
                String subjectKey = pointView.getSubjectComboBoxSelectedItem();
                List<Point> searchedPointList = pointDao.searchByNameOrStudentId(key, classKey, subjectKey);
                pointView.showListPoint(searchedPointList);
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
