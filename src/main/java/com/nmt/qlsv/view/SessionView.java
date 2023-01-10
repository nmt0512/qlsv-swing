package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.Session;
import com.nmt.qlsv.entity.Teacher;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SessionView extends JPanel {
    private JTable sessionTable;
    private JScrollPane jScrollPaneTable;

    private final String[] tableColumn = new String[]{"ID", "Năm bắt đầu", "Năm kết thúc", "Số lượng sinh viên", "Trưởng bộ môn"};
    private final Object data = new Object[][]{};

    private JLabel idLabel;
    private JLabel startYearLabel;
    private JLabel endYearLabel;
    private JLabel stuQuantityLabel;
    private JLabel teacherNameLabel;

    private JTextField idField;
    private JTextField startYearField;
    private JTextField endYearField;
    private JTextField stuQuantityField;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton clearBtn;

    private JButton exportToExcelBtn;

    private JComboBox<String> teacherComboBox;

    public SessionView()
    {
        initComponent();
    }

    private void initComponent()
    {
        sessionTable = new JTable();
        sessionTable.setModel(new DefaultTableModel((Object[][]) data, tableColumn));

        jScrollPaneTable = new JScrollPane();
        jScrollPaneTable.setViewportView(sessionTable);
        jScrollPaneTable.setPreferredSize(new Dimension(850, 700));

        idLabel = new JLabel("ID");
        startYearLabel = new JLabel("Start Year");
        endYearLabel = new JLabel("End Year");
        stuQuantityLabel = new JLabel("Student Quantity");
        teacherNameLabel = new JLabel("Teacher name");

        idField = new JTextField(6);
        startYearField = new JTextField(10);
        endYearField = new JTextField(10);
        stuQuantityField = new JTextField(8);
        stuQuantityField.setEditable(false);
        teacherComboBox = new JComboBox<>();

        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");

        exportToExcelBtn = new JButton("Export to Excel");

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.add(idLabel);
        this.add(startYearLabel);
        this.add(endYearLabel);
        this.add(stuQuantityLabel);
        this.add(teacherNameLabel);

        this.add(idField);
        this.add(startYearField);
        this.add(endYearField);
        this.add(stuQuantityField);
        this.add(teacherComboBox);

        this.add(jScrollPaneTable);

        this.add(exportToExcelBtn);

        this.add(addBtn);
        this.add(editBtn);
        this.add(deleteBtn);
        this.add(clearBtn);

        int westLabel = 40;
        int northLabel = 70;
        layout.putConstraint(SpringLayout.WEST, idLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idLabel, northLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, startYearLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, startYearLabel, 30, SpringLayout.NORTH, idLabel);
        layout.putConstraint(SpringLayout.WEST, endYearLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, endYearLabel, 30, SpringLayout.NORTH, startYearLabel);
        layout.putConstraint(SpringLayout.WEST, stuQuantityLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, stuQuantityLabel, 30, SpringLayout.NORTH, endYearLabel);
        layout.putConstraint(SpringLayout.WEST, teacherNameLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, teacherNameLabel, 30, SpringLayout.NORTH, stuQuantityLabel);

        int westTextField = 150;
        int northTextField = 70;
        layout.putConstraint(SpringLayout.WEST, idField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idField, northTextField, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, startYearField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, startYearField, 30, SpringLayout.NORTH, idField);
        layout.putConstraint(SpringLayout.WEST, endYearField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, endYearField, 30, SpringLayout.NORTH, startYearField);
        layout.putConstraint(SpringLayout.WEST, stuQuantityField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, stuQuantityField, 30, SpringLayout.NORTH, endYearField);
        layout.putConstraint(SpringLayout.WEST, teacherComboBox, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, teacherComboBox, 30, SpringLayout.NORTH, stuQuantityField);

        int westBtn = 40;
        int northBtn = 240;
        layout.putConstraint(SpringLayout.WEST, addBtn, westBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, addBtn, northBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, editBtn, 60, SpringLayout.WEST, addBtn);
        layout.putConstraint(SpringLayout.NORTH, editBtn, northBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, deleteBtn, 60, SpringLayout.WEST, editBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, northBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, northBtn, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneTable, 330, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneTable, 50, SpringLayout.NORTH, this);

        int westChooseExcel = 110;
        int northChooseExcel = 300;
        layout.putConstraint(SpringLayout.NORTH, exportToExcelBtn, northChooseExcel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, exportToExcelBtn, westChooseExcel, SpringLayout.WEST, this);

        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
        this.setSize(1140, 800);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void showSessionList(List<Session> list) {
        int size = list.size();
        Object[][] session = new Object[size][5];
        for (int i = 0; i < size; i++) {
            session[i][0] = list.get(i).getId();
            session[i][1] = list.get(i).getStartYear();
            session[i][2] = list.get(i).getEndYear();
            session[i][3] = list.get(i).getStuQuantity();
            session[i][4] = list.get(i).getTeacherName() +" - "+ list.get(i).getTeacherId();
        }
        sessionTable.setModel(new DefaultTableModel(session, tableColumn));
    }

    public void setTeacherComboBoxData(List<Teacher> teacherList)
    {
        teacherComboBox.addItem("");
        for(Teacher teacher: teacherList)
        {
            teacherComboBox.addItem(teacher.getName() +" - "+ teacher.getId());
        }
    }

    public void refreshComboBoxData(List<Teacher> teacherList)
    {
        teacherComboBox.removeAllItems();
        setTeacherComboBoxData(teacherList);
    }

    public void clearSessionInfo() {
        idField.setText("");
        startYearField.setText("");
        endYearField.setText("");
        stuQuantityField.setText("");
        teacherComboBox.setSelectedItem("");

        idField.setEditable(true);
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
    }

    public void fillFieldFromSelectedRow() {
        // get all values of selected row
        int row = sessionTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(sessionTable.getModel().getValueAt(row, 0).toString());
            if (sessionTable.getModel().getValueAt(row, 1) != null)
                startYearField.setText(sessionTable.getModel().getValueAt(row, 1).toString());
            if (sessionTable.getModel().getValueAt(row, 2) != null)
                endYearField.setText(sessionTable.getModel().getValueAt(row, 2).toString());
            if (sessionTable.getModel().getValueAt(row, 3) != null)
                stuQuantityField.setText(sessionTable.getModel().getValueAt(row, 3).toString());
            teacherComboBox.setSelectedItem(sessionTable.getModel().getValueAt(row, 4));

            idField.setEditable(false);
            editBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            addBtn.setEnabled(false);
        }
    }

    public Session getSessionInfo() {
        if (!validateStartYear() || !validateTeacherName() || !validateEndYear() || !validateId() || !validateStartYearEndYear())
            return null;
        try {
            Session session = new Session();
            session.setId(Integer.parseInt(idField.getText()));
            session.setStartYear(Integer.parseInt(startYearField.getText()));
            session.setEndYear(Integer.parseInt(endYearField.getText()));
            if(stuQuantityField.getText().equals(""))
                session.setStuQuantity(0);
            else
                session.setStuQuantity(Integer.parseInt(stuQuantityField.getText()));

            String selectedTeacher = teacherComboBox.getSelectedItem().toString();
            List<String> teacher = List.of(selectedTeacher.split(" - "));
            session.setTeacherName(teacher.get(0));
            session.setTeacherId(Integer.parseInt(teacher.get(1)));
            return session;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateId() {
        try {
            Integer id = Integer.parseInt(idField.getText());
            if (id < 0) {
                idField.requestFocus();
                showMessage("ID khóa không hợp lệ (ID > 0)");
                return false;
            }
        } catch (Exception e) {
            idField.requestFocus();
            showMessage("ID khóa không hợp lệ");
            return false;
        }
        return true;
    }
    private boolean validateStartYear() {
        try {
            Integer year = Integer.parseInt(startYearField.getText());
            if (year > 3000 || year < 1990) {
                startYearField.requestFocus();
                showMessage("Năm bắt đầu không hợp lệ (1990 -> 3000)");
                return false;
            }
        } catch (Exception e) {
            startYearField.requestFocus();
            showMessage("Năm bắt đầu không hợp lệ");
            return false;
        }
        return true;
    }

    private boolean validateEndYear() {
        try {
            Integer year = Integer.parseInt(endYearField.getText());
            if (year > 3000 || year < 1990) {
                endYearField.requestFocus();
                showMessage("Năm kết thúc không hợp lệ (1990 -> 3000)");
                return false;
            }
        } catch (Exception e) {
            endYearField.requestFocus();
            showMessage("Năm kết thúc không hợp lệ");
            return false;
        }
        return true;
    }

    private boolean validateStartYearEndYear() {
        try {
            Integer startYear = Integer.parseInt(startYearField.getText());
            Integer endYear = Integer.parseInt(endYearField.getText());
            if (endYear <= startYear) {
                endYearField.requestFocus();
                showMessage("Năm kết thúc phải lớn hơn năm bắt đầu");
                return false;
            }
        } catch (Exception e) {
            endYearField.requestFocus();
            showMessage("Năm kết thúc hoặc năm bắt đầu không hợp lệ");
            return false;
        }
        return true;
    }

    private boolean validateTeacherName() {
        String teacherName = teacherComboBox.getSelectedItem().toString();
        if (teacherName.equals("")) {
            idField.requestFocus();
            showMessage("Vui lòng chọn tên trưởng bộ môn");
            return false;
        }
        return true;
    }

    public void addAddBtnListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }

    public void addEditBtnListener(ActionListener listener) {
        editBtn.addActionListener(listener);
    }

    public void addDeleteBtnListener(ActionListener listener) {
        deleteBtn.addActionListener(listener);
    }

    public void addClearBtnListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addExportExcelBtnListener(ActionListener listener) {
        exportToExcelBtn.addActionListener(listener);
    }

    public void addChooseRowOnTableListener(ListSelectionListener listener) {
        this.sessionTable.getSelectionModel().addListSelectionListener(listener);
    }
}
