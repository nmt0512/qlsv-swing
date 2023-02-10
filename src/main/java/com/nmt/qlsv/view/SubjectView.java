package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.Subject;
import com.nmt.qlsv.entity.Teacher;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

public class SubjectView extends JPanel {
    private JTable subjectTable;
    private JScrollPane jScrollPaneTable;

    private final String[] tableColumn = new String[]{"ID", "Tên môn học", "Số tín chỉ", "Trưởng bộ môn"};
    private final Object data = new Object[][]{};

    private JLabel idLabel;
    private JLabel subjectNameLabel;
    private JLabel creditLabel;
    private JLabel teacherLabel;
    private JLabel searchLabel;

    private JTextField idField;
    private JTextField subjectNameField;
    private JTextField creditField;
    //    private JTextField teacherField;
    private JTextField searchField;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton clearBtn;

    private JButton exportToExcelBtn;

    private JComboBox<String> teacherComboBox;

    public SubjectView() {
        initComponent();
    }

    private void initComponent() {
        subjectTable = new JTable();
        subjectTable.setModel(new DefaultTableModel((Object[][]) data, tableColumn));

        jScrollPaneTable = new JScrollPane();
        jScrollPaneTable.setViewportView(subjectTable);
        jScrollPaneTable.setPreferredSize(new Dimension(850, 700));

        idLabel = new JLabel("ID");
        subjectNameLabel = new JLabel("Tên môn");
        creditLabel = new JLabel("Số TC");
        teacherLabel = new JLabel("GV bộ môn");

        searchLabel = new JLabel();
        searchLabel.setIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/search-icon.png"));

        idField = new JTextField(6);
        idField.setEditable(false);
        subjectNameField = new JTextField(18);
        creditField = new JTextField(6);
//        teacherField = new JTextField(15);
        searchField = new JTextField(15);
        teacherComboBox = new JComboBox<>();

        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");

        exportToExcelBtn = new JButton("Export to Excel");

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.add(idLabel);
        this.add(subjectNameLabel);
        this.add(creditLabel);
        this.add(teacherLabel);
        this.add(searchLabel);

        this.add(idField);
        this.add(subjectNameField);
        this.add(creditField);
        this.add(teacherComboBox);
//        this.add(teacherField);
        this.add(searchField);

        this.add(jScrollPaneTable);

        this.add(exportToExcelBtn);

        this.add(addBtn);
        this.add(editBtn);
        this.add(deleteBtn);
        this.add(clearBtn);

        int westSearchLabel = 1140;
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 6, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchLabel, westSearchLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, searchField, 8, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchField, westSearchLabel - 160, SpringLayout.WEST, this);

        int westLabel = 40;
        int northLabel = 70;
        layout.putConstraint(SpringLayout.WEST, idLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idLabel, northLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, subjectNameLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, subjectNameLabel, 30, SpringLayout.NORTH, idLabel);
        layout.putConstraint(SpringLayout.WEST, creditLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, creditLabel, 30, SpringLayout.NORTH, subjectNameLabel);
        layout.putConstraint(SpringLayout.WEST, teacherLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, teacherLabel, 30, SpringLayout.NORTH, creditLabel);

        int westTextField = 130;
        int northTextField = 70;
        layout.putConstraint(SpringLayout.WEST, idField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idField, northTextField, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, subjectNameField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, subjectNameField, 30, SpringLayout.NORTH, idField);
        layout.putConstraint(SpringLayout.WEST, creditField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, creditField, 30, SpringLayout.NORTH, subjectNameField);
        layout.putConstraint(SpringLayout.WEST, teacherComboBox, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, teacherComboBox, 30, SpringLayout.NORTH, creditField);

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

    public void showSubjectList(List<Subject> list) {
        int size = list.size();
        Object[][] subject = new Object[size][4];
        for (int i = 0; i < size; i++) {
            subject[i][0] = list.get(i).getId();
            subject[i][1] = list.get(i).getName();
            subject[i][2] = list.get(i).getCredit();
            subject[i][3] = list.get(i).getTeacherName() +" - "+ list.get(i).getTeacherId();
        }
        subjectTable.setModel(new DefaultTableModel(subject, tableColumn));
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

    public String getSearchedKey()
    {
        return searchField.getText();
    }

    public void clearSubjectInfo() {
        idField.setText("");
        subjectNameField.setText("");
        creditField.setText("");
        teacherComboBox.setSelectedItem("");

//        teacherField.setText("");
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
    }

    public void fillFieldFromSelectedRow() {
        // get all values of selected row
        int row = subjectTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(subjectTable.getModel().getValueAt(row, 0).toString());
            if (subjectTable.getModel().getValueAt(row, 1) != null)
                subjectNameField.setText(subjectTable.getModel().getValueAt(row, 1).toString());
            if (subjectTable.getModel().getValueAt(row, 2) != null)
                creditField.setText(subjectTable.getModel().getValueAt(row, 2).toString());
            if (subjectTable.getModel().getValueAt(row, 3) != null)
                teacherComboBox.setSelectedItem(subjectTable.getModel().getValueAt(row, 3));

            editBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            addBtn.setEnabled(false);
        }
    }

    public Subject getSubjectInfo() {
        if (!validateSubjectName() || !validateTeacherName() || !validateCredit())
            return null;
        try {
            Subject subject = new Subject();
            if(!idField.getText().isBlank())
                subject.setId(Integer.parseInt(idField.getText()));
            subject.setName(subjectNameField.getText());
            subject.setCredit(Integer.parseInt(creditField.getText()));

            String selectedTeacher = teacherComboBox.getSelectedItem().toString();
            List<String> teacher = List.of(selectedTeacher.split(" - "));

            subject.setTeacherName(teacher.get(0));
            subject.setTeacherId(Integer.parseInt(teacher.get(1)));
            return subject;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateSubjectName() {
        String subjectName = subjectNameField.getText();
        if (subjectName == null || "".equals(subjectName.trim())) {
            subjectNameField.requestFocus();
            showMessage("Tên môn học không được trống");
            return false;
        }
        return true;
    }

    private boolean validateCredit() {
        try {
            Integer credit = Integer.parseInt(creditField.getText());
            if (credit > 10 || credit < 1) {
                creditField.requestFocus();
                showMessage("Số tín chỉ không hợp lệ (số tín chỉ phải từ 1 đến 10)");
                return false;
            }
        } catch (Exception e) {
            creditField.requestFocus();
            showMessage("Số tín chỉ không hợp lệ");
            return false;
        }
        return true;
    }

    private boolean validateTeacherName() {
        String teacherName = teacherComboBox.getSelectedItem().toString();
        if (teacherName.equals("")) {
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
        this.subjectTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void addSearchFieldListener(KeyListener keyListener) {
        searchField.addKeyListener(keyListener);
    }
}
