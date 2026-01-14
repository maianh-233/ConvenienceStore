package com.conveniencestore.gui;

import javax.swing.*;

import java.awt.*;
import java.net.URL;

import javax.swing.table.DefaultTableModel;

import com.conveniencestore.DTO.CustomerResponseDTO;
import com.conveniencestore.constant.CustomerTier;
import com.conveniencestore.gui.customer.CustomerDialog;
import com.conveniencestore.gui.customer.CustomerStatPanel;
import com.conveniencestore.gui.customer.FilterCustomerTierPanel;
import com.conveniencestore.gui.utils.ButtonPanelUtil;
import com.conveniencestore.gui.utils.ComboItem;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.HeaderPanelUtil;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.PaginationUtil;
import com.conveniencestore.gui.utils.SearchPanelUtil;
import com.conveniencestore.gui.utils.TableUtil;

public class PanelCustomer extends JPanel {
        private JFrame parentFrame;
        // ================= HEADER =================
        private String titlePanel = "Quản lý khách hàng";
        private CustomButton btnReload;

        // ================= STAT =================
        private CustomerStatPanel customerStatPanel;

        // ================= SEARCH =================
        private JTextField txtSearch;
        private CustomButton btnSearch;

        // ================= FILTERSTATUS =================
        private JLabel lblStatusCustomerTier;
        private JComboBox cbStatusCustomerTier;

        private CustomButton btnFilterStatus;

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

        public PanelCustomer(JFrame parentFrame) {
                this.parentFrame = parentFrame;
                initComponent();
                initLayout();
                initEvent();
        }

        private URL getIconUrl(String path) {
                return getClass().getResource(path);

        }

        // ================= KHỞI TẠO COMPONENT =================
        private void initComponent() {

                // ===== HEADER =====
                btnReload = new CustomButton(
                                "Reload",
                                ImageUtil.scaleIcon(
                                                new ImageIcon(getIconUrl("/icon/load.png")), 18, 18));

                // ===== SEARCH =====
                txtSearch = new JTextField(20);
                btnSearch = new CustomButton(
                                "Tìm Kiếm",
                                ImageUtil.scaleIcon(
                                                new ImageIcon(getIconUrl("/icon/search.png")), 18, 18));

                // ===== FILTER STATUS =====

                lblStatusCustomerTier = new JLabel("Customer Tier:");
                cbStatusCustomerTier = createStatusCutsomerTierCombo();

                btnFilterStatus = new CustomButton(
                                "Lọc",
                                ImageUtil.scaleIcon(
                                                new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18));

                // ===== BUTTON ACTION =====
                btnView = new CustomButton("Xem", loadIcon("see"));
                btnAdd = new CustomButton("Thêm", loadIcon("plus"));
                btnDelete = new CustomButton("Xóa", loadIcon("delete"));
                btnEdit = new CustomButton("Sửa", loadIcon("edit"));
                btnRestore = new CustomButton("Restore", loadIcon("restore"));

                // ===== TABLE =====
                table = new JTable();
                TableUtil.style(table);

                // Tạo header bảng
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Tên khách hàng");
                tableModel.addColumn("Email");
                tableModel.addColumn("Số điện thoại");
                tableModel.addColumn("Loại khách hàng");
                tableModel.addColumn("Trạng thái");
                table.setModel(tableModel);

                // ===== PAGINATION =====
                btnPrev = new CustomButton(
                                "Trước",
                                ImageUtil.scaleIcon(
                                                new ImageIcon(getIconUrl("/icon/previous.png")), 16, 16));

                btnNext = new CustomButton(
                                "Sau",
                                ImageUtil.scaleIcon(
                                                new ImageIcon(getIconUrl("/icon/next.png")), 16, 16));

                // ===== STAT PANEL =====
                customerStatPanel = new CustomerStatPanel();

                // ví dụ test dữ liệu
                customerStatPanel.setcardActiveCustomer(120);
                customerStatPanel.setcardVIPCustomer(18);
                customerStatPanel.setcardREGULARCustomer(120);
                customerStatPanel.setcardPREMIUMCustomer(18);
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
                                HeaderPanelUtil.createHeaderPanel(titlePanel, btnReload));
                topPanel.add(Box.createVerticalStrut(12));

