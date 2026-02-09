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
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.conveniencestore.DTO.UserDTO;
import com.conveniencestore.DTO.UserRequestDTO;
import com.conveniencestore.bus.AuthBUS;

import com.conveniencestore.gui.utils.AppColor;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.ModernScrollBarUI;
import com.conveniencestore.mapper.UserMapper;
import com.conveniencestore.security.AuthContext;
import com.conveniencestore.util.ValidationUtil;

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
    private JTextField txtFullName;
    private JSpinner spDob;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextArea txtAddress;
    private JTextField txtRole;

    private JCheckBox cbChangePassword;
    private JPasswordField txtNewPass;
    private JPasswordField txtConfirmPass;
    private JPanel passwordPanel;

    private JRadioButton rdMale;
    private JRadioButton rdFemale;
    private JButton btnSave;

    private Long currentUserID;

    private AuthBUS authBUS = new AuthBUS();

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
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(leftWrapper, BorderLayout.WEST);
        add(rightScroll, BorderLayout.CENTER);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                updateResponsiveLayout();
            }
        });

        // Load user data
        loadUserData();

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

        btnChooseImage.addActionListener(e -> {
            handleSavePicture();
        });

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
        txtRole = createField(true);

        txtFullName = createField(false);
        spDob = new JSpinner(new SpinnerDateModel(
                new Date(),
                null,
                null,
                Calendar.DAY_OF_MONTH));

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spDob, "dd/MM/yyyy");
        spDob.setEditor(editor);
        spDob.setFont(FONT_FIELD);
        spDob.setPreferredSize(FIELD_SIZE);

        txtEmail = createField(false);
        txtPhone = createField(false);
        txtAddress = createTextArea(false);

        txtNewPass = new JPasswordField();
        txtConfirmPass = new JPasswordField();

        rdMale = new JRadioButton("Nam");
        rdFemale = new JRadioButton("Nữ");
        ButtonGroup group = new ButtonGroup();
        group.add(rdMale);
        group.add(rdFemale);

        addRow(panel, gbc, "Mã nhân viên", txtUsername);
        addRow(panel, gbc, "Họ và tên", txtFullName);
        addRow(panel, gbc, "Ngày sinh", spDob);
        addRow(panel, gbc, "Email", txtEmail);
        addRow(panel, gbc, "Số điện thoại", txtPhone);
        addRow(panel, gbc, "Địa chỉ", createTextAreaWrapper(txtAddress));

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

        // ===== CHANGE PASSWORD SECTION =====
        cbChangePassword = new JCheckBox("Thay đổi mật khẩu");
        cbChangePassword.setFont(FONT_FIELD);
        cbChangePassword.setOpaque(false);
        cbChangePassword.setForeground(AppColor.PRIMARY);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(cbChangePassword, gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;

        // Create password panel that will be shown/hidden
        passwordPanel = createPasswordPanel();
        passwordPanel.setVisible(false);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(passwordPanel, gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;

        // Add action listener to checkbox
        cbChangePassword.addActionListener(e -> {
            passwordPanel.setVisible(cbChangePassword.isSelected());
            panel.revalidate();
            panel.repaint();
        });

        gbc.gridy++;

        // Button
        btnSave = new JButton("LƯU THÔNG TIN MỚI");
        btnSave.setFont(FONT_BUTTON);
        btnSave.setBackground(AppColor.PRIMARY);
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(BUTTON_SIZE);
        btnSave.setFocusPainted(false);
        btnSave.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        btnSave.addActionListener(e -> {
            handleSaveInformation();
        });

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
                readonly ? new Color(235, 235, 235) : Color.WHITE);
        return field;
    }

    private JTextArea createTextArea(boolean readonly) {
        JTextArea area = new JTextArea();
        area.setFont(FONT_FIELD);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setRows(3);
        area.setEditable(!readonly);
        area.setBackground(
                readonly ? new Color(235, 235, 235) : Color.WHITE);
        return area;
    }

    private JScrollPane createTextAreaWrapper(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(320, 80));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scrollPane.setOpaque(false);

        JScrollBar vBar = scrollPane.getVerticalScrollBar();
        vBar.setUI(new ModernScrollBarUI());
        vBar.setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
        vBar.setOpaque(false);

        return scrollPane;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        // New Password
        addRow(panel, gbc, "Mật khẩu mới", createPasswordField(txtNewPass));

        // Confirm Password
        addRow(panel, gbc, "Xác nhận mật khẩu", createPasswordField(txtConfirmPass));

        txtNewPass.setFont(FONT_FIELD);
        txtNewPass.setPreferredSize(FIELD_SIZE);

        txtConfirmPass.setFont(FONT_FIELD);
        txtConfirmPass.setPreferredSize(FIELD_SIZE);

        return panel;
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
                30);

        ImageIcon eyeClosed = ImageUtil.scaleIconKeepRatioWidth(
                new ImageIcon(getClass().getResource("/icon/closeeye.png")),
                30);

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

    // ================= LOAD USER DATA =================
    public void loadUserData() {
        UserDTO user = AuthContext.get();

        if (user == null) {
            return;
        }

        currentUserID = user.getId();

        // Set username (read-only)
        if (user.getUsername() != null) {
            txtUsername.setText(user.getUsername());
        }

        // Set full name
        if (user.getFullName() != null) {
            txtFullName.setText(user.getFullName());
        }


        // Set date of birth
        if (user.getDateOfBirth() != null) {
            Date dob = java.sql.Date.valueOf(user.getDateOfBirth());
            spDob.setValue(dob);
        }


        // Set email
        if (user.getEmail() != null) {
            txtEmail.setText(user.getEmail());
        }

        // Set phone
        if (user.getPhone() != null) {
            txtPhone.setText(user.getPhone());
        }

        // Set address
        if (user.getAddress() != null) {
            txtAddress.setText(user.getAddress());
        }

        // Set role
        if (user.getRoleName() != null) {
            txtRole.setText(user.getRoleName());
        }

        // Set gender (0 = Nam, 1 = Nữ)
        if (user.getGender() == 1) {
            rdFemale.setSelected(true);
        } else {
            rdMale.setSelected(true);
        }

        // Set avatar image
        if (user.getImgUrl() != null && !user.getImgUrl().isEmpty()) {
            try {
                URL imageUrl = new URL(user.getImgUrl());
                Image image = ImageIO.read(imageUrl);

                if (image != null) {
                    Image scaled = image.getScaledInstance(
                            320, 320, Image.SCALE_SMOOTH);
                    lblAvatar.setIcon(new ImageIcon(scaled));
                } else {
                    System.out.println("ImageIO đọc ảnh = null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

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

    private void handleSavePicture() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn ảnh đại diện");

        chooser.setFileFilter(new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png"));

        int result = chooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION)
            return;

        File selectedFile = chooser.getSelectedFile();

        // ===== PREVIEW NGAY ẢNH =====
        try {
            ImageIcon preview = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaled = preview.getImage().getScaledInstance(
                    200, 200, Image.SCALE_SMOOTH);
            lblAvatar.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Không thể hiển thị ảnh",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ===== GỌI BUS UPLOAD =====
        try {

            boolean success = authBUS.updateUserAvatar(selectedFile);

            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cập nhật ảnh đại diện thành công 🎉");

                // reload lại avatar từ cloud (đảm bảo đúng URL mới)
                loadUserData();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Cập nhật ảnh thất bại",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Lỗi upload ảnh: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSaveInformation() {

        String fullName = txtFullName.getText().trim();
        Date dob = (Date) spDob.getValue();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();
        int gender;

        if (rdMale.isSelected()) {
            gender = 0;
        } else if (rdFemale.isSelected()) {
            gender = 1;
        } else {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn giới tính!",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ===== VALIDATE CƠ BẢN =====
        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Không được để trống thông tin!",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (ValidationUtil.isValidEmail(email) == false) {
            JOptionPane.showMessageDialog(this,
                    "Hãy nhập đúng định dạng mail!",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (ValidationUtil.isValidPhoneVN(phone) == false) {
            JOptionPane.showMessageDialog(this,
                    "Hãy nhập đúng số điện thoại!",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate localDob = dob.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        UserRequestDTO requestDTO = UserMapper.toRequestDTO(AuthContext.get());

        // Gọi BUS
        requestDTO.setFullName(fullName);
        requestDTO.setDateOfBirth(localDob);
        requestDTO.setEmail(email);
        requestDTO.setPhone(phone);
        requestDTO.setAddress(address);
        requestDTO.setGender(gender);

        // ===== CHANGE PASSWORD =====

        String newPass = null;

        if (cbChangePassword.isSelected()) {
            newPass = new String(txtNewPass.getPassword()).trim();
            String confirmPass = new String(txtConfirmPass.getPassword()).trim();

            if (newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập đầy đủ thông tin mật khẩu!",
                        "Lỗi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this,
                        "Mật khẩu xác nhận không khớp!",
                        "Lỗi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            requestDTO.setPassword(newPass);
        }

        try {
            boolean success = authBUS.updateUserInfo(requestDTO);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Cập nhật thông tin thành công!",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Cập nhật thất bại!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
            txtNewPass.setText("");
            txtConfirmPass.setText("");

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}
