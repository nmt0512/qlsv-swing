package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ClassView extends JPanel {
    private JTable classTable;
    private JScrollPane jScrollPaneTable;

    private final String[] tableColumn = new String[]{"ID", "Niên khóa", "Khoa", "Tên lớp", "Số lượng SV"};
    private final Object data = new Object[][]{};

    private JLabel idLabel;
    private JLabel sessionLabel;
    private JLabel departmentLabel;
    private JLabel nameLabel;
    private JLabel stuQuantityLabel;

    private JTextField idField;
    private JComboBox<String> sessionComboBox;
    private JComboBox<String> departmentComboBox;
    private JTextField nameField;
    private JTextField stuQuantityField;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton clearBtn;

    private JButton exportToExcelBtn;

    public ClassView() {
        initComponent();
    }

    public void initComponent() {
        classTable = new JTable();
        classTable.setModel(new DefaultTableModel((Object[][]) data, tableColumn));

        jScrollPaneTable = new JScrollPane();
        jScrollPaneTable.setViewportView(classTable);
        jScrollPaneTable.setPreferredSize(new Dimension(850, 700));

        idLabel = new JLabel("ID");
        sessionLabel = new JLabel("Session");
        departmentLabel = new JLabel("Department");
        nameLabel = new JLabel("Name");
        stuQuantityLabel = new JLabel("Student Quantity");

        idField = new JTextField(6);
        idField.setEditable(false);
        sessionComboBox = new JComboBox<>();
        departmentComboBox = new JComboBox<>();
        nameField = new JTextField(8);
        stuQuantityField = new JTextField(6);
        stuQuantityField.setEditable(false);

        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        exportToExcelBtn = new JButton("Export to Excel");

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.add(idLabel);
        this.add(sessionLabel);
        this.add(departmentLabel);
        this.add(nameLabel);
        this.add(stuQuantityLabel);

        this.add(idField);
        this.add(sessionComboBox);
        this.add(departmentComboBox);
        this.add(nameField);
        this.add(stuQuantityField);

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
        layout.putConstraint(SpringLayout.WEST, sessionLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, sessionLabel, 30, SpringLayout.NORTH, idLabel);
        layout.putConstraint(SpringLayout.WEST, departmentLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, departmentLabel, 30, SpringLayout.NORTH, sessionLabel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 30, SpringLayout.NORTH, departmentLabel);
        layout.putConstraint(SpringLayout.WEST, stuQuantityLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, stuQuantityLabel, 30, SpringLayout.NORTH, nameLabel);

        int westTextField = 150;
        int northTextField = 69;
        layout.putConstraint(SpringLayout.WEST, idField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idField, northTextField, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sessionComboBox, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, sessionComboBox, 30, SpringLayout.NORTH, idField);
        layout.putConstraint(SpringLayout.WEST, departmentComboBox, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, departmentComboBox, 30, SpringLayout.NORTH, sessionComboBox);
        layout.putConstraint(SpringLayout.WEST, nameField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameField, 30, SpringLayout.NORTH, departmentComboBox);
        layout.putConstraint(SpringLayout.WEST, stuQuantityField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, stuQuantityField, 30, SpringLayout.NORTH, nameField);


        int westBtn = 40;
        int northBtn = 270;
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
        int northChooseExcel = 330;
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

    public void showClassList(List<Clazz> list) {
        int size = list.size();
        Object[][] clazz = new Object[size][5];
        for (int i = 0; i < size; i++) {
            clazz[i][0] = list.get(i).getId();
            clazz[i][1] = list.get(i).getSessionId();
            clazz[i][2] = list.get(i).getDepartmentCode();
            clazz[i][3] = list.get(i).getName();
            clazz[i][4] = list.get(i).getStudentQuantity();
        }
        classTable.setModel(new DefaultTableModel(clazz, tableColumn));
    }

    public void setSessionComboBoxData(List<Session> sessionList) {
        sessionComboBox.addItem("");
        for (Session session : sessionList) {
            sessionComboBox.addItem(session.getId().toString());
        }
    }

    public void setDepartmentComboBoxData(List<Department> departmentList) {
        departmentComboBox.addItem("");
        for (Department department: departmentList) {
            departmentComboBox.addItem(department.getCode());
        }
    }

    public void refreshComboBoxData(List<Session> sessionList, List<Department> departmentList)
    {
        sessionComboBox.removeAllItems();
        departmentComboBox.removeAllItems();

        setSessionComboBoxData(sessionList);
        setDepartmentComboBoxData(departmentList);
    }

    public void clearClassInfo() {
        idField.setText("");
        sessionComboBox.setSelectedItem("");
        departmentComboBox.setSelectedItem("");
        nameField.setText("");
        stuQuantityField.setText("");

        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
    }

    public void fillFieldFromSelectedRow() {
        // get all values of selected row
        int row = classTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(classTable.getModel().getValueAt(row, 0).toString());
            sessionComboBox.setSelectedItem(classTable.getModel().getValueAt(row, 1).toString());
            departmentComboBox.setSelectedItem(classTable.getModel().getValueAt(row, 2));
            nameField.setText(classTable.getModel().getValueAt(row, 3).toString());
            stuQuantityField.setText(classTable.getModel().getValueAt(row, 4).toString());

            editBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            addBtn.setEnabled(false);
        }
    }

    public Clazz getClassInfo() {
        if (!validateClassName() || !validateSession() || !validateDepartment())
            return null;
        try {
            Clazz clazz = new Clazz();
            if(!idField.getText().isBlank())
                clazz.setId(Integer.parseInt(idField.getText()));
            clazz.setSessionId(Integer.parseInt(sessionComboBox.getSelectedItem().toString()));
            clazz.setDepartmentCode(departmentComboBox.getSelectedItem().toString());
            clazz.setName(nameField.getText());
            if(!stuQuantityField.getText().isBlank())
                clazz.setStudentQuantity(Integer.parseInt(stuQuantityField.getText()));
            else
                clazz.setStudentQuantity(0);
            return clazz;
        }
        catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateClassName() {
        String className = nameField.getText();
        if (className == null || "".equals(className.trim())) {
            nameField.requestFocus();
            showMessage("Tên lớp học không được trống");
            return false;
        }
        else if(!className.startsWith(departmentComboBox.getSelectedItem().toString()))
        {
            nameField.requestFocus();
            showMessage("Tên lớp học không hợp lệ với khoa " + departmentComboBox.getSelectedItem().toString());
            return false;
        }
        return true;
    }

    private boolean validateSession()
    {
        String session = sessionComboBox.getSelectedItem().toString();
        if (session.equals("")) {
            showMessage("Vui lòng chọn niên khóa");
            return false;
        }
        return true;
    }

    private boolean validateDepartment()
    {
        String department = departmentComboBox.getSelectedItem().toString();
        if (department.equals("")) {
            showMessage("Vui lòng chọn tên khoa");
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
        this.classTable.getSelectionModel().addListSelectionListener(listener);
    }


}
