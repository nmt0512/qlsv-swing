package com.nmt.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.nmt.qlsv.dao.UserDao;
import com.nmt.qlsv.view.LoginView;
import com.nmt.qlsv.view.HomeView;
import com.nmt.qlsv.entity.User;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private HomeView homeView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
        view.addPasswordFieldEnterListener(new PasswordEnterListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // if login successfully, display the student management view
                HomeController homeController = new HomeController();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("Username hoặc password không đúng.");
            }
        }
    }

    class PasswordEnterListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                User user = loginView.getUser();
                if (userDao.checkUser(user)) {
                    // if login successfully, display the student management view
                    HomeController homeController = new HomeController();
                    loginView.setVisible(false);
                } else {
                    loginView.showMessage("Username hoặc password không đúng.");
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
