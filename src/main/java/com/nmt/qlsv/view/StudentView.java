package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentView extends JPanel implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton sortStudentNameBtn;
    private JButton chooseExcelFileBtn;
    private JButton exportToExcelBtn;
    //    private JButton showPointBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JScrollPane jScrollPaneAddress;
    private JTable studentTable;
    private JLabel idLabel;
    private JLabel studentIdLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel classLabel;
    private JLabel birthdayLabel;
    private JLabel hometownLabel;
    private JLabel classLabelBesideComboBox;
    private JLabel searchLabel;
    private JTextField idField;
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField searchField;
    private JTextArea addressTA;
    private JComboBox<String> classComboBox;
    private JComboBox<String> classComboBoxToUpdate;
    private JTextField birthdayField;
    private JTextField hometownField;

    // define columns in student table
    private final String[] columnNames = new String[]{"ID", "Mã SV", "Họ tên", "Tuổi", "Địa chỉ", "Ngày sinh", "Quê quán", "Lớp"};
    private final Object data = new Object[][]{};

    public StudentView() {
        initComponents();
    }

    private void initComponents() {
        // init all the button
        addStudentBtn = new JButton("Add");
        editStudentBtn = new JButton("Edit");
        deleteStudentBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortStudentNameBtn = new JButton("Sort By Name");
        chooseExcelFileBtn = new JButton("Import Excel");
        exportToExcelBtn = new JButton("Export Excel");
//        showPointBtn = new JButton("Show student point");
        // init student table
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();

        // init label
        idLabel = new JLabel("ID");
        studentIdLabel = new JLabel("Mã SV");
        nameLabel = new JLabel("Họ tên");
        ageLabel = new JLabel("Tuổi");
        addressLabel = new JLabel("Địa chỉ");
        classLabel = new JLabel("Lớp");
        birthdayLabel = new JLabel("Ngày sinh");
        hometownLabel = new JLabel("Quê quán");
        searchLabel = new JLabel();
        searchLabel.setIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/search-icon.png"));
        classLabelBesideComboBox = new JLabel("Class");
        classLabelBesideComboBox.setForeground(Color.GRAY);

        classComboBox = new JComboBox<>();
        classComboBoxToUpdate = new JComboBox<>();

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
        birthdayField = new JTextField(15);
        hometownField = new JTextField(15);

        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(850, 650));

        SpringLayout layout = new SpringLayout();

        this.setSize(1140, 800);
        this.setLayout(layout);
        this.add(jScrollPaneStudentTable);

        this.add(addStudentBtn);
        this.add(editStudentBtn);
        this.add(deleteStudentBtn);
        this.add(clearBtn);
        this.add(sortStudentNameBtn);
        this.add(chooseExcelFileBtn);
        this.add(exportToExcelBtn);
