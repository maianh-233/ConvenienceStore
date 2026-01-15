package com.conveniencestore.gui.customer;

import com.conveniencestore.DTO.CustomerResponseDTO;
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

public class CustomerDialog extends JDialog {

    /* ========== MODE ========== */
    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;
    private final CustomerResponseDTO dto;

    /* ========== COMPONENT ========== */
    private JTextField txtId, txtFullName, txtDob, txtEmail, txtPhone;
    private JTextField txtAddress, txtPoints;
    private JComboBox<ComboItem<Integer>> cbbGender;
    private JComboBox<ComboItem<CustomerTier>> cbbTier;

    private JLabel lblIsDeleted;
    private JTextField txtCreatedAt, txtUpdatedAt;

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
    public CustomerDialog(JFrame parent, int mode, CustomerResponseDTO dto) {
        super(parent, true);
        this.mode = mode;
        this.dto = dto;

        setTitle(getTitleByMode());
        setSize(600, 650);
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
        txtFullName = createTextField();
        txtDob = createTextField();
        txtEmail = createTextField();
        txtPhone = createTextField();
        txtAddress = createTextField();
        cbbGender = createGenderCombo();
        cbbTier = createCustomerTierCombo();
        txtPoints = createTextField();

        lblIsDeleted = createStatusLabel();

        txtCreatedAt = createTextField();
        txtUpdatedAt = createTextField();

        addRow(form, "ID", txtId);
        addRow(form, "Họ tên", txtFullName);
        addRow(form, "Ngày sinh", txtDob);
        addRow(form, "Email", txtEmail);
        addRow(form, "Số điện thoại", txtPhone);
        addRow(form, "Địa chỉ", txtAddress);
        addRow(form, "Giới tính", cbbGender);
        addRow(form, "Điểm", txtPoints);
        addRow(form, "Hạng", cbbTier);
        addRow(form, "Trạng thái", lblIsDeleted);
        addRow(form, "Ngày tạo", txtCreatedAt);
        addRow(form, "Ngày cập nhật", txtUpdatedAt);

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
        lbl.setPreferredSize(new Dimension(160, 36));

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
        cbbGender.setEnabled(false);
        cbbTier.setEnabled(false);
        rows.values().forEach(r -> r.setEnabled(false));

    }

    private void hideRow(String key) {
        JPanel row = rows.get(key);
        if (row != null)
            row.setVisible(false);
    }

    /* ================= DTO ================= */
    private void bindDTO(CustomerResponseDTO dto) {
        txtId.setText(dto.getId() == null ? "" : dto.getId().toString());
        txtFullName.setText(dto.getFullName());
        txtDob.setText(dto.getDateOfBirth() == null ? "" : dto.getDateOfBirth().toString());
        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());
        txtAddress.setText(dto.getAddress());

        for (int i = 0; i < cbbGender.getItemCount(); i++) {
            if (cbbGender.getItemAt(i).getValue() == dto.getGender()) {
                cbbGender.setSelectedIndex(i);
                break;
            }
        }

        txtPoints.setText(String.valueOf(dto.getPoints()));
        for (int i = 0; i < cbbTier.getItemCount(); i++) {
            ComboItem<CustomerTier> item = cbbTier.getItemAt(i);
            if (item.getValue() == dto.getTier()) {
                cbbTier.setSelectedIndex(i);
                break;
            }
        }

        boolean active = dto.getIsDeleted() == 0;
        lblIsDeleted.setText(active ? "Hoạt động" : "Đã xóa");
        lblIsDeleted.setForeground(active ? new Color(22, 101, 52) : new Color(153, 27, 27));
        lblIsDeleted.setBackground(active ? new Color(220, 252, 231) : new Color(254, 226, 226));

        txtCreatedAt.setText(dto.getCreatedAt() == null ? "" : dto.getCreatedAt().format(UI_DATE));
        txtUpdatedAt.setText(dto.getUpdatedAt() == null ? "" : dto.getUpdatedAt().format(UI_DATE));
    }

    private String getTitleByMode() {
        return switch (mode) {
            case MODE_ADD -> "Thêm khách hàng";
            case MODE_EDIT -> "Sửa khách hàng";
            default -> "Xem khách hàng";
        };
    }

    private JComboBox<ComboItem<Integer>> createGenderCombo() {
        JComboBox<ComboItem<Integer>> combo = new JComboBox<>();

        combo.addItem(new ComboItem<>(0, "Nam"));
        combo.addItem(new ComboItem<>(1, "Nữ"));
        combo.addItem(new ComboItem<>(2, "Khác"));

        combo.setFont(FIELD_FONT);
        combo.setBorder(BorderFactory.createLineBorder(BORDER));

        return combo;
    }

    private JComboBox<ComboItem<CustomerTier>> createCustomerTierCombo() {
        JComboBox<ComboItem<CustomerTier>> combo = new JComboBox<>();

        for (CustomerTier tier : CustomerTier.values()) {
            combo.addItem(
                    new ComboItem<>(tier, tier.getDisplayName()));
        }

        combo.setFont(FIELD_FONT);
        combo.setBorder(BorderFactory.createLineBorder(BORDER));

        return combo;
    }

    /* ================= GETTER BUTTON ================= */
    public CustomButton getBtnAdd() {
        return btnAdd;
    }

    public CustomButton getBtnEdit() {
        return btnEdit;
    }
}
