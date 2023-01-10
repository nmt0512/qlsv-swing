package com.nmt.qlsv.view;

import com.nmt.qlsv.dao.UserDao;
import com.nmt.qlsv.entity.Point;
import com.nmt.qlsv.entity.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends JFrame {
    private JButton studentMngBtn;
    private JButton pointMngBtn;
    private JButton teacherMngBtn;
    private JButton subjectMngBtn;
    private JButton classMngBtn;
    private JButton sessionMngBtn;
    private JButton logoutBtn;
    private JButton emptyBtn;
    private JButton emptyBtn1;
    private JButton emptyBtn2;
    private JButton emptyBtn3;
    private JButton emptyBtn4;
    private JButton emptyBtn5;
    private JButton emptyBtn6;
    private JButton emptyBtn7;
    private JButton emptyBtn8;
    private JButton emptyBtn9;
    private JButton emptyBtn10;
    private JLabel logoKmaLabel;
//    private JPanel emptyPanel;
//    private JPanel emptyPanelFooter;

    private JPanel menuPanel;
    private JPanel mainPanel;

    private CardLayout cardLayout;

    private StudentView studentView;
    private PointView pointView;
    private SubjectView subjectView;
    private TeacherView teacherView;
    private SessionView sessionView;
    private ClassView classView;

    private ChangingPasswordView changingPasswordView;

    private JLabel loggingInUserLabel;

    private JMenuBar menuBar;
    private JMenu accountMenu;
    private JMenuItem changePasswordMenuItem;

    public HomeView(StudentView studentView, PointView pointView, SubjectView subjectView,
                    TeacherView teacherView, SessionView sessionView, ClassView classView) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loggingInUserLabel = new JLabel(" User: " + UserDao.loggingInUser.getFullname());

        //init menu Panel
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        studentMngBtn = new JButton("        Quản lý sinh viên        ");
        pointMngBtn = new JButton("       Điểm thi sinh viên        ");
        teacherMngBtn = new JButton("       Quản lý giảng viên       ");
        subjectMngBtn = new JButton("         Quản lý môn học        ");
        classMngBtn = new JButton("          Quản lý lớp học         ");
        sessionMngBtn = new JButton("        Quản lý niên khóa       ");
        logoutBtn = new JButton("              Đăng xuất               ");
        emptyBtn = new JButton("                                         ");
        emptyBtn1 = new JButton("                                         ");
        emptyBtn2 = new JButton("                                         ");
        emptyBtn3 = new JButton("                                         ");
        emptyBtn4 = new JButton("                                         ");
        emptyBtn5 = new JButton("                                         ");
        emptyBtn6 = new JButton("                                         ");
        emptyBtn7 = new JButton("                                         ");
        emptyBtn8 = new JButton("                                         ");
        emptyBtn9 = new JButton("                                         ");
        emptyBtn10 = new JButton("                                         ");

        logoKmaLabel = new JLabel();
        logoKmaLabel.setIcon(new ImageIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/" +
                "Logo-Hoc-Vien-Ky-Thuat-Mat-Ma-ACTVN-1.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));

        studentMngBtn.setBackground(Color.WHITE);
        pointMngBtn.setBackground(Color.WHITE);
        teacherMngBtn.setBackground(Color.WHITE);
        subjectMngBtn.setBackground(Color.WHITE);
        classMngBtn.setBackground(Color.WHITE);
        sessionMngBtn.setBackground(Color.WHITE);
        logoutBtn.setBackground(Color.WHITE);

        emptyBtn.setBackground(Color.WHITE);
        emptyBtn1.setBackground(Color.WHITE);
        emptyBtn2.setBackground(Color.WHITE);
        emptyBtn3.setBackground(Color.WHITE);
        emptyBtn4.setBackground(Color.WHITE);
        emptyBtn5.setBackground(Color.WHITE);
        emptyBtn6.setBackground(Color.WHITE);
        emptyBtn7.setBackground(Color.WHITE);
        emptyBtn8.setBackground(Color.WHITE);
        emptyBtn9.setBackground(Color.WHITE);
        emptyBtn10.setBackground(Color.WHITE);

        emptyBtn.setEnabled(false);
        emptyBtn1.setEnabled(false);
        emptyBtn2.setEnabled(false);
        emptyBtn3.setEnabled(false);
        emptyBtn4.setEnabled(false);
        emptyBtn5.setEnabled(false);
        emptyBtn6.setEnabled(false);
        emptyBtn7.setEnabled(false);
        emptyBtn8.setEnabled(false);
        emptyBtn9.setEnabled(false);
        emptyBtn10.setEnabled(false);

        studentMngBtn.setBorder(new RoundedBorder(20));
        pointMngBtn.setBorder(new RoundedBorder(20));
        teacherMngBtn.setBorder(new RoundedBorder(20));
        subjectMngBtn.setBorder(new RoundedBorder(20));
        classMngBtn.setBorder(new RoundedBorder(20));
        sessionMngBtn.setBorder(new RoundedBorder(20));
        logoutBtn.setBorder(new RoundedBorder(20));

        logoutBtn.setForeground(Color.RED);

        emptyBtn.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn1.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn2.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn3.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn4.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn5.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn6.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn7.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn8.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn9.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn10.setBorder(BorderFactory.createEmptyBorder());

//        emptyPanelHeader = new JPanel();
//        emptyPanelFooter = new JPanel();

        BoxLayout menuLayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);

        menuPanel.setLayout(menuLayout);

        menuPanel.add(logoKmaLabel);

        menuPanel.add(emptyBtn);
        menuPanel.add(emptyBtn1);
        menuPanel.add(emptyBtn2);

        menuPanel.add(studentMngBtn);
        menuPanel.add(pointMngBtn);
        menuPanel.add(teacherMngBtn);
        menuPanel.add(subjectMngBtn);
        menuPanel.add(classMngBtn);
        menuPanel.add(sessionMngBtn);
        menuPanel.add(emptyBtn3);
        menuPanel.add(emptyBtn4);
        menuPanel.add(emptyBtn5);
        menuPanel.add(emptyBtn6);
        menuPanel.add(emptyBtn7);
//        menuPanel.add(emptyBtn8);

        menuPanel.add(logoutBtn);
        menuPanel.add(emptyBtn9);
        menuPanel.add(emptyBtn10);
        menuPanel.add(loggingInUserLabel);


        //init main Panel
        mainPanel = new JPanel();

        cardLayout = new CardLayout();

        this.studentView = studentView;
        this.pointView = pointView;
        this.subjectView = subjectView;
        this.teacherView = teacherView;
        this.classView = classView;
        this.sessionView = sessionView;

        cardLayout.addLayoutComponent(this.studentView, "studentView");
        cardLayout.addLayoutComponent(this.pointView, "pointView");
        cardLayout.addLayoutComponent(this.subjectView, "subjectView");
        cardLayout.addLayoutComponent(this.teacherView, "teacherView");
        cardLayout.addLayoutComponent(this.classView, "classView");
        cardLayout.addLayoutComponent(this.sessionView, "sessionView");

        mainPanel.add(this.studentView, "studentView");
        mainPanel.add(this.pointView, "pointView");
        mainPanel.add(this.subjectView, "subjectView");
        mainPanel.add(this.teacherView, "teacherView");
        mainPanel.add(this.classView, "classView");
        mainPanel.add(this.sessionView, "sessionView");

        mainPanel.setLayout(cardLayout);

        studentView.setVisible(true);
        pointView.setVisible(false);

        menuBar = new JMenuBar();

        accountMenu = new JMenu("Account");
        changePasswordMenuItem = new JMenuItem("Change Password...");
        changePasswordMenuItem.setIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/icons8-key-security-16.png"));
        accountMenu.add(changePasswordMenuItem);
        menuBar.add(accountMenu);

        this.setJMenuBar(menuBar);
        this.add(BorderLayout.WEST, menuPanel);
        this.add(mainPanel);
        this.pack();
        this.setTitle("Student Management Studio");
        this.setSize(1400, 850);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void setChangingPasswordView(ChangingPasswordView changingPasswordView)
    {
        this.changingPasswordView = changingPasswordView;
    }

    public void showChangingPasswordView()
    {
        this.changingPasswordView.setVisible(true);
    }

    public void setStudentMngBtnColor() {
        studentMngBtn.setBackground(Color.LIGHT_GRAY);
        pointMngBtn.setBackground(Color.WHITE);
        teacherMngBtn.setBackground(Color.WHITE);
        subjectMngBtn.setBackground(Color.WHITE);
        classMngBtn.setBackground(Color.WHITE);
        sessionMngBtn.setBackground(Color.WHITE);
    }

    public void setPointMngBtnColor() {
        studentMngBtn.setBackground(Color.WHITE);
        pointMngBtn.setBackground(Color.LIGHT_GRAY);
        teacherMngBtn.setBackground(Color.WHITE);
        subjectMngBtn.setBackground(Color.WHITE);
        classMngBtn.setBackground(Color.WHITE);
        sessionMngBtn.setBackground(Color.WHITE);
    }

    public void setSubjectMngBtnColor() {
        studentMngBtn.setBackground(Color.WHITE);
        pointMngBtn.setBackground(Color.WHITE);
        teacherMngBtn.setBackground(Color.WHITE);
        subjectMngBtn.setBackground(Color.LIGHT_GRAY);
        classMngBtn.setBackground(Color.WHITE);
        sessionMngBtn.setBackground(Color.WHITE);
    }

    public void setTeacherMngBtnColor() {
        studentMngBtn.setBackground(Color.WHITE);
        pointMngBtn.setBackground(Color.WHITE);
        teacherMngBtn.setBackground(Color.LIGHT_GRAY);
        subjectMngBtn.setBackground(Color.WHITE);
        classMngBtn.setBackground(Color.WHITE);
        sessionMngBtn.setBackground(Color.WHITE);
    }

    public void setClassMngBtnColor() {
        studentMngBtn.setBackground(Color.WHITE);
        pointMngBtn.setBackground(Color.WHITE);
        teacherMngBtn.setBackground(Color.WHITE);
        subjectMngBtn.setBackground(Color.WHITE);
        classMngBtn.setBackground(Color.LIGHT_GRAY);
        sessionMngBtn.setBackground(Color.WHITE);
    }

    public void setSessionMngBtnColor() {
        studentMngBtn.setBackground(Color.WHITE);
        pointMngBtn.setBackground(Color.WHITE);
        teacherMngBtn.setBackground(Color.WHITE);
        subjectMngBtn.setBackground(Color.WHITE);
        classMngBtn.setBackground(Color.WHITE);
        sessionMngBtn.setBackground(Color.LIGHT_GRAY);
    }

    public void refreshPointView(List<Point> pointList, List<String> listSubjectName, List<String> listClassName) {
        pointView.showListPoint(pointList);
        pointView.refreshComboBoxData(listSubjectName, listClassName);
    }

    public void refreshStudentView(List<Student> studentList, List<Clazz> clazzList) {
        studentView.showListStudents(studentList);
        List<String> classNameList = new ArrayList<>();
        for (Clazz clazz : clazzList)
            classNameList.add(clazz.getName());
        studentView.refreshComboBoxData(classNameList);
    }

    public void refreshSubjectView(List<Subject> subjectList, List<Teacher> teacherList) {
        subjectView.showSubjectList(subjectList);
        subjectView.refreshComboBoxData(teacherList);
    }

    public void refreshSessionView(List<Session> sessionList, List<Teacher> teacherList) {
        sessionView.showSessionList(sessionList);
        sessionView.refreshComboBoxData(teacherList);
    }

    public void refreshClassView(List<Clazz> clazzList, List<Session> sessionList, List<Department> departmentList) {
        classView.showClassList(clazzList);
        classView.refreshComboBoxData(sessionList, departmentList);
    }

    class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public void changeMainPanelMng(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void studentMngBtnListener(ActionListener listener) {
        studentMngBtn.addActionListener(listener);
    }

    public void pointMngBtnListener(ActionListener listener) {
        pointMngBtn.addActionListener(listener);
    }

    public void subjectMngBtnListener(ActionListener listener) {
        subjectMngBtn.addActionListener(listener);
    }

    public void teacherMngBtnListener(ActionListener listener) {
        teacherMngBtn.addActionListener(listener);
    }

    public void classMngBtnListener(ActionListener listener) {
        classMngBtn.addActionListener(listener);
    }

    public void sessionMngBtnListener(ActionListener listener) {
        sessionMngBtn.addActionListener(listener);
    }

    public void logoutBtnListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }
    public void addChangePasswordMenuItemListener(ActionListener listener)
    {
        changePasswordMenuItem.addActionListener(listener);
    }

}
