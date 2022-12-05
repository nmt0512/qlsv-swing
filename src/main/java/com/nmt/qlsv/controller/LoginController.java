package com.nmt.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nmt.qlsv.dao.UserDao;
import com.nmt.qlsv.view.LoginView;
import com.nmt.qlsv.view.StudentView;
import com.nmt.qlsv.entity.User;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private StudentView studentView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // if login successfully, display the student management view
                studentView = new StudentView();
                StudentController studentController = new StudentController(studentView);
                studentController.showStudentView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("Username hoặc password không đúng.");
            }
        }
    }
}
