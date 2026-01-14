package com.conveniencestore.gui.product;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import com.conveniencestore.gui.utils.StatCard;
import com.conveniencestore.gui.utils.StatTheme;
import com.conveniencestore.gui.utils.ImageUtil;

public class ProductStatPanel extends JPanel {

    private StatCard cardActiveProduct;
    private StatCard cardInActiveProduct;
    private StatCard cardInStock;
    private StatCard cardOutOfStock;

    public ProductStatPanel() {
        setLayout(new GridLayout(1, 4, 15, 0));

        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Icon iconActive = ImageUtil.scaleIcon(
                new ImageIcon(getIconUrl("/icon/active.png")), 20, 20);
        Icon iconInactive = ImageUtil.scaleIcon(
                new ImageIcon(getIconUrl("/icon/unactive.png")), 20, 20);

        // ===== PRODUCT STATUS =====
        cardActiveProduct = new StatCard(
                "Sản phẩm còn bán",
                "0",
                iconActive);

        cardInActiveProduct = new StatCard(
                "Sản phẩm bị khóa",
                "0",
                iconInactive);

        // ===== INVENTORY STATUS =====
        cardInStock = new StatCard(
                "Sản phẩm còn hàng",
                "0",
                iconActive);

        cardOutOfStock = new StatCard(
                "Sản phẩm hết hàng",
                "0",
                iconInactive);

        // Theme đồng bộ
        cardActiveProduct.setBackground(StatTheme.CARD_BG);
        cardInActiveProduct.setBackground(StatTheme.CARD_BG);
        cardInStock.setBackground(StatTheme.CARD_BG);
        cardOutOfStock.setBackground(StatTheme.CARD_BG);

        add(cardActiveProduct);
        add(cardInActiveProduct);
        add(cardInStock);
        add(cardOutOfStock);
    }

    /* ================= API ================= */

    public void setActiveProduct(int value) {
        cardActiveProduct.setValue(String.valueOf(value));
    }

    public void setInActiveProduct(int value) {
        cardInActiveProduct.setValue(String.valueOf(value));
    }

    public void setInStock(int value) {
        cardInStock.setValue(String.valueOf(value));
    }

    public void setOutOfStock(int value) {
        cardOutOfStock.setValue(String.valueOf(value));
    }

    private URL getIconUrl(String path) {
        return getClass().getResource(path);
    }
}
