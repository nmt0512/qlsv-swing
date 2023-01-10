package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.*;
import com.nmt.qlsv.entity.Clazz;
import com.nmt.qlsv.entity.Subject;
import com.nmt.qlsv.entity.User;
import com.nmt.qlsv.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private HomeView homeView;

    private StudentController studentController;
    private PointController pointController;
    private SubjectController subjectController;
    private TeacherController teacherController;
    private SessionController sessionController;
    private ClassController classController;
    private ChangingPasswordController changingPasswordController;

    private StudentDao studentDao;
    private PointDao pointDao;
    private SubjectDao subjectDao;
    private ClazzDao clazzDao;
    private TeacherDao teacherDao;
    private SessionDao sessionDao;
    private DepartmentDao departmentDao;

    public HomeController() {
        studentController = new StudentController();
        pointController = new PointController();
        subjectController = new SubjectController();
        teacherController = new TeacherController();
        sessionController = new SessionController();
        classController = new ClassController();
        changingPasswordController = new ChangingPasswordController();

        subjectDao = new SubjectDao();
        clazzDao = new ClazzDao();
        teacherDao = new TeacherDao();
        sessionDao = new SessionDao();
        studentDao = new StudentDao();
        pointDao = new PointDao();
        departmentDao = new DepartmentDao();

        StudentView studentView = studentController.getStudentView();
        PointView pointView = pointController.getPointView();
        SubjectView subjectView = subjectController.getSubjectView();
        TeacherView teacherView = teacherController.getTeacherView();
        SessionView sessionView = sessionController.getSessionView();
        ClassView classView = classController.getClassView();
        ChangingPasswordView changingPasswordView = changingPasswordController.getChangingPasswordView();

        homeView = new HomeView(studentView, pointView, subjectView, teacherView, sessionView, classView);
        homeView.setChangingPasswordView(changingPasswordView);

        homeView.pointMngBtnListener(new PointMngBtnListener());
        homeView.studentMngBtnListener(new StudentMngBtnListener());
        homeView.subjectMngBtnListener(new SubjectMngBtnListener());
        homeView.teacherMngBtnListener(new TeacherMngBtnListener());
        homeView.sessionMngBtnListener(new SessionMngBtnListener());
        homeView.classMngBtnListener(new ClassMngBtnListener());
        homeView.logoutBtnListener(new LogoutBtnListener());
        homeView.addChangePasswordMenuItemListener(new ChangePasswordMenuItemListener());
        homeView.setVisible(true);
    }

    class PointMngBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> subjectNameList = new ArrayList<>();
            List<String> classNameList = new ArrayList<>();
            for(Subject subject: subjectDao.findAll())
                subjectNameList.add(subject.getName());
            for(Clazz clazz: clazzDao.findAll())
                classNameList.add(clazz.getName());
            homeView.refreshPointView(pointDao.findAll(null, null), subjectNameList, classNameList);

            homeView.setPointMngBtnColor();
            homeView.changeMainPanelMng("pointView");
        }
    }

    class StudentMngBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.refreshStudentView(studentDao.findAll(), clazzDao.findAll());

            homeView.setStudentMngBtnColor();
            homeView.changeMainPanelMng("studentView");
        }
    }

    class SubjectMngBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.refreshSubjectView(subjectDao.findAll(), teacherDao.findAll());

            homeView.setSubjectMngBtnColor();
            homeView.changeMainPanelMng("subjectView");
        }
    }

    class TeacherMngBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.setTeacherMngBtnColor();
            homeView.changeMainPanelMng("teacherView");
        }
    }

    class SessionMngBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.refreshSessionView(sessionDao.findAll(), teacherDao.findAll());

            homeView.setSessionMngBtnColor();
            homeView.changeMainPanelMng("sessionView");
        }
    }

    class ClassMngBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.refreshClassView(clazzDao.findAll(), sessionDao.findAll(), departmentDao.findAll());

            homeView.setClassMngBtnColor();
            homeView.changeMainPanelMng("classView");
        }
    }

    class LogoutBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (JOptionPane.YES_OPTION == choice)
            {
                UserDao.loggingInUser = new User();
                homeView.setVisible(false);
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginController.showLoginView();
            }
        }
    }

    class ChangePasswordMenuItemListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.showChangingPasswordView();
        }
    }
}
