package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * FilterDateUtil - Panel lọc theo ngày RESPONSIVE
 */
public class FilterDateUtil {

    // ===================== CONFIG =====================
    private static final int MIN_WIDTH_SHOW_LABEL = 380;

    private static final int COMPONENT_HEIGHT = 44;

    private static final int SPINNER_WIDTH_LARGE = 140;
    private static final int SPINNER_WIDTH_SMALL = 90;

    // 🎨 TÍM XÁM MODERN
    private static final Color BUTTON_BG = new Color(99, 102, 241);
    private static final Color BUTTON_HOVER = new Color(79, 70, 229);

    private FilterDateUtil() {}

    // ===================== MAIN =====================
    public static JPanel createFilterDatePanel(
            JLabel lblFrom,
            JSpinner spFrom,
            JLabel lblTo,
            JSpinner spTo,
            JButton btnFilter
    ) {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        String originalText = btnFilter.getText();

        styleLabel(lblFrom);
        styleLabel(lblTo);

        styleSpinner(spFrom, SPINNER_WIDTH_LARGE);
        styleSpinner(spTo, SPINNER_WIDTH_LARGE);

        styleButton(btnFilter);

        panel.add(lblFrom);
        panel.add(Box.createHorizontalStrut(6));
        panel.add(spFrom);

        panel.add(Box.createHorizontalStrut(14));

        panel.add(lblTo);
        panel.add(Box.createHorizontalStrut(6));
        panel.add(spTo);

        panel.add(Box.createHorizontalStrut(16));
        panel.add(btnFilter);

        // ===================== RESPONSIVE =====================
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                boolean small = panel.getWidth() < MIN_WIDTH_SHOW_LABEL;

                lblFrom.setVisible(!small);
                lblTo.setVisible(!small);

                spFrom.setPreferredSize(new Dimension(
                        small ? SPINNER_WIDTH_SMALL : SPINNER_WIDTH_LARGE,
                        COMPONENT_HEIGHT
                ));

                spTo.setPreferredSize(new Dimension(
                        small ? SPINNER_WIDTH_SMALL : SPINNER_WIDTH_LARGE,
                        COMPONENT_HEIGHT
                ));

                

                panel.revalidate();
                panel.repaint();
            }
        });

        return panel;
    }

    // ===================== STYLE =====================
    private static void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(75, 85, 99));
    }

    private static void styleSpinner(JSpinner spinner, int width) {
        spinner.setPreferredSize(new Dimension(width, COMPONENT_HEIGHT));
        spinner.setMinimumSize(new Dimension(80, COMPONENT_HEIGHT));
        spinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, COMPONENT_HEIGHT));

        if (spinner.getEditor() instanceof JSpinner.DefaultEditor editor) {
            editor.getTextField().setFont(
                    new Font("Segoe UI", Font.PLAIN, 13)
            );
            editor.getTextField().setBorder(
                    BorderFactory.createEmptyBorder(0, 8, 0, 8)
            );
        }
    }

    private static void styleButton(JButton btn) {

        Dimension size = new Dimension(120, COMPONENT_HEIGHT);

        btn.setPreferredSize(size);
        btn.setMinimumSize(new Dimension(COMPONENT_HEIGHT, 36));
        btn.setMaximumSize(new Dimension(COMPONENT_HEIGHT, 36)); // 🔥 FIX

        btn.setMargin(new Insets(0, 18, 0, 18));

        if (btn instanceof CustomButton cbtn) {
            cbtn.setBackgroundColor(BUTTON_BG);
            cbtn.setHoverColor(BUTTON_HOVER);
            cbtn.setCornerRadius(12);
            cbtn.setButtonFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    // ===================== FACTORY =====================
    public static CustomButton createFilterButton(String iconPath) {
        ImageIcon icon = ImageUtil.scaleIcon(
                new ImageIcon(iconPath), 18, 18
        );
        CustomButton btn = new CustomButton("Lọc", icon);
        btn.setToolTipText("Lọc theo ngày");
        return btn;
    }
}
