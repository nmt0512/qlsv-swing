package com.nmt.qlsv.controller;

import com.nmt.qlsv.dao.UserDao;
import com.nmt.qlsv.entity.ChangingPasswordDto;
import com.nmt.qlsv.entity.User;
import com.nmt.qlsv.view.ChangingPasswordView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ChangingPasswordController {
    private ChangingPasswordView changingPasswordView;
    private UserDao userDao;

    public ChangingPasswordController()
    {
        changingPasswordView = new ChangingPasswordView();
        userDao = new UserDao();

        changingPasswordView.addConfirmBtnListener(new ConfirmBtnListener());
    }

    public ChangingPasswordView getChangingPasswordView()
    {
        return changingPasswordView;
    }

    class ConfirmBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChangingPasswordDto dto = changingPasswordView.getChangingPasswordDto();
            if(dto != null)
            {
                User user = new User(UserDao.loggingInUser.getUsername(), dto.getOldPassword());
                if(userDao.checkUser(user))
                {
                    user.setPassword(dto.getNewPassword());
                    try {
                        userDao.updatePassword(user);
                        changingPasswordView.showMessage("Đổi mật khẩu thành công");
                        UserDao.loggingInUser.setPassword(dto.getNewPassword());
                        changingPasswordView.setVisible(false);
                    }
                    catch (SQLException e1)
                    {
                        changingPasswordView.showMessage("Lỗi hệ thống khi đổi mật khẩu");
                    }
                }
                else
                {
                    changingPasswordView.showMessage("Mật khẩu cũ không đúng");
                }
            }
        }
    }
}
