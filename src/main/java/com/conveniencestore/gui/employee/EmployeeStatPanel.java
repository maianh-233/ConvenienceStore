package com.conveniencestore.gui.employee;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import com.conveniencestore.gui.utils.StatCard;
import com.conveniencestore.gui.utils.StatTheme;
import com.conveniencestore.gui.utils.ImageUtil;

public class EmployeeStatPanel extends JPanel {
    private StatCard cardActiveEmployee;
    private StatCard cardUnActiveEmployee;

    public EmployeeStatPanel() {
        setLayout(new GridLayout(1, 2, 15, 0));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Icon iconcardActiveEmployee = ImageUtil.scaleIcon(
                new ImageIcon(getIconUrl("/icon/active.png")), 20, 20);
        Icon iconcardUnActiveEmployee = ImageUtil.scaleIcon(
                new ImageIcon(getIconUrl("/icon/unactive.png")), 20, 20);

        cardActiveEmployee = new StatCard(
                "Nhân viên còn hoạt động",
                "0",
                iconcardActiveEmployee);

        cardUnActiveEmployee = new StatCard(
                "Nhân viên không hoạt động",
                "0",
                iconcardUnActiveEmployee);

        // Tuỳ chỉnh màu icon nền cho dễ phân biệt
        cardActiveEmployee.setBackground(StatTheme.CARD_BG);
        cardUnActiveEmployee.setBackground(StatTheme.CARD_BG);

        add(cardActiveEmployee);
        add(cardUnActiveEmployee);

    }

    /* ================= API cho PanelInventory ================= */

    public void setcardActiveEmployee(int value) {
        cardActiveEmployee.setValue(String.valueOf(value));
    }

    public void setcardUnActiveEmployee(int value) {
        cardUnActiveEmployee.setValue(String.valueOf(value));
    }

    private URL getIconUrl(String path) {
        return getClass().getResource(path);

    }
}