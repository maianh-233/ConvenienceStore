package com.conveniencestore.gui.order;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import com.conveniencestore.DTO.OrderItemResponseDTO;
import com.conveniencestore.DTO.OrderResponseDTO;
import com.conveniencestore.constant.OrderStatus;
import com.conveniencestore.constant.PaymentMethod;
import com.conveniencestore.constant.PaymentStatus;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;


public class OrderDialog extends JDialog {

    /* ================= MODE ================= */
    public static final int MODE_VIEW = 0;
    public static final int MODE_ADD  = 1;
    public static final int MODE_EDIT = 2;

    private final int mode;
    private final OrderResponseDTO dto;
    private final List<OrderItemResponseDTO> orderItems;

    /* ================= FORMAT ================= */
    private static final DateTimeFormatter UI_DATE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /* ================= COMPONENTS ================= */
    private JTextField txtId;
    private JTextField txtOrderNumber;
    private JTextField txtCustomer;
    private JTextField txtStaff;

    private JLabel lblOrderStatus;
    private JLabel lblIsDeleted;

    private JTextField txtCreatedAt;
    private JTextField txtUpdatedAt;
    private JTextArea  txtNote;

    private JComboBox<String> cmbPromotion;
    private JTextField txtSubtotal;
    private JTextField txtDiscount;
    private JTextField txtTotal;

    private JComboBox<PaymentMethod> cmbPaymentMethod;
    private JTextField txtTransactionRef;
    private JLabel lblPaymentStatus;

    private OrderItemPanel orderItemPanel;

    private CustomButton btnSave;
    private CustomButton btnEdit;
    private CustomButton btnClose;

    /* ================= THEME ================= */
    private static final Color PRIMARY       = new Color(22, 163, 74);
    private static final Color PRIMARY_DARK  = new Color(21, 128, 61);
    private static final Color BORDER        = new Color(187, 247, 208);
    private static final Color LABEL_COLOR   = new Color(22, 101, 52);

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private final Map<String, JPanel> rows = new LinkedHashMap<>();

    /* ================= CONSTRUCTOR ================= */
    public OrderDialog(
            JFrame parent,
            int mode,
            OrderResponseDTO dto,
            List<OrderItemResponseDTO> orderItems
    ) {
        super(parent, true);
        this.mode = mode;
        this.dto = dto;
        this.orderItems = orderItems;

        setTitle(getTitleByMode());
        setSize(760, 720);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createScrollableForm(), BorderLayout.CENTER);
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
        txtOrderNumber = createTextField();
        txtCustomer = createTextField();
        txtStaff = createTextField();

        lblOrderStatus = createStatusLabel();
        lblIsDeleted   = createStatusLabel();

        txtCreatedAt = createTextField();
        txtUpdatedAt = createTextField();

        txtNote = createTextArea();

        cmbPromotion = new JComboBox<>();
        cmbPromotion.setFont(FIELD_FONT);

        txtSubtotal = createTextField();
        txtDiscount = createTextField();
        txtTotal    = createTextField();

        cmbPaymentMethod = new JComboBox<>(PaymentMethod.values());
        cmbPaymentMethod.setFont(FIELD_FONT);

        txtTransactionRef = createTextField();
        lblPaymentStatus  = createStatusLabel();

        // ===== Rows =====
        addRow(form, "ID", txtId);
        addRow(form, "Mã đơn", txtOrderNumber);
        addRow(form, "Khách hàng", txtCustomer);
        addRow(form, "Nhân viên", txtStaff);

        addRow(form, "Trạng thái đơn", lblOrderStatus);
        addRow(form, "Xóa", lblIsDeleted);

        addRow(form, "Ngày tạo", txtCreatedAt);
        addRow(form, "Ngày cập nhật", txtUpdatedAt);

        addRow(form, "Ghi chú", new JScrollPane(txtNote));

        addSectionTitle(form, "Danh sách sản phẩm");
        orderItemPanel = new OrderItemPanel();
        form.add(orderItemPanel);
        form.add(Box.createVerticalStrut(12));

