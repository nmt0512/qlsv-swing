package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.Point;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PointView extends JFrame implements ActionListener, ListSelectionListener {
    private JTable pointTable;
    private JScrollPane jScrollPanePointTable;

    private JLabel studentIdLabel;
    private JLabel point1Label;
    private JLabel point2Label;
    private JLabel pointFinalLabel;
    private JLabel subjectLabelBesideComboBox;

    private JTextField studentIdField;
    private JTextField point1Field;
    private JTextField point2Field;
    private JTextField pointFinalField;

    private JButton addPointBtn;
    private JButton editPointBtn;
    private JButton deletePointBtn;
    private JButton clearBtn;
    private JButton sortByNameBtn;
    private JButton sortByTotalPoint;
    private JButton chooseExcelFileBtn;
    private JButton exportToExcelBtn;
    private List<String> subjectComboBoxData;
    private JComboBox<String> subjectComboBox;
    private Object data = new Object [][] {};
    private String [] columnNames = new String [] {"Student ID", "Name", "Subject Name", "Point 1"
            , "Point 2", "Point Final", "Total Point"};


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
        subjectLabelBesideComboBox = new JLabel("Subject");
        subjectLabelBesideComboBox.setForeground(Color.GRAY);

        studentIdField = new JTextField(10);
        point1Field = new JTextField(6);
        point2Field = new JTextField(6);
        pointFinalField = new JTextField(6);
        subjectComboBox = new JComboBox<>();
        subjectComboBox.setUI(new PopUpMetalComboBoxUI());

        addPointBtn = new JButton("Add");
        editPointBtn = new JButton("Edit");
        deletePointBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortByNameBtn = new JButton("Sort by name");
        sortByTotalPoint = new JButton("Sort by Total Point");
        chooseExcelFileBtn = new JButton("Import excel");
        exportToExcelBtn = new JButton("Export excel");

        jScrollPanePointTable = new JScrollPane();

        subjectComboBoxData = new ArrayList<>();

        pointTable = new JTable();
        pointTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPanePointTable.setViewportView(pointTable);
        jScrollPanePointTable.setPreferredSize(new Dimension(680, 370));

        SpringLayout layout = new SpringLayout();

        JPanel panel = new JPanel();
        panel.setSize(1000, 450);
        panel.setLayout(layout);
        panel.add(studentIdLabel);
        panel.add(point1Label);
        panel.add(point2Label);
        panel.add(pointFinalLabel);
        panel.add(subjectLabelBesideComboBox);

        panel.add(studentIdField);
        panel.add(point1Field);
        panel.add(point2Field);
        panel.add(pointFinalField);

        panel.add(subjectComboBox);
        
        panel.add(addPointBtn);
        panel.add(editPointBtn);
        panel.add(deletePointBtn);
        panel.add(clearBtn);
        panel.add(sortByNameBtn);
        panel.add(sortByTotalPoint);
        panel.add(chooseExcelFileBtn);
        panel.add(exportToExcelBtn);

        panel.add(jScrollPanePointTable);

        layout.putConstraint(SpringLayout.WEST, studentIdLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, studentIdLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, point1Label, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, point1Label, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, point2Label, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, point2Label, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, pointFinalLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, pointFinalLabel, 130, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, studentIdField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, studentIdField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, point1Field, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, point1Field, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, point2Field, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, point2Field, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, pointFinalField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, pointFinalField, 130, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addPointBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addPointBtn, 180, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editPointBtn, 60, SpringLayout.WEST, addPointBtn);
        layout.putConstraint(SpringLayout.NORTH, editPointBtn, 180, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deletePointBtn, 60, SpringLayout.WEST, editPointBtn);
        layout.putConstraint(SpringLayout.NORTH, deletePointBtn, 180, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deletePointBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 180, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPanePointTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPanePointTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, sortByNameBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByNameBtn, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortByTotalPoint, 125, SpringLayout.WEST, sortByNameBtn);
        layout.putConstraint(SpringLayout.NORTH, sortByTotalPoint, 250, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, chooseExcelFileBtn, 290, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, chooseExcelFileBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, exportToExcelBtn, 290, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, exportToExcelBtn, 125, SpringLayout.WEST, sortByNameBtn);

        layout.putConstraint(SpringLayout.WEST, subjectLabelBesideComboBox, 800, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, subjectLabelBesideComboBox, 387, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, subjectComboBox, 850, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, subjectComboBox, 384, SpringLayout.NORTH, panel);

        editPointBtn.setEnabled(false);
        deletePointBtn.setEnabled(false);
        addPointBtn.setEnabled(true);
        this.add(panel);
        this.pack();
        this.setTitle("Student Point");
        this.setSize(1000, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
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

    public String getSubjectComboBoxSelectedItem()
    {
        return subjectComboBox.getSelectedItem().toString();
    }

    public void setDataSubjectComboBox(List<String> listSubjectName)
    {
        for(String name: listSubjectName)
        {
            subjectComboBox.addItem(name);
        }
    }

    public void showListPoint(List<Point> list) {
        int size = list.size();
        // StudentTable: 5 columns
        Object [][] point = new Object[size][7];
        for (int i = 0; i < size; i++) {
            point[i][0] = list.get(i).getStudentId();
            point[i][1] = list.get(i).getStudentName();
            point[i][2] = list.get(i).getSubjectName();
            point[i][3] = list.get(i).getPoint1();
            point[i][4] = list.get(i).getPoint2();
            point[i][5] = list.get(i).getPointFinal();
            point[i][6] = list.get(i).getTotalPoint();
        }
        pointTable.setModel(new DefaultTableModel(point, columnNames));
        pointTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        pointTable.getColumnModel().getColumn(2).setPreferredWidth(150);
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
            subjectComboBox.setSelectedItem(pointTable.getModel().getValueAt(row, 2));
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
            point.setSubjectName(subjectComboBox.getSelectedItem().toString());
            point.setPoint1(Float.parseFloat(point1Field.getText()));
            point.setPoint2(Float.parseFloat(point2Field.getText()));
            point.setPointFinal(Float.parseFloat(pointFinalField.getText()));
            return point;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }



    public String getSubjectSelectedFromComboBox()
    {
        return subjectComboBox.getSelectedItem().toString();
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

    @Override
    public void actionPerformed(ActionEvent e) {
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
    public void addChooseRowOnTableListener(ListSelectionListener listener)
    {
        this.pointTable.getSelectionModel().addListSelectionListener(listener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
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
