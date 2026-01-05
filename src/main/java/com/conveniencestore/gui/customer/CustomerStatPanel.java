package com.conveniencestore.gui.customer;

import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.StatCard;
import com.conveniencestore.gui.utils.StatTheme;

public class CustomerStatPanel extends JPanel {

    private StatCard cardActiveCustomer;
    private StatCard cardVIPCustomer;
    private StatCard cardREGULARCustomer;
    private StatCard cardPREMIUMCustomer;
    public CustomerStatPanel() {
        setLayout(new GridLayout(1, 2, 15, 0));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       
        Icon iconcardActiveCustomer = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                );
        Icon iconcardVIPCustomer = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                );
        Icon iconcardREGULARCustomer = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                );
        Icon iconcardPREMIUMCustomer = ImageUtil.scaleIcon(
                        new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18
                );

        cardActiveCustomer = new StatCard(
                "Khách hàng còn hoạt động",
                "0",
                iconcardActiveCustomer 
        );

        cardVIPCustomer = new StatCard(
                "Khách hàng VIP",
                "0",
                iconcardVIPCustomer
        );

        cardREGULARCustomer = new StatCard(
                "Khách hàng REGULAR",
                "0",
                iconcardREGULARCustomer
        );

        cardPREMIUMCustomer = new StatCard(
                "Khách hàng PREMIUM",
                "0",
                iconcardPREMIUMCustomer
        );

        // Tuỳ chỉnh màu icon nền cho dễ phân biệt
        cardActiveCustomer.setBackground(StatTheme.CARD_BG);
        cardVIPCustomer.setBackground(StatTheme.CARD_BG);
        cardREGULARCustomer.setBackground(StatTheme.CARD_BG);
        cardPREMIUMCustomer.setBackground(StatTheme.CARD_BG);


        add(cardActiveCustomer);
        add(cardVIPCustomer);
        add(cardREGULARCustomer);
        add(cardPREMIUMCustomer);
    }

    /* ================= API cho PanelInventory ================= */

    public void setcardActiveCustomer(int value) {
        cardActiveCustomer.setValue(String.valueOf(value));
    }

    public void setcardVIPCustomer(int value) {
        cardVIPCustomer.setValue(String.valueOf(value));
    }

    public void setcardREGULARCustomer(int value) {
        cardREGULARCustomer.setValue(String.valueOf(value));
    }

    public void setcardPREMIUMCustomer(int value) {
        cardPREMIUMCustomer.setValue(String.valueOf(value));
    }

    private URL getIconUrl(String path){
         return getClass().getResource(path);
       
    }

}