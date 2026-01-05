package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class ButtonPanelUtil {

   
    private static final int BUTTON_HEIGHT = 44;

    private ButtonPanelUtil() {}

    public static JPanel createButtonPanel(
            JButton btnView,
            JButton btnAdd,
            JButton btnDelete,
            JButton btnEdit,
            JButton btnExportExcel,
            JButton btnImportExcel,
            JButton btnExportPDF,
            JButton btnImportPDF
    ) {

        // 🔥 CENTER
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setOpaque(false);

        List<JButton> buttons = new ArrayList<>();

        add(panel, buttons, style(btnView, new Color(75, 85, 99)));    // Xám
        add(panel, buttons, style(btnAdd, new Color(99, 102, 241)));   // Tím
        add(panel, buttons, style(btnDelete, new Color(220, 38, 38))); // Đỏ
        add(panel, buttons, style(btnEdit, new Color(124, 58, 237)));  // Tím đậm

        if (btnExportExcel != null)
            add(panel, buttons, style(btnExportExcel, new Color(5, 150, 105)));

        if (btnImportExcel != null)
            add(panel, buttons, style(btnImportExcel, new Color(4, 120, 87)));

        if (btnExportPDF != null)
            add(panel, buttons, style(btnExportPDF, new Color(159, 18, 57)));

        if (btnImportPDF != null)
            add(panel, buttons, style(btnImportPDF, new Color(136, 19, 55)));

    
        return panel;
    }

    // ================= STYLE =================
    private static JButton style(JButton btn, Color bg) {

        // lưu text gốc
        btn.setToolTipText(btn.getText());

        btn.setMinimumSize(new Dimension(BUTTON_HEIGHT, BUTTON_HEIGHT));
        btn.setMaximumSize(new Dimension(150, BUTTON_HEIGHT));
        btn.setMargin(new Insets(0, 16, 0, 16));

        if (btn instanceof CustomButton cbtn) {
            cbtn.setBackgroundColor(bg);
            cbtn.setHoverColor(bg.darker());
            cbtn.setCornerRadius(12);
            cbtn.setButtonFont(new Font("Segoe UI", Font.BOLD, 14));
        } else {
            btn.setBackground(bg);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private static void add(JPanel panel, List<JButton> list, JButton btn) {
        panel.add(btn);
        list.add(btn);
    }
}
