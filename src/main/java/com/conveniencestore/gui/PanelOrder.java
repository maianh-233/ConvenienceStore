package com.conveniencestore.gui;


import javax.swing.*;

import java.awt.*;
import java.net.URL;

import com.conveniencestore.constant.OrderStatus;
import com.conveniencestore.constant.PaymentMethod;
import com.conveniencestore.constant.PaymentStatus;
import com.conveniencestore.gui.mainlayout.SidebarButton;

import com.conveniencestore.gui.order.PanelOrderFilterStatus;
import com.conveniencestore.gui.utils.ButtonPanelUtil;
import com.conveniencestore.gui.utils.ComboItem;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.FilterDateUtil;
import com.conveniencestore.gui.utils.HeaderPanelUtil;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.PaginationUtil;
import com.conveniencestore.gui.utils.SearchPanelUtil;
import com.conveniencestore.gui.utils.TableUtil;

/**
 * PanelOrder
 *
 * Panel quản lý đơn hàng
 * - Tự khởi tạo toàn bộ component
 * - Sử dụng các Util:
 *   HeaderPanelUtil
 *   SearchPanelUtil
 *   FilterDateUtil
 *   ButtonPanelUtil
 *   TableUtil
 *   PaginationUtil
 */
public class PanelOrder extends JPanel {

    // ================= HEADER =================
    private String titlePanel = "Quản lý đơn hàng";
    private CustomButton btnReload;

    // ================= FILTERSTATUS =================
    private JLabel lblStatus;
    private JLabel lblPaymentStatus;
    private JLabel lblPaymentMethod;
    private JComboBox cbStatus;
    private JComboBox cbPaymentStatus;
    private JComboBox cbPaymentMethod;

    private CustomButton btnFilterStatus;


    // ================= SEARCH =================
    private JTextField txtSearch;
    private CustomButton btnSearch;

    // ================= FILTER DATE =================
    private JLabel lblFrom;
    private JLabel lblTo;
    private JSpinner spFrom;
    private JSpinner spTo;
    private CustomButton btnFilter;

    // ================= BUTTON ACTION =================
    private CustomButton btnView;
    private CustomButton btnAdd;
    private CustomButton btnDelete;
    private CustomButton btnEdit;
    private CustomButton btnRestore;
  

    // ================= TABLE =================
    private JTable table;

    // ================= PAGINATION =================
    private CustomButton btnPrev;
    private CustomButton btnNext;

    public PanelOrder() {
        initComponent();
        initLayout();
    }
    private URL getIconUrl(String path){
         return getClass().getResource(path);
       
    }

    // ================= KHỞI TẠO COMPONENT =================
    private void initComponent() {

        // ===== HEADER =====
        btnReload = new CustomButton(
                "Reload",
                ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/load.png")), 18, 18
                )
        );

        // ===== FILTER STATUS =====
       
        lblStatus = new JLabel("Order Status:");
        cbStatus = createOrderStatusCombo();

        lblPaymentStatus = new JLabel("Payment Status");
        cbPaymentStatus = createPaymentStatusCombo();

        lblPaymentMethod = new JLabel("Payment Method:");
        cbPaymentMethod = createPaymentMethodCombo();


