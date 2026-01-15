package com.conveniencestore.gui.exportcomponent;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import com.conveniencestore.DTO.InventoryExportItemResponseDTO;
import com.conveniencestore.DTO.InventoryExportResponseDTO;
import com.conveniencestore.constant.ExportStatus;
import com.conveniencestore.constant.ExportType;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;

public class ExportDialog extends JDialog {

    /* ================= MODE ================= */
    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;
    private final InventoryExportResponseDTO dto;
    private final List<InventoryExportItemResponseDTO> exportItems;

    /* ================= FORMAT ================= */
    private static final DateTimeFormatter UI_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /* ================= COMPONENTS ================= */
    private JTextField txtId;
    private JTextField txtExportCode;
    private JLabel lblExportStatus;
    private JComboBox cbbExportType;
    private JTextArea txtNote;
    private JLabel lblIsDeleted;
    private JTextField txtStaff;
    private JTextField txtUpdatedAt;
    private JTextField txtCreatedAt;
    private ExportItemPanel exportItemPanel;
    private CustomButton btnSave;
    private CustomButton btnEdit;
    private CustomButton btnClose;

    /* ================= THEME ================= */
    private static final Color PRIMARY = new Color(22, 163, 74);
    private static final Color PRIMARY_DARK = new Color(21, 128, 61);
    private static final Color BORDER = new Color(187, 247, 208);
    private static final Color LABEL_COLOR = new Color(22, 101, 52);

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private final Map<String, JPanel> rows = new LinkedHashMap<>();

    /* ================= CONSTRUCTOR ================= */
    public ExportDialog(
            JFrame parent,
            int mode,
            InventoryExportResponseDTO dto,
            List<InventoryExportItemResponseDTO> exportItems) {
        super(parent, true);
        this.mode = mode;
        this.dto = dto;
        this.exportItems = exportItems;

        setTitle(getTitleByMode());
        setSize(760, 720);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createScrollableForm(), BorderLayout.CENTER);
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
        panel.add(title);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY));
        return panel;
    }

    /* ================= SCROLL FORM ================= */
    private JScrollPane createScrollableForm() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        // ===== Fields =====
        txtId = createTextField();
        txtExportCode = createTextField();
        txtStaff = createTextField();
        lblExportStatus = createStatusLabel();
        lblIsDeleted = createStatusLabel();
        cbbExportType = new JComboBox<>(ExportType.values());
        cbbExportType.setFont(FIELD_FONT);
        txtCreatedAt = createTextField();
        txtUpdatedAt = createTextField();
        txtNote = createTextArea();

        // ===== Rows =====
        addRow(form, "ID", txtId);
        addRow(form, "Mã đơn", txtExportCode);
        addRow(form, "Nhân viên", txtStaff);
        addRow(form, "Loại xuất", cbbExportType);
        addRow(form, "Trạng thái đơn", lblExportStatus);
        addRow(form, "Xóa", lblIsDeleted);

        addRow(form, "Ngày tạo", txtCreatedAt);
        addRow(form, "Ngày cập nhật", txtUpdatedAt);

        addRow(form, "Ghi chú", new JScrollPane(txtNote));

        addSectionTitle(form, "Danh sách sản phẩm");
        exportItemPanel = new ExportItemPanel();
        form.add(exportItemPanel);
        form.add(Box.createVerticalStrut(12));

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(18);

        scroll.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));

        return scroll;
    }

    /* ================= ROW UTILS ================= */
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

    private void addSectionTitle(JPanel parent, String title) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);

        JLabel lbl = new JLabel("\t << " + title + " >>");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lbl.setForeground(PRIMARY_DARK);
        lbl.setBorder(BorderFactory.createEmptyBorder(12, 0, 6, 0));

        JPanel accent = new JPanel();
        accent.setPreferredSize(new Dimension(4, 1));
        accent.setBackground(PRIMARY);
        accent.setOpaque(true);

        wrapper.add(accent, BorderLayout.WEST);
        wrapper.add(lbl, BorderLayout.CENTER);
        wrapper.setBackground(new Color(240, 253, 244));
        wrapper.setOpaque(true);
        wrapper.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        parent.add(wrapper);
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

        btnSave = new CustomButton("Lưu",
                ImageUtil.scaleIcon(
                        new ImageIcon(getClass().getResource("/icon/save.png")), 18, 18));
        btnEdit = new CustomButton("Sửa",
                ImageUtil.scaleIcon(
                        new ImageIcon(getClass().getResource("/icon/edit.png")), 18, 18));
        btnClose = new CustomButton("Đóng", null);

        btnSave.setBackgroundColor(PRIMARY);
        btnEdit.setBackgroundColor(PRIMARY_DARK);

        panel.add(btnEdit);
        panel.add(btnSave);
        panel.add(btnClose);
        return panel;
    }

    /* ================= MODE ================= */
    private void applyMode() {
        switch (mode) {
            case MODE_VIEW -> {
                exportItemPanel.setViewData(exportItems);
                setViewOnly();
                btnSave.setVisible(false);
                btnEdit.setVisible(false);
            }
            case MODE_ADD -> {
                exportItemPanel.setAddMode();
                hideRow("ID");
                hideRow("Trạng thái đơn");
                hideRow("Xóa");
                hideRow("Ngày tạo");
                hideRow("Ngày cập nhật");
                btnEdit.setVisible(false);
            }
            case MODE_EDIT -> {
                exportItemPanel.setEditData(exportItems, new ArrayList<>());
                hideRow("Ngày tạo");
                hideRow("Ngày cập nhật");
                btnSave.setVisible(false);
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

    /* ================= STATUS CUSTOM ================= */
    private void setExportStatus(ExportStatus status) {
        lblExportStatus.setText(status.getDisplayName());
        lblExportStatus.setForeground(new Color(22, 101, 52));
        lblExportStatus.setBackground(new Color(220, 252, 231));
    }

    private void setIsDeleted(int value) {
        boolean active = value == 0;
        lblIsDeleted.setText(active ? "Hoạt động" : "Đã xóa");
        lblIsDeleted.setBackground(
                active ? new Color(220, 252, 231) : new Color(254, 226, 226));
    }

    /* ================= BIND DTO ================= */
    private void bindDTO(InventoryExportResponseDTO dto) {

        txtId.setText(String.valueOf(dto.getId()));
        txtExportCode.setText(dto.getCode());
        cbbExportType.setSelectedItem(dto.getExportType());
        txtStaff.setText(dto.getCreatedByName());
        setExportStatus(dto.getStatus());
        setIsDeleted(dto.getIsDeleted());
        txtCreatedAt.setText(
                dto.getCreatedAt() != null
                        ? dto.getCreatedAt().format(UI_DATE)
                        : "");
        txtUpdatedAt.setText(
                dto.getUpdatedAt() != null
                        ? dto.getUpdatedAt().format(UI_DATE)
                        : "");
        txtNote.setText(dto.getNote());
    }

    private String getTitleByMode() {
        return switch (mode) {
            case MODE_ADD -> "Tạo đơn hàng";
            case MODE_EDIT -> "Sửa đơn hàng";
            default -> "Xem đơn hàng";
        };
    }
}
