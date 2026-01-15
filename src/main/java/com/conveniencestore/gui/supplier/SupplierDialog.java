package com.conveniencestore.gui.supplier;

import com.conveniencestore.DTO.SupplierResponseDTO;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SupplierDialog extends JDialog {

    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;
    private final SupplierResponseDTO dto;

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextArea txtAddress;
    private JLabel lblStatus;
    private JTextField txtCreatedAt;
    private JTextField txtUpdatedAt;
    private JTextArea txtNote;
    private CustomButton btnAdd;
    private CustomButton btnEdit;

    // ===== GREEN THEME =====
    private static final Color PRIMARY = new Color(22, 163, 74);
    private static final Color PRIMARY_DARK = new Color(21, 128, 61);
    private static final Color BORDER = new Color(187, 247, 208);
    private static final Color LABEL_COLOR = new Color(22, 101, 52);

    private static final DateTimeFormatter UI_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ===== STYLE =====
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private final Map<String, JPanel> rows = new LinkedHashMap<>();

    public SupplierDialog(JFrame parent, int mode, SupplierResponseDTO dto) {
        super(parent, true);
        this.mode = mode;
        this.dto = dto;

        getContentPane().setBackground(Color.WHITE);

        setTitle(getTitleByMode());
        setSize(540, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(12, 12));

        add(createHeader(), BorderLayout.NORTH);
        add(createForm(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        if (dto != null)
            bindDTO(dto);
        applyMode();
        setVisible(true);
    }

    // ================= HEADER =================
    private JPanel createHeader() {
        JLabel title = new JLabel(getTitleByMode());
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY_DARK);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 14));
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);

        panel.add(title);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY));

        return panel;
    }

    // ================= FORM =================
    private JPanel createForm() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        wrapper.setBackground(Color.WHITE);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);

        txtId = createTextField();
        txtName = createTextField();
        txtEmail = createTextField();
        txtPhone = createTextField();
        txtAddress = createTextArea();
        txtNote = createTextArea();
        lblStatus = createStatusLabel();
        txtCreatedAt = createTextField();
        txtUpdatedAt = createTextField();

        addRow(form, "ID", txtId);
        addRow(form, "Tên nhà cung cấp", txtName);
        addRow(form, "Email", txtEmail);
        addRow(form, "Số điện thoại", txtPhone);
        addRow(form, "Địa chỉ", new JScrollPane(txtAddress));
        addRow(form, "Ghi chú", new JScrollPane(txtNote));
        addRow(form, "Trạng thái", lblStatus);
        addRow(form, "Ngày tạo", txtCreatedAt);
        addRow(form, "Ngày cập nhật", txtUpdatedAt);

        wrapper.add(form, BorderLayout.NORTH);
        return wrapper;
    }

    private void addRow(JPanel parent, String label, Component field) {
        JPanel row = new JPanel(new BorderLayout(10, 6));
        row.setOpaque(false);

        JLabel lbl = new JLabel(label);
        lbl.setFont(LABEL_FONT);
        lbl.setForeground(LABEL_COLOR);
        lbl.setPreferredSize(new Dimension(140, 36));

        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        parent.add(row);
        parent.add(Box.createVerticalStrut(6));

        rows.put(label, row);
    }

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
        JTextArea area = new JTextArea(3, 20);
        area.setFont(FIELD_FONT);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(new Color(240, 253, 244));
        area.setBorder(BorderFactory.createLineBorder(BORDER));
        return area;
    }

    private JLabel createStatusLabel() {
        JLabel lbl = new JLabel("", SwingConstants.CENTER);
        lbl.setFont(FIELD_FONT);
        lbl.setOpaque(true);
        lbl.setPreferredSize(new Dimension(120, 36));
        lbl.setBorder(BorderFactory.createLineBorder(BORDER));
        return lbl;
    }

    // ================= BUTTONS =================
    private JPanel createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER));
        panel.setBackground(Color.WHITE);

        btnAdd = new CustomButton(
                "Thêm",
                ImageUtil.scaleIcon(new ImageIcon(getClass().getResource("/icon/plus.png")), 18, 18));
        btnEdit = new CustomButton(
                "Sửa",
                ImageUtil.scaleIcon(new ImageIcon(getClass().getResource("/icon/edit.png")), 18, 18));

        btnAdd.setBackgroundColor(PRIMARY);
        btnEdit.setBackgroundColor(PRIMARY_DARK);

        panel.add(btnEdit);
        panel.add(btnAdd);
        return panel;
    }

    // ================= MODE =================
    private void applyMode() {
        switch (mode) {
            case MODE_VIEW -> {
                setViewOnly();
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
            }
            case MODE_ADD -> {
                txtId.setText("");
                txtName.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
                txtNote.setText("");

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
        txtId.setEditable(false);
        txtName.setEditable(false);
        txtEmail.setEditable(false);
        txtPhone.setEditable(false);
        txtAddress.setEditable(false);
        txtCreatedAt.setEditable(false);
        txtUpdatedAt.setEditable(false);

        txtId.setFocusable(false);
        txtName.setFocusable(false);
        txtEmail.setFocusable(false);
        txtPhone.setFocusable(false);
        txtAddress.setFocusable(false);
        txtCreatedAt.setFocusable(false);
        txtUpdatedAt.setFocusable(false);
    }

    private void hideRow(String key) {
        JPanel row = rows.get(key);
        if (row != null)
            row.setVisible(false);
    }

    // ================= BUSINESS =================
    public void setIsDeleted(int value) {
        boolean active = value == 0;

        lblStatus.setText(active ? "Đang hoạt động" : "Ngưng hoạt động");
        lblStatus.setForeground(active ? new Color(22, 101, 52) : new Color(153, 27, 27));
        lblStatus.setBackground(active ? new Color(220, 252, 231) : new Color(254, 226, 226));
    }

    private String getTitleByMode() {
        return switch (mode) {
            case MODE_ADD -> "Thêm nhà cung cấp";
            case MODE_EDIT -> "Sửa nhà cung cấp";
            default -> "Xem nhà cung cấp";
        };
    }

    private void bindDTO(SupplierResponseDTO dto) {
        txtId.setText(dto.getId() == null ? "" : dto.getId().toString());
        txtName.setText(dto.getName());
        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());
        txtAddress.setText(dto.getAddress());
        txtNote.setText(dto.getNote());
        txtCreatedAt.setText(dto.getCreatedAt() == null ? "" : dto.getCreatedAt().format(UI_DATE));
        txtUpdatedAt.setText(dto.getUpdatedAt() == null ? "" : dto.getUpdatedAt().format(UI_DATE));
        setIsDeleted(dto.getIsDeleted());
    }
}
