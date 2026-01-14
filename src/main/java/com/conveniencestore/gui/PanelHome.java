package com.conveniencestore.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.conveniencestore.gui.utils.AppColor;
import com.conveniencestore.gui.utils.ImageUtil;

public class PanelHome extends JPanel {

    private JLabel lblLogo;
    private JLabel lblText;

    private ImageIcon originalLogo;

    private final String fullText = "Chào mừng bạn đến với hệ thống quản lí cửa hàng FreshMart";
    private int charIndex = 0;

    public PanelHome() {
        setLayout(new BorderLayout());
        setBackground(AppColor.CONTENT_BG);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // ================= LOGO =================
        lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        originalLogo = new ImageIcon(
                getClass().getResource("/icon/logo.png"));
        lblLogo.setIcon(originalLogo);

        // ================= TEXT =================
        lblText = new JLabel("");
        lblText.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblText.setForeground(AppColor.PRIMARY);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(lblLogo);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        centerPanel.add(lblText);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);

        startTypingEffect();

        // Resize logo theo panel
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeLogo();
            }
        });
    }

    // ================= TYPING EFFECT =================
    private void startTypingEffect() {
        Timer timer = new Timer(60, (ActionEvent e) -> {
            if (charIndex < fullText.length()) {
                lblText.setText(fullText.substring(0, charIndex + 1));
                charIndex++;
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    // ================= RESIZE LOGO (ImageUtil) =================
    private void resizeLogo() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (panelWidth <= 0 || panelHeight <= 0)
            return;

        int targetWidth = (int) (Math.min(panelWidth, panelHeight) / 2);

        ImageIcon scaled = ImageUtil.scaleIconKeepRatioWidth(
                originalLogo,
                targetWidth);
        lblLogo.setIcon(scaled);
    }
}