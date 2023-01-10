package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.Point;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

public class PointView extends JPanel {
    private JTable pointTable;
    private JScrollPane jScrollPanePointTable;

    private JLabel studentIdLabel;
    private JLabel point1Label;
    private JLabel point2Label;
    private JLabel pointFinalLabel;
    private JLabel subjectLabel;
    private JLabel subjectLabelUpdate;
    private JLabel searchLabel;
    private JLabel classLabel;

    private JTextField studentIdField;
    private JTextField point1Field;
    private JTextField point2Field;
    private JTextField pointFinalField;
    private JTextField searchField;

    private JButton addPointBtn;
    private JButton editPointBtn;
    private JButton deletePointBtn;
    private JButton clearBtn;
    private JButton sortByNameBtn;
    private JButton sortByTotalPoint;
    private JButton chooseExcelFileBtn;
    private JButton exportToExcelBtn;
//    private List<String> subjectComboBoxData;
    private JComboBox<String> subjectComboBox;
    private JComboBox<String> subjectComboBoxUpdate;
    private JComboBox<String> classComboBox;
    private final Object data = new Object [][] {};
    private final String [] columnNames = new String [] {"Student ID", "Name", "Subject Name", "Point 1"
            , "Point 2", "Point Final", "Total Point", "Class"};

    public PointView()
    {
        initComponent();
    }

    public void initComponent()
    {
        studentIdLabel = new JLabel("Student Id");
        point1Label = new JLabel("Point 1");
        point2Label = new JLabel("Point 2");
        pointFinalLabel = new JLabel("Point Final");
        subjectLabelUpdate = new JLabel("Subject");
        subjectLabel = new JLabel("Subject");
        subjectLabel.setForeground(Color.GRAY);
        classLabel = new JLabel("Class");
        classLabel.setForeground(Color.GRAY);

        searchLabel = new JLabel();
        searchLabel.setIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/search-icon.png"));

        studentIdField = new JTextField(10);
        point1Field = new JTextField(6);
        point2Field = new JTextField(6);
        pointFinalField = new JTextField(6);
        subjectComboBox = new JComboBox<>();
        searchField = new JTextField(15);
        subjectComboBoxUpdate = new JComboBox<>();
        subjectComboBox.setUI(new PopUpMetalComboBoxUI());
        classComboBox = new JComboBox<>();
        classComboBox.setUI(new PopUpMetalComboBoxUI());

        addPointBtn = new JButton("Add");
        editPointBtn = new JButton("Edit");
        deletePointBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortByNameBtn = new JButton("Sort by name");
        sortByTotalPoint = new JButton("Sort by Total Point");
        chooseExcelFileBtn = new JButton("Import excel");
        exportToExcelBtn = new JButton("Export excel");

        jScrollPanePointTable = new JScrollPane();

//        subjectComboBoxData = new ArrayList<>();

        pointTable = new JTable();
        pointTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPanePointTable.setViewportView(pointTable);
        jScrollPanePointTable.setPreferredSize(new Dimension (850, 700));

        SpringLayout layout = new SpringLayout();
        
        this.setLayout(layout);
        this.add(studentIdLabel);
        this.add(point1Label);
        this.add(point2Label);
        this.add(pointFinalLabel);
        this.add(subjectLabel);
        this.add(subjectLabelUpdate);
        this.add(searchLabel);
        this.add(classLabel);

        this.add(studentIdField);
        this.add(point1Field);
        this.add(point2Field);
        this.add(pointFinalField);
        this.add(searchField);

        this.add(subjectComboBox);
        this.add(subjectComboBoxUpdate);
        this.add(classComboBox);

        this.add(addPointBtn);
        this.add(editPointBtn);
        this.add(deletePointBtn);
        this.add(clearBtn);
        this.add(sortByNameBtn);
        this.add(sortByTotalPoint);
        this.add(chooseExcelFileBtn);
        this.add(exportToExcelBtn);

        this.add(jScrollPanePointTable);

        int westSearchLabel = 1140;
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 6, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchLabel, westSearchLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, searchField, 8, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchField, westSearchLabel - 160, SpringLayout.WEST, this);

