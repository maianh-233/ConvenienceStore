package com.conveniencestore.gui.stat;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.conveniencestore.gui.stat.mock.InventoryAlertDTO;
import com.conveniencestore.gui.utils.StatTheme;

import java.awt.*;
import java.util.List;
import javax.swing.table.JTableHeader;


public class InventoryAlertPanel extends JPanel {

    private JTable table;

    //
    public InventoryAlertPanel(List<InventoryAlertDTO> items) {

        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Cảnh báo tồn kho");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        table = new JTable();
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(220, 220, 220)); // grid nhạt

        applyQuantityRenderer();

        loadData(items);

        add(title, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(table);
        
        add( scroll, BorderLayout.CENTER);
   

    }

    private void applyHeaderStyle() {

        JTableHeader header = table.getTableHeader();
        header.setBackground(StatTheme.GREEN_PRIMARY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 36));
    }

    private void applyQuantityRenderer() {

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                // Reset màu mặc định
                c.setForeground(Color.BLACK);

                // Cột số lượng = index 2
                if (column == 2) {
                    int qty = Integer.parseInt(value.toString());

                    if (qty == 0) {
                        c.setForeground(Color.RED);
                    } else if (qty <= 5) {
                        c.setForeground(new Color(234, 88, 12)); // cam
                    }
                }

                return c;
            }
        });
    }


    // ================= LOAD DATA =================
    private void loadData(List<InventoryAlertDTO> items) {

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã SP", "Tên sản phẩm", "Số lượng"}, 0) {

            //Không cho sửa
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (InventoryAlertDTO item : items) {
            model.addRow(new Object[]{
                    item.productCode,
                    item.productName,
                    item.quantity
            });
        }

        table.setModel(model);
        applyHeaderStyle();

    }
}
