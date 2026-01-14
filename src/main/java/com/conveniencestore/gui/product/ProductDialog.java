package com.conveniencestore.gui.product;

import com.conveniencestore.DTO.ProductResponseDTO;
import com.conveniencestore.gui.utils.ComboItem;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductDialog extends JDialog {

    /* ========== MODE ========== */
    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;
    private final ProductResponseDTO dto;

    /* ========== COMPONENT ========== */
    private JLabel lblImg;
    private CustomButton btnChonAnh;

    private JTextField txtId, txtSku, txtBarcode, txtProductName;
    private JTextField txtPrice, txtCost;
    private JTextArea txtDescription;
    private JTextField txtCreatedAt, txtUpdatedAt;

    private JComboBox<ComboItem<Long>> cbbCategory;
    private JComboBox<ComboItem<Long>> cbbSupplier;
    private JComboBox<ComboItem<Long>> cbbUnit;

    private CustomButton btnAdd, btnEdit;

    /* ========== THEME ========== */
    private static final Color PRIMARY = new Color(22, 163, 74);
    private static final Color PRIMARY_DARK = new Color(21, 128, 61);
    private static final Color BORDER = new Color(187, 247, 208);
    private static final Color LABEL_COLOR = new Color(22, 101, 52);

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private static final DateTimeFormatter UI_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /* ========== ROW MAP ========== */
    private final Map<String, JPanel> rows = new LinkedHashMap<>();

    /* ========== CONSTRUCTOR ========== */
    public ProductDialog(JFrame parent, int mode, ProductResponseDTO dto) {
        super(parent, true);
        this.mode = mode;
        this.dto = dto;

        setTitle(getTitleByMode());
        setSize(720, 760);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        if (dto != null)
            bindDTO(dto);
        applyMode();

        setVisible(true);
    }

    /* ================= HEADER ================= */
    private JPanel createHeader() {
        JLabel title = new JLabel(getTitleByMode());
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY_DARK);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 14));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY));
        panel.add(title);
        return panel;
    }

    /* ================= CONTENT ================= */
    private JScrollPane createContent() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(Color.WHITE);

        root.add(createImagePanel());
        root.add(Box.createVerticalStrut(10));
        root.add(createFormPanel());

        JScrollPane scroll = new JScrollPane(root);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        return scroll;
    }

    /* ================= IMAGE ================= */
    private void setProductImage(String imgPath) {
        ImageIcon icon;
        if (imgPath != null) {
            icon = new ImageIcon(imgPath);
        } else {
            icon = new ImageIcon(getClass().getResource("/icon/imgproduct.jpg"));
        }
        lblImg.setIcon(ImageUtil.scaleIcon(icon, 150, 150));
    }

    private JPanel createImagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        lblImg = new JLabel();
        lblImg.setPreferredSize(new Dimension(170, 170));
        lblImg.setMaximumSize(new Dimension(170, 170));
        lblImg.setBorder(BorderFactory.createLineBorder(BORDER));
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);

        setProductImage(null);

        btnChonAnh = new CustomButton(
                "Chọn ảnh",
                ImageUtil.scaleIcon(
                        new ImageIcon(getClass().getResource("/icon/image.png")),
                        16, 16));
        btnChonAnh.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblImg);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnChonAnh);
        return panel;
    }

    /* ================= FORM ================= */
    private JPanel createFormPanel() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(0, 16, 10, 16));

        txtId = createTextField();
        txtSku = createTextField();
        txtBarcode = createTextField();
        txtProductName = createTextField();
        txtPrice = createTextField();
        txtCost = createTextField();
        txtCreatedAt = createTextField();
        txtUpdatedAt = createTextField();

        txtDescription = new JTextArea(4, 20);
        txtDescription.setFont(FIELD_FONT);
        txtDescription.setLineWrap(true);
        txtDescription.setBorder(BorderFactory.createLineBorder(BORDER));

        cbbCategory = createCategoryCombo();
        cbbSupplier = createSupplierCombo();
        cbbUnit = createUnitCombo();

        addRow(form, "ID", txtId);
        addRow(form, "SKU", txtSku);
        addRow(form, "Barcode", txtBarcode);
        addRow(form, "Tên sản phẩm", txtProductName);
        addRow(form, "Danh mục", cbbCategory);
        addRow(form, "Nhà cung cấp", cbbSupplier);
        addRow(form, "Đơn vị", cbbUnit);
        addRow(form, "Giá bán", txtPrice);
        addRow(form, "Giá nhập", txtCost);
        addRow(form, "Mô tả", new JScrollPane(txtDescription));
        addRow(form, "Ngày tạo", txtCreatedAt);
        addRow(form, "Ngày cập nhật", txtUpdatedAt);

        return form;
    }

    private void addRow(JPanel parent, String label, Component field) {
        JPanel row = new JPanel(new BorderLayout(10, 6));
        row.setOpaque(false);

        JLabel lbl = new JLabel(label);
        lbl.setFont(LABEL_FONT);
        lbl.setForeground(LABEL_COLOR);
        lbl.setPreferredSize(new Dimension(150, 36));

        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        parent.add(row);
        parent.add(Box.createVerticalStrut(6));
        rows.put(label, row);
    }

    /* ================= FIELD FACTORY ================= */
    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setFont(FIELD_FONT);
        txt.setPreferredSize(new Dimension(200, 36));
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        return txt;
    }

    private JComboBox<ComboItem<Long>> createCategoryCombo() {
        JComboBox<ComboItem<Long>> cb = new JComboBox<>();
        cb.addItem(new ComboItem<>(1L, "Đồ uống"));
        cb.addItem(new ComboItem<>(2L, "Bánh kẹo"));
        return cb;
    }

    private JComboBox<ComboItem<Long>> createSupplierCombo() {
        JComboBox<ComboItem<Long>> cb = new JComboBox<>();
        cb.addItem(new ComboItem<>(1L, "Coca-Cola"));
        cb.addItem(new ComboItem<>(2L, "Pepsi"));
        return cb;
    }

    private JComboBox<ComboItem<Long>> createUnitCombo() {
        JComboBox<ComboItem<Long>> cb = new JComboBox<>();
        cb.addItem(new ComboItem<>(1L, "Chai"));
        cb.addItem(new ComboItem<>(2L, "Lon"));
        return cb;
    }

    /* ================= DTO ================= */
    private void bindDTO(ProductResponseDTO p) {
        txtId.setText(String.valueOf(p.getId()));
        txtSku.setText(p.getSku());
        txtBarcode.setText(p.getBarcode());
        txtProductName.setText(p.getProductName());
        txtPrice.setText(p.getPrice() == null ? "" : p.getPrice().toString());
        txtCost.setText(p.getCost() == null ? "" : p.getCost().toString());
        txtDescription.setText(p.getDescription());

        txtCreatedAt.setText(p.getCreatedAt() == null ? "" : p.getCreatedAt().format(UI_DATE));
        txtUpdatedAt.setText(p.getUpdatedAt() == null ? "" : p.getUpdatedAt().format(UI_DATE));

        setProductImage(p.getImageUrl());
    }

    /* ================= MODE ================= */
    private void applyMode() {
        switch (mode) {
            case MODE_VIEW -> {
                rows.values().forEach(r -> r.setEnabled(false));
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnChonAnh.setVisible(false);
            }
            case MODE_ADD -> {
                hideRow("Ngày tạo");
                hideRow("Ngày cập nhật");
                txtId.setEnabled(false);
                btnEdit.setVisible(false);
            }
            case MODE_EDIT -> {
                hideRow("Ngày tạo");
                hideRow("Ngày cập nhật");
                txtId.setEnabled(false);
                btnAdd.setVisible(false);
            }
        }
    }

    private void hideRow(String key) {
        JPanel row = rows.get(key);
        if (row != null)
            row.setVisible(false);
    }

    private String getTitleByMode() {
        return switch (mode) {
            case MODE_ADD -> "Thêm sản phẩm";
            case MODE_EDIT -> "Sửa sản phẩm";
            default -> "Xem sản phẩm";
        };
    }

    /* ================= BUTTON ================= */
    private JPanel createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(Color.WHITE);

        btnAdd = new CustomButton("Thêm",
                ImageUtil.scaleIcon(new ImageIcon(getClass().getResource("/icon/plus.png")), 18, 18));

        btnEdit = new CustomButton("Sửa",
                ImageUtil.scaleIcon(new ImageIcon(getClass().getResource("/icon/edit.png")), 18, 18));

        panel.add(btnEdit);
        panel.add(btnAdd);
        return panel;
    }
}
