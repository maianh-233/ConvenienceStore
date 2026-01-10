package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonPanelUtil {

    private static final int BUTTON_HEIGHT = 44;

    private ButtonPanelUtil() {}

    /**
     * @param btnView     nút xem
     * @param btnAdd      nút thêm
     * @param btnDelete   nút xóa
     * @param btnEdit     nút sửa
     * @param btnRestore  nút khôi phục
     * @param extraButtons các nút mở rộng phía sau (0..n)
     */
    public static JPanel createButtonPanel(
            JButton btnView,
            JButton btnAdd,
            JButton btnDelete,
            JButton btnEdit,
            JButton btnRestore,
            JButton... extraButtons
    ) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        panel.setOpaque(false);

        List<JButton> buttons = new ArrayList<>();

        // ====== NÚT CHÍNH ======
        add(panel, buttons, style(btnView,   new Color(75, 85, 99)));     // Xám
        add(panel, buttons, style(btnAdd,    new Color(99, 102, 241)));   // Tím
        add(panel, buttons, style(btnDelete, new Color(220, 38, 38)));    // Đỏ
        add(panel, buttons, style(btnEdit,   new Color(124, 58, 237)));   // Tím đậm
        add(panel, buttons, style(btnRestore,new Color(14, 165, 233)));   // Xanh

        // ====== NÚT MỞ RỘNG (CÓ THỂ 0 NÚT) ======
        if (extraButtons != null) {
            for (JButton btn : extraButtons) {
                if (btn != null) {
                    add(panel, buttons, style(btn, pickColor(btn)));
                }
            }
        }

        return panel;
    }

    // ================= STYLE =================
    private static JButton style(JButton btn, Color bg) {

        btn.setToolTipText(btn.getText());
        btn.setMinimumSize(new Dimension(BUTTON_HEIGHT, BUTTON_HEIGHT));
        btn.setMaximumSize(new Dimension(120, BUTTON_HEIGHT));
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

    // ================= COLOR RULE =================
    private static Color pickColor(JButton btn) {
        String text = btn.getText().toLowerCase();

        if (text.contains("excel")) return new Color(5, 150, 105);
        if (text.contains("pdf"))   return new Color(159, 18, 57);
        if (text.contains("import"))return new Color(4, 120, 87);
        if (text.contains("export"))return new Color(136, 19, 55);

        return new Color(100, 116, 139); // mặc định
    }
}
