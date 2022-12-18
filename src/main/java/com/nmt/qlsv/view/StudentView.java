package com.nmt.qlsv.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import com.nmt.qlsv.entity.Student;

public class StudentView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton sortStudentNameBtn;
    private JButton chooseExcelFileBtn;
    private JButton exportToExcelBtn;
    private JButton showPointBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JScrollPane jScrollPaneAddress;
    private JTable studentTable;
    private JLabel idLabel;
    private JLabel studentIdLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel classLabel;
    private JLabel classLabelBesideComboBox;
    private JLabel searchLabel;
    private JTextField idField;
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField searchField;
    private JTextArea addressTA;
    private JComboBox<String> classComboBox;
    private JComboBox<String> classToUpdateComboBox;
    
    // define columns in student table
    private String [] columnNames = new String [] {"ID", "Student ID", "Name", "Age", "Address", "Class"};
    private String[] classComboBoxData = new String[] {"All", "CT4A", "CT4B", "CT4C", "CT4D"};
    private String[] classToUpdateComboBoxData = new String[] {"", "CT4A", "CT4B", "CT4C", "CT4D"};

    private Object data = new Object [][] {};
    
    public StudentView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // init all the button
        addStudentBtn = new JButton("Add");
        editStudentBtn = new JButton("Edit");
        deleteStudentBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortStudentNameBtn = new JButton("Sort By Name");
        chooseExcelFileBtn = new JButton("Import Excel");
        exportToExcelBtn = new JButton("Export Excel");
        showPointBtn = new JButton("Show student point");
        // init student table
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();
        
        // init label
        idLabel = new JLabel("Id");
        studentIdLabel = new JLabel("Student Id");
        nameLabel = new JLabel("Name");
        ageLabel = new JLabel("Age");
        addressLabel = new JLabel("Address");
        classLabel = new JLabel("Class");
        searchLabel = new JLabel();
        searchLabel.setIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/search-icon.png"));
        classLabelBesideComboBox = new JLabel("Class");
        classLabelBesideComboBox.setForeground(Color.GRAY);
        
        // init text field for student information
        idField = new JTextField((6));
        idField.setEditable(false);
        studentIdField = new JTextField(6);
        nameField = new JTextField(15);
        ageField = new JTextField(6);
        searchField = new JTextField(15);
        addressTA = new JTextArea();
        addressTA.setColumns(15);
        addressTA.setRows(5);
        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(addressTA);

        classComboBox = new JComboBox<>(classComboBoxData);
        classToUpdateComboBox = new JComboBox<>(classToUpdateComboBoxData);

        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension (680, 330));

        SpringLayout layout = new SpringLayout();

        JPanel panel = new JPanel();
        panel.setSize(1000, 500);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);
        
        panel.add(addStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);
        panel.add(sortStudentNameBtn);
        panel.add(chooseExcelFileBtn);
        panel.add(exportToExcelBtn);
        panel.add(showPointBtn);

        panel.add(idLabel);
        panel.add(studentIdLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(addressLabel);
        panel.add(classLabel);
        panel.add(classLabelBesideComboBox);
        panel.add(searchLabel);

        panel.add(idField);
        panel.add(studentIdField);
        panel.add(nameField);
        panel.add(ageField);
        panel.add(jScrollPaneAddress);
        panel.add(classToUpdateComboBox);
        panel.add(searchField);

        panel.add(classComboBox);

        //WEST: tinh tu ben trai
        //NORTH: tinh tu ben tren

        // setup the location of all component on login view
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 3, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchLabel, 950, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchField, 5, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchField, 790, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, studentIdLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, studentIdLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, classLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, classLabel, 233, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, studentIdField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, studentIdField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, classToUpdateComboBox, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, classToUpdateComboBox, 230, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 30, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 60, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, editStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteStudentBtn);

        layout.putConstraint(SpringLayout.WEST, sortStudentNameBtn, 559, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortStudentNameBtn, 380, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, chooseExcelFileBtn, 415, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, chooseExcelFileBtn, 500, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, exportToExcelBtn, 415, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, exportToExcelBtn, 618, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, showPointBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, showPointBtn, 415, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, classLabelBesideComboBox, 880, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, classLabelBesideComboBox, 373, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, classComboBox, 920, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, classComboBox, 370, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Student Information");
        this.setSize(1000, 500);
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
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
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListStudents(List<Student> list) {
        int size = list.size();
        Object [][] students = new Object[size][6];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getStudentId();
            students[i][2] = list.get(i).getName();
            students[i][3] = list.get(i).getAge();
            students[i][4] = list.get(i).getAddress();
            students[i][5] = list.get(i).getStudentClass();
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(150);
    }

    public void fillStudentFromSelectedRow() {
        // get all values of selected row
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(studentTable.getModel().getValueAt(row,0).toString());
            studentIdField.setText(studentTable.getModel().getValueAt(row, 1).toString());
            nameField.setText(studentTable.getModel().getValueAt(row, 2).toString());
            if(studentTable.getModel().getValueAt(row, 3) != null)
                ageField.setText(studentTable.getModel().getValueAt(row, 3).toString());
            if(studentTable.getModel().getValueAt(row, 4) != null)
                addressTA.setText(studentTable.getModel().getValueAt(row, 4).toString());
            if(studentTable.getModel().getValueAt(row, 5) != null)
                classToUpdateComboBox.setSelectedItem(studentTable.getModel().getValueAt(row, 5).toString());
            // enable Edit and Delete buttons
            editStudentBtn.setEnabled(true);
            deleteStudentBtn.setEnabled(true);
            // disable Add button
            addStudentBtn.setEnabled(false);
        }
    }

    public void clearStudentInfo() {
        idField.setText("");
        studentIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        classToUpdateComboBox.setSelectedItem("");
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }

    public void showStudentToTextField(Student student) {
        studentIdField.setText("" + student.getStudentId());
        nameField.setText(student.getName());
        ageField.setText("" + student.getAge());
        addressTA.setText(student.getAddress());
        classToUpdateComboBox.setSelectedItem(student.getStudentClass());
        // enable Edit and Delete buttons
        editStudentBtn.setEnabled(true);
        deleteStudentBtn.setEnabled(true);
        // disable Add button
        addStudentBtn.setEnabled(false);
    }

    public Student getStudentInfo() {
        // validate student
        if (!validateStudentId() || !validateName() || !validateAge() || !validateAddress()) {
            return null;
        }
        try {
            Student student = new Student();
            if(!idField.getText().isBlank())
                student.setId(Integer.parseInt(idField.getText()));
            student.setStudentId(studentIdField.getText());
            student.setName(nameField.getText().trim());
            student.setAge(Integer.parseInt(ageField.getText()));
            student.setAddress(addressTA.getText().trim());
            student.setStudentClass(classToUpdateComboBox.getSelectedItem().toString());
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public String getClassSelectedFromComboBox()
    {
        return classComboBox.getSelectedItem().toString();
    }
    public String getSearchedKey(){
        return searchField.getText();
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
    
    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Name không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAddress() {
        String address = addressTA.getText();
        if (address == null || "".equals(address.trim())) {
            addressTA.requestFocus();
            showMessage("Address không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAge() {
        try {
            Integer age = Integer.parseInt(ageField.getText());
            if (age < 0 || age > 100) {
                ageField.requestFocus();
                showMessage("Age không hợp lệ, age nên trong khoảng 0 đến 100.");
                return false;
            }
        } catch (Exception e) {
            ageField.requestFocus();
            showMessage("Age không hợp lệ!");
            return false;
        }
        return true;
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }
    
    public void addEdiStudentListener(ActionListener listener) {
        editStudentBtn.addActionListener(listener);
    }
    
    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    
    public void addSortStudentNameListener(ActionListener listener) {
        sortStudentNameBtn.addActionListener(listener);
    }

    public void addChooseExcelFileListener(ActionListener listener) {
        chooseExcelFileBtn.addActionListener(listener);
    }

    public void addExportToExcelListener(ActionListener listener) {
        exportToExcelBtn.addActionListener(listener);
    }

    public void addClassComboBoxListener(ActionListener listener) {
        classComboBox.addActionListener(listener);
    }

    public void addShowPointListener(ActionListener listener) {
        showPointBtn.addActionListener(listener);
    }
    public void addSearchFieldListener(KeyListener keyListener)
    {
        searchField.addKeyListener(keyListener);
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }
}
