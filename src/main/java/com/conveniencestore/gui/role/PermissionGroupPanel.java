package com.conveniencestore.gui.role;

import javax.swing.*;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

import com.conveniencestore.constant.PermissionAction;

class PermissionGroupPanel extends JPanel {

    private final String groupName;
    private final Map<PermissionAction, JCheckBox> checkBoxes = new EnumMap<>(PermissionAction.class);

    public PermissionGroupPanel(String groupName, Color primaryColor) {
        this.groupName = groupName;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        setBackground(Color.WHITE);

        // Title
        JLabel lblTitle = new JLabel(groupName);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(primaryColor);
        add(lblTitle, BorderLayout.NORTH);

        // Checkbox row
        JPanel actionPanel = new JPanel(new GridLayout(1, PermissionAction.values().length, 12, 0));
        actionPanel.setBackground(Color.WHITE);

        for (PermissionAction action : PermissionAction.values()) {
            JCheckBox cb = new JCheckBox(action.getDisplayName());
            cb.setBackground(Color.WHITE);
            cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            checkBoxes.put(action, cb);
            actionPanel.add(cb);
        }

        add(actionPanel, BorderLayout.CENTER);
    }

    public void setEditable(boolean editable) {
        checkBoxes.values().forEach(cb -> cb.setEnabled(editable));
    }
}
