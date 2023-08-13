package com.nmt.qlsv;

import java.awt.EventQueue;

import com.nmt.qlsv.controller.LoginController;
import com.nmt.qlsv.view.LoginView;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView view = new LoginView();
            LoginController controller = new LoginController(view);
            // show the login view
            controller.showLoginView();
        });
    }
}