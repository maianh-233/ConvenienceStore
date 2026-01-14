package com.conveniencestore.gui.promotion;

import com.conveniencestore.DTO.PromotionResponseDTO;
import com.conveniencestore.constant.CustomerTier;
import com.conveniencestore.gui.utils.ComboItem;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class PromotionDialog extends JDialog {

    /* ========== MODE ========== */
    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;
    private final PromotionResponseDTO dto;

    /* ========== COMPONENT ========== */
    private JTextField txtId, txtName, txtValue, txtCode;
    private JTextField txtStartAt, txtEndAt;
    private JTextField txtMaxUses, txtMinOrderAmount;
    private JTextField txtCreatedAt, txtUpdatedAt;

    private JComboBox<String> cbbType;
    private JComboBox<ComboItem<CustomerTier>> cbbCustomerTier;

    private JTextArea txtNote;
    private JLabel lblStatus;

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
    public PromotionDialog(JFrame parent, int mode, PromotionResponseDTO dto) {
        super(parent, true);
        this.mode = mode;
        this.dto = dto;

        setTitle(getTitleByMode());
        setSize(620, 680);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createFormScroll(), BorderLayout.CENTER);
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

    /* ================= FORM + SCROLL ================= */
    private JScrollPane createFormScroll() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        txtId = createTextField();
        txtCode = createTextField();
        txtName = createTextField();
        txtValue = createTextField();

        cbbType = createCombo(new String[] { "PERCENT", "FIXED", "FREESHIP" });
        txtStartAt = createTextField();
        txtEndAt = createTextField();

        lblStatus = createStatusLabel();

        cbbCustomerTier = createCustomerTierCombo();
        txtMaxUses = createTextField();
        txtMinOrderAmount = createTextField();

        txtNote = createTextArea();

        txtCreatedAt = createTextField();
        txtUpdatedAt = createTextField();

        addRow(form, "ID", txtId);
        addRow(form, "Code", txtCode);
        addRow(form, "Tên khuyến mãi", txtName);
        addRow(form, "Loại", cbbType);
        addRow(form, "Giá trị", txtValue);
        addRow(form, "Bắt đầu", txtStartAt);
        addRow(form, "Kết thúc", txtEndAt);
        addRow(form, "Trạng thái", lblStatus);
        addRow(form, "Hạng KH áp dụng", cbbCustomerTier);
        addRow(form, "Số lượt tối đa", txtMaxUses);
        addRow(form, "Đơn tối thiểu", txtMinOrderAmount);
        addRow(form, "Ghi chú", new JScrollPane(txtNote));
        addRow(form, "Ngày tạo", txtCreatedAt);
        addRow(form, "Ngày cập nhật", txtUpdatedAt);

        form.add(Box.createVerticalStrut(10));

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        return scroll;
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

    private JTextArea createTextArea() {
        JTextArea area = new JTextArea(4, 20);
        area.setFont(FIELD_FONT);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(new Color(240, 253, 244));
        area.setBorder(BorderFactory.createLineBorder(BORDER));
        return area;
    }

    private JComboBox<String> createCombo(String[] items) {
        JComboBox<String> cbb = new JComboBox<>(items);
        cbb.setFont(FIELD_FONT);
        cbb.setBorder(BorderFactory.createLineBorder(BORDER));
        return cbb;
    }

    private JLabel createStatusLabel() {
        JLabel lbl = new JLabel("", SwingConstants.CENTER);
        lbl.setFont(FIELD_FONT);
        lbl.setOpaque(true);
        lbl.setPreferredSize(new Dimension(120, 36));
        lbl.setBorder(BorderFactory.createLineBorder(BORDER));
        return lbl;
    }

    /* ================= BUTTONS ================= */
    private JPanel createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER));

        btnAdd = new CustomButton("Thêm",
                ImageUtil.scaleIcon(
                        new ImageIcon(getClass().getResource("/icon/plus.png")), 18, 18));

        btnEdit = new CustomButton("Sửa",
                ImageUtil.scaleIcon(
                        new ImageIcon(getClass().getResource("/icon/edit.png")), 18, 18));

        btnAdd.setBackgroundColor(PRIMARY);
        btnEdit.setBackgroundColor(PRIMARY_DARK);

        panel.add(btnEdit);
        panel.add(btnAdd);
        return panel;
    }

    /* ================= MODE ================= */
    private void applyMode() {
        switch (mode) {
            case MODE_VIEW -> {
                setViewOnly();
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
            }
            case MODE_ADD -> {
                hideRow("Trạng thái");
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

    private void setViewOnly() {
        for (Component c : rows.values()) {
            c.setEnabled(false);
        }
        txtNote.setEditable(false);
    }

    private void hideRow(String key) {
        JPanel row = rows.get(key);
        if (row != null)
            row.setVisible(false);
    }

    /* ================= DTO ================= */
    private void bindDTO(PromotionResponseDTO dto) {
        txtId.setText(dto.getId() == null ? "" : dto.getId().toString());
        txtCode.setText(dto.getCode());
        txtName.setText(dto.getName());
        cbbType.setSelectedItem(dto.getType());
        txtValue.setText(dto.getValue() == null ? "" : dto.getValue().toString());
        txtStartAt.setText(dto.getStartAt() == null ? "" : dto.getStartAt().format(UI_DATE));
        txtEndAt.setText(dto.getEndAt() == null ? "" : dto.getEndAt().format(UI_DATE));
        cbbCustomerTier.setSelectedItem(dto.getCustomerTier());
        txtMaxUses.setText(dto.getMaxUses() == null ? "" : dto.getMaxUses().toString());
        txtMinOrderAmount.setText(dto.getMinOrderAmount() == null ? "" : dto.getMinOrderAmount().toString());
        txtNote.setText(dto.getNote());
        txtCreatedAt.setText(dto.getCreatedAt() == null ? "" : dto.getCreatedAt().format(UI_DATE));
        txtUpdatedAt.setText(dto.getUpdatedAt() == null ? "" : dto.getUpdatedAt().format(UI_DATE));
        setStatus(dto);
    }

    private void setStatus(PromotionResponseDTO dto) {
        boolean active = dto.getIsActive() == 1
                && (dto.getEndAt() == null || dto.getEndAt().isAfter(java.time.LocalDateTime.now()));

        lblStatus.setText(active ? "Đang hoạt động" : "Ngưng hoạt động");
        lblStatus.setForeground(active ? new Color(22, 101, 52) : new Color(153, 27, 27));
        lblStatus.setBackground(active ? new Color(220, 252, 231) : new Color(254, 226, 226));
    }

    private String getTitleByMode() {
        return switch (mode) {
            case MODE_ADD -> "Thêm khuyến mãi";
            case MODE_EDIT -> "Sửa khuyến mãi";
            default -> "Xem khuyến mãi";
        };
    }

    private JComboBox<ComboItem<CustomerTier>> createCustomerTierCombo() {
        JComboBox<ComboItem<CustomerTier>> combo = new JComboBox<>();

        combo.addItem(new ComboItem<>(null, "Tất cả"));

        for (CustomerTier status : CustomerTier.values()) {
            combo.addItem(
                    new ComboItem<>(status, status.getDisplayName()));
        }

        return combo;
    }
}
