package com.conveniencestore.gui.mainlayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.conveniencestore.gui.PanelCategory;
import com.conveniencestore.gui.PanelCustomer;
import com.conveniencestore.gui.PanelEmployee;
import com.conveniencestore.gui.PanelHome;
import com.conveniencestore.gui.PanelImport;
import com.conveniencestore.gui.PanelInformation;
import com.conveniencestore.gui.PanelInventory;
import com.conveniencestore.gui.PanelOrder;
import com.conveniencestore.gui.PanelProduct;
import com.conveniencestore.gui.PanelPromotion;
import com.conveniencestore.gui.PanelRole;
import com.conveniencestore.gui.PanelStat;
import com.conveniencestore.gui.PanelSupplier;
import com.conveniencestore.gui.PanelUnit;
import com.conveniencestore.gui.utils.AppColor;
public class MainContentPanel extends JPanel {

    private CardLayout cardLayout = new CardLayout();
    

    public MainContentPanel(JFrame parentFrame) {
        setLayout(cardLayout);
        setBackground(AppColor.CONTENT_BG);

        add(new PanelHome(), "HOME");
        add(new PanelOrder(), "ORDER");
        add(new PanelCustomer(), "CUSTOMER");
        add(new PanelEmployee(), "EMPLOYEE");
        add(new PanelSupplier(parentFrame), "SUPPLIER");
        add(new PanelUnit(parentFrame), "UNIT");
        add(new PanelCategory(parentFrame), "CATEGORY");
        add(new PanelProduct(), "PRODUCT");
        add(new PanelInventory(), "INVENTORY");
        add(new PanelImport(), "IMPORT");
        add(new PanelPromotion(parentFrame), "PROMOTION");
        add(new PanelStat(), "STAT");
        add(new PanelRole(), "ROLE");
        add(new PanelInformation(), "INFORMATION");
    }

    public void showPage(String name) {
        cardLayout.show(this, name);
    }

}

