package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HeaderPanelUtil {

        // ===================== CONFIG =====================

        private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);

        private static final Color BUTTON_BG = new Color(37, 99, 235); 

        private static final Color BUTTON_HOVER = new Color(29, 78, 216); 

        private HeaderPanelUtil() {
        }

        // ===================== HEADER =====================
        public static JPanel createHeaderPanel(
                        String title,
                        JButton btnReload) {

                JPanel panel = new JPanel(new BorderLayout());
                panel.setOpaque(false);

                // ===== TITLE =====
                JLabel lblTitle = new JLabel(title);
                lblTitle.setFont(TITLE_FONT);
                lblTitle.setForeground(new Color(55, 65, 81)); 

                // ===== BUTTON =====
                String text = btnReload.getText();
                Icon icon = btnReload.getIcon();

                styleButton(btnReload);

                panel.add(lblTitle, BorderLayout.WEST);
                panel.add(btnReload, BorderLayout.EAST);

                return panel;
        }

        // ===================== STYLE BUTTON =====================
        private static void styleButton(JButton button) {

                button.setMinimumSize(new Dimension(40, 36));
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setHorizontalTextPosition(SwingConstants.RIGHT);
                button.setIconTextGap(8);

                if (button instanceof CustomButton customBtn) {
                        customBtn.setBackgroundColor(BUTTON_BG);
                        customBtn.setHoverColor(BUTTON_HOVER);
                        customBtn.setCornerRadius(10);
                        customBtn.setButtonFont(
                                        new Font("Segoe UI", Font.BOLD, 13));
                }
        }

        // ===================== FACTORY =====================
        public static CustomButton createReloadButton(String iconPath) {

                ImageIcon icon = ImageUtil.scaleIcon(new ImageIcon(iconPath), 16, 16);

                CustomButton btn = new CustomButton("Reload", icon);

                btn.setToolTipText("Tải lại dữ liệu");

                btn.setHorizontalAlignment(SwingConstants.CENTER);
                btn.setHorizontalTextPosition(SwingConstants.RIGHT);
                btn.setIconTextGap(8);

                return btn;
        }
}