                /* ================= INVENTORY STAT ================= */
                topPanel.add(customerStatPanel);
                topPanel.add(Box.createVerticalStrut(15));

                /* ================= SEARCH ================= */
                topPanel.add(
                                SearchPanelUtil.createSearchPanel(txtSearch, btnSearch));
                topPanel.add(Box.createVerticalStrut(10));

                /* ================= FILTER STATUS ================= */
                topPanel.add(
                                FilterCustomerTierPanel.create(
                                                lblStatusCustomerTier, cbStatusCustomerTier,
                                                btnFilterStatus));
                topPanel.add(Box.createVerticalStrut(10));

                /* ================= BUTTON ACTION ================= */
                topPanel.add(
                                ButtonPanelUtil.createButtonPanel(
                                                btnView,
                                                btnAdd,
                                                btnDelete,
                                                btnEdit,
                                                btnRestore));

                add(topPanel, BorderLayout.NORTH);

                add(topPanel, BorderLayout.NORTH);

                // ================= CENTER (TABLE) =================
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.getViewport().setBackground(Color.WHITE);
                scrollPane.setBorder(
                                BorderFactory.createLineBorder(new Color(229, 231, 235)));

                add(scrollPane, BorderLayout.CENTER);

                // ================= BOTTOM (PAGINATION) =================
                add(
                                PaginationUtil.createPaginationPanel(btnPrev, btnNext),
                                BorderLayout.SOUTH);
        }

        // ================= HELPER =================

        private Icon loadIcon(String name) {
                return ImageUtil.scaleIcon(
                                new ImageIcon(getIconUrl("/icon/" + name + ".png")),
                                18, 18);
        }

        private JComboBox<ComboItem<CustomerTier>> createStatusCutsomerTierCombo() {
                JComboBox<ComboItem<CustomerTier>> combo = new JComboBox<>();

                combo.addItem(new ComboItem<>(null, "Tất cả"));

                for (CustomerTier status : CustomerTier.values()) {
                        combo.addItem(
                                        new ComboItem<>(status, status.getDisplayName()));
                }

                return combo;
        }

        private CustomerResponseDTO mockCustomerResponse() {
                CustomerResponseDTO dto = new CustomerResponseDTO();

                dto.setId(1L);
                dto.setFullName("Nguyễn Văn A");

                dto.setDateOfBirth(java.time.LocalDate.of(1998, 5, 20));
                dto.setEmail("nguyenvana@gmail.com");
                dto.setPhone("0987654321");
                dto.setAddress("123 Lê Lợi, Quận 1, TP.HCM");

                dto.setGender(0); // 0 = Nam, 1 = Nữ, 2 = Khác

                dto.setTier(CustomerTier.PREMIUM);
                dto.setPoints(1200);

                dto.setIsDeleted(0); // 0 = hoạt động, 1 = đã xóa

                dto.setCreatedAt(java.time.LocalDateTime.now().minusDays(10));
                dto.setUpdatedAt(java.time.LocalDateTime.now());

                return dto;
        }

        // ACTION EVENT
        private void initEvent() {
                // TEST ADD
                btnAdd.addActionListener(e -> {
                        new CustomerDialog(
                                        parentFrame,
                                        CustomerDialog.MODE_ADD,
                                        null);
                });

                // TEST EDIT
                btnEdit.addActionListener(e -> {
                        new CustomerDialog(
                                        parentFrame,
                                        CustomerDialog.MODE_EDIT,
                                        mockCustomerResponse());
                });

                // TEST VIEW
                btnView.addActionListener(e -> {
                        new CustomerDialog(
                                        parentFrame,
                                        CustomerDialog.MODE_VIEW,
                                        mockCustomerResponse());
                });
        }

}
