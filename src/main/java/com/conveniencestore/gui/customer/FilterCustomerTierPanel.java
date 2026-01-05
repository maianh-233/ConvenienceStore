package com.conveniencestore.gui.customer;

import javax.swing.*;

import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class FilterCustomerTierPanel {

    // ===================== CONFIG =====================
    private static final int MIN_WIDTH_SHOW_LABEL = 520;
    private static final int COMPONENT_HEIGHT = 44;

    private static final int COMBO_WIDTH_LARGE = 150;
    private static final int COMBO_WIDTH_SMALL = 110;

    // XÁM MODERN
    private static final Color BUTTON_BG    = new Color(55, 65, 81);
    private static final Color BUTTON_HOVER = new Color(31, 41, 55);

    private FilterCustomerTierPanel() {}

    // ===================== MAIN =====================
    public static JPanel create(
            JLabel lblStatus,
            JComboBox<?> cbStatus,

            JButton btnFilter
    ) {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // ===== STYLE =====
        styleLabel(lblStatus);


        styleCombo(cbStatus, COMBO_WIDTH_LARGE);


        styleButton(btnFilter);

        // ===== LAYOUT =====
        addGroup(panel, lblStatus, cbStatus);
        addGap(panel, 14);

       

        panel.add(btnFilter);

        // ===== RESPONSIVE =====
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                boolean small = panel.getWidth() < MIN_WIDTH_SHOW_LABEL;

                lblStatus.setVisible(!small);
              

                resizeCombo(cbStatus, small);
                

                panel.revalidate();
                panel.repaint();
            }
        });

        return panel;
    }

    // ===================== HELPER =====================
    private static void addGroup(JPanel panel, JLabel label, JComboBox<?> combo) {
        panel.add(label);
        panel.add(Box.createHorizontalStrut(6));
        panel.add(combo);
    }

    private static void addGap(JPanel panel, int width) {
        panel.add(Box.createHorizontalStrut(width));
    }

    private static void resizeCombo(JComboBox<?> combo, boolean small) {
        combo.setPreferredSize(new Dimension(
                small ? COMBO_WIDTH_SMALL : COMBO_WIDTH_LARGE,
                COMPONENT_HEIGHT
        ));
    }

    // ===================== STYLE =====================
    private static void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(75, 85, 99));
    }

   private static void styleCombo(JComboBox<?> combo, int width) {

        combo.setPreferredSize(new Dimension(width, COMPONENT_HEIGHT));
        combo.setMinimumSize(new Dimension(90, COMPONENT_HEIGHT));
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, COMPONENT_HEIGHT));

        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //  QUAN TRỌNG
        combo.setBackground(Color.WHITE);
        combo.setOpaque(true);

        // Renderer để item bên trong cũng trắng
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                JLabel lbl = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                if (isSelected) {
                    lbl.setBackground(new Color(229, 231, 235)); // xám nhạt modern
                } else {
                    lbl.setBackground(Color.WHITE);
                }

                lbl.setForeground(Color.BLACK);
                lbl.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
                return lbl;
            }
        });

        // Viền gọn gàng
        combo.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219)));
    }


    private static void styleButton(JButton btn) {

        Dimension size = new Dimension(120, COMPONENT_HEIGHT);

        btn.setPreferredSize(size);
        btn.setMinimumSize(new Dimension(COMPONENT_HEIGHT, 36));
        btn.setMaximumSize(new Dimension(COMPONENT_HEIGHT, 36));
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
        return new CustomButton("Lọc", icon);
    }
}

