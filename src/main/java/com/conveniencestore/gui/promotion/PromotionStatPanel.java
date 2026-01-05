package com.conveniencestore.gui.promotion;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import com.conveniencestore.gui.utils.StatCard;
import com.conveniencestore.gui.utils.StatTheme;
import com.conveniencestore.gui.utils.ImageUtil;

public class PromotionStatPanel extends JPanel {

    private StatCard cardActivePromotion;
    private StatCard cardInActivePromotion;

    public  PromotionStatPanel () {
        setLayout(new GridLayout(1, 2, 15, 0));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Icon (bạn đổi path theo project)
        Icon iconcardActivePromotion = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/active.png")), 20, 20
                );
        Icon iconcardInActivePromotion = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/unactive.png")), 20, 20
                );

        cardActivePromotion = new StatCard(
                "Khuyến mãi đang hoạt động",
                "0",
                iconcardActivePromotion
        );

        cardInActivePromotion = new StatCard(
                "Khuyến mãi không hoạt động",
                "0",
                iconcardInActivePromotion   
        );

        // Tuỳ chỉnh màu icon nền cho dễ phân biệt
        cardActivePromotion.setBackground(StatTheme.CARD_BG);
        cardInActivePromotion.setBackground(StatTheme.CARD_BG);

        add(cardActivePromotion);
        add(cardInActivePromotion);
    }

    /* ================= API cho PanelInventory ================= */

    public void setcardActivePromotion(int value) {
        cardActivePromotion.setValue(String.valueOf(value));
    }

    public void setcardInActivePromotion(int value) {
        cardInActivePromotion.setValue(String.valueOf(value));
    }

    private URL getIconUrl(String path){
         return getClass().getResource(path);
       
    }   
}

