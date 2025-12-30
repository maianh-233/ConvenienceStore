package com.conveniencestore.gui.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * TableUtil nâng cao: custom JTable, hover, alternate color, header style
 * @param <T> Entity type
 */
public abstract class TableUtil<T> {
    protected JTable table;
    protected DefaultTableModel model;

    private Color headerBgColor = new Color(63, 81, 181); // Màu header mặc định
    private Color headerFgColor = Color.WHITE;
    private Font headerFont = new Font("Segoe UI", Font.BOLD, 14);

    private Color rowColor1 = Color.WHITE;  // Màu chẵn
    private Color rowColor2 = new Color(240, 240, 240); // Màu lẻ
    private Color hoverColor = new Color(200, 230, 250); // Màu hover

    public TableUtil(JTable table, String[] columnNames) {
        this.table = table;
        this.model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tất cả column read-only
            }
        };
        table.setModel(model);
        initCustomTable();
    }

    /** Init custom style cho table */
    private void initCustomTable() {
        // Header
        JTableHeader header = table.getTableHeader();
        header.setBackground(headerBgColor);
        header.setForeground(headerFgColor);
        header.setFont(headerFont);
        header.setReorderingAllowed(false);

        // Row color & hover
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                // Hover
                if (row == table.getSelectedRow()) {
                    c.setBackground(hoverColor);
                } else {
                    // Alternate row color
                    c.setBackground(row % 2 == 0 ? rowColor1 : rowColor2);
                }

                return c;
            }
        });

        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
    }

    /** Load dữ liệu entity */
    public abstract void loadData(List<T> data);

    /** Xóa tất cả row */
    public void clear() {
        model.setRowCount(0);
    }

    /** Xóa 1 row */
    public void removeRow(int index) {
        if (index >= 0 && index < model.getRowCount()) {
            model.removeRow(index);
        }
    }

    /** Tùy chỉnh màu header */
    public void setHeaderColor(Color bg, Color fg, Font font) {
        this.headerBgColor = bg;
        this.headerFgColor = fg;
        this.headerFont = font;
        initCustomTable();
    }

    /** Tùy chỉnh màu hover */
    public void setHoverColor(Color hover) {
        this.hoverColor = hover;
        initCustomTable();
    }

    /** Tùy chỉnh màu alternate row */
    public void setRowColors(Color color1, Color color2) {
        this.rowColor1 = color1;
        this.rowColor2 = color2;
        initCustomTable();
    }
}
