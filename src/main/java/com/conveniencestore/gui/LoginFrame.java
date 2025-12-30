package com.conveniencestore.gui;

import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

import java.awt.event.FocusAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    // Màu custom
    private final Color primaryGreen = new Color(34, 139, 34); 
    private final Color hoverGreen = new Color(0, 128, 0);     
    private final Color lightGreen = new Color(248, 255, 248); 
    private final Color textDark = new Color(20, 20, 20);      
    private final Color borderFocus = new Color(34, 139, 34);

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginFrame() {
        initUI();
        addEvents();
    }

    private void initUI() {
        setTitle("FreshMart - Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // ================== LEFT PANEL ==================
        JPanel leftPanel = createLeftPanel();
        
        // ================== RIGHT PANEL - Form đăng nhập ==================
        JPanel rightPanel = createRightPanel();
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
        // Focus vào username khi mở
        SwingUtilities.invokeLater(() -> txtUsername.requestFocus());
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(420, 0));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(primaryGreen);

        // Gradient overlay cho ảnh
        JPanel overlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(0,0,0,0), 0, getHeight(), new Color(0,0,0,100));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlay.setLayout(new BorderLayout());

        try {
            ImageIcon icon = new ImageIcon("src/main/resources/icon/background.jpg");
            ImageIcon scaled = ImageUtil.scaleIconKeepRatioWidth(icon, 420);
            JLabel imageLabel = new JLabel(scaled);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            overlay.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            // Fallback image nếu không có file
            JLabel fallback = new JLabel("FreshMart", SwingConstants.CENTER);
            fallback.setFont(new Font("Segoe UI", Font.BOLD, 24));
            fallback.setForeground(Color.WHITE);
            overlay.add(fallback, BorderLayout.CENTER);
        }

        leftPanel.add(overlay, BorderLayout.CENTER);
        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(250, 255, 250));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 40, 20, 40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0; // Cho phép component mở rộng theo width!

        // 1. LOGO + TITLE
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(titlePanel, gbc);

        JLabel titleLabel = new JLabel("FreshMart");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(primaryGreen);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // 2. USERNAME FIELD
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        addLabel(rightPanel, "Username:", gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        txtUsername = createStyledTextField();
        rightPanel.add(txtUsername, gbc);

        // 3. PASSWORD FIELD
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        addLabel(rightPanel, "Password:", gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        txtPassword = createStyledPasswordField();
        rightPanel.add(txtPassword, gbc);

        // 4. LOGIN BUTTON
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        CustomButton btnLogin = createLoginButton();
        rightPanel.add(btnLogin, gbc);

        // 5. FORGOT PASSWORD
        gbc.gridy = 5;
        JButton btnForgot = createForgotButton();
        rightPanel.add(btnForgot, gbc);

        return rightPanel;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 40)); 
        textField.setMinimumSize(new Dimension(300, 40));
        textField.setMaximumSize(new Dimension(400, 40));
        textField.setBackground(lightGreen);
        textField.setForeground(textDark);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 230, 200), 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Focus effect đẹp
        textField.setCaretColor(primaryGreen);
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borderFocus, 3),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 230, 200), 2),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }
        });
        return textField;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setMinimumSize(new Dimension(300, 40));
        passwordField.setMaximumSize(new Dimension(400, 40));
        passwordField.setBackground(lightGreen);
        passwordField.setForeground(textDark);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 230, 200), 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        passwordField.setCaretColor(primaryGreen);


        // Focus effect cho password
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borderFocus, 3),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 230, 200), 2),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }
        });

        return passwordField;
    }

    private void addLabel(JPanel panel, String text, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(textDark);
        gbc.weightx = 0;
        panel.add(label, gbc);
    }

    private CustomButton createLoginButton() {
        CustomButton btnLogin = new CustomButton("ĐĂNG NHẬP");
        btnLogin.setPreferredSize(new Dimension(320, 50));
        btnLogin.setBackgroundColor(primaryGreen);
        btnLogin.setHoverColor(hoverGreen);
        btnLogin.setTextColor(Color.WHITE);
        btnLogin.setButtonFont(new Font("Segoe UI", Font.BOLD, 18));
        return btnLogin;
    }

    private JButton createForgotButton() {
        JButton btnForgot = new JButton("Quên mật khẩu?");
        btnForgot.setBorderPainted(false);
        btnForgot.setContentAreaFilled(false);
        btnForgot.setForeground(primaryGreen);
        btnForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnForgot.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnForgot.setHorizontalAlignment(SwingConstants.CENTER);
        btnForgot.setPreferredSize(new Dimension(200, 30));
        return btnForgot;
    }

    private void addEvents() {
        // Event Login (Demo)
        for (Component c : getComponents()) {
            if (c instanceof JPanel) {
                for (Component child : ((JPanel) c).getComponents()) {
                    if (child instanceof CustomButton && "ĐĂNG NHẬP".equals(((CustomButton) child).getText())) {
                        ((CustomButton) child).addActionListener(e -> {
                            String username = txtUsername.getText().trim();
                            String password = new String(txtPassword.getPassword()).trim();
                            
                            if (username.isEmpty() || password.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            
                            // TODO: Gọi API login
                            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!\n " + username, "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            // new MainFrame().setVisible(true);
                            // dispose();
                        });
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LoginFrame().setVisible(true);
        });
    }
}