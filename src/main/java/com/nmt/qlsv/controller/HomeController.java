package com.nmt.qlsv.controller;

import com.nmt.qlsv.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController {
    private HomeView homeView;
    private StudentController studentController;
    private PointController pointController;
    private SubjectController subjectController;
    private TeacherController teacherController;
    public HomeController()
    {
        studentController = new StudentController();
        pointController = new PointController();
        subjectController = new SubjectController();
        teacherController = new TeacherController();

        StudentView studentView = studentController.getStudentView();
        PointView pointView = pointController.getPointView();
        SubjectView subjectView = subjectController.getSubjectView();
        TeacherView teacherView = teacherController.getTeacherView();

        this.homeView = new HomeView(studentView, pointView, subjectView, teacherView);

        homeView.pointMngBtnListener(new PointMngBtnListener());
        homeView.studentMngBtnListener(new StudentMngBtnListener());
        homeView.subjectMngBtnListener(new SubjectMngBtnListener());
        homeView.teacherMngBtnListener(new TeacherMngBtnListener());
        homeView.setVisible(true);
    }

    class PointMngBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.changeMainPanelMng("pointView");
        }
    }

    class StudentMngBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.changeMainPanelMng("studentView");
        }
    }

    class SubjectMngBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.changeMainPanelMng("subjectView");
        }
    }

    class TeacherMngBtnListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            homeView.changeMainPanelMng("teacherView");
        }
    }

}
