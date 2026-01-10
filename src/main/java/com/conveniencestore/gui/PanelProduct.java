package com.conveniencestore.gui;
import javax.swing.*;

import java.awt.*;
import java.net.URL;

import javax.swing.table.DefaultTableModel;

import com.conveniencestore.DTO.ProductResponseDTO;
import com.conveniencestore.gui.product.ProductDialog;
import com.conveniencestore.gui.product.ProductStatPanel;
import com.conveniencestore.gui.utils.ButtonPanelUtil;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.HeaderPanelUtil;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.PaginationUtil;
import com.conveniencestore.gui.utils.SearchPanelUtil;
import com.conveniencestore.gui.utils.TableUtil;
public class PanelProduct extends JPanel{
    private JFrame parentFrame;
    // ================= HEADER =================
    private String titlePanel = "Quản lý sản phẩm";
    private CustomButton btnReload;

    // ================= SEARCH =================
    private JTextField txtSearch;
    private CustomButton btnSearch;

    // ================= STAT =================
    private ProductStatPanel productStatPanel;


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

    public PanelProduct (JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initComponent();
        initLayout();
        initEvent();
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

        // ===== SEARCH =====
        txtSearch = new JTextField(20);
        btnSearch = new CustomButton(
                "Tìm Kiếm",
                ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/search.png")), 18, 18
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

        // Tạo header bảng
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên sản phẩm");
        tableModel.addColumn("Mã SKU");
        tableModel.addColumn("Mã vạch");
        tableModel.addColumn("Trạng thái");
        table.setModel(tableModel);

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

        // ===== STAT PANEL =====
        productStatPanel = new ProductStatPanel();

        // ví dụ test dữ liệu
        productStatPanel.setActiveProduct(120);
        productStatPanel.setInActiveProduct(5);
        productStatPanel.setInStock(98);
        productStatPanel.setOutOfStock(27);
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
        
        /* ================= INVENTORY STAT ================= */
        topPanel.add(productStatPanel);
        topPanel.add(Box.createVerticalStrut(15));
       
        topPanel.add(
                SearchPanelUtil.createSearchPanel(txtSearch, btnSearch)
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

    private Icon loadIcon(String name) {
        return ImageUtil.scaleIcon(
                new ImageIcon(getIconUrl("/icon/" + name + ".png")),
                18, 18
        );
    } 

    private ProductResponseDTO mockProductResponseDTO() {
        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(101L);
        dto.setSku("SP001");
        dto.setBarcode("8938505974192");
        dto.setProductName("Nước ngọt Coca-Cola 330ml");

        dto.setCategoryId(1L);
        dto.setCategoryName("Đồ uống");

        dto.setSupplierId(2L);
        dto.setSupplierName("Coca-Cola Việt Nam");

        dto.setUnitId(1L);
        dto.setUnitName("Lon");

        dto.setPrice(new java.math.BigDecimal("10000"));
        dto.setCost(new java.math.BigDecimal("7500"));

        dto.setDescription("Nước ngọt có gas Coca-Cola lon 330ml, giải khát tức thì.");

        dto.setImageUrl("/icon/product.png"); 
        // set null để test ảnh mặc định

        dto.setIsActive(1); // 1 = đang bán, 0 = ngưng bán

        dto.setCreatedAt(java.time.LocalDateTime.now().minusDays(15));
        dto.setUpdatedAt(java.time.LocalDateTime.now().minusDays(2));

        return dto;
    }

        // ACTION EVENT
    private void initEvent() {
        // TEST ADD
        btnAdd.addActionListener(e -> {
                new ProductDialog(
                        parentFrame,
                        ProductDialog.MODE_ADD,
                        null
                );
        });

        // TEST EDIT
        btnEdit.addActionListener(e -> {
                new ProductDialog(
                        parentFrame,
                        ProductDialog.MODE_EDIT,
                        mockProductResponseDTO()
                );
        });

        // TEST VIEW
        btnView.addActionListener(e -> {
                new ProductDialog(
                        parentFrame,
                        ProductDialog.MODE_VIEW,
                        mockProductResponseDTO()
                );
        });
   }

}