        btnFilterStatus = new CustomButton(
                "Lọc",
                ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                )
        );

       



        // ===== SEARCH =====
        txtSearch = new JTextField(20);
        btnSearch = new CustomButton(
                "Tìm Kiếm",
                ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/search.png")), 18, 18
                )
        );

        // ===== FILTER DATE =====
        lblFrom = new JLabel("Từ");
        lblTo   = new JLabel("Đến");

        spFrom = createDateSpinner();
        spTo   = createDateSpinner();

        btnFilter = new CustomButton(
                "Lọc",
                ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                )
        );

        // ===== BUTTON ACTION =====
        btnView   = new CustomButton("Xem",   loadIcon("see"));
        btnAdd    = new CustomButton("Thêm",  loadIcon("plus"));
        btnDelete = new CustomButton("Xóa",   loadIcon("delete"));
        btnEdit   = new CustomButton("Sửa",   loadIcon("edit"));
        btnRestore   = new CustomButton("Restore",   loadIcon("restore"));

       

        // ===== TABLE =====
        table = new JTable();
        TableUtil.style(table);

        // ===== PAGINATION =====
        btnPrev = new CustomButton(
                "Trước",
                ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/previous.png")), 16, 16
                )
        );

        btnNext = new CustomButton(
                "Sau",
                ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/next.png")), 16, 16
                )
        );
    }

    // ================= LAYOUT =================
    private void initLayout() {

        setLayout(new BorderLayout(10, 10));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ================= TOP =================
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        // Header
        topPanel.add(
                HeaderPanelUtil.createHeaderPanel(titlePanel, btnReload)
        );
        topPanel.add(Box.createVerticalStrut(10));

        

        // Search + Filter
        topPanel.add(
                PanelOrderFilterStatus.create(
                lblStatus, cbStatus,
                lblPaymentStatus, cbPaymentStatus,
                lblPaymentMethod, cbPaymentMethod,
                btnFilterStatus
                )

        );
        topPanel.add(Box.createVerticalStrut(10));

        topPanel.add(
                SearchPanelUtil.createSearchPanel(txtSearch, btnSearch)
        );
        topPanel.add(Box.createVerticalStrut(10));

        // ===== FILTER DATE =====
        topPanel.add(
                FilterDateUtil.createFilterDatePanel(
                        lblFrom, spFrom,
                        lblTo, spTo,
                        btnFilter
                )
        );
        topPanel.add(Box.createVerticalStrut(10));


        // Button action
        topPanel.add(
                ButtonPanelUtil.createButtonPanel(
                        btnView,
                        btnAdd,
                        btnDelete,
                        btnEdit,
                        btnRestore
                )
        );

        add(topPanel, BorderLayout.NORTH);

        // ================= CENTER (TABLE) =================
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235))
        );

        add(scrollPane, BorderLayout.CENTER);

        // ================= BOTTOM (PAGINATION) =================
        add(
                PaginationUtil.createPaginationPanel(btnPrev, btnNext),
                BorderLayout.SOUTH
        );
    }

    // ================= HELPER =================
        private JSpinner createDateSpinner() {
                JSpinner spinner = new JSpinner(new SpinnerDateModel());
                spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
                spinner.setPreferredSize(new Dimension(120, 30));
                return spinner;
        }

        private Icon loadIcon(String name) {
                return ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/" + name + ".png")),
                        18, 18
                );
        }

        private JComboBox<ComboItem<OrderStatus>> createOrderStatusCombo() {
                JComboBox<ComboItem<OrderStatus>> combo = new JComboBox<>();

                combo.addItem(new ComboItem<>(null, "Tất cả"));

                for (OrderStatus status : OrderStatus.values()) {
                        combo.addItem(
                                new ComboItem<>(status, status.getDisplayName())
                        );
                }

                return combo;
        }

        private JComboBox<ComboItem<PaymentStatus>> createPaymentStatusCombo() {
                JComboBox<ComboItem<PaymentStatus>> combo = new JComboBox<>();

                combo.addItem(new ComboItem<>(null, "Tất cả"));

                for (PaymentStatus status : PaymentStatus.values()) {
                        combo.addItem(
                                new ComboItem<>(status, status.getDisplayName())
                        );
                }

                return combo;
        }

        private JComboBox<ComboItem<PaymentMethod>> createPaymentMethodCombo() {
                JComboBox<ComboItem<PaymentMethod>> combo = new JComboBox<>();

             
                combo.addItem(new ComboItem<>(null, "Tất cả"));

                for (PaymentMethod method : PaymentMethod.values()) {
                        combo.addItem(
                                new ComboItem<>(method, method.getDisplayName())
                        );
                }

                return combo;
        }


}
