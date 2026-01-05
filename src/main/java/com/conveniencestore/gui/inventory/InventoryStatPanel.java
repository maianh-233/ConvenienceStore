package com.conveniencestore.gui.inventory;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import com.conveniencestore.gui.utils.StatCard;
import com.conveniencestore.gui.utils.StatTheme;
import com.conveniencestore.gui.utils.ImageUtil;

public class InventoryStatPanel extends JPanel {

    private StatCard cardInStock;
    private StatCard cardOutOfStock;

    public InventoryStatPanel() {
        setLayout(new GridLayout(1, 2, 15, 0));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Icon (bạn đổi path theo project)
        Icon iconInStock = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                );
        Icon iconOutStock = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                );

        cardInStock = new StatCard(
                "Sản phẩm còn hàng",
                "0",
                iconInStock
        );

        cardOutOfStock = new StatCard(
                "Sản phẩm hết hàng",
                "0",
                iconOutStock
        );

        // Tuỳ chỉnh màu icon nền cho dễ phân biệt
        cardInStock.setBackground(StatTheme.CARD_BG);
        cardOutOfStock.setBackground(StatTheme.CARD_BG);

        add(cardInStock);
        add(cardOutOfStock);
    }

    /* ================= API cho PanelInventory ================= */

    public void setInStock(int value) {
        cardInStock.setValue(String.valueOf(value));
    }

    public void setOutOfStock(int value) {
        cardOutOfStock.setValue(String.valueOf(value));
    }

    private URL getIconUrl(String path){
         return getClass().getResource(path);
       
    }

}