//        this.add(showPointBtn);

        this.add(idLabel);
        this.add(studentIdLabel);
        this.add(nameLabel);
        this.add(ageLabel);
        this.add(addressLabel);
        this.add(classLabel);
        this.add(classLabelBesideComboBox);
        this.add(searchLabel);
        this.add(birthdayLabel);
        this.add(hometownLabel);

        this.add(idField);
        this.add(studentIdField);
        this.add(nameField);
        this.add(ageField);
        this.add(jScrollPaneAddress);
        this.add(classComboBoxToUpdate);
        this.add(searchField);
        this.add(birthdayField);
        this.add(hometownField);

        this.add(classComboBox);

        //WEST: tinh tu ben trai
        //NORTH: tinh tu ben tren

        // setup the location of all component on login view
        int westSearchLabel = 1140;
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchLabel, westSearchLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, searchField, 7, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchField, westSearchLabel - 160, SpringLayout.WEST, this);

        int westLabel = 40;
        int westTextField = 130;
        int northLabel = 50;

        int northClassLabel = 263;

        layout.putConstraint(SpringLayout.WEST, idLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idLabel, northLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, studentIdLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, studentIdLabel, 30, SpringLayout.NORTH, idLabel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 30, SpringLayout.NORTH, studentIdLabel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 30, SpringLayout.NORTH, nameLabel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 30, SpringLayout.NORTH, ageLabel);
        layout.putConstraint(SpringLayout.WEST, classLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, classLabel, northClassLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, birthdayLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, birthdayLabel, 30, SpringLayout.NORTH, classLabel);
        layout.putConstraint(SpringLayout.WEST, hometownLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hometownLabel, 30, SpringLayout.NORTH, birthdayLabel);

        layout.putConstraint(SpringLayout.WEST, idField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idField, northLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, studentIdField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, studentIdField, 30, SpringLayout.NORTH, idField);
        layout.putConstraint(SpringLayout.WEST, nameField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameField, 30, SpringLayout.NORTH, studentIdField);
        layout.putConstraint(SpringLayout.WEST, ageField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ageField, 30, SpringLayout.NORTH, nameField);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 30, SpringLayout.NORTH, ageField);
        layout.putConstraint(SpringLayout.WEST, classComboBoxToUpdate, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, classComboBoxToUpdate, northClassLabel - 3, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, birthdayField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, birthdayField, 33, SpringLayout.NORTH, classComboBoxToUpdate);
        layout.putConstraint(SpringLayout.WEST, hometownField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hometownField, 30, SpringLayout.NORTH, birthdayField);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 330, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 45, SpringLayout.NORTH, this);

        int westAddBtn = 50;
        int northAddBtn = 390;
        layout.putConstraint(SpringLayout.WEST, addStudentBtn, westAddBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, northAddBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 60, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, northAddBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, editStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, northAddBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, northAddBtn, SpringLayout.NORTH, this);

        int westSortNameBtn = 689;
        int westChooseExcelBtn = 630;
        int northSortNameBtn = 710;
        int northChooseExcelBtn = 745;
        layout.putConstraint(SpringLayout.WEST, sortStudentNameBtn, westSortNameBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, sortStudentNameBtn, northSortNameBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, chooseExcelFileBtn, westChooseExcelBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, chooseExcelFileBtn, northChooseExcelBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, exportToExcelBtn, northChooseExcelBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, exportToExcelBtn, 118, SpringLayout.WEST, chooseExcelFileBtn);
//        layout.putConstraint(SpringLayout.WEST, showPointBtn, 20, SpringLayout.WEST, this);
//        layout.putConstraint(SpringLayout.NORTH, showPointBtn, 715, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, classLabelBesideComboBox, 1040, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, classLabelBesideComboBox, 703, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, classComboBox, 1080, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, classComboBox, 700, SpringLayout.NORTH, this);

        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }

    public void setDataForComboBox(List<String> listClassName) {
        listClassName.add(0, "");
        for (String className : listClassName) {
            classComboBoxToUpdate.addItem(className);
        }
        listClassName.set(0, "All");
        for (String className : listClassName) {
            classComboBox.addItem(className);
        }
    }

    public void refreshComboBoxData(List<String> listClassName) {
        classComboBox.removeAllItems();
        classComboBoxToUpdate.removeAllItems();

        setDataForComboBox(listClassName);
    }

    public String chooseExcelFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void showListStudents(List<Student> list) {
        int size = list.size();
        Object[][] students = new Object[size][8];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getStudentId();
            students[i][2] = list.get(i).getName();
            students[i][3] = list.get(i).getAge();
            students[i][4] = list.get(i).getAddress();
            students[i][5] = dateToString(list.get(i).getBirthday());
            students[i][6] = list.get(i).getHometown();
            students[i][7] = list.get(i).getClassName();
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(150);
    }

    public void fillStudentFromSelectedRow() {
        // get all values of selected row
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(studentTable.getModel().getValueAt(row, 0).toString());
            studentIdField.setText(studentTable.getModel().getValueAt(row, 1).toString());
            nameField.setText(studentTable.getModel().getValueAt(row, 2).toString());
            if (studentTable.getModel().getValueAt(row, 3) != null)
                ageField.setText(studentTable.getModel().getValueAt(row, 3).toString());
            if (studentTable.getModel().getValueAt(row, 4) != null)
                addressTA.setText(studentTable.getModel().getValueAt(row, 4).toString());
            if (studentTable.getModel().getValueAt(row, 5) != null)
                birthdayField.setText(studentTable.getModel().getValueAt(row, 5).toString());
            if (studentTable.getModel().getValueAt(row, 6) != null)
                hometownField.setText(studentTable.getModel().getValueAt(row, 6).toString());
            if (studentTable.getModel().getValueAt(row, 7) != null)
                classComboBoxToUpdate.setSelectedItem(studentTable.getModel().getValueAt(row, 7).toString());
            // enable Edit and Delete buttons
            editStudentBtn.setEnabled(true);
            deleteStudentBtn.setEnabled(true);
            // disable Add button
            addStudentBtn.setEnabled(false);

//            studentIdField.setEditable(false);
        }
    }

    public void clearStudentInfo() {
        idField.setText("");
        studentIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        birthdayField.setText("");
        hometownField.setText("");
        classComboBoxToUpdate.setSelectedItem("");
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);

