package com.conveniencestore.gui.employee;

import com.conveniencestore.DTO.UserResponseDTO;
import com.conveniencestore.gui.utils.ComboItem;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeDialog extends JDialog {

    /* ========== MODE ========== */
    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD  = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;
    private final UserResponseDTO dto;

    /* ========== COMPONENT ========== */
    private JLabel lblImg;
    private CustomButton btnChonAnh;

    private JTextField txtId, txtUsername, txtFullName;
    private JTextField txtDob, txtEmail, txtPhone;
    private JTextField txtCreatedAt, txtUpdatedAt;

    private JComboBox<ComboItem<Integer>> cbbGender;
    private JComboBox<ComboItem<Long>> cbbRole;

    private JLabel lblActive;

    private CustomButton btnAdd, btnEdit;

    /* ========== THEME ========== */
    private static final Color PRIMARY      = new Color(22, 163, 74);
    private static final Color PRIMARY_DARK = new Color(21, 128, 61);
    private static final Color BORDER       = new Color(187, 247, 208);
    private static final Color LABEL_COLOR  = new Color(22, 101, 52);

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private static final DateTimeFormatter UI_DATE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /* ========== ROW MAP ========== */
    private final Map<String, JPanel> rows = new LinkedHashMap<>();

    /* ========== CONSTRUCTOR ========== */
    public EmployeeDialog(JFrame parent, int mode, UserResponseDTO dto) {
        super(parent, true);
        this.mode = mode;
        this.dto = dto;

        setTitle(getTitleByMode());
        setSize(650, 720);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createContent(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        if (dto != null) bindDTO(dto);
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

    /* ================= IMAGE PANEL ================= */
    private void setEmployeeImage(String imgPath) {
        ImageIcon icon;

        if (imgPath != null) {
            icon = new ImageIcon(getClass().getResource(imgPath));
        } else {
            icon = new ImageIcon(getClass().getResource("/icon/imguser.jpg"));
        }

        lblImg.setIcon(
            ImageUtil.scaleIcon(icon, 150, 150)
        );
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

        // set ảnh mặc định
        setEmployeeImage(null);

        btnChonAnh = new CustomButton(
                "Chọn ảnh",
                ImageUtil.scaleIcon(
                        new ImageIcon(getClass().getResource("/icon/image.png")),
                        16, 16
                )
        );
        btnChonAnh.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblImg);
        panel.add(Box.createVerticalStrut(10)); // khoảng cách
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
        txtUsername = createTextField();
        txtFullName = createTextField();
        txtDob = createTextField();
        txtEmail = createTextField();
        txtPhone = createTextField();
        txtCreatedAt = createTextField();
        txtUpdatedAt = createTextField();

        cbbGender = createGenderCombo();
        cbbRole = createRoleCombo();

        lblActive = createStatusLabel();

        addRow(form, "ID", txtId);
        addRow(form, "Username", txtUsername);
        addRow(form, "Họ tên", txtFullName);
        addRow(form, "Ngày sinh", txtDob);
        addRow(form, "Email", txtEmail);
        addRow(form, "SĐT", txtPhone);
        addRow(form, "Giới tính", cbbGender);
        addRow(form, "Chức vụ", cbbRole);
        addRow(form, "Trạng thái", lblActive);
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
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        return txt;
    }

    private JLabel createStatusLabel() {
        JLabel lbl = new JLabel("", SwingConstants.CENTER);
        lbl.setFont(FIELD_FONT);
        lbl.setOpaque(true);
        lbl.setBorder(BorderFactory.createLineBorder(BORDER));
        return lbl;
    }

    private JComboBox<ComboItem<Integer>> createGenderCombo() {
        JComboBox<ComboItem<Integer>> combo = new JComboBox<>();
        combo.addItem(new ComboItem<>(0, "Nam"));
        combo.addItem(new ComboItem<>(1, "Nữ"));
        combo.addItem(new ComboItem<>(2, "Khác"));
        return combo;
    }

    private JComboBox<ComboItem<Long>> createRoleCombo() {
        JComboBox<ComboItem<Long>> combo = new JComboBox<>();

        combo.addItem(new ComboItem<>(1L, "Quản lý"));
        combo.addItem(new ComboItem<>(2L, "Nhân viên"));
        combo.addItem(new ComboItem<>(3L, "Thu ngân"));

        return combo;
    }

    /* ================= DTO ================= */
    private void bindDTO(UserResponseDTO dto) {
        txtId.setText(String.valueOf(dto.getId()));
        txtUsername.setText(dto.getUsername());
        txtFullName.setText(dto.getFullName());
        txtDob.setText(dto.getDateOfBirth() == null ? "" : dto.getDateOfBirth().toString());
        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());

        for (int i = 0; i < cbbGender.getItemCount(); i++) {
            if (cbbGender.getItemAt(i).getValue() == dto.getGender()) {
                cbbGender.setSelectedIndex(i);
                break;
            }
        }

        lblActive.setText(dto.getActive() == 1 ? "Hoạt động" : "Ngưng hoạt động");
        lblActive.setBackground(dto.getActive() == 1
                ? new Color(220, 252, 231)
                : new Color(254, 226, 226));

        txtCreatedAt.setText(dto.getCreatedAt() == null ? "" : dto.getCreatedAt().format(UI_DATE));
        txtUpdatedAt.setText(dto.getUpdatedAt() == null ? "" : dto.getUpdatedAt().format(UI_DATE));
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

    private void hideRow(String key) {
        JPanel row = rows.get(key);
        if (row != null) row.setVisible(false);
    }

    private String getTitleByMode() {
        return switch (mode) {
            case MODE_ADD  -> "Thêm nhân viên";
            case MODE_EDIT -> "Sửa nhân viên";
            default        -> "Xem nhân viên";
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
