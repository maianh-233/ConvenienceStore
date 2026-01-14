package com.conveniencestore.gui;

import javax.swing.*;

import java.awt.*;
import java.net.URL;

import javax.swing.table.DefaultTableModel;

import com.conveniencestore.DTO.UserResponseDTO;
import com.conveniencestore.gui.employee.EmployeeDialog;
import com.conveniencestore.gui.employee.EmployeeStatPanel;
import com.conveniencestore.gui.utils.ButtonPanelUtil;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.HeaderPanelUtil;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.PaginationUtil;
import com.conveniencestore.gui.utils.SearchPanelUtil;
import com.conveniencestore.gui.utils.TableUtil;

public class PanelEmployee extends JPanel {
        private JFrame parentFrame;
        // ================= HEADER =================
        private String titlePanel = "Quản lý nhân viên";
        private CustomButton btnReload;
        // ================= STAT =================
        private EmployeeStatPanel employeeStatPanel;

        // ================= SEARCH =================
        private JTextField txtSearch;
        private CustomButton btnSearch;

        // ================= BUTTON ACTION =================
        private CustomButton btnView;
        private CustomButton btnAdd;
        private CustomButton btnDelete;
        private CustomButton btnEdit;
        private CustomButton btnExportExcel;
        private CustomButton btnImportExcel;
        private CustomButton btnExportPDF;
        private CustomButton btnRestore;
        private CustomButton btnImportPDF;

        // ================= TABLE =================
        private JTable table;

        // ================= PAGINATION =================
        private CustomButton btnPrev;
        private CustomButton btnNext;

        public PanelEmployee(JFrame parentFrame) {
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
                tableModel.addColumn("Tên nhân viên");
                tableModel.addColumn("Email");
                tableModel.addColumn("Số điện thoại");
                tableModel.addColumn("Chức vụ");
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
                employeeStatPanel = new EmployeeStatPanel();

                // ví dụ test dữ liệu
                employeeStatPanel.setcardActiveEmployee(120);
                employeeStatPanel.setcardUnActiveEmployee(18);
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
                topPanel.add(Box.createVerticalStrut(10));

                /* ================= STAT ================= */
                topPanel.add(employeeStatPanel);
                topPanel.add(Box.createVerticalStrut(15));
                // Search + Filter

                topPanel.add(
                                SearchPanelUtil.createSearchPanel(txtSearch, btnSearch));
                topPanel.add(Box.createVerticalStrut(10));

                // Button action
                topPanel.add(
                                ButtonPanelUtil.createButtonPanel(
                                                btnView,
                                                btnAdd,
                                                btnDelete,
                                                btnEdit,
                                                btnRestore));

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

        private UserResponseDTO mockUserResponseDTO() {
                UserResponseDTO dto = new UserResponseDTO();

                dto.setId(1L);
                dto.setUsername("nv001");
                dto.setFullName("Trần Thị B");
                dto.setPasswordHash("$2a$10$mocked_password_hash");

                dto.setDateOfBirth(java.time.LocalDate.of(1997, 8, 15));
                dto.setEmail("tranthib@gmail.com");
                dto.setPhone("0909123456");
                dto.setAddress("456 Nguyễn Trãi, Quận 5, TP.HCM");

                dto.setImgUrl("/icon/user.png"); // hoặc null để test ảnh mặc định
                dto.setGender(1); // 0 = Nam, 1 = Nữ, 2 = Khác

                dto.setRoleId(2L);

                dto.setActive(1); // 1 = hoạt động, 0 = ngưng

                dto.setCreatedAt(java.time.LocalDateTime.now().minusDays(20));
                dto.setUpdatedAt(java.time.LocalDateTime.now().minusDays(1));

                return dto;
        }

        // ACTION EVENT
        private void initEvent() {
                // TEST ADD
                btnAdd.addActionListener(e -> {
                        new EmployeeDialog(
                                        parentFrame,
                                        EmployeeDialog.MODE_ADD,
                                        null);
                });

                // TEST EDIT
                btnEdit.addActionListener(e -> {
                        new EmployeeDialog(
                                        parentFrame,
                                        EmployeeDialog.MODE_EDIT,
                                        mockUserResponseDTO());
                });

                // TEST VIEW
                btnView.addActionListener(e -> {
                        new EmployeeDialog(
                                        parentFrame,
                                        EmployeeDialog.MODE_VIEW,
                                        mockUserResponseDTO());
                });
        }

}
