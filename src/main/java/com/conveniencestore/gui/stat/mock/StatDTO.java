package com.conveniencestore.gui.stat.mock;

import java.util.List;
import java.util.Map;

public class StatDTO {

    public int totalOrders;
    public int totalCustomers;
    public int soldItems;
    public long revenueToday;
    public int activeEmployees;


    public Map<String, Long> revenueByDay;
    public Map<String, Integer> topProducts;
    public List<InventoryAlertDTO> lowInventory;
    public List<String> auditLogs;
}
