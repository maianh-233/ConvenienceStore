package com.conveniencestore.gui.stat.mock;

import java.util.*;

public class StatMockDAO {

    public static StatDTO getDashboardData() {
        StatDTO dto = new StatDTO();

        dto.totalOrders = 124;
        dto.totalCustomers = 98;
        dto.soldItems = 542;
        dto.revenueToday = 12_500_000;
        dto.activeEmployees = 6;


        dto.revenueByDay = new LinkedHashMap<>();
        dto.revenueByDay.put("T2", 5_000_000L);
        dto.revenueByDay.put("T3", 7_200_000L);
        dto.revenueByDay.put("T4", 6_800_000L);
        dto.revenueByDay.put("T5", 9_100_000L);
        dto.revenueByDay.put("T6", 12_500_000L);

        dto.topProducts = new LinkedHashMap<>();
        dto.topProducts.put("Mì Hảo Hảo", 320);
        dto.topProducts.put("Coca", 280);
        dto.topProducts.put("Snack", 250);
        dto.topProducts.put("Nước suối", 200);

        dto.lowInventory = List.of(
            new InventoryAlertDTO("SP001", "Mì Hảo Hảo", 3),
            new InventoryAlertDTO("SP015", "Nước ngọt", 0)
        );

        dto.auditLogs = List.of(
            "NV01 tạo hóa đơn #HD123",
            "NV02 nhập kho từ NCC Pepsi",
            "NV03 áp dụng KM TẾT2026"
        );

        return dto;
    }
}
