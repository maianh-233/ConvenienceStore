package com.conveniencestore.gui.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renderer dùng để:
 * - Cắt chuỗi quá dài trong JTable
 * - Hiển thị dạng: 20 ký tự đầu + "..."
 * - Không làm thay đổi dữ liệu trong TableModel
 */
public class EllipsisCellRenderer extends DefaultTableCellRenderer {

    private final int maxLength;

    /**
     * @param maxLength số ký tự tối đa được hiển thị
     */
    public EllipsisCellRenderer(int maxLength) {
        this.maxLength = maxLength;
        setVerticalAlignment(CENTER);
    }

    @Override
    protected void setValue(Object value) {
        if (value == null) {
            setText("");
            setToolTipText(null);
            return;
        }

        String text = value.toString();

        // Nếu dài hơn maxLength thì cắt + "..."
        if (text.length() > maxLength) {
            setText(text.substring(0, maxLength) + "...");
        } else {
            setText(text);
        }

        // Tooltip để xem toàn bộ nội dung khi hover
        setToolTipText(text);
    }
}
