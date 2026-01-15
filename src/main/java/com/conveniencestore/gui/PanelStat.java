package com.conveniencestore.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.conveniencestore.gui.stat.*;
import com.conveniencestore.gui.stat.mock.StatDTO;
import com.conveniencestore.gui.stat.mock.StatMockDAO;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;
import com.conveniencestore.gui.utils.StatCard;
import com.conveniencestore.gui.utils.StatTheme;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PanelStat extends JPanel {

    private StatDTO data;

    private JPanel statCardsPanel;
    private JPanel lineChartPanel;
    private JPanel barChartPanel;
    private JPanel bottomPanel;

    private JScrollPane mainScroll;

    public PanelStat() {
        data = StatMockDAO.getDashboardData();

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(createHeader(), BorderLayout.NORTH);
        add(createScrollableMain(), BorderLayout.CENTER);

        // Responsive khi resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                handleResponsiveLayout();
            }
        });
    }

    // ==================== HANDLE RESPONSIVE ====================
    private void handleResponsiveLayout() {
        int width = getWidth();
        boolean compact = width < 1100;

        // --- Stat Cards ---
        if (compact) {
            statCardsPanel.setLayout(new BoxLayout(statCardsPanel, BoxLayout.Y_AXIS));
            for (Component c : statCardsPanel.getComponents()) {
                if (c instanceof StatCard card) {
                    card.setAlignmentX(Component.CENTER_ALIGNMENT); // căn giữa
                    card.setMaximumSize(new Dimension(400, 120));
                }
            }
        } else { // màn hình rộng, ngang
            statCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
            for (Component c : statCardsPanel.getComponents()) {
                if (c instanceof StatCard card) {
                    card.setMaximumSize(new Dimension(240, 120));
                }
            }
        }

        // --- Bottom Panel ---
        if (compact) {
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
            for (Component c : bottomPanel.getComponents()) {
                ((JComponent) c).setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
                ((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        } else {
            bottomPanel.setLayout(new GridLayout(1, 2, 15, 15));
            for (Component c : bottomPanel.getComponents()) {
                ((JComponent) c).setMaximumSize(null); 
            }
        }

        // --- Charts ---
        int chartHeight = compact ? 300 : 420;
        lineChartPanel.setPreferredSize(new Dimension(mainScroll.getWidth(), chartHeight));
        barChartPanel.setPreferredSize(new Dimension(mainScroll.getWidth(), chartHeight));

        // Revalidate & repaint
        statCardsPanel.revalidate();
        bottomPanel.revalidate();
        lineChartPanel.revalidate();
        barChartPanel.revalidate();
        repaint();
    }

    // ==================== SCROLLABLE MAIN ====================
    private JScrollPane createScrollableMain() {
        JPanel main = createMain();

        mainScroll = new JScrollPane(main);
        mainScroll.setBorder(BorderFactory.createEmptyBorder());
        mainScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScroll.getVerticalScrollBar().setUnitIncrement(16);
        mainScroll.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        mainScroll.setBackground(Color.WHITE); // hoặc StatTheme.BG nếu muốn custom

        mainScroll.getViewport().setBackground(Color.WHITE);

        mainScroll.getVerticalScrollBar().setUI(new ModernScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(0, 200, 100); // màu thumb (xanh lá trong ví dụ)
                trackColor = Color.WHITE; // track màu trắng
            }

            // Nếu track vẫn không trắng, override paintTrack
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(Color.WHITE); // set màu trắng
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }
        });

        return mainScroll;
    }

    // ==================== HEADER ====================
    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel title = new JLabel("Thống kê cửa hàng");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(StatTheme.TEXT_TITLE);

        panel.add(title, BorderLayout.WEST);
        return panel;
    }

    // ==================== MAIN PANEL ====================
    private JPanel createMain() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);
        main.setBackground(Color.WHITE);

        main.add(createStatCards());
        main.add(Box.createVerticalStrut(15));

        main.add(createLineChartSection());
        main.add(Box.createVerticalStrut(15));

        main.add(createBarChartSection());
        main.add(Box.createVerticalStrut(15));

        main.add(createBottom());
        return main;
    }

    // ==================== STAT CARDS ====================
    private JPanel createStatCards() {
        statCardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));

        // Set màu nền trắng và bật opaque
        statCardsPanel.setOpaque(true);
        statCardsPanel.setBackground(Color.WHITE);

        statCardsPanel.add(new StatCard("Doanh thu hôm nay", "0",
                ImageUtil.scaleIcon(new ImageIcon(StatCard.class.getResource("/icon/revenue.png")), 28, 28)));
        statCardsPanel.add(new StatCard("Số hóa đơn", "0",
                ImageUtil.scaleIcon(new ImageIcon(StatCard.class.getResource("/icon/orders.png")), 28, 28)));
        statCardsPanel.add(new StatCard("Khách hàng", "0",
                ImageUtil.scaleIcon(new ImageIcon(StatCard.class.getResource("/icon/customer.png")), 28, 28)));
        statCardsPanel.add(new StatCard("NV đang hoạt động", "0",
                ImageUtil.scaleIcon(new ImageIcon(StatCard.class.getResource("/icon/employee.png")), 28, 28)));

        // Set border nếu muốn có padding
        statCardsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        return statCardsPanel;
    }

    // ==================== BOTTOM PANEL ====================
    private JPanel createBottom() {
        bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new GridLayout(1, 2, 15, 15));

        bottomPanel.add(new InventoryAlertPanel(data.lowInventory));
        bottomPanel.add(createAuditPreview());

        return bottomPanel;
    }

    // ==================== AUDIT PREVIEW ====================

    private JPanel createAuditPreview() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Tiêu đề
        JLabel title = new JLabel("Hoạt động nhân viên");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(StatTheme.TEXT_TITLE);

        // Table
        JTable table = new JTable();
        table.setRowHeight(30); // giống Inventory
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220)); // grid nhạt
        table.setSelectionBackground(new Color(220, 235, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setOpaque(true);

        // Model
        DefaultTableModel model = new DefaultTableModel(
                new String[] { "Mã NV", "Payload", "Entity", "Action" }, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        for (String log : data.auditLogs) {
            model.addRow(new Object[] { "NV01", log, "Order", "CREATE" });
        }
        table.setModel(model);

        // Header giống InventoryAlertPanel
        JTableHeader header = table.getTableHeader();
        header.setBackground(StatTheme.GREEN_PRIMARY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 36));

        // Renderer giống InventoryAlertPanel (reset màu toàn bộ)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                c.setForeground(Color.BLACK); // reset màu
                return c;
            }
        });

        // ScrollPane giống Inventory (có margin, không empty border)
        JScrollPane scroll = new JScrollPane(table);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void applyAuditColumnRenderer(JTable table) {
        table.getColumnModel().getColumn(3)
                .setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {
                        JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                                table, value, isSelected, hasFocus, row, column);
                        lbl.setHorizontalAlignment(JLabel.CENTER);
                        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
                        String action = value.toString();
                        switch (action) {
                            case "CREATE" -> lbl.setForeground(new Color(22, 163, 74));
                            case "UPDATE" -> lbl.setForeground(new Color(234, 179, 8));
                            case "DELETE" -> lbl.setForeground(Color.RED);
                            default -> lbl.setForeground(Color.GRAY);
                        }
                        return lbl;
                    }
                });
    }

    // ==================== LINE CHART ====================
    private JPanel createLineChartSection() {
        lineChartPanel = new JPanel(new BorderLayout(10, 10));
        lineChartPanel.setBackground(Color.WHITE);
        lineChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        lineChartPanel.setPreferredSize(new Dimension(0, 600));

        JLabel title = new JLabel("Doanh thu theo ngày");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        lineChartPanel.add(title, BorderLayout.NORTH);
        lineChartPanel.add(new RevenueLineChartPanel(data.revenueByDay), BorderLayout.CENTER);

        return lineChartPanel;
    }

    // ==================== BAR CHART ====================
    private JPanel createBarChartSection() {
        barChartPanel = new JPanel(new BorderLayout(10, 10));
        barChartPanel.setBackground(Color.WHITE);
        barChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        barChartPanel.setPreferredSize(new Dimension(0, 600));

        JLabel title = new JLabel("Top sản phẩm bán chạy");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        barChartPanel.add(title, BorderLayout.NORTH);
        barChartPanel.add(new ProductBarChartPanel(data.topProducts), BorderLayout.CENTER);

        return barChartPanel;
    }

}
