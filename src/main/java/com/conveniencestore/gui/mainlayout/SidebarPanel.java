package com.conveniencestore.gui.mainlayout;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.conveniencestore.gui.utils.AppColor;

public class SidebarPanel extends JPanel {
    private SidebarListener listener;
    private final List<SidebarButton> buttons = new ArrayList<>();
    private boolean collapsed = false;

    public SidebarPanel() {
        setBackground(AppColor.SIDEBAR_BG);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setPreferredSize(new Dimension(150, 0));
        setMinimumSize(new Dimension(70, 0));

        add(Box.createVerticalStrut(10));

        addMenu("/icon/Home.png", "Home", "HOME");
        addMenu("/icon/orders.png", "Sales", "ORDER");
        addMenu("/icon/customer.png", "Customer", "CUSTOMER");
        addMenu("/icon/employee.png", "Employee", "EMPLOYEE");
        addMenu("/icon/supplier.png", "Supplier", "SUPPLIER");
        addMenu("/icon/units.png", "Unit", "UNIT");
        addMenu("/icon/category.png", "Category", "CATEGORY");
        addMenu("/icon/products.png", "Product", "PRODUCT");
        addMenu("/icon/imports.png", "Import", "IMPORT");
        addMenu("/icon/export.png", "Export", "EXPORT");
        addMenu("/icon/promotion.png", "Promotion", "PROMOTION");
        addMenu("/icon/stat.png", "Statistics", "STAT");
        addMenu("/icon/role.png", "Role", "ROLE");
        addMenu("/icon/user.png", "Information", "INFORMATION");
        add(Box.createVerticalGlue());
    }

    public void setSidebarListener(SidebarListener listener) {
        this.listener = listener;
    }

    private void addMenu(String icon, String text, String key) {
        SidebarButton btn = new SidebarButton(icon, text);

        btn.addActionListener(e -> {
            if (listener != null) {
                listener.onMenuSelected(key);
            }
            setActive(btn);
        });

        buttons.add(btn);
        add(btn);
    }

    private void setActive(SidebarButton activeBtn) {
        for (SidebarButton btn : buttons) {
            btn.setBackground(AppColor.SIDEBAR_BG);
        }
        activeBtn.setBackground(AppColor.PRIMARY_DARK);
    }

    public void collapse(boolean collapse) {
        if (this.collapsed == collapse)
            return;

        this.collapsed = collapse;

        setPreferredSize(
                collapse ? new Dimension(70, getHeight())
                        : new Dimension(150, getHeight()));

        for (SidebarButton btn : buttons) {
            btn.showText(!collapse);
        }

        revalidate();
        repaint();
    }

}
