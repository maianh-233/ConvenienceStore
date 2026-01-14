package com.conveniencestore.constant;

/**
 * Enum thể hiện các phương thức thanh toán
 * Dùng để tránh viết trực tiếp String "cash", "card", "momo", "vnpay", ...
 */
public enum PaymentMethod {
	CASH("Tiền mặt"),
	CARD("Thẻ ngân hàng"),
	MOMO("Ví Momo"),
	VNPAY("Ví VNPay"),
	ZALO_PAY("ZaloPay"),
	BANK_TRANSFER("Chuyển khoản ngân hàng");

	private final String displayName;

	PaymentMethod(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Lấy tên hiển thị phương thức thanh toán
	 */
	public String getDisplayName() {
		return displayName;
	}
}
