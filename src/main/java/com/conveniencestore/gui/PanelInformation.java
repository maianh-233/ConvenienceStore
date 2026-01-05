package com.conveniencestore.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.conveniencestore.gui.utils.AppColor;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;

public class PanelInformation extends JPanel {

    private static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FONT_FIELD = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 26);

    private static final Dimension FIELD_SIZE = new Dimension(320, 35);
    private static final Dimension BUTTON_SIZE = new Dimension(320, 40);

    private JPanel leftPanel;
    private JPanel leftWrapper;
    private JScrollPane rightScroll;

    // ===== LEFT =====
    private JLabel lblAvatar;
    private JButton btnChooseImage;

    // ===== RIGHT =====
    private JTextField txtUsername;
    private JTextField txtIdentity;
    private JTextField txtFullName;
    private JSpinner spDob;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtAddress;
    private JTextField txtRole;

    private JPasswordField txtNewPass;
    private JPasswordField txtConfirmPass;
    private JRadioButton rdMale;
    private JRadioButton rdFemale;
    private JButton btnSave;

    public PanelInformation() {
        setLayout(new BorderLayout(30, 0));
        setBackground(AppColor.CONTENT_BG);
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        leftPanel = createLeftPanel();

        leftWrapper = new JPanel(new BorderLayout());
        leftWrapper.setOpaque(false);
        leftWrapper.add(leftPanel, BorderLayout.CENTER);


        rightScroll = new JScrollPane(createRightPanel());
        rightScroll.setBorder(null);
        rightScroll.setOpaque(false);
        rightScroll.getViewport().setOpaque(false);

        JScrollBar vBar = rightScroll.getVerticalScrollBar();
        vBar.setUI(new ModernScrollBarUI());
        vBar.setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
        vBar.setUnitIncrement(16);
        vBar.setOpaque(false);

        rightScroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );


        add(leftWrapper, BorderLayout.WEST);
        add(rightScroll, BorderLayout.CENTER);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                updateResponsiveLayout();
            }
        });


    }

    // ================= LEFT PANEL =================
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(420, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        // ===== AVATAR =====
        lblAvatar = new JLabel();
        lblAvatar.setPreferredSize(new Dimension(320, 320));
        lblAvatar.setHorizontalAlignment(JLabel.CENTER);
        lblAvatar.setVerticalAlignment(JLabel.CENTER);
        lblAvatar.setBorder(BorderFactory.createLineBorder(AppColor.PRIMARY, 2));
        lblAvatar.setMinimumSize(new Dimension(320, 320));
        lblAvatar.setMaximumSize(new Dimension(320, 320));


        gbc.gridy = 0;
        panel.add(lblAvatar, gbc);

        // ===== BUTTON =====
        btnChooseImage = new JButton("Chọn ảnh");
        btnChooseImage.setFont(FONT_BUTTON);
        btnChooseImage.setBackground(AppColor.PRIMARY);
        btnChooseImage.setForeground(Color.WHITE);
        btnChooseImage.setFocusPainted(false);
        btnChooseImage.setPreferredSize(new Dimension(160, 42));
        btnChooseImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        gbc.gridy = 1;
        panel.add(btnChooseImage, gbc);

        return panel;
    }


    // ================= RIGHT PANEL =================
    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        JLabel lblTitle = new JLabel("THÔNG TIN CÁ NHÂN");
        lblTitle.setFont(FONT_TITLE);
        lblTitle.setForeground(AppColor.PRIMARY); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblTitle, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;


        txtUsername = createField(true);
        txtIdentity = createField(true);
        txtRole = createField(true);

        txtFullName = createField(false);
        spDob = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(spDob, "dd/MM/yyyy");
        spDob.setEditor(editor);
        spDob.setFont(FONT_FIELD);
        spDob.setPreferredSize(FIELD_SIZE);

        txtEmail = createField(false);
        txtPhone = createField(false);
        txtAddress = createField(false);

        txtNewPass = new JPasswordField();
        txtConfirmPass = new JPasswordField();

        rdMale = new JRadioButton("Nam");
        rdFemale = new JRadioButton("Nữ");
        ButtonGroup group = new ButtonGroup();
        group.add(rdMale);
        group.add(rdFemale);

        addRow(panel, gbc, "Mã nhân viên", txtUsername);
        addRow(panel, gbc, "Căn cước", txtIdentity);
        addRow(panel, gbc, "Họ và tên", txtFullName);
        addRow(panel, gbc, "Ngày sinh", spDob);
        addRow(panel, gbc, "Email", txtEmail);
        addRow(panel, gbc, "Số điện thoại", txtPhone);
        addRow(panel, gbc, "Địa chỉ", txtAddress);

        // Gender
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setOpaque(false);
        genderPanel.add(rdMale);
        genderPanel.add(rdFemale);
        addRow(panel, gbc, "Giới tính", genderPanel);
        rdMale.setFont(FONT_FIELD);
        rdFemale.setFont(FONT_FIELD);
        rdMale.setOpaque(false);
        rdFemale.setOpaque(false);


        addRow(panel, gbc, "Vai trò", txtRole);
        addRow(panel, gbc, "Mật khẩu mới",createPasswordField(txtNewPass));

        addRow(panel, gbc, "Xác nhận mật khẩu",createPasswordField(txtConfirmPass));

        txtNewPass.setFont(FONT_FIELD);
        txtNewPass.setPreferredSize(FIELD_SIZE);

        txtConfirmPass.setFont(FONT_FIELD);
        txtConfirmPass.setPreferredSize(FIELD_SIZE);


        // Button
        btnSave = new JButton("LƯU THÔNG TIN MỚI");
        btnSave.setFont(FONT_BUTTON);
        btnSave.setBackground(AppColor.PRIMARY);
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(BUTTON_SIZE);
        btnSave.setFocusPainted(false);
        btnSave.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));


        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(btnSave, gbc);

        return panel;
    }

    // ================= HELPER =================
    private JTextField createField(boolean readonly) {
        JTextField field = new JTextField();
        field.setFont(FONT_FIELD);
        field.setPreferredSize(FIELD_SIZE);
        field.setMinimumSize(FIELD_SIZE);

        field.setEditable(!readonly);
        field.setBackground(
            readonly ? new Color(235, 235, 235) : Color.WHITE
        );
        return field;
    }

    private JPanel createPasswordField(JPasswordField passwordField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        passwordField.setFont(FONT_FIELD);
        passwordField.setPreferredSize(FIELD_SIZE);
        passwordField.setEchoChar('•');

        // ===== LOAD ICON =====
        ImageIcon eyeOpen = ImageUtil.scaleIconKeepRatioWidth(
                new ImageIcon(getClass().getResource("/icon/openeye.png")),
                30
        );

        ImageIcon eyeClosed = ImageUtil.scaleIconKeepRatioWidth(
                new ImageIcon(getClass().getResource("/icon/closeeye.png")),
                30
        );

        JButton btnToggle = new JButton(eyeClosed);
        btnToggle.setPreferredSize(new Dimension(40, 40));
        btnToggle.setFocusPainted(false);
        btnToggle.setBorder(BorderFactory.createEmptyBorder());
        btnToggle.setContentAreaFilled(false);
        btnToggle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnToggle.setToolTipText("Ẩn / hiện mật khẩu");
        btnToggle.setBackground(AppColor.PRIMARY);
        btnToggle.setOpaque(false);
        btnToggle.setFocusable(false);



        btnToggle.addActionListener(e -> {
            if (passwordField.getEchoChar() == 0) {
                // ĐANG HIỆN → ẨN
                passwordField.setEchoChar('•');
                btnToggle.setIcon(eyeClosed);
            } else {
                // ĐANG ẨN → HIỆN
                passwordField.setEchoChar((char) 0);
                btnToggle.setIcon(eyeOpen);
            }
        });

        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(btnToggle, BorderLayout.EAST);

        return panel;
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, String label, Component comp) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(FONT_LABEL);

        gbc.gridx = 0;
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        panel.add(comp, gbc);

        gbc.gridy++;
    }

    // Thay đổi bố cục
    private void updateResponsiveLayout() {
        int width = getWidth();

        remove(leftWrapper);
        remove(rightScroll);

        if (width < 900) {
            // TOP - BOTTOM
            add(leftWrapper, BorderLayout.NORTH);
            add(rightScroll, BorderLayout.CENTER);

            leftWrapper.setPreferredSize(new Dimension(0, 420));
        } else {
            // LEFT - RIGHT
            add(leftWrapper, BorderLayout.WEST);
            add(rightScroll, BorderLayout.CENTER);

            leftWrapper.setPreferredSize(new Dimension(420, 0));
        }

        revalidate();
        repaint();
    }



}

