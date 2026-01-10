package com.conveniencestore.gui.utils;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Utility class dùng để custom JTable:
 * - Header xanh lá, chữ trắng
 * - Hàng chẵn xanh nhạt, hàng lẻ trắng
 * - Cho phép resize cột
 * - Tự động cắt text dài (ellipsis)
 */
public class TableUtil {

    // ===================== MÀU SẮC =====================
    public static final Color HEADER_BG = new Color(22, 163, 74);   // Xanh lá chủ đạo
    public static final Color HEADER_FG = Color.WHITE;

    public static final Color ROW_EVEN = new Color(240, 253, 244);  // Xanh nhạt
    public static final Color ROW_ODD  = Color.WHITE;

    public static final Color GRID_COLOR = new Color(229, 231, 235);

    // ===================== FONT =====================
    public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font BODY_FONT   = new Font("Segoe UI", Font.PLAIN, 13);

   
    private TableUtil() {
        // Không cho tạo instance
    }

    // ===================== HÀM GỌI CHÍNH =====================
    public static void style(JTable table) {

        // ===== AUTO CREATE HEADER IF EMPTY =====
        if (table.getModel() == null
            || table.getColumnCount() == 0) {

            DefaultTableModel emptyModel =
                new DefaultTableModel(
                    new Object[][]{},
                    new String[]{" "} // 1 cột giả để header xuất hiện
                ) {
                    @Override
                    public boolean isCellEditable(int r, int c) {
                        return false;
                    }
                };

            table.setModel(emptyModel);
        }

        // ===== STYLE CŨ =====
        styleHeader(table);
        styleBody(table);
        styleGrid(table);
        styleBehavior(table);
    }


    // ===================== HEADER =====================
    private static void styleHeader(JTable table) {
        JTableHeader header = table.getTableHeader();

        header.setPreferredSize(new Dimension(header.getWidth(), 42));
        header.setReorderingAllowed(false); // Không cho kéo đổi vị trí cột
        header.setResizingAllowed(true);    // CHO resize cột

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);

                label.setOpaque(true);
                label.setBackground(HEADER_BG);
                label.setForeground(HEADER_FG);
                label.setFont(HEADER_FONT);
                label.setHorizontalAlignment(CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                return label;
            }
        });
    }

    // ===================== BODY =====================
    private static void styleBody(JTable table) {
        table.setFont(BODY_FONT);
        table.setRowHeight(38);

        // Cho table co giãn khi resize panel
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        // Renderer cắt text (20 ký tự)
        DefaultTableCellRenderer renderer = new EllipsisCellRenderer(20) {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);

                // Màu xen kẽ chẵn / lẻ
                if (!isSelected) {
                    label.setBackground(row % 2 == 0 ? ROW_EVEN : ROW_ODD);
                }

                label.setFont(BODY_FONT);
                label.setForeground(Color.DARK_GRAY);
                label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

                return label;
            }
        };

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    // ===================== GRID =====================
    private static void styleGrid(JTable table) {
        table.setGridColor(GRID_COLOR);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setIntercellSpacing(new Dimension(0, 1));
    }

    // ===================== HÀNH VI =====================
    private static void styleBehavior(JTable table) {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setFocusable(false);
    }
}
/*
JTable table = new JTable(tableModel);

// Custom style dùng chung
TableUtil.style(table);

JScrollPane scrollPane = new JScrollPane(table);
scrollPane.setBorder(BorderFactory.createEmptyBorder());
scrollPane.getViewport().setBackground(Color.WHITE);

// ScrollBar custom của bạn (nếu có)
scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());

*/