        int westLabel = 40;
        int northLabel = 70;
        layout.putConstraint(SpringLayout.WEST, studentIdLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, studentIdLabel, northLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, point1Label, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, point1Label, 30, SpringLayout.NORTH, studentIdLabel);
        layout.putConstraint(SpringLayout.WEST, point2Label, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, point2Label, 30, SpringLayout.NORTH, point1Label);
        layout.putConstraint(SpringLayout.WEST, pointFinalLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pointFinalLabel, 30, SpringLayout.NORTH, point2Label);
        layout.putConstraint(SpringLayout.WEST, subjectLabelUpdate, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, subjectLabelUpdate, 32, SpringLayout.NORTH, pointFinalLabel);

        int westTextField = 130;
        int northTextField = 70;
        layout.putConstraint(SpringLayout.WEST, studentIdField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, studentIdField, northTextField, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, point1Field, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, point1Field, 30, SpringLayout.NORTH, studentIdField);
        layout.putConstraint(SpringLayout.WEST, point2Field, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, point2Field, 30, SpringLayout.NORTH, point1Field);
        layout.putConstraint(SpringLayout.WEST, pointFinalField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pointFinalField, 30, SpringLayout.NORTH, point2Field);
        layout.putConstraint(SpringLayout.WEST, subjectComboBoxUpdate, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, subjectComboBoxUpdate, 30, SpringLayout.NORTH, pointFinalField);

        int westBtn = 40;
        int northBtn = 240;
        layout.putConstraint(SpringLayout.WEST, addPointBtn, westBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, addPointBtn, northBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, editPointBtn, 60, SpringLayout.WEST, addPointBtn);
        layout.putConstraint(SpringLayout.NORTH, editPointBtn, northBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, deletePointBtn, 60, SpringLayout.WEST, editPointBtn);
        layout.putConstraint(SpringLayout.NORTH, deletePointBtn, northBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deletePointBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, northBtn, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, jScrollPanePointTable, 330, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, jScrollPanePointTable, 50, SpringLayout.NORTH, this);

        int westSortBtn = 40;
        int northSortBtn = 310;
        layout.putConstraint(SpringLayout.WEST, sortByNameBtn, westSortBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, sortByNameBtn, northSortBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sortByTotalPoint, 125, SpringLayout.WEST, sortByNameBtn);
        layout.putConstraint(SpringLayout.NORTH, sortByTotalPoint, northSortBtn, SpringLayout.NORTH, this);

        int westChooseExcel = 40;
        int northChooseExcel = 350;
        layout.putConstraint(SpringLayout.NORTH, chooseExcelFileBtn, northChooseExcel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, chooseExcelFileBtn, westChooseExcel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, exportToExcelBtn, northChooseExcel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, exportToExcelBtn, 125, SpringLayout.WEST, sortByNameBtn);

        int westSubjectLabelComboBox = 980;
        int northSubjectLabelComboBox = 760;
        layout.putConstraint(SpringLayout.WEST, subjectLabel, westSubjectLabelComboBox, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, subjectLabel, northSubjectLabelComboBox, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, subjectComboBox, westSubjectLabelComboBox + 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, subjectComboBox, northSubjectLabelComboBox - 3, SpringLayout.NORTH, this);

        int westClassLabelComboBox = 830;
        int northClassLabelComboBox = 760;
        layout.putConstraint(SpringLayout.WEST, classLabel, westClassLabelComboBox, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, classLabel, northClassLabelComboBox, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, classComboBox, westClassLabelComboBox + 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, classComboBox, northClassLabelComboBox - 3, SpringLayout.NORTH, this);

