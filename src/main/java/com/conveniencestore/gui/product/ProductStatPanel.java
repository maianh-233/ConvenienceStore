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

    public  ProductStatPanel () {
        setLayout(new GridLayout(1, 2, 15, 0));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Icon (bạn đổi path theo project)
        Icon iconcardActiveProduct = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/active.png")), 20, 20
                );
        Icon iconcardInActiveProduct = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/unactive.png")), 20, 20
                );

        cardActiveProduct = new StatCard(
                "Sản phẩm còn bán",
                "0",
                iconcardActiveProduct
        );

        cardInActiveProduct = new StatCard(
                "Sản phẩm bị khóa",
                "0",
                iconcardInActiveProduct
        );

        // Tuỳ chỉnh màu icon nền cho dễ phân biệt
        cardActiveProduct.setBackground(StatTheme.CARD_BG);
        cardInActiveProduct.setBackground(StatTheme.CARD_BG);

        add(cardActiveProduct);
        add(cardInActiveProduct);
    }

    /* ================= API cho PanelInventory ================= */

    public void setcardActiveProduct(int value) {
        cardActiveProduct.setValue(String.valueOf(value));
    }

    public void setcardInActiveProduct(int value) {
        cardInActiveProduct.setValue(String.valueOf(value));
    }

    private URL getIconUrl(String path){
         return getClass().getResource(path);
       
    }

}