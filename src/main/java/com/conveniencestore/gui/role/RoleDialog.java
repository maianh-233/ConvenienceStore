package com.conveniencestore.gui.role;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.conveniencestore.DTO.RoleResponseDTO;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ModernScrollBarUI;

public class RoleDialog extends JDialog {

    /* ========== MODE ========== */
    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD  = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;

    /* ========== THEME ========== */
    private static final Color PRIMARY       = new Color(22, 163, 74);
    private static final Color PRIMARY_DARK  = new Color(21, 128, 61);
    private static final Color BORDER        = new Color(187, 247, 208);
    private static final Color LABEL_COLOR   = new Color(22, 101, 52);

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    /* ========== COMPONENT ========== */
    private JTextField txtRoleName;
    private JLabel lblStatus;
    private JPanel permissionContainer;

    private CustomButton btnSave;
    private CustomButton btnClose;


    /* ========== CONSTRUCTOR ========== */
    public RoleDialog(Frame owner, int mode, RoleResponseDTO dto) {
        super(owner, true);
        this.mode = mode;


        setTitle(getDialogTitle());
        setSize(620, 680);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createFormScroll(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);
        if ( dto!= null)
            bindDTO(dto);

        applyMode();
        setVisible(true);
    }

    /* ================= HEADER ================= */
    private JPanel createHeader() {
        JLabel title = new JLabel(getDialogTitle());
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

        txtRoleName = createTextField();
        lblStatus = createStatusLabel();

        addRow(form, "Tên chức vụ", txtRoleName);
        addRow(form, "Trạng thái", lblStatus);

        form.add(Box.createVerticalStrut(10));
        form.add(createPermissionSection());

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
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));

        JLabel lbl = new JLabel(label);
        lbl.setFont(LABEL_FONT);
        lbl.setForeground(LABEL_COLOR);
        lbl.setPreferredSize(new Dimension(150, 36));

        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        parent.add(row);
        parent.add(Box.createVerticalStrut(6));
    }


    /* ================= FIELD FACTORY ================= */
    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setFont(FIELD_FONT);

        Dimension size = new Dimension(200, 36);
        txt.setPreferredSize(size);
        txt.setMinimumSize(size);
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

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
        lbl.setPreferredSize(new Dimension(120, 36));
        lbl.setBorder(BorderFactory.createLineBorder(BORDER));
        return lbl;
    }

    public void setIsDeleted(int value) {
        boolean active = value == 1;

        lblStatus.setText(active ? "Đang hoạt động" : "Ngưng hoạt động");
        lblStatus.setForeground(active ? new Color(22, 101, 52) : new Color(153, 27, 27));
        lblStatus.setBackground(active ? new Color(220, 252, 231) : new Color(254, 226, 226));
    }


    /* ================= PERMISSION ================= */
    private JPanel createPermissionSection() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);

        JLabel title = new JLabel("Phân quyền");
        title.setFont(LABEL_FONT);
        title.setForeground(LABEL_COLOR);
        title.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        permissionContainer = new JPanel();
        permissionContainer.setLayout(new BoxLayout(permissionContainer, BoxLayout.Y_AXIS));
        permissionContainer.setBackground(Color.WHITE);

        List<String> groups = List.of(
                "ORDER", "CUSTOMER", "EMPLOYEE", "SUPPLIER",
                "UNIT", "CATEGORY", "PRODUCT", "IMPORT",
                "EXPORT", "PROMOTION", "ROLE"
        );

        for (String g : groups) {
            PermissionGroupPanel panel = new PermissionGroupPanel(g, PRIMARY);
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            permissionContainer.add(panel);
            permissionContainer.add(Box.createVerticalStrut(10));
        }

        JScrollPane scroll = new JScrollPane(permissionContainer);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER));
        scroll.setPreferredSize(new Dimension(0, 360));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(scroll, BorderLayout.CENTER);

        return wrapper;
    }

    /* ================= BUTTONS ================= */
    private JPanel createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER));

        btnSave = new CustomButton("Lưu");
        btnClose = new CustomButton("Đóng");

        btnSave.setBackgroundColor(PRIMARY);
        btnClose.setBackgroundColor(new Color(229, 231, 235));

        btnClose.addActionListener(e -> dispose());

        panel.add(btnClose);
        panel.add(btnSave);

        return panel;
    }

    /* ================= MODE ================= */
    private void applyMode() {
        boolean editable = mode != MODE_VIEW;

        txtRoleName.setEditable(editable);
        btnSave.setVisible(editable);

        for (Component c : permissionContainer.getComponents()) {
            if (c instanceof PermissionGroupPanel pg) {
                pg.setEditable(editable);
            }
        }
    }

    private String getDialogTitle() {
        return switch (mode) {
            case MODE_ADD  -> "Thêm chức vụ";
            case MODE_EDIT -> "Sửa chức vụ";
            default        -> "Chi tiết chức vụ";
        };
    }

    private void bindDTO(RoleResponseDTO dto) {
        txtRoleName.setText(dto.getName());
        setIsDeleted(dto.getIsActive());
    }
}
