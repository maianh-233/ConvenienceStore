package com.conveniencestore.gui.mainlayout;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import com.conveniencestore.gui.utils.AppColor;
import com.conveniencestore.gui.utils.ImageUtil;

public class SidebarButton extends JButton {

    private final String textLabel;

    public SidebarButton(String iconPath, String text) {
        this.textLabel = text;

        // ===== Load icon từ resources =====
        URL iconURL = SidebarButton.class.getResource(iconPath);
        if (iconURL == null) {
            throw new RuntimeException("Không tìm thấy icon: " + iconPath);
        }

        ImageIcon icon = new ImageIcon(iconURL);
        setIcon(ImageUtil.scaleIcon(icon, 30, 30));
        setText(text);

        // ===== Layout & style =====
        setHorizontalAlignment(LEFT);
        setIconTextGap(12);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(AppColor.SIDEBAR_BG);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));

        // ===== QUAN TRỌNG CHO BOX LAYOUT =====
        Dimension size = new Dimension(Integer.MAX_VALUE, 60);
        setPreferredSize(size);
        setMinimumSize(new Dimension(0, 60));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
    }

    /**
     * Hiện / ẩn text khi collapse sidebar
     */
    public void showText(boolean show) {
        if (show) {
            setText(textLabel);
            setHorizontalAlignment(LEFT);
            setIconTextGap(12);
            setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        } else {
            setText("");
            setHorizontalAlignment(CENTER);
            setIconTextGap(0);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        }
    }
}
