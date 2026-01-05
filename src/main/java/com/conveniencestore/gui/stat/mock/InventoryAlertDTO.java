package com.conveniencestore.gui.stat.mock;

public class InventoryAlertDTO {
    public String productCode;
    public String productName;
    public int quantity;

    public InventoryAlertDTO(String code, String name, int qty) {
        this.productCode = code;
        this.productName = name;
        this.quantity = qty;
    }
}
