package com.conveniencestore.gui.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * SearchPanelUtil - Search panel responsive
 *
 * - Panel nhỏ : chỉ icon search
 * - Panel lớn : icon + text
 * - Không bị "..."
 * - Không xử lý logic tìm kiếm
 */
public class SearchPanelUtil {

    // ===================== CONFIG =====================
    private static final int MIN_WIDTH_SHOW_TEXT = 260;

    private static final int TEXTFIELD_HEIGHT = 36;
    private static final int TEXTFIELD_MIN_WIDTH = 120;
    private static final int TEXTFIELD_MAX_WIDTH = 220;

    private static final Dimension BTN_ICON_SIZE = new Dimension(44, 36);
    private static final Dimension BTN_TEXT_SIZE = new Dimension(110, 36);

    private static final Color BUTTON_BG = new Color(22, 163, 74);
    private static final Color BUTTON_HOVER = new Color(21, 128, 61);

    private SearchPanelUtil() {
    }

    // ===================== CREATE SEARCH PANEL =====================
    public static JPanel createSearchPanel(JTextField txtSearch, JButton btnSearch) {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Lưu text gốc
        final String originalText = btnSearch.getText();

        // ===== TEXT FIELD =====
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setPreferredSize(new Dimension(TEXTFIELD_MAX_WIDTH, TEXTFIELD_HEIGHT));
        txtSearch.setMinimumSize(new Dimension(TEXTFIELD_MIN_WIDTH, TEXTFIELD_HEIGHT));
        txtSearch.setMaximumSize(new Dimension(Integer.MAX_VALUE, TEXTFIELD_HEIGHT));

        // ===== BUTTON =====
        styleButton(btnSearch);
        setButtonIconOnly(btnSearch);

        // ===== ADD =====
        panel.add(txtSearch);
        panel.add(Box.createHorizontalStrut(8));
        panel.add(btnSearch);

        // ===== RESPONSIVE =====
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();

                if (width < MIN_WIDTH_SHOW_TEXT) {
                    setButtonIconOnly(btnSearch);
                    txtSearch.setPreferredSize(
                            new Dimension(TEXTFIELD_MIN_WIDTH, TEXTFIELD_HEIGHT));
                } else {
                    setButtonWithText(btnSearch, originalText);
                    txtSearch.setPreferredSize(
                            new Dimension(TEXTFIELD_MAX_WIDTH, TEXTFIELD_HEIGHT));
                }

                panel.revalidate();
                panel.repaint();
            }
        });

        return panel;
    }

    // ===================== BUTTON STATES =====================
    private static void setButtonIconOnly(JButton btn) {
        btn.setText("");
        btn.setPreferredSize(BTN_ICON_SIZE);
        btn.setMinimumSize(BTN_ICON_SIZE);
        btn.setMaximumSize(BTN_ICON_SIZE);
    }

    private static void setButtonWithText(JButton btn, String text) {
        btn.setText(text);
        btn.setPreferredSize(BTN_TEXT_SIZE);
        btn.setMinimumSize(BTN_TEXT_SIZE);
        btn.setMaximumSize(BTN_TEXT_SIZE);
    }

    // ===================== STYLE BUTTON =====================
    private static void styleButton(JButton button) {

        if (button instanceof CustomButton customBtn) {
            customBtn.setBackgroundColor(BUTTON_BG);
            customBtn.setHoverColor(BUTTON_HOVER);
            customBtn.setCornerRadius(10);
            customBtn.setButtonFont(new Font("Segoe UI", Font.BOLD, 13));
        }

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(6);
    }

    // ===================== FACTORY BUTTON =====================
    public static CustomButton createSearchButton(String iconPath) {
        ImageIcon icon = ImageUtil.scaleIcon(new ImageIcon(iconPath), 16, 16);
        CustomButton btn = new CustomButton("Tìm kiếm", icon);
        btn.setToolTipText("Tìm kiếm");
        return btn;
    }
}