        addSectionTitle(form, "Thanh toán");
        addRow(form, "Khuyến mãi", cmbPromotion);
        addRow(form, "Tạm tính", txtSubtotal);
        addRow(form, "Giảm giá", txtDiscount);
        addRow(form, "Tổng tiền", txtTotal);
        addRow(form, "PT thanh toán", cmbPaymentMethod);
        addRow(form, "Mã GD", txtTransactionRef);
        addRow(form, "TT thanh toán", lblPaymentStatus);

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(18);

        // 🔥 GẮN MODERN SCROLLBAR
        scroll.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));

        // (Optional) nếu không cần scroll ngang
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

        JLabel lbl = new JLabel("\t << " + title+ " >>");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lbl.setForeground(PRIMARY_DARK);
        lbl.setBorder(BorderFactory.createEmptyBorder(12, 0, 6, 0));

        

        // Thanh nhấn bên trái
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
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
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
                        new ImageIcon(getClass().getResource("/icon/save.png")), 18, 18
                ));
        btnEdit = new CustomButton("Sửa",
                ImageUtil.scaleIcon(
                        new ImageIcon(getClass().getResource("/icon/edit.png")), 18, 18
                ));
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
                orderItemPanel.setViewData(orderItems);
                setViewOnly();
                btnSave.setVisible(false);
                btnEdit.setVisible(false);
            }
            case MODE_ADD -> {
                orderItemPanel.setAddMode();
                hideRow("ID");
                hideRow("Trạng thái đơn");
                hideRow("Xóa");
                hideRow("Ngày tạo");
                hideRow("Ngày cập nhật");
                btnEdit.setVisible(false);
            }
            case MODE_EDIT -> {
                orderItemPanel.setEditData(orderItems, new ArrayList<>());
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
        if (row != null) row.setVisible(false);
    }

    /* ================= STATUS CUSTOM ================= */
    private void setOrderStatus(OrderStatus status) {
        lblOrderStatus.setText(status.getDisplayName());
        lblOrderStatus.setForeground(new Color(22, 101, 52));
        lblOrderStatus.setBackground(new Color(220, 252, 231));
    }

    private void setPaymentStatus(PaymentStatus status) {
        lblPaymentStatus.setText(status.getDisplayName());
        Color bg = switch (status) {
            case COMPLETED -> new Color(220, 252, 231);
            case FAILED, CANCELLED -> new Color(254, 226, 226);
            default -> new Color(254, 249, 195);
        };
        lblPaymentStatus.setBackground(bg);
    }

    private void setIsDeleted(int value) {
        boolean active = value == 0;
        lblIsDeleted.setText(active ? "Hoạt động" : "Đã xóa");
        lblIsDeleted.setBackground(
                active ? new Color(220, 252, 231) : new Color(254, 226, 226)
        );
    }


    
    /* ================= BIND DTO ================= */
    private void bindDTO(OrderResponseDTO dto) {
        txtId.setText(String.valueOf(dto.getId()));
        txtOrderNumber.setText(dto.getOrderNumber());
        txtCustomer.setText(dto.getCustomerName());
        txtStaff.setText(dto.getStaffName());

        setOrderStatus(dto.getStatus());
        setIsDeleted(dto.getIsDeleted());

        if (dto.getCreatedAt() != null) {
            txtCreatedAt.setText(dto.getCreatedAt().format(UI_DATE));
        }

        if (dto.getUpdatedAt() != null) {
            txtUpdatedAt.setText(dto.getUpdatedAt().format(UI_DATE));
        }

        txtNote.setText(dto.getNote());

        txtSubtotal.setText(
                dto.getSubtotal() != null ? dto.getSubtotal().toString() : "0"
        );
        txtDiscount.setText(
                dto.getDiscount() != null ? dto.getDiscount().toString() : "0"
        );
        txtTotal.setText(
                dto.getTotalAmount() != null ? dto.getTotalAmount().toString() : "0"
        );

        // ===== Payment =====
        cmbPaymentMethod.setSelectedItem(dto.getPaymentMethod());
        setPaymentStatus(dto.getPaymentStatus());
    }



    private String getTitleByMode() {
        return switch (mode) {
            case MODE_ADD  -> "Tạo đơn hàng";
            case MODE_EDIT -> "Sửa đơn hàng";
            default        -> "Xem đơn hàng";
        };
    }
}
