package com.conveniencestore.gui.mainlayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

import java.awt.event.FocusAdapter;
import java.awt.event.MouseEvent;

import com.conveniencestore.gui.utils.AppColor;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;

public class HeaderPanel extends JPanel {

    public HeaderPanel() {
        setPreferredSize(new Dimension(0, 70));
        setBackground(AppColor.HEADER_BG);
        setLayout(new BorderLayout());

        // LEFT
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        left.setOpaque(false);

        JLabel title = new JLabel("Freshman Convenience Store");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

        left.add(title);

        // RIGHT
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        right.setOpaque(false);

        // Load + scale icon
        ImageIcon notifyIcon = ImageUtil.scaleIcon(
                new ImageIcon(getClass().getResource("/icon/notification.png")),
                20, 20);

        ImageIcon logoutIcon = ImageUtil.scaleIcon(
                new ImageIcon(getClass().getResource("/icon/logout.png")),
                20, 20);

        // Create buttons (icon only)
        CustomButton btnNotify = new CustomButton("", notifyIcon);
        CustomButton btnLogout = new CustomButton("", logoutIcon);

        Dimension btnSize = new Dimension(40, 40);
        btnNotify.setPreferredSize(btnSize);
        btnLogout.setPreferredSize(btnSize);

        // Style Notify
        btnNotify.setBackgroundColor(new Color(52, 152, 219));
        btnNotify.setHoverColor(new Color(41, 128, 185));
        btnNotify.setCornerRadius(20);
        btnNotify.setToolTipText("Thông báo");

        // Style Logout
        btnLogout.setBackgroundColor(new Color(231, 76, 60));
        btnLogout.setHoverColor(new Color(192, 57, 43));
        btnLogout.setCornerRadius(20);
        btnLogout.setToolTipText("Đăng xuất");

        right.add(btnNotify);
        right.add(btnLogout);

        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
    }

}
