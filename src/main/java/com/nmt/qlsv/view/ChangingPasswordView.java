package com.nmt.qlsv.view;

import com.nmt.qlsv.entity.ChangingPasswordDto;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ChangingPasswordView extends JFrame {
    private JLabel oldPasswordLabel;
    private JLabel newPasswordLabel;
    private JLabel retypedNewPasswordLabel;

    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField retypedNewPasswordField;

    private JButton confirmBtn;

    public ChangingPasswordView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        oldPasswordLabel = new JLabel("Mật khẩu cũ");
        newPasswordLabel = new JLabel("Mật khẩu mới");
        retypedNewPasswordLabel = new JLabel("Nhập lại mật khẩu mới");

        oldPasswordField = new JPasswordField(15);
        newPasswordField = new JPasswordField(15);
        retypedNewPasswordField = new JPasswordField(15);

        confirmBtn = new JButton("Xác nhận");

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        // tạo đối tượng panel để chứa các thành phần của màn hình login
        panel.setSize(400, 300);
        panel.setLayout(layout);
        panel.add(oldPasswordLabel);
        panel.add(newPasswordLabel);
        panel.add(retypedNewPasswordLabel);
        panel.add(oldPasswordField);
        panel.add(newPasswordField);
        panel.add(retypedNewPasswordField);

        panel.add(confirmBtn);

        // cài đặt vị trí các thành phần trên màn hình login
        int westLabel = 40;
        int northLabel = 60;
        layout.putConstraint(SpringLayout.WEST, oldPasswordLabel, westLabel, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, oldPasswordLabel, northLabel, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, newPasswordLabel, westLabel, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, newPasswordLabel, 40, SpringLayout.NORTH, oldPasswordLabel);
        layout.putConstraint(SpringLayout.WEST, retypedNewPasswordLabel, westLabel, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, retypedNewPasswordLabel, 30, SpringLayout.NORTH, newPasswordLabel);

        int westField = 140;
        int northField = 60;
        layout.putConstraint(SpringLayout.WEST, oldPasswordField, westField, SpringLayout.WEST, oldPasswordLabel);
        layout.putConstraint(SpringLayout.NORTH, oldPasswordField, northField, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, newPasswordField, westField, SpringLayout.WEST, newPasswordLabel);
        layout.putConstraint(SpringLayout.NORTH, newPasswordField, 40, SpringLayout.NORTH, oldPasswordField);
        layout.putConstraint(SpringLayout.WEST, retypedNewPasswordField, westField, SpringLayout.WEST, retypedNewPasswordLabel);
        layout.putConstraint(SpringLayout.NORTH, retypedNewPasswordField, 30, SpringLayout.NORTH, newPasswordField);

        layout.putConstraint(SpringLayout.WEST, confirmBtn, 145, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, confirmBtn, 180, SpringLayout.NORTH, panel);

        // add panel tới JFrame
        this.add(panel);
        this.pack();
        // cài đặt các thuộc tính cho JFrame
        this.setTitle("Đổi mật khẩu");
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public ChangingPasswordDto getChangingPasswordDto() {
        if(validateNewPassword())
        {
            ChangingPasswordDto dto = new ChangingPasswordDto();
            dto.setOldPassword(String.valueOf(oldPasswordField.getPassword()));
            dto.setNewPassword(String.valueOf(newPasswordField.getPassword()));
            return dto;
        }
        showMessage("Mật khẩu mới không trùng khớp");
        return null;
    }

    private Boolean validateNewPassword()
    {
        String newPassword = String.valueOf(newPasswordField.getPassword());
        String retypedNewPassword = String.valueOf(retypedNewPasswordField.getPassword());
        if(newPassword.equals(retypedNewPassword))
            return true;
        return false;
    }

    public void addConfirmBtnListener(ActionListener listener) {
        confirmBtn.addActionListener(listener);
    }
}
