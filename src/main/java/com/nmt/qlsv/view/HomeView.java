package com.nmt.qlsv.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JFrame{
    private JButton studentMngBtn;
    private JButton pointMngBtn;
    private JButton teacherMngBtn;
    private JButton subjectMngBtn;
    private JButton classMngBtn;
    private JButton sessionMngBtn;
    private JButton statisticBtn;
    private JButton emptyBtn;
    private JButton emptyBtn1;
    private JButton emptyBtn2;
    private JButton emptyBtn3;
    private JButton emptyBtn4;
    private JButton emptyBtn5;
    private JButton emptyBtn6;
    private JLabel logoKmaLabel;
//    private JPanel emptyPanel;
//    private JPanel emptyPanelFooter;

    private JPanel menuPanel;
    private JPanel mainPanel;

    private CardLayout cardLayout;
    public HomeView(StudentView studentView, PointView pointView, SubjectView subjectView, TeacherView teacherView) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //init menu Panel
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        studentMngBtn = new JButton("        Quản lý sinh viên        ");
        pointMngBtn   = new JButton("       Điểm thi sinh viên        ");
        teacherMngBtn = new JButton("       Quản lý giảng viên       ");
        subjectMngBtn = new JButton("         Quản lý môn học        ");
        classMngBtn = new JButton("          Quản lý lớp học         ");
        sessionMngBtn = new JButton("        Quản lý niên khóa       ");
        statisticBtn  = new JButton("              Thống kê               ");
        emptyBtn = new JButton("                                         ");
        emptyBtn1 = new JButton("                                         ");
        emptyBtn2 = new JButton("                                         ");
        emptyBtn3 = new JButton("                                         ");
        emptyBtn4 = new JButton("                                         ");
        emptyBtn5 = new JButton("                                         ");
        emptyBtn6 = new JButton("                                         ");

        logoKmaLabel = new JLabel();
        logoKmaLabel.setIcon(new ImageIcon(new ImageIcon("D:/Workspace/IntelliJ/qlsv-swing/src/main/resources/image/" +
                "Logo-Hoc-Vien-Ky-Thuat-Mat-Ma-ACTVN-1.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));

        studentMngBtn.setBackground(Color.WHITE);
        pointMngBtn.setBackground(Color.WHITE);
        teacherMngBtn.setBackground(Color.WHITE);
        subjectMngBtn.setBackground(Color.WHITE);
        classMngBtn.setBackground(Color.WHITE);
        sessionMngBtn.setBackground(Color.WHITE);
        statisticBtn.setBackground(Color.WHITE);

        emptyBtn.setBackground(Color.WHITE);
        emptyBtn1.setBackground(Color.WHITE);
        emptyBtn2.setBackground(Color.WHITE);
        emptyBtn3.setBackground(Color.WHITE);
        emptyBtn4.setBackground(Color.WHITE);
        emptyBtn5.setBackground(Color.WHITE);
        emptyBtn6.setBackground(Color.WHITE);

        emptyBtn.setEnabled(false);
        emptyBtn1.setEnabled(false);
        emptyBtn2.setEnabled(false);
        emptyBtn3.setEnabled(false);
        emptyBtn4.setEnabled(false);
        emptyBtn5.setEnabled(false);
        emptyBtn6.setEnabled(false);

        studentMngBtn.setBorder(new RoundedBorder(20));
        pointMngBtn.setBorder(new RoundedBorder(20));
        teacherMngBtn.setBorder(new RoundedBorder(20));
        subjectMngBtn.setBorder(new RoundedBorder(20));
        classMngBtn.setBorder(new RoundedBorder(20));
        sessionMngBtn.setBorder(new RoundedBorder(20));
        statisticBtn.setBorder(new RoundedBorder(20));

        emptyBtn.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn1.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn2.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn3.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn4.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn5.setBorder(BorderFactory.createEmptyBorder());
        emptyBtn6.setBorder(BorderFactory.createEmptyBorder());

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
        menuPanel.add(statisticBtn);


        //init main Panel
        mainPanel = new JPanel();

        cardLayout = new CardLayout();

        cardLayout.addLayoutComponent(studentView, "studentView");
        cardLayout.addLayoutComponent(pointView, "pointView");
        cardLayout.addLayoutComponent(subjectView, "subjectView");
        cardLayout.addLayoutComponent(teacherView, "teacherView");
//        cardLayout.addLayoutComponent(classView, "classView");
//        cardLayout.addLayoutComponent(sessionView, "sessionView");

        mainPanel.add(studentView, "studentView");
        mainPanel.add(pointView, "pointView");
        mainPanel.add(subjectView, "subjectView");
        mainPanel.add(teacherView, "teacherView");
//        mainPanel.add(classView, "classView");
//        mainPanel.add(sessionView, "sessionView");

        studentView.setVisible(true);
        pointView.setVisible(false);

        mainPanel.setLayout(cardLayout);

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
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

    public void changeMainPanelMng(String panelName)
    {
        cardLayout.show(mainPanel, panelName);
    }
    public void studentMngBtnListener(ActionListener listener)
    {
        studentMngBtn.addActionListener(listener);
    }
    public void pointMngBtnListener(ActionListener listener)
    {
        pointMngBtn.addActionListener(listener);
    }
    public void subjectMngBtnListener(ActionListener listener)
    {
        subjectMngBtn.addActionListener(listener);
    }
    public void teacherMngBtnListener(ActionListener listener)
    {
        teacherMngBtn.addActionListener(listener);
    }

    public void classMngBtnListener(ActionListener listener)
    {
        classMngBtn.addActionListener(listener);
    }

    public void sessionMngBtnListener(ActionListener listener)
    {
        sessionMngBtn.addActionListener(listener);
    }

}
