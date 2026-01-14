package com.conveniencestore.gui.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * PaginationUtil - Panel phân trang RESPONSIVE
 *
 * Chức năng:
 * - Khi panel đủ rộng → hiện icon + text
 * - Khi panel bị thu nhỏ → CHỈ hiện icon
 * - Tự động theo resize
 * - Không xử lý logic phân trang
 */
public class PaginationUtil {

    // ===================== CẤU HÌNH =====================
    private static final Color BUTTON_BG = new Color(245, 158, 11); // amber-500
    private static final Color BUTTON_HOVER = new Color(217, 119, 6); // amber-600

    // Ngưỡng width để ẩn/hiện text
    private static final int MIN_WIDTH_SHOW_TEXT = 200;

    private PaginationUtil() {
    }

    /**
     * Tạo panel phân trang responsive
     *
     * @param btnPrev nút Prev (CustomButton hoặc JButton)
     * @param btnNext nút Next
     * @return JPanel phân trang
     */
    public static JPanel createPaginationPanel(JButton btnPrev, JButton btnNext) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        panel.setOpaque(false);

        // Lưu text gốc để khôi phục khi resize lớn
        String prevText = btnPrev.getText();
        String nextText = btnNext.getText();

        styleButton(btnPrev);
        styleButton(btnNext);

        panel.add(btnPrev);
        panel.add(btnNext);

        return panel;
    }

    // ===================== STYLE BUTTON =====================
    private static void styleButton(JButton button) {

        // Preferred size mềm → resize được
        button.setPreferredSize(new Dimension(100, 36));

        if (button instanceof CustomButton customBtn) {
            customBtn.setBackgroundColor(BUTTON_BG);
            customBtn.setHoverColor(BUTTON_HOVER);
            customBtn.setCornerRadius(10);
        }

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // ===================== FACTORY BUTTON =====================
    public static CustomButton createPrevButton(String iconPath) {
        ImageIcon icon = ImageUtil.scaleIcon(new ImageIcon(iconPath), 16, 16);
        CustomButton btn = new CustomButton("Prev", icon);
        btn.setToolTipText("Trang trước");
        return btn;
    }

    public static CustomButton createNextButton(String iconPath) {
        ImageIcon icon = ImageUtil.scaleIcon(new ImageIcon(iconPath), 16, 16);
        CustomButton btn = new CustomButton("Next", icon);
        btn.setToolTipText("Trang sau");
        return btn;
    }
}
