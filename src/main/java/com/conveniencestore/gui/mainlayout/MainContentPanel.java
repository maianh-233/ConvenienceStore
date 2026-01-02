package com.conveniencestore.gui.mainlayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.conveniencestore.gui.utils.AppColor;
public class MainContentPanel extends JPanel {

    private CardLayout cardLayout = new CardLayout();

    public MainContentPanel() {
        setLayout(cardLayout);
        setBackground(AppColor.CONTENT_BG);

        // add(new PanelHome(), "HOME");
        // add(new PanelOrder(), "ORDER");
        // add(new PanelCustomer(), "CUSTOMER");
        // add(new PanelEmployee(), "EMPLOYEE");
        // add(new PanelSupplier(), "SUPPLIER");
        // add(new PanelUnit(), "UNIT");
        // add(new PanelCategory(), "CATEGORY");
        // add(new PanelProduct(), "PRODUCT");
        // add(new PanelInventory(), "INVENTORY");
        // add(new PanelImport(), "IMPORT");
        // add(new PanelThongKe(), "STAT");
    }

    public void showPage(String name) {
        cardLayout.show(this, name);
    }

}