        editPointBtn.setEnabled(false);
        deletePointBtn.setEnabled(false);
        addPointBtn.setEnabled(true);
        this.setSize(1140, 800);
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public String chooseExcelFile()
    {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    public String getSearchedKey(){
        return searchField.getText();
    }

    public String getClassComboBoxSelectedItem()
    {
        if(classComboBox.getSelectedItem() != null)
            return classComboBox.getSelectedItem().toString();
        return null;
    }

    public String getSubjectComboBoxSelectedItem()
    {
        if(subjectComboBox.getSelectedItem() != null)
            return subjectComboBox.getSelectedItem().toString();
        return null;
    }

    public void setSubjectComboBoxData(List<String> listSubjectName)
    {
        subjectComboBox.addItem("All");
        subjectComboBoxUpdate.addItem("All");
        for(String name: listSubjectName)
        {
            subjectComboBox.addItem(name);
            subjectComboBoxUpdate.addItem(name);
        }
    }

    public void setClassComboBoxData(List<String> listClassName)
    {
        classComboBox.addItem("All");
        for(String name: listClassName)
        {
            classComboBox.addItem(name);
        }
    }

    public void refreshComboBoxData(List<String> listSubjectName, List<String> listClassName)
    {
        subjectComboBox.removeAllItems();
        subjectComboBoxUpdate.removeAllItems();
        classComboBox.removeAllItems();

        setSubjectComboBoxData(listSubjectName);
        setClassComboBoxData(listClassName);
    }

    public void showListPoint(List<Point> list) {
        int size = list.size();
        Object [][] point = new Object[size][8];
        for (int i = 0; i < size; i++) {
            point[i][0] = list.get(i).getStudentId();
            point[i][1] = list.get(i).getStudentName();
            point[i][2] = list.get(i).getSubjectName();
            point[i][3] = list.get(i).getPoint1();
            point[i][4] = list.get(i).getPoint2();
            point[i][5] = list.get(i).getPointFinal();
            point[i][6] = list.get(i).getTotalPoint();
            point[i][7] = list.get(i).getStudentClass();
        }
        pointTable.setModel(new DefaultTableModel(point, columnNames));
        pointTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        pointTable.getColumnModel().getColumn(2).setPreferredWidth(160);
    }

    public void fillFieldFromSelectedRow() {
        // get all values of selected row
        int row = pointTable.getSelectedRow();
        if (row >= 0) {
            studentIdField.setText(pointTable.getModel().getValueAt(row,0).toString());
            if(pointTable.getModel().getValueAt(row, 4) != null)
                point1Field.setText(pointTable.getModel().getValueAt(row, 4).toString());
            if(pointTable.getModel().getValueAt(row, 5) != null)
                point2Field.setText(pointTable.getModel().getValueAt(row, 5).toString());
            if(pointTable.getModel().getValueAt(row, 6) != null)
                pointFinalField.setText(pointTable.getModel().getValueAt(row, 6).toString());
            subjectComboBoxUpdate.setSelectedItem(pointTable.getModel().getValueAt(row, 2));

            studentIdField.setEditable(false);
            subjectComboBoxUpdate.setEnabled(false);

            editPointBtn.setEnabled(true);
            deletePointBtn.setEnabled(true);
            addPointBtn.setEnabled(false);
            subjectComboBox.setEnabled(false);
        }
    }

    public void clearPointInfo() {
        studentIdField.setText("");
        point1Field.setText("");
        point2Field.setText("");
        pointFinalField.setText("");
        subjectComboBoxUpdate.setSelectedItem("All");

        studentIdField.setEditable(true);
        subjectComboBoxUpdate.setEnabled(true);

        editPointBtn.setEnabled(false);
        deletePointBtn.setEnabled(false);
        addPointBtn.setEnabled(true);
        subjectComboBox.setEnabled(true);
    }

    public void showPointToTextField(Point point) {
        studentIdField.setText("" + point.getStudentId());
        point1Field.setText(point.getPoint1().toString());
        point2Field.setText("" + point.getPoint2().toString());
        pointFinalField.setText(point.getPointFinal().toString());

        studentIdField.setEditable(false);
        subjectComboBoxUpdate.setEnabled(false);

        editPointBtn.setEnabled(true);
        deletePointBtn.setEnabled(true);
        addPointBtn.setEnabled(false);
        subjectComboBox.setEnabled(false);
    }

    public Point getPointInfo() {
        // validate student
        if (!validateStudentId() || !validatePoint1() || !validatePoint2() || !validatePointFinal()) {
            return null;
        }
        try {
            Point point = new Point();
            point.setStudentId(studentIdField.getText());
            point.setSubjectName(subjectComboBoxUpdate.getSelectedItem().toString());
            point.setPoint1(Float.parseFloat(point1Field.getText()));
            point.setPoint2(Float.parseFloat(point2Field.getText()));
            point.setPointFinal(Float.parseFloat(pointFinalField.getText()));
            return point;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateStudentId() {
        String studentId = studentIdField.getText();
        if (studentId == null || "".equals(studentId.trim())) {
            studentIdField.requestFocus();
            showMessage("Mã sinh viên không được trống.");
            return false;
        }
        return true;
    }

    private boolean validatePoint1() {
        try {
            Float point = Float.parseFloat(point1Field.getText());
            if (point < 0 || point > 10) {
                point1Field.requestFocus();
                showMessage("Điểm không hợp lệ, điểm trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            point1Field.requestFocus();
            showMessage("Điểm không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validatePoint2() {
        try {
            Float point = Float.parseFloat(point2Field.getText());
            if (point < 0 || point > 10) {
                point2Field.requestFocus();
                showMessage("Điểm không hợp lệ, điểm trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            point2Field.requestFocus();
            showMessage("Điểm không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validatePointFinal() {
        try {
            Float point = Float.parseFloat(pointFinalField.getText());
            if (point < 0 || point > 10) {
                pointFinalField.requestFocus();
                showMessage("Điểm không hợp lệ, điểm trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            pointFinalField.requestFocus();
            showMessage("Điểm không hợp lệ!");
            return false;
        }
        return true;
    }

    public void addAddPointListener(ActionListener listener)
    {
        addPointBtn.addActionListener(listener);
    }
    public void addEditPointListener(ActionListener listener)
    {
        editPointBtn.addActionListener(listener);
    }
    public void addDeletePointListener(ActionListener listener)
    {
        deletePointBtn.addActionListener(listener);
    }
    public void addClearPointListener(ActionListener listener)
    {
        clearBtn.addActionListener(listener);
    }
    public void addSortByTotalPoint(ActionListener listener) {
        sortByTotalPoint.addActionListener(listener);
    }
    public void addSortByNameListener(ActionListener listener)
    {
        sortByNameBtn.addActionListener(listener);
    }
    public void addChooseExcelFileListener(ActionListener listener)
    {
        chooseExcelFileBtn.addActionListener(listener);
    }
    public void addExportExcelListener(ActionListener listener)
    {
        exportToExcelBtn.addActionListener(listener);
    }
    public void addSubjectComboxBoxListener(ActionListener listener)
    {
        subjectComboBox.addActionListener(listener);
    }
    public void addClassComboBoxListener(ActionListener listener)
    {
        classComboBox.addActionListener(listener);
    }
    public void addChooseRowOnTableListener(ListSelectionListener listener)
    {
        this.pointTable.getSelectionModel().addListSelectionListener(listener);
    }
    public void addSearchFieldListener(KeyListener keyListener)
    {
        searchField.addKeyListener(keyListener);
    }

    class PopUpMetalComboBoxUI extends MetalComboBoxUI {
        protected ComboPopup createPopup() {
            return new BasicComboPopup(comboBox) {
                protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {
                    Rectangle rect = super.computePopupBounds(px, py, pw, ph);
                    rect.y = -rect.height;
                    return rect;
                }
            };
        }
    }
}
