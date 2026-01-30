package com.conveniencestore;

import javax.swing.SwingUtilities;

import com.conveniencestore.bus.AuthBUS;
import com.conveniencestore.entity.User;
import com.conveniencestore.gui.LoginFrame;
import com.conveniencestore.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthBUS authBUS = new AuthBUS();
            User user = authBUS.autoLogin();
            
            if (user != null) {
                // Tự động đăng nhập thành công, hiển thị giao diện chính
                new MainFrame().setVisible(true);
            } else {
                // Không có token hoặc token hết hạn, hiển thị giao diện đăng nhập
                new LoginFrame().setVisible(true);
            }
        });
    }
}
