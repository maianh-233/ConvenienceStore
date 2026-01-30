package com.conveniencestore.gui;

import com.conveniencestore.bus.AuthBUS;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;

public class ResetPasswordDialog extends JDialog {

    // ===== THEME SYSTEM =====
    private static final Color PRIMARY = new Color(22, 163, 74); // green-600
    private static final Color PRIMARY_HOVER = new Color(21, 128, 61); // green-700
    private static final Color BORDER = new Color(187, 247, 208); // green-200
    private static final Color BG = new Color(240, 253, 244); // green-50
    private static final Color TEXT = new Color(22, 101, 52); // green-800

    private final AuthBUS authBUS = new AuthBUS();
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    // Components cho từng bước
    private JTextField txtEmail, txtOTP;
    private JPasswordField txtNewPass, txtConfirmPass;
    private String currentEmail;

    private CustomButton btnSendOtp;
    private CustomButton btnVerifyOtp;
    private CustomButton btnSave;

    private String email;


    public ResetPasswordDialog(JFrame parent) {
        super(parent, "Khôi phục mật khẩu", true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initUI() {

        // ===== MAIN PANEL =====
        mainPanel.setPreferredSize(new Dimension(400, 250));
        mainPanel.setBackground(BG);
        mainPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER, 1),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        /*
         * ==================================================
         * STEP 1: NHẬP EMAIL
         * ==================================================
         */
        JPanel pnlEmail = new JPanel(new GridLayout(3, 1, 10, 10));
        pnlEmail.setBackground(BG);

        pnlEmail.add(createLabel("Nhập email khôi phục:"));
        txtEmail = createTextField();
        pnlEmail.add(txtEmail);

        btnSendOtp = new CustomButton("Gửi mã OTP");
        btnSendOtp.setBackgroundColor(PRIMARY);
        btnSendOtp.setHoverColor(PRIMARY_HOVER);
        btnSendOtp.setCornerRadius(12);
        btnSendOtp.addActionListener(e -> handleSendOtp());
        pnlEmail.add(btnSendOtp);

        /*
         * ==================================================
         * STEP 2: NHẬP OTP
         * ==================================================
         */
        JPanel pnlOTP = new JPanel(new GridLayout(3, 1, 10, 10));
        pnlOTP.setBackground(BG);

        pnlOTP.add(createLabel("Nhập mã OTP đã gửi về Email:"));
        txtOTP = createTextField();
        pnlOTP.add(txtOTP);

        btnVerifyOtp = new CustomButton("Xác nhận mã");
        btnVerifyOtp.setBackgroundColor(PRIMARY);
        btnVerifyOtp.setHoverColor(PRIMARY_HOVER);
        btnVerifyOtp.addActionListener(e -> handleVerifyOtp());
        pnlOTP.add(btnVerifyOtp);

        /*
         * ==================================================
         * STEP 3: ĐỔI MẬT KHẨU
         * ==================================================
         */
        JPanel pnlNewPass = new JPanel(new GridLayout(5, 1, 5, 5));
        pnlNewPass.setBackground(BG);

        pnlNewPass.add(createLabel("Mật khẩu mới:"));
        txtNewPass = createPasswordField();
        pnlNewPass.add(txtNewPass);

        pnlNewPass.add(createLabel("Xác nhận mật khẩu:"));
        txtConfirmPass = createPasswordField();
        pnlNewPass.add(txtConfirmPass);

        btnSave = new CustomButton("Lưu mật khẩu");
        btnSave.setBackgroundColor(PRIMARY);
        btnSave.setHoverColor(PRIMARY_HOVER);
        btnSave.addActionListener(e -> handleResetPassword());
        pnlNewPass.add(btnSave);

        /*
         * ==================================================
         * ADD CARD
         * ==================================================
         */
        mainPanel.add(pnlEmail, "stepEmail");
        mainPanel.add(pnlOTP, "stepOTP");
        mainPanel.add(pnlNewPass, "stepNewPass");

        add(mainPanel);

        /*
         * ==================================================
         * INIT STATE
         * ==================================================
         */
        cardLayout.show(mainPanel, "stepEmail");
        getRootPane().setDefaultButton(btnSendOtp);
    }

    private JPasswordField createPasswordField() {
        JPasswordField txt = new JPasswordField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        return txt;
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(TEXT);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return lbl;
    }

    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        return txt;
    }

    private void handleSendOtp() {
        email = txtEmail.getText().trim();

        if (email.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email!");
            return;
        }
            

         if (!ValidationUtil.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
            return;
        }

        try {
           setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            authBUS.sendOtp(email);
            this.currentEmail = email;
            JOptionPane.showMessageDialog(this, "Mã OTP đã được gửi!");
            cardLayout.show(mainPanel, "stepOTP"); 
            getRootPane().setDefaultButton(btnVerifyOtp); // Chuyển nút Enter sang xác nhận OTP
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void handleVerifyOtp() {
        String otp = txtOTP.getText().trim();

        if (otp.length() != 6) {
            JOptionPane.showMessageDialog(this, "Mã OTP phải có 6 chữ số!");
            return;
        }

        try {
            authBUS.verifyOtp(currentEmail, otp); 
            cardLayout.show(mainPanel, "stepNewPass");
            getRootPane().setDefaultButton(btnSave);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void handleResetPassword() {
        String otp = txtOTP.getText().trim();
        String pass = new String(txtNewPass.getPassword());
        String confirm = new String(txtConfirmPass.getPassword());

        // Kiểm tra mật khẩu không được trống
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới!");
            return;
        }

        if (confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng xác nhận mật khẩu!");
            return;
        }

        //  Validate độ mạnh mật khẩu
        if (!ValidationUtil.isValidPassword(pass)) {
            JOptionPane.showMessageDialog(
                this,
                "Mật khẩu phải ≥ 8 ký tự, có chữ hoa, chữ thường và số"
            );
            return;
        }

        //  So khớp mật khẩu
        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không khớp!");
            return;
        }

        //Gọi BUS
        try {
            authBUS.resetPassword(currentEmail, otp, pass);
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

}