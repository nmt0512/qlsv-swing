package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.Teacher;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TeacherView extends JPanel {
    private JTable teacherTable;
    private JScrollPane jScrollPaneTable;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel birthdayLabel;
    private JLabel startWorkingLabel;
    private JLabel hometownLabel;

    private JLabel searchLabel;
    private JTextField searchField;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField birthdayField;
    private JTextField startWorkingField;
    private JTextField hometownField;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton clearBtn;

    private JButton exportToExcelBtn;
    private JButton sortByNameBtn;

    private final String[] tableColumn = new String[]{"ID", "Tên giáo viên", "Tuổi", "Số điện thoại", "Email", "Ngày sinh",
            "Ngày bắt đầu", "Quê quán"};
    private final Object data = new Object[][]{};

    public TeacherView()
    {
        initComponent();
    }

    private void initComponent()
    {
        idLabel = new JLabel("ID");
        nameLabel = new JLabel("Name");
        ageLabel = new JLabel("Age");
        phoneLabel = new JLabel("Phone");
        emailLabel = new JLabel("Email");
        birthdayLabel = new JLabel("Birthday");
        startWorkingLabel = new JLabel("Start Working");
        hometownLabel = new JLabel("Hometown");

        searchLabel = new JLabel();
        searchLabel.setIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/search-icon.png"));
        searchField = new JTextField(15);

        teacherTable = new JTable();
        teacherTable.setModel(new DefaultTableModel((Object[][]) data, tableColumn));
        jScrollPaneTable = new JScrollPane();
        jScrollPaneTable.setViewportView(teacherTable);
        jScrollPaneTable.setPreferredSize(new Dimension(850, 650));

        idField = new JTextField(6);
        idField.setEditable(false);
        nameField = new JTextField(15);
        ageField = new JTextField(6);
        phoneField = new JTextField(10);
        emailField = new JTextField(15);
        birthdayField = new JTextField(15);
        startWorkingField = new JTextField(15);
        hometownField = new JTextField(15);

        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");

        exportToExcelBtn = new JButton("Export to Excel");

        sortByNameBtn = new JButton("Sort by name");

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.add(jScrollPaneTable);

        this.add(idLabel);
        this.add(nameLabel);
        this.add(ageLabel);
        this.add(phoneLabel);
        this.add(emailLabel);
        this.add(birthdayLabel);
        this.add(startWorkingLabel);
        this.add(hometownLabel);

        this.add(searchLabel);
        this.add(searchField);

        this.add(idField);
        this.add(nameField);
        this.add(ageField);
        this.add(phoneField);
        this.add(emailField);
        this.add(birthdayField);
        this.add(startWorkingField);
        this.add(hometownField);

        this.add(exportToExcelBtn);

        this.add(sortByNameBtn);

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
        int westTextField = 130;
        int northLabel = 50;

        layout.putConstraint(SpringLayout.WEST, idLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idLabel, northLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 30, SpringLayout.NORTH, idLabel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 30, SpringLayout.NORTH, nameLabel);
        layout.putConstraint(SpringLayout.WEST, phoneLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, phoneLabel, 30, SpringLayout.NORTH, ageLabel);
        layout.putConstraint(SpringLayout.WEST, emailLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 30, SpringLayout.NORTH, phoneLabel);
        layout.putConstraint(SpringLayout.WEST, birthdayLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, birthdayLabel, 30, SpringLayout.NORTH, emailLabel);
        layout.putConstraint(SpringLayout.WEST, startWorkingLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, startWorkingLabel, 30, SpringLayout.NORTH, birthdayLabel);
        layout.putConstraint(SpringLayout.WEST, hometownLabel, westLabel, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hometownLabel, 30, SpringLayout.NORTH, startWorkingLabel);

        layout.putConstraint(SpringLayout.WEST, idField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, idField, northLabel, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameField, 30, SpringLayout.NORTH, idField);
        layout.putConstraint(SpringLayout.WEST, ageField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ageField, 30, SpringLayout.NORTH, nameField);
        layout.putConstraint(SpringLayout.WEST, phoneField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, phoneField, 30, SpringLayout.NORTH, ageField);
        layout.putConstraint(SpringLayout.WEST, emailField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, emailField, 30, SpringLayout.NORTH, phoneField);
        layout.putConstraint(SpringLayout.WEST, birthdayField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, birthdayField, 30, SpringLayout.NORTH, emailField);
        layout.putConstraint(SpringLayout.WEST, startWorkingField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, startWorkingField, 30, SpringLayout.NORTH, birthdayField);
        layout.putConstraint(SpringLayout.WEST, hometownField, westTextField, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, hometownField, 30, SpringLayout.NORTH, startWorkingField);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneTable, 330, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneTable, 45, SpringLayout.NORTH, this);

        int westAddBtn = 30;
        int northAddBtn = 300;
        layout.putConstraint(SpringLayout.WEST, addBtn, westAddBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, addBtn, northAddBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, editBtn, 60, SpringLayout.WEST, addBtn);
        layout.putConstraint(SpringLayout.NORTH, editBtn, northAddBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, deleteBtn, 60, SpringLayout.WEST, editBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, northAddBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, northAddBtn, SpringLayout.NORTH, this);

        int westSortNameBtn = 650;
        int northSortNameBtn = 710;
        layout.putConstraint(SpringLayout.WEST, sortByNameBtn, westSortNameBtn, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, sortByNameBtn, northSortNameBtn, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, exportToExcelBtn, 130, SpringLayout.WEST, sortByNameBtn);
        layout.putConstraint(SpringLayout.NORTH, exportToExcelBtn, northSortNameBtn, SpringLayout.NORTH, this);

        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
        this.setSize(1140, 800);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public String getSearchedKey()
    {
        return searchField.getText();
    }

    public void showTeacherList(List<Teacher> list) {
        int size = list.size();
        Object[][] teacher = new Object[size][8];
        for (int i = 0; i < size; i++) {
            teacher[i][0] = list.get(i).getId();
            teacher[i][1] = list.get(i).getName();
            teacher[i][2] = list.get(i).getAge();
            teacher[i][3] = list.get(i).getPhone();
            teacher[i][4] = list.get(i).getEmail();
            teacher[i][5] = dateToString(list.get(i).getBirthday());
            teacher[i][6] = dateToString(list.get(i).getStartWorking());
            teacher[i][7] = list.get(i).getHometown();
        }
        teacherTable.setModel(new DefaultTableModel(teacher, tableColumn));

        teacherTable.getColumnModel().getColumn(1).setPreferredWidth(140);
    }

    public void clearTeacherInfo() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        phoneField.setText("");
        emailField.setText("");
        birthdayField.setText("");
        startWorkingField.setText("");
        hometownField.setText("");

        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(true);
    }

    public void fillFieldFromSelectedRow() {
        // get all values of selected row
        int row = teacherTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(teacherTable.getModel().getValueAt(row, 0).toString());
            if (teacherTable.getModel().getValueAt(row, 1) != null)
                nameField.setText(teacherTable.getModel().getValueAt(row, 1).toString());
            if (teacherTable.getModel().getValueAt(row, 2) != null)
                ageField.setText(teacherTable.getModel().getValueAt(row, 2).toString());
            if (teacherTable.getModel().getValueAt(row, 3) != null)
                phoneField.setText(teacherTable.getModel().getValueAt(row, 3).toString());
            if (teacherTable.getModel().getValueAt(row, 4) != null)
                emailField.setText(teacherTable.getModel().getValueAt(row, 4).toString());
            if (teacherTable.getModel().getValueAt(row, 5) != null)
                birthdayField.setText(teacherTable.getModel().getValueAt(row, 5).toString());
            if (teacherTable.getModel().getValueAt(row, 6) != null)
                startWorkingField.setText(teacherTable.getModel().getValueAt(row, 6).toString());
            if (teacherTable.getModel().getValueAt(row, 7) != null)
                hometownField.setText(teacherTable.getModel().getValueAt(row, 7).toString());

            editBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            addBtn.setEnabled(false);
        }
    }

    public Teacher getTeacherInfo() {
        if (!validateName() || !validateAge() || !validatePhone() || !validateEmail()
                || !validateBirthday() || !validateStartWorking() || !validateHometown())
            return null;
        try
        {
            Teacher teacher = new Teacher();
            if(!idField.getText().isBlank())
                teacher.setId(Integer.parseInt(idField.getText()));
            teacher.setName(nameField.getText());
            teacher.setAge(Integer.parseInt(ageField.getText()));
            teacher.setPhone(phoneField.getText());
            teacher.setEmail(emailField.getText());
            teacher.setBirthday(stringToDate(birthdayField.getText()));
            teacher.setStartWorking(stringToDate(startWorkingField.getText()));
            teacher.setHometown(hometownField.getText());
            return teacher;
        }
        catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private Boolean validateName()
    {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            idField.requestFocus();
            showMessage("Tên giảng viên không được trống");
            return false;
        }
        return true;
    }

    private Boolean validateAge()
    {
        try {
            Integer age = Integer.parseInt(ageField.getText());
            if (age >= 100 || age <= 16) {
                ageField.requestFocus();
                showMessage("Tuổi không hợp lệ (tuổi lớn hơn 16 và nhỏ hơn 100)");
                return false;
            }
        } catch (Exception e) {
            ageField.requestFocus();
            showMessage("Tuổi không hợp lệ");
            return false;
        }
        return true;
    }

    private boolean validatePhone() {
        String phone = phoneField.getText();
        String regex = "0\\d{9}";
        if(phone == null || "".equals(phone.trim()) || !phone.matches(regex))
        {
            phoneField.requestFocus();
            showMessage("Số điện thoại không hợp lệ (10 chữ số và bắt đầu bằng số 0)");
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String email = emailField.getText();
        String regex = "\\w+@\\w+\\.\\w+";
        if(email == null || "".equals(email.trim()) || !email.matches(regex))
        {
            birthdayField.requestFocus();
            showMessage("Email không đúng định dạng");
            return false;
        }
        return true;
    }

    private boolean validateBirthday() {
        String date = birthdayField.getText();
        String regex = "[0-3]\\d/[01]\\d/[12]\\d{3}";
        if(date == null || "".equals(date.trim()) || !date.matches(regex))
        {
            birthdayField.requestFocus();
            showMessage("Ngày sinh không hợp lệ, vui lòng nhập ngày sinh đúng dạng dd/MM/yyyy");
            return false;
        }
        return true;
    }

    private boolean validateStartWorking() {
        String date = startWorkingField.getText();
        String regex = "[0-3]\\d/[01]\\d/[12]\\d{3}";
        if(date == null || "".equals(date.trim()) || !date.matches(regex))
        {
            birthdayField.requestFocus();
            showMessage("Ngày bắt đầu đi làm không hợp lệ, vui lòng nhập đúng định dạng dd/MM/yyyy");
            return false;
        }
        return true;
    }

    private Boolean validateHometown()
    {
        String hometown = hometownField.getText();
        if (hometown == null || "".equals(hometown.trim())) {
            hometownField.requestFocus();
            showMessage("Quê quán không được trống");
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

    private String dateToString(java.sql.Date date)
    {
        if(date != null)
        {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(date);
        }
        return "";
    }

    public void addAddBtnListener(ActionListener listener)
    {
        addBtn.addActionListener(listener);
    }
    public void addEditBtnListener(ActionListener listener)
    {
        editBtn.addActionListener(listener);
    }
    public void addDeleteBtnListener(ActionListener listener)
    {
        deleteBtn.addActionListener(listener);
    }
    public void addClearBtnListener(ActionListener listener)
    {
        clearBtn.addActionListener(listener);
    }
    public void addSortByNameBtnListener(ActionListener listener)
    {
        sortByNameBtn.addActionListener(listener);
    }
    public void addExportExcelBtnListener(ActionListener listener)
    {
        exportToExcelBtn.addActionListener(listener);
    }
    public void addSearchFieldListener(KeyListener listener)
    {
        searchField.addKeyListener(listener);
    }
    public void addChooseRowOnTableListener(ListSelectionListener listener) {
        this.teacherTable.getSelectionModel().addListSelectionListener(listener);
    }
}