//        studentIdField.setEditable(true);
    }

    public void showStudentToTextField(Student student) {
        studentIdField.setText(student.getStudentId());
        nameField.setText(student.getName());
        ageField.setText("" + student.getAge());
        addressTA.setText(student.getAddress());
        birthdayField.setText(dateToString(student.getBirthday()));
        hometownField.setText(student.getHometown());
        classComboBoxToUpdate.setSelectedItem(student.getClassName());
        // enable Edit and Delete buttons
        editStudentBtn.setEnabled(true);
        deleteStudentBtn.setEnabled(true);
        // disable Add button
        addStudentBtn.setEnabled(false);

//        studentIdField.setEditable(false);
    }

    public Student getStudentInfo() {
        // validate student
        if (!validateStudentId() || !validateName() || !validateAge()
                || !validateAddress() || !validateBirthday() || !validateClassName()) {
            return null;
        }
        try {
            Student student = new Student();
            if (!idField.getText().isBlank())
                student.setId(Integer.parseInt(idField.getText()));
            student.setStudentId(studentIdField.getText());
            student.setName(nameField.getText().trim());
            student.setAge(Integer.parseInt(ageField.getText()));
            student.setAddress(addressTA.getText().trim());
            student.setBirthday(stringToDate(birthdayField.getText()));
            student.setHometown(hometownField.getText());
            student.setClassName(classComboBoxToUpdate.getSelectedItem().toString());
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public String getClassSelectedFromComboBox() {
        if (classComboBox.getSelectedItem() != null)
            return classComboBox.getSelectedItem().toString();
        return null;
    }

    public String getSearchedKey() {
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

    private boolean validateBirthday() {
        String date = birthdayField.getText();
        String regex = "[0-3]\\d/[01]\\d/[12]\\d{3}";
        if (date == null || "".equals(date.trim()) || !date.matches(regex)) {
            birthdayField.requestFocus();
            showMessage("Ngày sinh không hợp lệ, vui lòng nhập ngày sinh đúng dạng dd/MM/yyyy");
            return false;
        }
        return true;
    }

    private boolean validateClassName() {
        String className = classComboBoxToUpdate.getSelectedItem().toString();
        if (className.equals("")) {
            showMessage("Vui lòng chọn tên lớp");
            return false;
        }
        return true;
    }

    private Date stringToDate(String strDate) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
// because PreparedStatement#setDate(..) expects a java.sql.Date argument
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    private String dateToString(java.sql.Date date) {
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(date);
        }
        return "";
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }

    public void addEditStudentListener(ActionListener listener) {
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

    public void addSearchFieldListener(KeyListener keyListener) {
        searchField.addKeyListener(keyListener);
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }
}
