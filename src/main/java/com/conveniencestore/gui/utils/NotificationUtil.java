package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * NotificationUtil - Hiển thị thông báo dạng toast trong Swing
 * Loại: SUCCESS, WARNING, ERROR, INFO
 * Hỗ trợ:
 * - Màu sắc, icon, font đẹp
 * - Tự động ẩn sau khoảng thời gian
 * - Có thể hiển thị trên component cha hoặc góc màn hình
 */
public class NotificationUtil {

    public enum Type {
        SUCCESS, WARNING, ERROR, INFO
    }

    // Thời gian hiển thị mặc định (ms)
    private static final int DEFAULT_DURATION = 2500;

    /**
     * Hiển thị thông báo toast
     *
     * @param parentComponent component chứa thông báo, null nếu hiển thị ở màn hình
     * @param message         nội dung
     * @param type            loại thông báo
     */
    public static void showToast(Component parentComponent, String message, Type type) {
        showToast(parentComponent, message, type, DEFAULT_DURATION);
    }

    /**
     * Hiển thị toast với thời gian tùy chỉnh
     */
    public static void showToast(Component parentComponent, String message, Type type, int duration) {
        // Panel thông báo
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setBackground(getBackgroundColor(type));

        JLabel lblIcon = new JLabel(getIcon(type));
        JLabel lblMessage = new JLabel(message);
        lblMessage.setForeground(Color.WHITE);
        lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(lblIcon, BorderLayout.WEST);
        panel.add(lblMessage, BorderLayout.CENTER);

        // Tạo JWindow để hiển thị toast
        JWindow window = new JWindow();
        window.add(panel);
        window.pack();
        window.setAlwaysOnTop(true);

        // Vị trí hiển thị
        Point location;
        if (parentComponent != null) {
            location = parentComponent.getLocationOnScreen();
            location.translate(20, 20); // cách biên một chút
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            location = new Point(screenSize.width - window.getWidth() - 50, 50);
        }
        window.setLocation(location);

        window.setVisible(true);

        // Timer tự ẩn
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(window::dispose);
            }
        }, duration);
    }

    // Lấy màu background theo loại
    private static Color getBackgroundColor(Type type) {
        return switch (type) {
            case SUCCESS -> new Color(46, 204, 113);
            case WARNING -> new Color(241, 196, 15);
            case ERROR -> new Color(231, 76, 60);
            case INFO -> new Color(52, 152, 219);
        };
    }

    // Lấy icon theo loại
    private static Icon getIcon(Type type) {
        return switch (type) {
            case SUCCESS -> UIManager.getIcon("OptionPane.informationIcon");
            case WARNING -> UIManager.getIcon("OptionPane.warningIcon");
            case ERROR -> UIManager.getIcon("OptionPane.errorIcon");
            case INFO -> UIManager.getIcon("OptionPane.informationIcon");
        };
    }
}
