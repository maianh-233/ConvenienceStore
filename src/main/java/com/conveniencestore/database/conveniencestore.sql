-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 30, 2026 lúc 06:07 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `conveniencestore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL COMMENT 'ID danh mục, tự tăng',
  `name` varchar(100) NOT NULL COMMENT 'Tên danh mục, duy nhất',
  `description` varchar(255) DEFAULT NULL COMMENT 'Mô tả danh mục (tùy chọn)',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật',
  `is_deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu các danh mục sản phẩm';

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `created_at`, `updated_at`, `is_deleted`) VALUES
(1, 'Nước giải khát', 'Các loại nước ngọt, nước suối, trà đóng chai', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(2, 'Bánh kẹo', 'Bánh, kẹo, chocolate, snack', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(3, 'Mì ăn liền', 'Mì gói, mì ly, phở ăn liền', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(4, 'Thực phẩm đông lạnh', 'Cá viên, xúc xích, đồ đông lạnh', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(5, 'Sữa & sản phẩm từ sữa', 'Sữa tươi, sữa chua, phô mai', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(6, 'Cà phê & Trà', 'Cà phê gói, trà túi lọc, trà hòa tan', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(7, 'Đồ ăn nhanh', 'Hamburger, bánh bao, pizza mini', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(8, 'Gia vị', 'Muối, đường, nước mắm, nước tương', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(9, 'Đồ hộp', 'Cá hộp, thịt hộp, rau củ đóng hộp', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(10, 'Thực phẩm khô', 'Gạo, đậu, mì khô, miến', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(11, 'Rau củ quả', 'Rau xanh, củ quả tươi', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(12, 'Trái cây', 'Trái cây tươi, trái cây đóng hộp', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(13, 'Đồ dùng cá nhân', 'Kem đánh răng, dầu gội, sữa tắm', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(14, 'Văn phòng phẩm', 'Bút, vở, giấy, pin', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(15, 'Hóa mỹ phẩm', 'Nước rửa chén, bột giặt, lau sàn', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(16, 'Bia & đồ uống có cồn', 'Bia lon, bia chai, rượu nhẹ', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(17, 'Đồ ăn vặt', 'Khô gà, khô bò, snack cay', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(18, 'Thực phẩm chay', 'Mì chay, đồ chay đóng gói', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(19, 'Đồ dùng gia đình', 'Ly, chén, dao kéo, hộp nhựa', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(20, 'Thẻ cào & dịch vụ', 'Thẻ điện thoại, thẻ game, thanh toán', '2026-01-22 10:30:00', '2026-01-30 23:57:30', 1),
(21, 'Bánh mì & bánh ngọt', 'Bánh mì, bánh ngọt, bánh kem mini', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(22, 'Thực phẩm chế biến sẵn', 'Cơm hộp, mì trộn, thức ăn sẵn', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(23, 'Đồ uống pha sẵn', 'Trà sữa, cà phê pha sẵn', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(24, 'Kem & tráng miệng lạnh', 'Kem cây, kem hộp, tráng miệng đông lạnh', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(25, 'Đồ ăn sáng', 'Bánh bao, xôi, cháo gói', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(26, 'Đồ ăn tối', 'Món ăn nhanh dùng cho buổi tối', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(27, 'Thực phẩm nhập khẩu', 'Đồ ăn, thức uống nhập khẩu', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(28, 'Đồ ăn cho trẻ em', 'Bánh kẹo, sữa, đồ ăn cho bé', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(29, 'Đồ ăn cho người ăn kiêng', 'Ít đường, ít béo, eat clean', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(30, 'Thực phẩm organic', 'Thực phẩm hữu cơ, an toàn', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(31, 'Đồ uống thể thao', 'Nước bù khoáng, nước điện giải', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(32, 'Đồ ăn vặt cay', 'Snack cay, mì cay', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(33, 'Đồ ăn Hàn Quốc', 'Mì Hàn, bánh gạo, snack Hàn', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(34, 'Đồ ăn Nhật Bản', 'Mì Nhật, snack Nhật', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(35, 'Đồ ăn Thái Lan', 'Snack Thái, mì Thái', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(36, 'Thực phẩm theo mùa', 'Sản phẩm bán theo mùa', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(37, 'Combo khuyến mãi', 'Combo sản phẩm ưu đãi', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(38, 'Sản phẩm mới', 'Sản phẩm mới ra mắt', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(39, 'Sản phẩm bán chạy', 'Top sản phẩm bán chạy', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1),
(40, 'Hàng thanh lý', 'Sản phẩm giảm giá, xả kho', '2026-01-22 10:40:00', '2026-01-30 23:57:30', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL COMMENT 'ID khách hàng, tự tăng',
  `fullName` varchar(150) NOT NULL COMMENT 'Họ và tên khách hàng',
  `dateOfBirth` date DEFAULT NULL COMMENT 'Ngày sinh',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email liên hệ (duy nhất)',
  `phone` varchar(50) DEFAULT NULL COMMENT 'Số điện thoại (duy nhất)',
  `address` varchar(500) DEFAULT NULL COMMENT 'Địa chỉ đầy đủ',
  `gender` int(11) NOT NULL,
  `tier` enum('REGULAR','VIP','PREMIUM') DEFAULT 'REGULAR' COMMENT 'Hạng khách: REGULAR, VIP, PREMIUM',
  `points` int(11) DEFAULT 0 COMMENT 'Điểm tích lũy của khách hàng',
  `password` varchar(255) DEFAULT NULL COMMENT 'Mật khẩu (nên lưu hashed)',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm thêm khách hàng',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật gần nhất',
  `is_deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin khách hàng';

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`id`, `fullName`, `dateOfBirth`, `email`, `phone`, `address`, `gender`, `tier`, `points`, `password`, `created_at`, `updated_at`, `is_deleted`) VALUES
(1, 'Nguyễn Văn An', '1995-03-12', 'an.nguyen@gmail.com', '0901000001', 'Q1, TP.HCM', 0, 'REGULAR', 120, NULL, '2025-01-01 00:00:00', '2026-01-30 23:57:55', 1),
(2, 'Trần Thị Bình', '1998-07-22', 'binh.tran@gmail.com', '0901000002', 'Q3, TP.HCM', 0, 'REGULAR', 80, NULL, '2025-01-02 00:00:00', '2026-01-30 23:57:55', 1),
(3, 'Lê Văn Cường', '1990-11-05', 'cuong.le@gmail.com', '0901000003', 'Q5, TP.HCM', 0, 'VIP', 1450, NULL, '2025-01-03 00:00:00', '2026-01-30 23:57:55', 1),
(4, 'Phạm Thị Dung', '2000-02-18', 'dung.pham@gmail.com', '0901000004', 'Q7, TP.HCM', 0, 'REGULAR', 60, NULL, '2025-01-04 00:00:00', '2026-01-30 23:57:55', 1),
(5, 'Hoàng Văn Em', '1988-09-30', 'em.hoang@gmail.com', '0901000005', 'Q10, TP.HCM', 0, 'VIP', 2300, NULL, '2025-01-05 00:00:00', '2026-01-30 23:57:55', 1),
(6, 'Võ Thị Hạnh', '1997-05-14', 'hanh.vo@gmail.com', '0901000006', 'Thủ Đức, TP.HCM', 0, 'REGULAR', 200, NULL, '2025-01-06 00:00:00', '2026-01-30 23:57:55', 1),
(7, 'Đặng Văn Khải', '1993-12-01', 'khai.dang@gmail.com', '0901000007', 'Bình Thạnh, TP.HCM', 0, 'VIP', 1800, NULL, '2025-01-07 00:00:00', '2026-01-30 23:57:55', 1),
(8, 'Nguyễn Thị Lan', '2001-08-09', 'lan.nguyen@gmail.com', '0901000008', 'Gò Vấp, TP.HCM', 0, 'REGULAR', 90, NULL, '2025-01-08 00:00:00', '2026-01-30 23:57:55', 1),
(9, 'Bùi Văn Minh', '1985-06-25', 'minh.bui@gmail.com', '0901000009', 'Q12, TP.HCM', 0, 'PREMIUM', 5200, NULL, '2025-01-09 00:00:00', '2026-01-30 23:57:55', 1),
(10, 'Trịnh Thị Ngọc', '1999-10-10', 'ngoc.trinh@gmail.com', '0901000010', 'Q4, TP.HCM', 0, 'REGULAR', 150, NULL, '2025-01-10 00:00:00', '2026-01-30 23:57:55', 1),
(11, 'Phan Văn Phúc', '1992-01-17', 'phuc.phan@gmail.com', '0901000011', 'Q6, TP.HCM', 0, 'VIP', 2100, NULL, '2025-01-11 00:00:00', '2026-01-30 23:57:55', 1),
(12, 'Ngô Thị Quỳnh', '2002-04-02', 'quynh.ngo@gmail.com', '0901000012', 'Q8, TP.HCM', 0, 'REGULAR', 70, NULL, '2025-01-12 00:00:00', '2026-01-30 23:57:55', 1),
(13, 'Đỗ Văn Sơn', '1987-09-19', 'son.do@gmail.com', '0901000013', 'Q11, TP.HCM', 0, 'VIP', 1950, NULL, '2025-01-13 00:00:00', '2026-01-30 23:57:55', 1),
(14, 'Lý Thị Thu', '1996-06-06', 'thu.ly@gmail.com', '0901000014', 'Tân Bình, TP.HCM', 0, 'REGULAR', 300, NULL, '2025-01-14 00:00:00', '2026-01-30 23:57:55', 1),
(15, 'Mai Văn Tín', '1984-12-28', 'tin.mai@gmail.com', '0901000015', 'Phú Nhuận, TP.HCM', 0, 'PREMIUM', 6100, NULL, '2025-01-15 00:00:00', '2026-01-30 23:57:55', 1),
(16, 'Huỳnh Thị Uyên', '2003-03-03', 'uyen.huynh@gmail.com', '0901000016', 'Q9, TP.HCM', 0, 'REGULAR', 40, NULL, '2025-01-16 00:00:00', '2026-01-30 23:57:55', 1),
(17, 'Trần Văn Vũ', '1991-07-11', 'vu.tran@gmail.com', '0901000017', 'Q2, TP.HCM', 0, 'VIP', 1750, NULL, '2025-01-17 00:00:00', '2026-01-30 23:57:55', 1),
(18, 'Phạm Thị Xuân', '1994-02-27', 'xuan.pham@gmail.com', '0901000018', 'Bình Tân, TP.HCM', 0, 'REGULAR', 110, NULL, '2025-01-18 00:00:00', '2026-01-30 23:57:55', 1),
(19, 'Lâm Văn Y', '1982-05-08', 'y.lam@gmail.com', '0901000019', 'Q1, TP.HCM', 0, 'PREMIUM', 8300, NULL, '2025-01-19 00:00:00', '2026-01-30 23:57:55', 1),
(20, 'Tạ Thị Zinh', '1997-09-15', 'zinh.ta@gmail.com', '0901000020', 'Q3, TP.HCM', 0, 'REGULAR', 95, NULL, '2025-01-20 00:00:00', '2026-01-30 23:57:55', 1),
(21, 'Đinh Văn Long', '1989-11-23', 'long.dinh@gmail.com', '0901000021', 'Q7, TP.HCM', 0, 'VIP', 2600, NULL, '2025-01-21 00:00:00', '2026-01-30 23:57:55', 1),
(22, 'Nguyễn Thị Mai', '2000-01-09', 'mai.nguyen@gmail.com', '0901000022', 'Q5, TP.HCM', 0, 'REGULAR', 130, NULL, '2025-01-22 00:00:00', '2026-01-30 23:57:55', 1),
(23, 'Phạm Văn Nam', '1995-04-16', 'nam.pham@gmail.com', '0901000023', 'Q10, TP.HCM', 0, 'REGULAR', 180, NULL, '2025-01-23 00:00:00', '2026-01-30 23:57:55', 1),
(24, 'Lê Thị Oanh', '1993-08-30', 'oanh.le@gmail.com', '0901000024', 'Q12, TP.HCM', 0, 'VIP', 1550, NULL, '2025-01-24 00:00:00', '2026-01-30 23:57:55', 1),
(25, 'Vương Văn Phát', '1986-10-04', 'phat.vuong@gmail.com', '0901000025', 'Tân Phú, TP.HCM', 0, 'PREMIUM', 7400, NULL, '2025-01-25 00:00:00', '2026-01-30 23:57:55', 1),
(26, 'Nguyễn Thị Quyên', '1998-06-21', 'quyen.nguyen@gmail.com', '0901000026', 'Q6, TP.HCM', 0, 'REGULAR', 160, NULL, '2025-01-26 00:00:00', '2026-01-30 23:57:55', 1),
(27, 'Trần Văn Hải', '1990-03-14', 'hai.tran@gmail.com', '0901000027', 'Bình Thạnh, TP.HCM', 0, 'VIP', 2900, NULL, '2025-01-27 00:00:00', '2026-01-30 23:57:55', 1),
(28, 'Phan Thị Kim', '2001-12-05', 'kim.phan@gmail.com', '0901000028', 'Gò Vấp, TP.HCM', 0, 'REGULAR', 55, NULL, '2025-01-28 00:00:00', '2026-01-30 23:57:55', 1),
(29, 'Đỗ Văn Quang', '1983-07-07', 'quang.do@gmail.com', '0901000029', 'Q4, TP.HCM', 0, 'PREMIUM', 9100, NULL, '2025-01-29 00:00:00', '2026-01-30 23:57:55', 1),
(30, 'Lưu Thị Thanh', '1996-02-12', 'thanh.luu@gmail.com', '0901000030', 'Q8, TP.HCM', 0, 'REGULAR', 210, NULL, '2025-01-30 00:00:00', '2026-01-30 23:57:55', 1),
(31, 'Nguyễn Văn Hòa', '1994-04-10', 'hoa.nguyen31@gmail.com', '0902000031', 'Q1, TP.HCM', 0, 'REGULAR', 180, NULL, '2025-02-01 00:00:00', '2026-01-30 23:57:55', 1),
(32, 'Trần Thị Hương', '1999-09-21', 'huong.tran32@gmail.com', '0902000032', 'Q3, TP.HCM', 0, 'REGULAR', 95, NULL, '2025-02-02 00:00:00', '2026-01-30 23:57:55', 1),
(33, 'Lê Văn Khoa', '1988-12-12', 'khoa.le33@gmail.com', '0902000033', 'Q5, TP.HCM', 0, 'VIP', 1700, NULL, '2025-02-03 00:00:00', '2026-01-30 23:57:55', 1),
(34, 'Phạm Thị Liên', '2001-06-18', 'lien.pham34@gmail.com', '0902000034', 'Q7, TP.HCM', 0, 'REGULAR', 70, NULL, '2025-02-04 00:00:00', '2026-01-30 23:57:55', 1),
(35, 'Hoàng Văn Lâm', '1985-01-25', 'lam.hoang35@gmail.com', '0902000035', 'Q10, TP.HCM', 0, 'PREMIUM', 5400, NULL, '2025-02-05 00:00:00', '2026-01-30 23:57:55', 1),
(36, 'Võ Thị Mai', '1997-11-03', 'mai.vo36@gmail.com', '0902000036', 'Thủ Đức, TP.HCM', 0, 'REGULAR', 260, NULL, '2025-02-06 00:00:00', '2026-01-30 23:57:55', 1),
(37, 'Đặng Văn Phú', '1992-03-17', 'phu.dang37@gmail.com', '0902000037', 'Bình Thạnh, TP.HCM', 0, 'VIP', 2100, NULL, '2025-02-07 00:00:00', '2026-01-30 23:57:55', 1),
(38, 'Nguyễn Thị Thảo', '2000-08-29', 'thao.nguyen38@gmail.com', '0902000038', 'Gò Vấp, TP.HCM', 0, 'REGULAR', 140, NULL, '2025-02-08 00:00:00', '2026-01-30 23:57:55', 1),
(39, 'Bùi Văn Đức', '1983-05-06', 'duc.bui39@gmail.com', '0902000039', 'Q12, TP.HCM', 0, 'PREMIUM', 6800, NULL, '2025-02-09 00:00:00', '2026-01-30 23:57:55', 1),
(40, 'Trịnh Thị Yến', '1996-10-14', 'yen.trinh40@gmail.com', '0902000040', 'Q4, TP.HCM', 0, 'REGULAR', 220, NULL, '2025-02-10 00:00:00', '2026-01-30 23:57:55', 1),
(41, 'Phan Văn Duy', '1991-02-19', 'duy.phan41@gmail.com', '0902000041', 'Q6, TP.HCM', 0, 'VIP', 1950, NULL, '2025-02-11 00:00:00', '2026-01-30 23:57:55', 1),
(42, 'Ngô Thị Bích', '2002-07-07', 'bich.ngo42@gmail.com', '0902000042', 'Q8, TP.HCM', 0, 'REGULAR', 60, NULL, '2025-02-12 00:00:00', '2026-01-30 23:57:55', 1),
(43, 'Đỗ Văn Hải', '1987-09-01', 'hai.do43@gmail.com', '0902000043', 'Q11, TP.HCM', 0, 'VIP', 2350, NULL, '2025-02-13 00:00:00', '2026-01-30 23:57:55', 1),
(44, 'Lý Thị Ngân', '1998-04-27', 'ngan.ly44@gmail.com', '0902000044', 'Tân Bình, TP.HCM', 0, 'REGULAR', 310, NULL, '2025-02-14 00:00:00', '2026-01-30 23:57:55', 1),
(45, 'Mai Văn Thắng', '1984-12-09', 'thang.mai45@gmail.com', '0902000045', 'Phú Nhuận, TP.HCM', 0, 'PREMIUM', 7600, NULL, '2025-02-15 00:00:00', '2026-01-30 23:57:55', 1),
(46, 'Huỳnh Thị Nga', '2003-01-20', 'nga.huynh46@gmail.com', '0902000046', 'Q9, TP.HCM', 0, 'REGULAR', 50, NULL, '2025-02-16 00:00:00', '2026-01-30 23:57:55', 1),
(47, 'Trần Văn Lợi', '1990-06-11', 'loi.tran47@gmail.com', '0902000047', 'Q2, TP.HCM', 0, 'VIP', 2650, NULL, '2025-02-17 00:00:00', '2026-01-30 23:57:55', 1),
(48, 'Phạm Thị Hà', '1995-03-05', 'ha.pham48@gmail.com', '0902000048', 'Bình Tân, TP.HCM', 0, 'REGULAR', 170, NULL, '2025-02-18 00:00:00', '2026-01-30 23:57:55', 1),
(49, 'Lâm Văn Kiên', '1981-08-22', 'kien.lam49@gmail.com', '0902000049', 'Q1, TP.HCM', 0, 'PREMIUM', 9200, NULL, '2025-02-19 00:00:00', '2026-01-30 23:57:55', 1),
(50, 'Tạ Thị Loan', '1997-10-30', 'loan.ta50@gmail.com', '0902000050', 'Q3, TP.HCM', 0, 'REGULAR', 130, NULL, '2025-02-20 00:00:00', '2026-01-30 23:57:55', 1),
(51, 'Đinh Văn Toàn', '1989-11-15', 'toan.dinh51@gmail.com', '0902000051', 'Q7, TP.HCM', 0, 'VIP', 3100, NULL, '2025-02-21 00:00:00', '2026-01-30 23:57:55', 1),
(52, 'Nguyễn Thị Phương', '2000-02-08', 'phuong.nguyen52@gmail.com', '0902000052', 'Q5, TP.HCM', 0, 'REGULAR', 100, NULL, '2025-02-22 00:00:00', '2026-01-30 23:57:55', 1),
(53, 'Phạm Văn Quý', '1993-05-19', 'quy.pham53@gmail.com', '0902000053', 'Q10, TP.HCM', 0, 'REGULAR', 240, NULL, '2025-02-23 00:00:00', '2026-01-30 23:57:55', 1),
(54, 'Lê Thị Hồng', '1996-09-09', 'hong.le54@gmail.com', '0902000054', 'Q12, TP.HCM', 0, 'VIP', 1500, NULL, '2025-02-24 00:00:00', '2026-01-30 23:57:55', 1),
(55, 'Vương Văn Đức', '1986-07-04', 'duc.vuong55@gmail.com', '0902000055', 'Tân Phú, TP.HCM', 0, 'PREMIUM', 8000, NULL, '2025-02-25 00:00:00', '2026-01-30 23:57:55', 1),
(56, 'Nguyễn Thị Diễm', '1998-06-16', 'diem.nguyen56@gmail.com', '0902000056', 'Q6, TP.HCM', 0, 'REGULAR', 190, NULL, '2025-02-26 00:00:00', '2026-01-30 23:57:55', 1),
(57, 'Trần Văn Phong', '1991-01-13', 'phong.tran57@gmail.com', '0902000057', 'Bình Thạnh, TP.HCM', 0, 'VIP', 2800, NULL, '2025-02-27 00:00:00', '2026-01-30 23:57:55', 1),
(58, 'Phan Thị Trúc', '2002-12-01', 'truc.phan58@gmail.com', '0902000058', 'Gò Vấp, TP.HCM', 0, 'REGULAR', 65, NULL, '2025-02-28 00:00:00', '2026-01-30 23:57:55', 1),
(59, 'Đỗ Văn Thành', '1982-04-26', 'thanh.do59@gmail.com', '0902000059', 'Q4, TP.HCM', 0, 'PREMIUM', 8700, NULL, '2025-03-01 00:00:00', '2026-01-30 23:57:55', 1),
(60, 'Lưu Thị Nhung', '1996-02-17', 'nhung.luu60@gmail.com', '0902000060', 'Q8, TP.HCM', 0, 'REGULAR', 210, NULL, '2025-03-02 00:00:00', '2026-01-30 23:57:55', 1),
(61, 'Nguyễn Văn Bình', '1994-03-28', 'binh.nguyen61@gmail.com', '0902000061', 'Q1, TP.HCM', 0, 'REGULAR', 160, NULL, '2025-03-03 00:00:00', '2026-01-30 23:57:55', 1),
(62, 'Trần Thị Tuyết', '1999-08-06', 'tuyet.tran62@gmail.com', '0902000062', 'Q3, TP.HCM', 0, 'REGULAR', 120, NULL, '2025-03-04 00:00:00', '2026-01-30 23:57:55', 1),
(63, 'Lê Văn Phúc', '1989-10-18', 'phuc.le63@gmail.com', '0902000063', 'Q5, TP.HCM', 0, 'VIP', 1850, NULL, '2025-03-05 00:00:00', '2026-01-30 23:57:55', 1),
(64, 'Phạm Thị Nga', '2001-05-22', 'nga.pham64@gmail.com', '0902000064', 'Q7, TP.HCM', 0, 'REGULAR', 75, NULL, '2025-03-06 00:00:00', '2026-01-30 23:57:55', 1),
(65, 'Hoàng Văn Quân', '1984-09-11', 'quan.hoang65@gmail.com', '0902000065', 'Q10, TP.HCM', 0, 'PREMIUM', 5600, NULL, '2025-03-07 00:00:00', '2026-01-30 23:57:55', 1),
(66, 'Võ Thị Thanh', '1997-11-26', 'thanh.vo66@gmail.com', '0902000066', 'Thủ Đức, TP.HCM', 0, 'REGULAR', 230, NULL, '2025-03-08 00:00:00', '2026-01-30 23:57:55', 1),
(67, 'Đặng Văn Trung', '1992-02-14', 'trung.dang67@gmail.com', '0902000067', 'Bình Thạnh, TP.HCM', 0, 'VIP', 2600, NULL, '2025-03-09 00:00:00', '2026-01-30 23:57:55', 1),
(68, 'Nguyễn Thị Hòa', '2000-07-19', 'hoa.nguyen68@gmail.com', '0902000068', 'Gò Vấp, TP.HCM', 0, 'REGULAR', 140, NULL, '2025-03-10 00:00:00', '2026-01-30 23:57:55', 1),
(69, 'Bùi Văn Tài', '1983-05-02', 'tai.bui69@gmail.com', '0902000069', 'Q12, TP.HCM', 0, 'PREMIUM', 6900, NULL, '2025-03-11 00:00:00', '2026-01-30 23:57:55', 1),
(70, 'Trịnh Thị Mai', '1996-10-08', 'mai.trinh70@gmail.com', '0902000070', 'Q4, TP.HCM', 0, 'REGULAR', 260, NULL, '2025-03-12 00:00:00', '2026-01-30 23:57:55', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `imports`
--

CREATE TABLE `imports` (
  `id` bigint(20) NOT NULL COMMENT 'ID phiếu nhập, tự tăng',
  `import_number` varchar(100) NOT NULL COMMENT 'Mã phiếu nhập, duy nhất',
  `supplier_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tham chiếu nhà cung cấp',
  `staff_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tham chiếu nhân viên nhập hàng',
  `total_amount` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Tổng tiền phiếu nhập',
  `note` varchar(500) DEFAULT NULL COMMENT 'Ghi chú phiếu nhập',
  `is_deleted` int(11) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo phiếu nhập',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật phiếu nhập',
  `status` enum('PENDING','COMPLETED','CANCELLED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng phiếu nhập hàng';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `import_items`
--

CREATE TABLE `import_items` (
  `id` bigint(20) NOT NULL COMMENT 'ID chi tiết nhập',
  `import_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại liên kết với phiếu nhập',
  `product_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại liên kết với sản phẩm',
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT 'Số lượng nhập',
  `unit_price` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Giá nhập 1 sản phẩm',
  `total_price` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Tổng tiền = quantity * unit_price',
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Chi tiết sản phẩm trong phiếu nhập';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `inventory`
--

CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL COMMENT 'ID inventory, tự tăng',
  `product_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại liên kết với bảng products (1 product ↔ 1 inventory)',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT 'Số lượng hiện tại của sản phẩm',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật inventory',
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu tồn kho của từng sản phẩm';

--
-- Đang đổ dữ liệu cho bảng `inventory`
--

INSERT INTO `inventory` (`id`, `product_id`, `quantity`, `updated_at`, `created_at`) VALUES
(1, 34, 192, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(2, 40, 118, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(3, 127, 195, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(4, 131, 37, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(5, 168, 166, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(6, 194, 134, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(7, 195, 163, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(8, 6, 23, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(9, 19, 190, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(10, 41, 107, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(11, 43, 148, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(12, 78, 28, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(13, 88, 66, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(14, 12, 47, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(15, 27, 26, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(16, 65, 173, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(17, 90, 10, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(18, 130, 97, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(19, 184, 63, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(20, 25, 16, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(21, 126, 73, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(22, 11, 116, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(23, 21, 161, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(24, 26, 66, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(25, 29, 28, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(26, 46, 125, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(27, 52, 150, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(28, 108, 172, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(29, 122, 20, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(30, 179, 148, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(31, 18, 97, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(32, 45, 33, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(33, 69, 55, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(34, 161, 164, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(35, 199, 75, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(36, 44, 62, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(37, 58, 77, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(38, 64, 191, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(39, 102, 139, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(40, 148, 114, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(41, 166, 143, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(42, 33, 172, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(43, 94, 41, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(44, 103, 62, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(45, 170, 176, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(46, 171, 112, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(47, 51, 24, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(48, 97, 156, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(49, 106, 125, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(50, 112, 149, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(51, 163, 169, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(52, 182, 196, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(53, 9, 82, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(54, 82, 196, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(55, 98, 151, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(56, 105, 158, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(57, 119, 135, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(58, 177, 194, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(59, 20, 171, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(60, 72, 75, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(61, 74, 41, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(62, 80, 161, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(63, 107, 100, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(64, 111, 197, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(65, 116, 104, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(66, 118, 109, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(67, 128, 33, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(68, 169, 20, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(69, 174, 181, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(70, 180, 72, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(71, 186, 190, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(72, 198, 151, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(73, 32, 174, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(74, 50, 27, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(75, 89, 175, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(76, 125, 20, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(77, 139, 137, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(78, 188, 46, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(79, 24, 191, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(80, 38, 42, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(81, 39, 12, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(82, 79, 115, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(83, 123, 149, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(84, 132, 197, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(85, 156, 148, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(86, 189, 138, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(87, 55, 44, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(88, 67, 182, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(89, 96, 192, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(90, 99, 26, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(91, 104, 114, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(92, 167, 103, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(93, 190, 164, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(94, 22, 118, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(95, 83, 91, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(96, 84, 89, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(97, 93, 163, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(98, 95, 156, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(99, 124, 91, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(100, 150, 170, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(101, 176, 185, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(102, 2, 24, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(103, 3, 130, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(104, 16, 185, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(105, 28, 142, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(106, 100, 147, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(107, 113, 106, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(108, 121, 82, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(109, 59, 82, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(110, 117, 153, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(111, 142, 128, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(112, 143, 172, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(113, 153, 85, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(114, 159, 88, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(115, 17, 178, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(116, 76, 45, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(117, 120, 61, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(118, 134, 162, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(119, 141, 45, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(120, 160, 111, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(121, 175, 30, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(122, 181, 187, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(123, 196, 74, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(124, 49, 180, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(125, 53, 94, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(126, 73, 112, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(127, 115, 77, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(128, 137, 41, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(129, 147, 154, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(130, 155, 66, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(131, 165, 48, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(132, 191, 33, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(133, 4, 11, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(134, 57, 136, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(135, 87, 66, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(136, 91, 104, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(137, 92, 120, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(138, 110, 90, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(139, 114, 78, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(140, 136, 111, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(141, 138, 120, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(142, 162, 68, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(143, 164, 159, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(144, 5, 10, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(145, 8, 136, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(146, 14, 66, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(147, 109, 104, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(148, 135, 122, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(149, 140, 96, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(150, 149, 105, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(151, 183, 38, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(152, 37, 55, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(153, 56, 150, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(154, 62, 197, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(155, 144, 140, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(156, 23, 102, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(157, 129, 78, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(158, 146, 75, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(159, 151, 131, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(160, 173, 40, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(161, 187, 181, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(162, 60, 10, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(163, 61, 70, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(164, 158, 120, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(165, 172, 188, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(166, 178, 189, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(167, 192, 180, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(168, 193, 131, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(169, 200, 108, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(170, 7, 135, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(171, 10, 152, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(172, 36, 156, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(173, 47, 121, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(174, 13, 128, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(175, 68, 78, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(176, 75, 186, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(177, 85, 115, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(178, 48, 196, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(179, 54, 53, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(180, 101, 50, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(181, 133, 84, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(182, 154, 66, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(183, 185, 70, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(184, 1, 141, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(185, 31, 104, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(186, 63, 86, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(187, 66, 108, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(188, 70, 85, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(189, 86, 88, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(190, 145, 176, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(191, 35, 34, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(192, 81, 16, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(193, 15, 161, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(194, 30, 171, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(195, 42, 172, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(196, 71, 145, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(197, 77, 200, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(198, 152, 176, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(199, 157, 78, '2026-01-23 00:05:26', '2026-01-23 00:05:26'),
(200, 197, 46, '2026-01-23 00:05:26', '2026-01-23 00:05:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `inventory_exports`
--

CREATE TABLE `inventory_exports` (
  `id` bigint(20) NOT NULL,
  `code` varchar(30) NOT NULL COMMENT 'Mã phiếu xuất kho (PXK-YYYY-XXXX)',
  `created_by` bigint(20) DEFAULT NULL COMMENT 'ID nhân viên tạo phiếu',
  `type` enum('SALE','DAMAGED','EXPIRED','ADJUSTMENT') NOT NULL,
  `status` enum('SUCCESS','FAILED','CANCELED') NOT NULL,
  `note` varchar(255) DEFAULT NULL COMMENT 'Ghi chú phiếu xuất kho',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `inventory_export_items`
--

CREATE TABLE `inventory_export_items` (
  `id` bigint(20) NOT NULL,
  `export_id` bigint(20) NOT NULL COMMENT 'ID phiếu xuất kho',
  `product_id` bigint(20) NOT NULL COMMENT 'ID sản phẩm',
  `quantity` int(11) NOT NULL COMMENT 'Số lượng xuất (>0)',
  `note` varchar(255) DEFAULT NULL COMMENT 'Ghi chú chi tiết xuất kho',
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL COMMENT 'ID đơn hàng, tự tăng',
  `order_number` varchar(100) NOT NULL COMMENT 'Mã đơn hàng, duy nhất',
  `customer_id` bigint(20) DEFAULT NULL COMMENT 'Khóa ngoại tham chiếu Customer',
  `staff_id` bigint(20) DEFAULT NULL COMMENT 'Khóa ngoại tham chiếu User (nhân viên bán)',
  `status` enum('PENDING','PROCESSING','SHIPPED','DELIVERED','CANCELLED') DEFAULT 'PENDING' COMMENT 'Trạng thái đơn hàng',
  `subtotal` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Tổng tiền trước giảm giá',
  `discount` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Giảm giá',
  `total_amount` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Tổng tiền thanh toán',
  `promotion_id` bigint(20) DEFAULT NULL COMMENT 'Khuyến mãi áp dụng (nếu có)',
  `note` varchar(500) DEFAULT NULL COMMENT 'Ghi chú đơn hàng',
  `shipping_address` varchar(500) DEFAULT NULL COMMENT 'Địa chỉ giao hàng',
  `is_online` int(11) DEFAULT NULL,
  `is_deleted` int(11) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo đơn hàng',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật đơn hàng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu đơn hàng';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL COMMENT 'ID dòng sản phẩm trong order, tự tăng',
  `order_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tham chiếu order',
  `product_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tham chiếu product',
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT 'Số lượng sản phẩm',
  `unit_price` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Giá 1 sản phẩm khi bán',
  `total_price` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Tổng tiền = quantity * unit_price',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo dòng order item'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Chi tiết sản phẩm trong từng đơn hàng';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payments`
--

CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL COMMENT 'ID thanh toán, tự tăng',
  `order_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại liên kết với đơn hàng (1 order ↔ 1 payment)',
  `amount` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Số tiền thanh toán',
  `method` enum('CASH','CARD','MOMO','VNPAY','ZALO_PAY','BANK_TRANSFER') NOT NULL DEFAULT 'CASH' COMMENT 'Hình thức thanh toán',
  `transaction_ref` varchar(255) DEFAULT NULL COMMENT 'Mã giao dịch (nếu có)',
  `status` enum('PENDING','COMPLETED','FAILED','CANCELLED') NOT NULL DEFAULT 'PENDING' COMMENT 'Trạng thái thanh toán',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm thanh toán'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thanh toán của từng đơn hàng';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permissions`
--

CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL COMMENT 'ID quyền, tự tăng',
  `name` varchar(100) NOT NULL COMMENT 'Tên quyền chi tiết, ví dụ: ORDER_CREATE, PRODUCT_EDIT',
  `description` varchar(255) DEFAULT NULL COMMENT 'Mô tả quyền, ví dụ: Cho phép tạo đơn hàng',
  `permission_action` enum('VIEW','CREATE','UPDATE','DELETE','RESTORE') NOT NULL,
  `group_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permission_groups`
--

CREATE TABLE `permission_groups` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL COMMENT 'ID sản phẩm, tự tăng',
  `sku` varchar(100) DEFAULT NULL COMMENT 'Mã sản phẩm, duy nhất (SKU)',
  `product_name` varchar(255) NOT NULL COMMENT 'Tên sản phẩm',
  `category_id` bigint(20) DEFAULT NULL COMMENT 'Khóa ngoại tham chiếu bảng categories',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT 'Khóa ngoại tham chiếu bảng suppliers',
  `unit_id` bigint(20) DEFAULT NULL COMMENT 'Khóa ngoại tham chiếu bảng units',
  `price` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Giá bán sản phẩm',
  `cost` decimal(12,2) DEFAULT NULL COMMENT 'Giá nhập/cost của sản phẩm',
  `description` varchar(500) DEFAULT NULL COMMENT 'Mô tả sản phẩm',
  `is_active` int(11) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo sản phẩm',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật sản phẩm',
  `barcode` varchar(100) DEFAULT NULL,
  `imageUrl` varchar(1024) DEFAULT NULL,
  `img_urlID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin sản phẩm của cửa hàng';

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `sku`, `product_name`, `category_id`, `supplier_id`, `unit_id`, `price`, `cost`, `description`, `is_active`, `created_at`, `updated_at`, `barcode`, `imageUrl`, `img_urlID`) VALUES
(1, 'SKU0001', 'Sản phẩm test 1', 36, 10, 28, 70277.00, 35805.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000001', NULL, NULL),
(2, 'SKU0002', 'Sản phẩm test 2', 35, 19, 16, 77744.00, 16049.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000002', NULL, NULL),
(3, 'SKU0003', 'Sản phẩm test 3', 26, 21, 16, 55211.00, 55316.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000003', NULL, NULL),
(4, 'SKU0004', 'Sản phẩm test 4', 13, 22, 20, 15236.00, 24644.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000004', NULL, NULL),
(5, 'SKU0005', 'Sản phẩm test 5', 24, 27, 21, 77598.00, 42060.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000005', NULL, NULL),
(6, 'SKU0006', 'Sản phẩm test 6', 11, 5, 2, 76021.00, 33964.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000006', NULL, NULL),
(7, 'SKU0007', 'Sản phẩm test 7', 20, 26, 25, 55226.00, 10077.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000007', NULL, NULL),
(8, 'SKU0008', 'Sản phẩm test 8', 33, 25, 21, 95880.00, 43471.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000008', NULL, NULL),
(9, 'SKU0009', 'Sản phẩm test 9', 32, 23, 10, 44085.00, 54272.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000009', NULL, NULL),
(10, 'SKU0010', 'Sản phẩm test 10', 24, 4, 25, 77628.00, 21605.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000010', NULL, NULL),
(11, 'SKU0011', 'Sản phẩm test 11', 9, 5, 5, 38797.00, 13423.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000011', NULL, NULL),
(12, 'SKU0012', 'Sản phẩm test 12', 28, 1, 3, 42078.00, 32986.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000012', NULL, NULL),
(13, 'SKU0013', 'Sản phẩm test 13', 22, 3, 26, 98627.00, 25770.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000013', NULL, NULL),
(14, 'SKU0014', 'Sản phẩm test 14', 37, 15, 21, 90128.00, 28964.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000014', NULL, NULL),
(15, 'SKU0015', 'Sản phẩm test 15', 22, 10, 30, 93468.00, 43176.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000015', NULL, NULL),
(16, 'SKU0016', 'Sản phẩm test 16', 34, 1, 16, 64366.00, 29347.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000016', NULL, NULL),
(17, 'SKU0017', 'Sản phẩm test 17', 17, 24, 18, 67825.00, 28884.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000017', NULL, NULL),
(18, 'SKU0018', 'Sản phẩm test 18', 11, 30, 6, 91214.00, 56273.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000018', NULL, NULL),
(19, 'SKU0019', 'Sản phẩm test 19', 9, 5, 2, 89285.00, 17781.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000019', NULL, NULL),
(20, 'SKU0020', 'Sản phẩm test 20', 18, 16, 11, 21179.00, 36651.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000020', NULL, NULL),
(21, 'SKU0021', 'Sản phẩm test 21', 24, 6, 5, 23253.00, 22892.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000021', NULL, NULL),
(22, 'SKU0022', 'Sản phẩm test 22', 6, 24, 15, 12487.00, 42558.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000022', NULL, NULL),
(23, 'SKU0023', 'Sản phẩm test 23', 19, 7, 23, 98955.00, 43656.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000023', NULL, NULL),
(24, 'SKU0024', 'Sản phẩm test 24', 29, 10, 13, 24559.00, 35306.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000024', NULL, NULL),
(25, 'SKU0025', 'Sản phẩm test 25', 14, 1, 4, 49622.00, 51318.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000025', NULL, NULL),
(26, 'SKU0026', 'Sản phẩm test 26', 5, 27, 5, 14693.00, 47806.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000026', NULL, NULL),
(27, 'SKU0027', 'Sản phẩm test 27', 37, 6, 3, 94128.00, 27111.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000027', NULL, NULL),
(28, 'SKU0028', 'Sản phẩm test 28', 9, 25, 16, 19116.00, 54814.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000028', NULL, NULL),
(29, 'SKU0029', 'Sản phẩm test 29', 20, 16, 5, 32945.00, 46376.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000029', NULL, NULL),
(30, 'SKU0030', 'Sản phẩm test 30', 7, 16, 30, 49283.00, 17912.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000030', NULL, NULL),
(31, 'SKU0031', 'Sản phẩm test 31', 32, 8, 28, 87944.00, 34651.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000031', NULL, NULL),
(32, 'SKU0032', 'Sản phẩm test 32', 7, 6, 12, 42548.00, 40746.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000032', NULL, NULL),
(33, 'SKU0033', 'Sản phẩm test 33', 12, 13, 8, 98078.00, 14156.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000033', NULL, NULL),
(34, 'SKU0034', 'Sản phẩm test 34', 32, 15, 1, 66034.00, 11764.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000034', NULL, NULL),
(35, 'SKU0035', 'Sản phẩm test 35', 25, 23, 29, 57634.00, 45292.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000035', NULL, NULL),
(36, 'SKU0036', 'Sản phẩm test 36', 10, 28, 25, 44833.00, 30298.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000036', NULL, NULL),
(37, 'SKU0037', 'Sản phẩm test 37', 7, 14, 22, 34703.00, 17274.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000037', NULL, NULL),
(38, 'SKU0038', 'Sản phẩm test 38', 9, 13, 13, 83487.00, 50323.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000038', NULL, NULL),
(39, 'SKU0039', 'Sản phẩm test 39', 36, 25, 13, 70931.00, 12640.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000039', NULL, NULL),
(40, 'SKU0040', 'Sản phẩm test 40', 22, 10, 1, 26843.00, 48794.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000040', NULL, NULL),
(41, 'SKU0041', 'Sản phẩm test 41', 25, 18, 2, 58461.00, 33064.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000041', NULL, NULL),
(42, 'SKU0042', 'Sản phẩm test 42', 40, 12, 30, 77243.00, 46137.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000042', NULL, NULL),
(43, 'SKU0043', 'Sản phẩm test 43', 27, 1, 2, 28788.00, 51726.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000043', NULL, NULL),
(44, 'SKU0044', 'Sản phẩm test 44', 34, 17, 7, 42970.00, 18251.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000044', NULL, NULL),
(45, 'SKU0045', 'Sản phẩm test 45', 2, 14, 6, 66514.00, 34158.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000045', NULL, NULL),
(46, 'SKU0046', 'Sản phẩm test 46', 34, 16, 5, 27266.00, 31458.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000046', NULL, NULL),
(47, 'SKU0047', 'Sản phẩm test 47', 35, 27, 25, 45294.00, 33721.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000047', NULL, NULL),
(48, 'SKU0048', 'Sản phẩm test 48', 20, 27, 27, 83442.00, 27771.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000048', NULL, NULL),
(49, 'SKU0049', 'Sản phẩm test 49', 26, 27, 19, 40705.00, 51152.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000049', NULL, NULL),
(50, 'SKU0050', 'Sản phẩm test 50', 16, 10, 12, 93917.00, 34280.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000050', NULL, NULL),
(51, 'SKU0051', 'Sản phẩm test 51', 38, 1, 9, 47352.00, 16764.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000051', NULL, NULL),
(52, 'SKU0052', 'Sản phẩm test 52', 30, 3, 5, 63120.00, 30465.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000052', NULL, NULL),
(53, 'SKU0053', 'Sản phẩm test 53', 24, 15, 19, 75834.00, 45510.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000053', NULL, NULL),
(54, 'SKU0054', 'Sản phẩm test 54', 27, 30, 27, 58240.00, 7443.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000054', NULL, NULL),
(55, 'SKU0055', 'Sản phẩm test 55', 18, 5, 14, 87522.00, 52567.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000055', NULL, NULL),
(56, 'SKU0056', 'Sản phẩm test 56', 39, 4, 22, 32691.00, 10843.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000056', NULL, NULL),
(57, 'SKU0057', 'Sản phẩm test 57', 26, 28, 20, 62329.00, 53095.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000057', NULL, NULL),
(58, 'SKU0058', 'Sản phẩm test 58', 35, 17, 7, 41549.00, 13684.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000058', NULL, NULL),
(59, 'SKU0059', 'Sản phẩm test 59', 25, 21, 17, 79427.00, 15253.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000059', NULL, NULL),
(60, 'SKU0060', 'Sản phẩm test 60', 21, 2, 24, 69078.00, 56003.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000060', NULL, NULL),
(61, 'SKU0061', 'Sản phẩm test 61', 38, 22, 24, 79298.00, 32020.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000061', NULL, NULL),
(62, 'SKU0062', 'Sản phẩm test 62', 8, 14, 22, 29798.00, 54073.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000062', NULL, NULL),
(63, 'SKU0063', 'Sản phẩm test 63', 2, 13, 28, 43184.00, 10970.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000063', NULL, NULL),
(64, 'SKU0064', 'Sản phẩm test 64', 12, 7, 7, 45555.00, 24447.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000064', NULL, NULL),
(65, 'SKU0065', 'Sản phẩm test 65', 23, 23, 3, 21333.00, 27126.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000065', NULL, NULL),
(66, 'SKU0066', 'Sản phẩm test 66', 26, 30, 28, 77182.00, 54212.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000066', NULL, NULL),
(67, 'SKU0067', 'Sản phẩm test 67', 20, 18, 14, 50757.00, 54755.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000067', NULL, NULL),
(68, 'SKU0068', 'Sản phẩm test 68', 17, 7, 26, 61835.00, 23946.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000068', NULL, NULL),
(69, 'SKU0069', 'Sản phẩm test 69', 39, 25, 6, 52868.00, 48243.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000069', NULL, NULL),
(70, 'SKU0070', 'Sản phẩm test 70', 28, 1, 28, 65572.00, 22649.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000070', NULL, NULL),
(71, 'SKU0071', 'Sản phẩm test 71', 29, 19, 30, 12811.00, 17749.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000071', NULL, NULL),
(72, 'SKU0072', 'Sản phẩm test 72', 40, 8, 11, 12366.00, 9285.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000072', NULL, NULL),
(73, 'SKU0073', 'Sản phẩm test 73', 6, 19, 19, 27856.00, 15655.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000073', NULL, NULL),
(74, 'SKU0074', 'Sản phẩm test 74', 11, 25, 11, 32797.00, 17832.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000074', NULL, NULL),
(75, 'SKU0075', 'Sản phẩm test 75', 13, 29, 26, 47291.00, 31169.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000075', NULL, NULL),
(76, 'SKU0076', 'Sản phẩm test 76', 7, 13, 18, 65396.00, 24679.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000076', NULL, NULL),
(77, 'SKU0077', 'Sản phẩm test 77', 37, 17, 30, 27763.00, 11255.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000077', NULL, NULL),
(78, 'SKU0078', 'Sản phẩm test 78', 34, 28, 2, 60200.00, 37652.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000078', NULL, NULL),
(79, 'SKU0079', 'Sản phẩm test 79', 16, 4, 13, 79275.00, 35507.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000079', NULL, NULL),
(80, 'SKU0080', 'Sản phẩm test 80', 22, 30, 11, 83238.00, 56288.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000080', NULL, NULL),
(81, 'SKU0081', 'Sản phẩm test 81', 20, 15, 29, 33134.00, 30307.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000081', NULL, NULL),
(82, 'SKU0082', 'Sản phẩm test 82', 23, 12, 10, 45748.00, 8420.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000082', NULL, NULL),
(83, 'SKU0083', 'Sản phẩm test 83', 39, 21, 15, 47725.00, 39179.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000083', NULL, NULL),
(84, 'SKU0084', 'Sản phẩm test 84', 39, 27, 15, 78481.00, 25990.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000084', NULL, NULL),
(85, 'SKU0085', 'Sản phẩm test 85', 25, 29, 26, 53206.00, 47834.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000085', NULL, NULL),
(86, 'SKU0086', 'Sản phẩm test 86', 26, 23, 28, 28576.00, 23457.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000086', NULL, NULL),
(87, 'SKU0087', 'Sản phẩm test 87', 2, 5, 20, 81822.00, 9489.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000087', NULL, NULL),
(88, 'SKU0088', 'Sản phẩm test 88', 35, 4, 2, 93290.00, 29355.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000088', NULL, NULL),
(89, 'SKU0089', 'Sản phẩm test 89', 19, 29, 12, 20315.00, 26297.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000089', NULL, NULL),
(90, 'SKU0090', 'Sản phẩm test 90', 24, 24, 3, 26507.00, 37924.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000090', NULL, NULL),
(91, 'SKU0091', 'Sản phẩm test 91', 22, 26, 20, 71862.00, 31227.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000091', NULL, NULL),
(92, 'SKU0092', 'Sản phẩm test 92', 15, 11, 20, 35378.00, 27570.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000092', NULL, NULL),
(93, 'SKU0093', 'Sản phẩm test 93', 9, 25, 15, 90969.00, 11019.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000093', NULL, NULL),
(94, 'SKU0094', 'Sản phẩm test 94', 29, 9, 8, 53035.00, 37557.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000094', NULL, NULL),
(95, 'SKU0095', 'Sản phẩm test 95', 25, 9, 15, 71127.00, 51887.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000095', NULL, NULL),
(96, 'SKU0096', 'Sản phẩm test 96', 19, 17, 14, 64836.00, 40413.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000096', NULL, NULL),
(97, 'SKU0097', 'Sản phẩm test 97', 21, 17, 9, 70463.00, 34180.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000097', NULL, NULL),
(98, 'SKU0098', 'Sản phẩm test 98', 29, 27, 10, 88317.00, 29039.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000098', NULL, NULL),
(99, 'SKU0099', 'Sản phẩm test 99', 24, 20, 14, 34986.00, 10077.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000099', NULL, NULL),
(100, 'SKU0100', 'Sản phẩm test 100', 19, 6, 16, 14691.00, 41385.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000100', NULL, NULL),
(101, 'SKU0101', 'Sản phẩm test 101', 12, 11, 27, 47760.00, 27996.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000101', NULL, NULL),
(102, 'SKU0102', 'Sản phẩm test 102', 34, 29, 7, 26074.00, 21229.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000102', NULL, NULL),
(103, 'SKU0103', 'Sản phẩm test 103', 36, 18, 8, 54737.00, 43976.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000103', NULL, NULL),
(104, 'SKU0104', 'Sản phẩm test 104', 9, 25, 14, 81689.00, 39046.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000104', NULL, NULL),
(105, 'SKU0105', 'Sản phẩm test 105', 33, 5, 10, 21068.00, 40360.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000105', NULL, NULL),
(106, 'SKU0106', 'Sản phẩm test 106', 39, 26, 9, 85406.00, 26283.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000106', NULL, NULL),
(107, 'SKU0107', 'Sản phẩm test 107', 17, 28, 11, 95640.00, 43815.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000107', NULL, NULL),
(108, 'SKU0108', 'Sản phẩm test 108', 34, 28, 5, 96150.00, 24296.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000108', NULL, NULL),
(109, 'SKU0109', 'Sản phẩm test 109', 35, 8, 21, 71051.00, 23539.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000109', NULL, NULL),
(110, 'SKU0110', 'Sản phẩm test 110', 25, 4, 20, 96892.00, 50201.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000110', NULL, NULL),
(111, 'SKU0111', 'Sản phẩm test 111', 17, 16, 11, 30800.00, 10442.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000111', NULL, NULL),
(112, 'SKU0112', 'Sản phẩm test 112', 27, 2, 9, 36262.00, 36878.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000112', NULL, NULL),
(113, 'SKU0113', 'Sản phẩm test 113', 5, 24, 16, 32056.00, 41646.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000113', NULL, NULL),
(114, 'SKU0114', 'Sản phẩm test 114', 30, 18, 20, 61096.00, 50725.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000114', NULL, NULL),
(115, 'SKU0115', 'Sản phẩm test 115', 27, 22, 19, 87974.00, 32535.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000115', NULL, NULL),
(116, 'SKU0116', 'Sản phẩm test 116', 39, 8, 11, 96568.00, 47020.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000116', NULL, NULL),
(117, 'SKU0117', 'Sản phẩm test 117', 5, 6, 17, 31300.00, 32770.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000117', NULL, NULL),
(118, 'SKU0118', 'Sản phẩm test 118', 35, 24, 11, 41764.00, 43633.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000118', NULL, NULL),
(119, 'SKU0119', 'Sản phẩm test 119', 25, 25, 10, 16539.00, 28775.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000119', NULL, NULL),
(120, 'SKU0120', 'Sản phẩm test 120', 39, 15, 18, 47464.00, 24356.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000120', NULL, NULL),
(121, 'SKU0121', 'Sản phẩm test 121', 20, 12, 16, 40774.00, 16859.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000121', NULL, NULL),
(122, 'SKU0122', 'Sản phẩm test 122', 39, 7, 5, 27948.00, 32048.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000122', NULL, NULL),
(123, 'SKU0123', 'Sản phẩm test 123', 37, 1, 13, 15528.00, 7814.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000123', NULL, NULL),
(124, 'SKU0124', 'Sản phẩm test 124', 36, 14, 15, 23828.00, 21487.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000124', NULL, NULL),
(125, 'SKU0125', 'Sản phẩm test 125', 40, 3, 12, 74176.00, 27618.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000125', NULL, NULL),
(126, 'SKU0126', 'Sản phẩm test 126', 37, 12, 4, 49204.00, 48979.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000126', NULL, NULL),
(127, 'SKU0127', 'Sản phẩm test 127', 36, 29, 1, 31869.00, 16145.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000127', NULL, NULL),
(128, 'SKU0128', 'Sản phẩm test 128', 8, 12, 11, 60530.00, 46376.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000128', NULL, NULL),
(129, 'SKU0129', 'Sản phẩm test 129', 11, 28, 23, 17836.00, 14802.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000129', NULL, NULL),
(130, 'SKU0130', 'Sản phẩm test 130', 21, 4, 3, 10697.00, 47279.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000130', NULL, NULL),
(131, 'SKU0131', 'Sản phẩm test 131', 1, 19, 1, 35101.00, 23746.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000131', NULL, NULL),
(132, 'SKU0132', 'Sản phẩm test 132', 34, 6, 13, 54869.00, 20041.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000132', NULL, NULL),
(133, 'SKU0133', 'Sản phẩm test 133', 33, 8, 27, 63191.00, 23631.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000133', NULL, NULL),
(134, 'SKU0134', 'Sản phẩm test 134', 36, 14, 18, 64213.00, 18802.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000134', NULL, NULL),
(135, 'SKU0135', 'Sản phẩm test 135', 15, 5, 21, 88655.00, 25065.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000135', NULL, NULL),
(136, 'SKU0136', 'Sản phẩm test 136', 8, 26, 20, 72939.00, 35293.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000136', NULL, NULL),
(137, 'SKU0137', 'Sản phẩm test 137', 30, 29, 19, 21934.00, 49841.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000137', NULL, NULL),
(138, 'SKU0138', 'Sản phẩm test 138', 36, 26, 20, 68720.00, 23026.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000138', NULL, NULL),
(139, 'SKU0139', 'Sản phẩm test 139', 26, 8, 12, 23028.00, 35023.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000139', NULL, NULL),
(140, 'SKU0140', 'Sản phẩm test 140', 15, 5, 21, 97896.00, 47548.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000140', NULL, NULL),
(141, 'SKU0141', 'Sản phẩm test 141', 5, 6, 18, 41284.00, 56201.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000141', NULL, NULL),
(142, 'SKU0142', 'Sản phẩm test 142', 36, 14, 17, 45748.00, 25291.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000142', NULL, NULL),
(143, 'SKU0143', 'Sản phẩm test 143', 26, 3, 17, 47612.00, 30839.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000143', NULL, NULL),
(144, 'SKU0144', 'Sản phẩm test 144', 6, 7, 22, 90960.00, 25052.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000144', NULL, NULL),
(145, 'SKU0145', 'Sản phẩm test 145', 5, 14, 28, 36497.00, 41299.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000145', NULL, NULL),
(146, 'SKU0146', 'Sản phẩm test 146', 22, 21, 23, 69870.00, 12534.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000146', NULL, NULL),
(147, 'SKU0147', 'Sản phẩm test 147', 23, 14, 19, 70925.00, 34846.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000147', NULL, NULL),
(148, 'SKU0148', 'Sản phẩm test 148', 31, 3, 7, 86824.00, 35955.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000148', NULL, NULL),
(149, 'SKU0149', 'Sản phẩm test 149', 14, 29, 21, 61790.00, 49342.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000149', NULL, NULL),
(150, 'SKU0150', 'Sản phẩm test 150', 21, 30, 15, 42360.00, 26193.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000150', NULL, NULL),
(151, 'SKU0151', 'Sản phẩm test 151', 34, 2, 23, 57252.00, 27865.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000151', NULL, NULL),
(152, 'SKU0152', 'Sản phẩm test 152', 21, 10, 30, 14030.00, 19375.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000152', NULL, NULL),
(153, 'SKU0153', 'Sản phẩm test 153', 5, 24, 17, 52223.00, 40596.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000153', NULL, NULL),
(154, 'SKU0154', 'Sản phẩm test 154', 39, 23, 27, 19979.00, 54328.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000154', NULL, NULL),
(155, 'SKU0155', 'Sản phẩm test 155', 17, 5, 19, 59355.00, 53115.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000155', NULL, NULL),
(156, 'SKU0156', 'Sản phẩm test 156', 39, 2, 13, 94150.00, 26846.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000156', NULL, NULL),
(157, 'SKU0157', 'Sản phẩm test 157', 8, 22, 30, 87909.00, 23792.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000157', NULL, NULL),
(158, 'SKU0158', 'Sản phẩm test 158', 4, 13, 24, 68445.00, 53525.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000158', NULL, NULL),
(159, 'SKU0159', 'Sản phẩm test 159', 29, 22, 17, 54856.00, 50934.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000159', NULL, NULL),
(160, 'SKU0160', 'Sản phẩm test 160', 36, 26, 18, 40533.00, 54676.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000160', NULL, NULL),
(161, 'SKU0161', 'Sản phẩm test 161', 30, 27, 6, 37519.00, 54274.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000161', NULL, NULL),
(162, 'SKU0162', 'Sản phẩm test 162', 33, 7, 20, 60866.00, 52039.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000162', NULL, NULL),
(163, 'SKU0163', 'Sản phẩm test 163', 33, 11, 9, 40857.00, 51876.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000163', NULL, NULL),
(164, 'SKU0164', 'Sản phẩm test 164', 19, 19, 20, 43668.00, 55055.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000164', NULL, NULL),
(165, 'SKU0165', 'Sản phẩm test 165', 28, 16, 19, 52786.00, 33627.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000165', NULL, NULL),
(166, 'SKU0166', 'Sản phẩm test 166', 10, 18, 7, 39017.00, 55279.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000166', NULL, NULL),
(167, 'SKU0167', 'Sản phẩm test 167', 35, 13, 14, 13730.00, 49493.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000167', NULL, NULL),
(168, 'SKU0168', 'Sản phẩm test 168', 6, 3, 1, 80633.00, 52868.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000168', NULL, NULL),
(169, 'SKU0169', 'Sản phẩm test 169', 10, 13, 11, 58166.00, 37832.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000169', NULL, NULL),
(170, 'SKU0170', 'Sản phẩm test 170', 20, 17, 8, 71187.00, 38113.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000170', NULL, NULL),
(171, 'SKU0171', 'Sản phẩm test 171', 3, 15, 8, 77482.00, 7666.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000171', NULL, NULL),
(172, 'SKU0172', 'Sản phẩm test 172', 33, 2, 24, 78176.00, 29496.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000172', NULL, NULL),
(173, 'SKU0173', 'Sản phẩm test 173', 40, 17, 23, 21755.00, 27514.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000173', NULL, NULL),
(174, 'SKU0174', 'Sản phẩm test 174', 27, 3, 11, 62690.00, 49745.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000174', NULL, NULL),
(175, 'SKU0175', 'Sản phẩm test 175', 21, 1, 18, 82083.00, 20859.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000175', NULL, NULL),
(176, 'SKU0176', 'Sản phẩm test 176', 40, 3, 15, 19387.00, 12373.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000176', NULL, NULL),
(177, 'SKU0177', 'Sản phẩm test 177', 9, 24, 10, 30593.00, 15358.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000177', NULL, NULL),
(178, 'SKU0178', 'Sản phẩm test 178', 6, 8, 24, 24351.00, 29989.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000178', NULL, NULL),
(179, 'SKU0179', 'Sản phẩm test 179', 33, 22, 5, 64473.00, 35058.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000179', NULL, NULL),
(180, 'SKU0180', 'Sản phẩm test 180', 40, 9, 11, 11582.00, 56862.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000180', NULL, NULL),
(181, 'SKU0181', 'Sản phẩm test 181', 38, 21, 18, 87417.00, 35443.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000181', NULL, NULL),
(182, 'SKU0182', 'Sản phẩm test 182', 11, 19, 9, 55860.00, 44016.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000182', NULL, NULL),
(183, 'SKU0183', 'Sản phẩm test 183', 7, 20, 21, 61180.00, 44169.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000183', NULL, NULL),
(184, 'SKU0184', 'Sản phẩm test 184', 1, 25, 3, 97663.00, 37087.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000184', NULL, NULL),
(185, 'SKU0185', 'Sản phẩm test 185', 4, 19, 27, 56470.00, 54074.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000185', NULL, NULL),
(186, 'SKU0186', 'Sản phẩm test 186', 7, 30, 11, 92396.00, 31307.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000186', NULL, NULL),
(187, 'SKU0187', 'Sản phẩm test 187', 28, 29, 23, 92330.00, 21614.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000187', NULL, NULL),
(188, 'SKU0188', 'Sản phẩm test 188', 29, 22, 12, 84806.00, 56093.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000188', NULL, NULL),
(189, 'SKU0189', 'Sản phẩm test 189', 17, 5, 13, 72708.00, 18286.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000189', NULL, NULL),
(190, 'SKU0190', 'Sản phẩm test 190', 2, 16, 14, 77146.00, 24821.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000190', NULL, NULL),
(191, 'SKU0191', 'Sản phẩm test 191', 22, 20, 19, 23549.00, 51581.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000191', NULL, NULL),
(192, 'SKU0192', 'Sản phẩm test 192', 1, 11, 24, 79006.00, 33512.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000192', NULL, NULL),
(193, 'SKU0193', 'Sản phẩm test 193', 15, 5, 24, 41477.00, 29157.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000193', NULL, NULL),
(194, 'SKU0194', 'Sản phẩm test 194', 7, 16, 1, 61140.00, 46741.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000194', NULL, NULL),
(195, 'SKU0195', 'Sản phẩm test 195', 11, 29, 1, 22584.00, 41111.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000195', NULL, NULL),
(196, 'SKU0196', 'Sản phẩm test 196', 40, 28, 18, 25803.00, 13608.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000196', NULL, NULL),
(197, 'SKU0197', 'Sản phẩm test 197', 6, 9, 30, 11324.00, 15586.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000197', NULL, NULL),
(198, 'SKU0198', 'Sản phẩm test 198', 33, 17, 11, 13960.00, 16521.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000198', NULL, NULL),
(199, 'SKU0199', 'Sản phẩm test 199', 33, 16, 6, 40493.00, 13921.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000199', NULL, NULL),
(200, 'SKU0200', 'Sản phẩm test 200', 28, 29, 24, 15134.00, 52852.00, 'Sản phẩm dùng để test phân trang', 1, '2026-01-23 00:03:54', '2026-01-30 23:56:13', '8930000000200', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotions`
--

CREATE TABLE `promotions` (
  `id` bigint(20) NOT NULL COMMENT 'ID khuyến mãi, tự tăng',
  `code` varchar(50) NOT NULL,
  `name` varchar(150) NOT NULL COMMENT 'Tên khuyến mãi',
  `type` enum('PERCENT','AMOUNT') NOT NULL,
  `value` decimal(12,2) DEFAULT 0.00 COMMENT 'Giá trị khuyến mãi',
  `start_at` datetime DEFAULT NULL COMMENT 'Thời điểm bắt đầu',
  `end_at` datetime DEFAULT NULL COMMENT 'Thời điểm kết thúc',
  `is_active` int(11) NOT NULL,
  `max_uses` int(11) DEFAULT NULL COMMENT 'Số lần tối đa áp dụng khuyến mãi',
  `min_order_amount` decimal(12,2) DEFAULT NULL COMMENT 'Giá trị đơn tối thiểu để áp dụng',
  `note` varchar(500) DEFAULT NULL COMMENT 'Ghi chú thêm',
  `created_at` datetime DEFAULT NULL COMMENT 'Thời điểm tạo',
  `updated_at` datetime DEFAULT NULL COMMENT 'Thời điểm cập nhật',
  `customerTier` enum('REGULAR','VIP','PREMIUM') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `promotions`
--

INSERT INTO `promotions` (`id`, `code`, `name`, `type`, `value`, `start_at`, `end_at`, `is_active`, `max_uses`, `min_order_amount`, `note`, `created_at`, `updated_at`, `customerTier`) VALUES
(1, 'SALE10_OLD', 'Giảm 10% cũ', 'PERCENT', 10.00, '2025-01-01 00:00:00', '2025-01-31 00:00:00', 0, 100, 100000.00, 'Đã hết hạn', '2024-12-20 00:00:00', '2025-02-01 00:00:00', 'REGULAR'),
(2, 'SALE20_TET', 'Tết giảm 20%', 'PERCENT', 20.00, '2025-02-01 00:00:00', '2025-02-10 00:00:00', 0, 200, 200000.00, 'Tết 2025', '2025-01-15 00:00:00', '2025-02-11 00:00:00', 'REGULAR'),
(3, 'AMOUNT50K_OLD', 'Giảm 50K', 'AMOUNT', 50000.00, '2025-03-01 00:00:00', '2025-03-31 00:00:00', 0, 50, 300000.00, 'Khuyến mãi cũ', '2025-02-20 00:00:00', '2025-04-01 00:00:00', 'VIP'),
(4, 'SALE15_OLD', 'Giảm 15%', 'PERCENT', 15.00, '2025-04-01 00:00:00', '2025-04-30 00:00:00', 0, 150, 150000.00, 'Hết hạn', '2025-03-20 00:00:00', '2025-05-01 00:00:00', 'REGULAR'),
(5, 'AMOUNT100K_OLD', 'Giảm 100K', 'AMOUNT', 100000.00, '2025-05-01 00:00:00', '2025-05-15 00:00:00', 0, 30, 500000.00, 'Đã kết thúc', '2025-04-20 00:00:00', '2025-05-16 00:00:00', 'PREMIUM'),
(6, 'SALE10_NOW', 'Giảm 10% hiện tại', 'PERCENT', 10.00, '2026-01-01 00:00:00', '2026-01-31 00:00:00', 1, 999, 100000.00, 'KM tháng', '2025-12-25 00:00:00', '2026-01-01 00:00:00', 'REGULAR'),
(7, 'AMOUNT30K_NOW', 'Giảm 30K', 'AMOUNT', 30000.00, '2026-01-05 00:00:00', '2026-02-05 00:00:00', 1, 200, 150000.00, 'Đơn nhỏ', '2026-01-01 00:00:00', '2026-01-05 00:00:00', 'REGULAR'),
(8, 'SALE20_VIP', 'VIP giảm 20%', 'PERCENT', 20.00, '2026-01-10 00:00:00', '2026-02-10 00:00:00', 1, 100, 300000.00, 'VIP only', '2026-01-05 00:00:00', '2026-01-10 00:00:00', 'VIP'),
(9, 'AMOUNT50K_VIP', 'VIP giảm 50K', 'AMOUNT', 50000.00, '2026-01-15 00:00:00', '2026-02-15 00:00:00', 1, 80, 250000.00, 'VIP only', '2026-01-10 00:00:00', '2026-01-15 00:00:00', 'VIP'),
(10, 'SALE25_PRE', 'Premium giảm 25%', 'PERCENT', 25.00, '2026-01-01 00:00:00', '2026-03-01 00:00:00', 1, 50, 500000.00, 'Premium VIP', '2025-12-20 00:00:00', '2026-01-01 00:00:00', 'PREMIUM'),
(11, 'SALE15_FEB', 'Giảm 15% tháng 2', 'PERCENT', 15.00, '2026-02-01 00:00:00', '2026-02-28 00:00:00', 1, 300, 200000.00, 'Sắp diễn ra', '2026-01-20 00:00:00', '2026-01-20 00:00:00', 'REGULAR'),
(12, 'AMOUNT40K_FEB', 'Giảm 40K', 'AMOUNT', 40000.00, '2026-02-05 00:00:00', '2026-02-20 00:00:00', 1, 150, 180000.00, 'Ưu đãi', '2026-01-25 00:00:00', '2026-01-25 00:00:00', 'REGULAR'),
(13, 'SALE30_VIP', 'VIP giảm 30%', 'PERCENT', 30.00, '2026-02-10 00:00:00', '2026-03-10 00:00:00', 1, 60, 400000.00, 'VIP only', '2026-01-30 00:00:00', '2026-01-30 00:00:00', 'VIP'),
(14, 'AMOUNT80K_MAR', 'Giảm 80K', 'AMOUNT', 80000.00, '2026-03-01 00:00:00', '2026-03-31 00:00:00', 1, 40, 600000.00, 'Tháng 3', '2026-02-15 00:00:00', '2026-02-15 00:00:00', 'PREMIUM'),
(15, 'SALE20_MAR', 'Giảm 20%', 'PERCENT', 20.00, '2026-03-05 00:00:00', '2026-03-25 00:00:00', 1, 200, 250000.00, 'Sale mùa', '2026-02-20 00:00:00', '2026-02-20 00:00:00', 'REGULAR'),
(16, 'SALE5_YEAR', 'Giảm 5% cả năm', 'PERCENT', 5.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 1, 9999, 50000.00, 'Dài hạn', '2025-12-01 00:00:00', '2026-01-01 00:00:00', 'REGULAR'),
(17, 'AMOUNT20K_YEAR', 'Giảm 20K mọi đơn', 'AMOUNT', 20000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 1, 9999, 100000.00, 'Luôn áp dụng', '2025-12-01 00:00:00', '2026-01-01 00:00:00', 'REGULAR'),
(18, 'SALE50_TEST', 'Test 50%', 'PERCENT', 50.00, '2026-01-01 00:00:00', '2026-01-10 00:00:00', 0, 10, 1000000.00, 'Test', '2025-12-20 00:00:00', '2026-01-05 00:00:00', 'PREMIUM'),
(19, 'AMOUNT200K_TEST', 'Test 200K', 'AMOUNT', 200000.00, '2026-01-01 00:00:00', '2026-01-05 00:00:00', 0, 5, 2000000.00, 'Test', '2025-12-20 00:00:00', '2026-01-05 00:00:00', 'PREMIUM'),
(20, 'SALE12', 'Giảm 12%', 'PERCENT', 12.00, '2026-04-01 00:00:00', '2026-04-30 00:00:00', 1, 300, 120000.00, 'Tháng 4', '2026-03-15 00:00:00', '2026-03-15 00:00:00', 'REGULAR'),
(21, 'SALE18', 'Giảm 18%', 'PERCENT', 18.00, '2026-05-01 00:00:00', '2026-05-31 00:00:00', 1, 200, 220000.00, 'Tháng 5', '2026-04-15 00:00:00', '2026-04-15 00:00:00', 'REGULAR'),
(22, 'AMOUNT60K', 'Giảm 60K', 'AMOUNT', 60000.00, '2026-06-01 00:00:00', '2026-06-30 00:00:00', 1, 150, 350000.00, 'Tháng 6', '2026-05-15 00:00:00', '2026-05-15 00:00:00', 'VIP'),
(23, 'SALE22', 'Giảm 22%', 'PERCENT', 22.00, '2026-07-01 00:00:00', '2026-07-31 00:00:00', 1, 100, 400000.00, 'Tháng 7', '2026-06-15 00:00:00', '2026-06-15 00:00:00', 'VIP'),
(24, 'AMOUNT90K', 'Giảm 90K', 'AMOUNT', 90000.00, '2026-08-01 00:00:00', '2026-08-31 00:00:00', 1, 80, 500000.00, 'Tháng 8', '2026-07-15 00:00:00', '2026-07-15 00:00:00', 'PREMIUM'),
(25, 'SALE35', 'Giảm 35%', 'PERCENT', 35.00, '2026-09-01 00:00:00', '2026-09-30 00:00:00', 1, 50, 700000.00, 'Sale lớn', '2026-08-15 00:00:00', '2026-08-15 00:00:00', 'PREMIUM'),
(26, 'SALE40', 'Giảm 40%', 'PERCENT', 40.00, '2026-10-01 00:00:00', '2026-10-31 00:00:00', 1, 30, 800000.00, 'Sale mạnh', '2026-09-15 00:00:00', '2026-09-15 00:00:00', 'PREMIUM'),
(27, 'AMOUNT150K', 'Giảm 150K', 'AMOUNT', 150000.00, '2026-11-01 00:00:00', '2026-11-30 00:00:00', 1, 20, 1000000.00, 'Cuối năm', '2026-10-15 00:00:00', '2026-10-15 00:00:00', 'PREMIUM'),
(28, 'SALE50_END', 'Sale 50% cuối năm', 'PERCENT', 50.00, '2026-12-01 00:00:00', '2026-12-31 00:00:00', 1, 10, 1500000.00, 'Cuối năm', '2026-11-15 00:00:00', '2026-11-15 00:00:00', 'PREMIUM'),
(29, 'SALE8', 'Giảm 8%', 'PERCENT', 8.00, '2026-01-01 00:00:00', '2026-01-15 00:00:00', 1, 500, 80000.00, 'Đơn nhỏ', '2025-12-28 00:00:00', '2026-01-01 00:00:00', 'REGULAR'),
(30, 'AMOUNT25K', 'Giảm 25K', 'AMOUNT', 25000.00, '2026-01-05 00:00:00', '2026-01-25 00:00:00', 1, 400, 120000.00, 'Nhanh gọn', '2026-01-01 00:00:00', '2026-01-05 00:00:00', 'REGULAR'),
(31, 'SALE7_NOW', 'Giảm 7% nhanh', 'PERCENT', 7.00, '2026-01-10 00:00:00', '2026-01-20 00:00:00', 1, 300, 70000.00, 'Khuyến mãi ngắn hạn', '2026-01-08 00:00:00', '2026-01-10 00:00:00', 'REGULAR'),
(32, 'AMOUNT15K_NOW', 'Giảm 15K', 'AMOUNT', 15000.00, '2026-01-15 00:00:00', '2026-02-15 00:00:00', 1, 500, 90000.00, 'Áp dụng mọi đơn', '2026-01-10 00:00:00', '2026-01-15 00:00:00', 'REGULAR'),
(33, 'SALE18_VIP_NOW', 'VIP giảm 18%', 'PERCENT', 18.00, '2026-01-12 00:00:00', '2026-02-12 00:00:00', 1, 120, 250000.00, 'VIP ưu đãi', '2026-01-08 00:00:00', '2026-01-12 00:00:00', 'VIP'),
(34, 'AMOUNT70K_PRE', 'Premium giảm 70K', 'AMOUNT', 70000.00, '2026-01-20 00:00:00', '2026-02-20 00:00:00', 1, 60, 450000.00, 'Premium only', '2026-01-15 00:00:00', '2026-01-20 00:00:00', 'PREMIUM'),
(35, 'SALE14_FEB', 'Giảm 14% tháng 2', 'PERCENT', 14.00, '2026-02-01 00:00:00', '2026-02-15 00:00:00', 1, 250, 180000.00, 'Ưu đãi đầu tháng', '2026-01-20 00:00:00', '2026-01-20 00:00:00', 'REGULAR'),
(36, 'AMOUNT35K_FEB', 'Giảm 35K', 'AMOUNT', 35000.00, '2026-02-10 00:00:00', '2026-02-25 00:00:00', 1, 200, 160000.00, 'Khuyến mãi tháng 2', '2026-01-25 00:00:00', '2026-01-25 00:00:00', 'REGULAR'),
(37, 'SALE28_VIP_FEB', 'VIP giảm 28%', 'PERCENT', 28.00, '2026-02-15 00:00:00', '2026-03-15 00:00:00', 1, 90, 380000.00, 'VIP only', '2026-01-30 00:00:00', '2026-01-30 00:00:00', 'VIP'),
(38, 'AMOUNT120K_PRE', 'Premium giảm 120K', 'AMOUNT', 120000.00, '2026-02-20 00:00:00', '2026-03-20 00:00:00', 1, 40, 700000.00, 'Premium ưu đãi', '2026-02-01 00:00:00', '2026-02-01 00:00:00', 'PREMIUM'),
(39, 'SALE9_OLD', 'Giảm 9% cũ', 'PERCENT', 9.00, '2025-11-01 00:00:00', '2025-11-15 00:00:00', 0, 150, 90000.00, 'Đã hết hạn', '2025-10-25 00:00:00', '2025-11-16 00:00:00', 'REGULAR'),
(40, 'AMOUNT45K_OLD', 'Giảm 45K cũ', 'AMOUNT', 45000.00, '2025-12-01 00:00:00', '2025-12-20 00:00:00', 0, 80, 220000.00, 'Đã kết thúc', '2025-11-25 00:00:00', '2025-12-21 00:00:00', 'VIP'),
(41, 'SALE11_EDGE', 'Giảm 11% biên', 'PERCENT', 11.00, '2026-01-22 00:00:00', '2026-01-22 00:00:00', 1, 100, 110000.00, 'Start = End', '2026-01-20 00:00:00', '2026-01-22 00:00:00', 'REGULAR'),
(42, 'AMOUNT55K_EDGE', 'Giảm 55K biên', 'AMOUNT', 55000.00, '2026-01-22 00:00:00', '2026-01-22 00:00:00', 1, 70, 260000.00, 'Test biên thời gian', '2026-01-20 00:00:00', '2026-01-22 00:00:00', 'VIP'),
(43, 'SALE6_LONG', 'Giảm 6% dài hạn', 'PERCENT', 6.00, '2026-03-01 00:00:00', '2026-12-31 00:00:00', 1, 2000, 60000.00, 'Áp dụng lâu dài', '2026-02-20 00:00:00', '2026-03-01 00:00:00', 'REGULAR'),
(44, 'AMOUNT18K_LONG', 'Giảm 18K dài hạn', 'AMOUNT', 18000.00, '2026-03-01 00:00:00', '2026-12-31 00:00:00', 1, 2000, 90000.00, 'Áp dụng lâu dài', '2026-02-20 00:00:00', '2026-03-01 00:00:00', 'REGULAR'),
(45, 'SALE33_OFF', 'Giảm 33% (tắt)', 'PERCENT', 33.00, '2026-02-01 00:00:00', '2026-02-28 00:00:00', 0, 20, 500000.00, 'Test tắt', '2026-01-25 00:00:00', '2026-01-25 00:00:00', 'PREMIUM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL COMMENT 'ID quyền, tự tăng',
  `name` varchar(50) NOT NULL COMMENT 'Tên quyền: ADMIN, STAFF,...',
  `is_active` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu các quyền người dùng';

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `name`, `is_active`) VALUES
(1, 'Quản lý', 1),
(2, 'Thu ngân', 1),
(3, 'Nhân viên kho', 1),
(4, 'Bảo vệ', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role_permissions`
--

CREATE TABLE `role_permissions` (
  `role_id` bigint(20) NOT NULL COMMENT 'ID role',
  `permission_id` bigint(20) NOT NULL COMMENT 'ID permission',
  `is_active` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suppliers`
--

CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL COMMENT 'ID nhà cung cấp, tự tăng',
  `name` varchar(150) NOT NULL COMMENT 'Tên nhà cung cấp',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email liên hệ',
  `phone` varchar(50) DEFAULT NULL COMMENT 'Số điện thoại liên hệ',
  `address` varchar(500) DEFAULT NULL COMMENT 'Địa chỉ nhà cung cấp',
  `note` varchar(500) DEFAULT NULL COMMENT 'Ghi chú thêm',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật',
  `is_deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin nhà cung cấp';

--
-- Đang đổ dữ liệu cho bảng `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `email`, `phone`, `address`, `note`, `created_at`, `updated_at`, `is_deleted`) VALUES
(1, 'Công ty TNHH Minh Phát', 'minhphat@gmail.com', '0901234567', 'Quận 1, TP.HCM', 'Nhà cung cấp thực phẩm', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(2, 'Công ty CP An Khang', 'ankhang@gmail.com', '0902345678', 'Quận 3, TP.HCM', 'Chuyên đồ khô', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(3, 'Công ty TNHH Phúc Lộc', 'phucloc@gmail.com', '0903456789', 'Quận 5, TP.HCM', 'Thực phẩm đông lạnh', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(4, 'Công ty CP Đại Hưng', 'daihung@gmail.com', '0904567890', 'Quận 7, TP.HCM', 'Nước giải khát', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(5, 'Công ty TNHH Việt Thái', 'vietthai@gmail.com', '0905678901', 'Quận Bình Thạnh, TP.HCM', 'Gia vị – phụ gia', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(6, 'Công ty CP Hòa Bình', 'hoabinh@gmail.com', '0906789012', 'TP. Thủ Đức, TP.HCM', 'Bao bì – đóng gói', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(7, 'Công ty TNHH Nam Phương', 'namphuong@gmail.com', '0907890123', 'Quận Tân Bình, TP.HCM', 'Hàng tiêu dùng', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(8, 'Công ty CP Sài Gòn Food', 'saigonfood@gmail.com', '0908901234', 'Quận 12, TP.HCM', 'Thực phẩm chế biến', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(9, 'Công ty TNHH Hồng Phúc', 'hongphuc@gmail.com', '0909012345', 'Quận Gò Vấp, TP.HCM', 'Rau củ – nông sản', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(10, 'Công ty CP Tân Tiến', 'tantien@gmail.com', '0910123456', 'Quận Bình Tân, TP.HCM', 'Nhà cung cấp tổng hợp', '2026-01-22 10:00:00', '2026-01-30 23:59:31', 1),
(11, 'Công ty TNHH Hoàng Long', 'hoanglong@gmail.com', '0911234567', 'Quận 8, TP.HCM', 'Thực phẩm tươi sống', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(12, 'Công ty CP Thiên Phú', 'thienphu@gmail.com', '0912345678', 'Quận 10, TP.HCM', 'Đồ uống đóng chai', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(13, 'Công ty TNHH Phương Nam', 'phuongnam@gmail.com', '0913456789', 'Quận 11, TP.HCM', 'Hàng tiêu dùng nhanh', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(14, 'Công ty CP Vạn Phát', 'vanphat@gmail.com', '0914567890', 'Quận Phú Nhuận, TP.HCM', 'Gia vị nhập khẩu', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(15, 'Công ty TNHH Kim Ngân', 'kimngan@gmail.com', '0915678901', 'Quận Bình Tân, TP.HCM', 'Bánh kẹo cao cấp', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(16, 'Công ty CP Ánh Dương', 'anhduong@gmail.com', '0916789012', 'TP. Thủ Đức, TP.HCM', 'Sữa & đồ uống dinh dưỡng', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(17, 'Công ty TNHH Đông Á', 'donga@gmail.com', '0917890123', 'Quận 6, TP.HCM', 'Thực phẩm chay', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(18, 'Công ty CP Nhật Minh', 'nhatminh@gmail.com', '0918901234', 'Quận 4, TP.HCM', 'Hàng nhập khẩu Nhật', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(19, 'Công ty TNHH Thành Công', 'thanhcong@gmail.com', '0919012345', 'Quận 9, TP.HCM', 'Đồ đông lạnh', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(20, 'Công ty CP Gia Phát', 'giaphat@gmail.com', '0920123456', 'Quận Tân Phú, TP.HCM', 'Thực phẩm chế biến sẵn', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(21, 'Công ty TNHH Bách Hóa Xanh', 'bachhoaxanh@gmail.com', '0921234567', 'Quận 2, TP.HCM', 'Nhà phân phối tổng hợp', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(22, 'Công ty CP Nam Việt', 'namviet@gmail.com', '0922345678', 'Quận 1, TP.HCM', 'Hải sản đông lạnh', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(23, 'Công ty TNHH Đại Lộc', 'dailoc@gmail.com', '0923456789', 'Quận 3, TP.HCM', 'Gạo & nông sản', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(24, 'Công ty CP Phú Quý', 'phuquy@gmail.com', '0924567890', 'Quận 7, TP.HCM', 'Hàng tiêu dùng nhập khẩu', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(25, 'Công ty TNHH Green Food', 'greenfood@gmail.com', '0925678901', 'Quận Bình Thạnh, TP.HCM', 'Thực phẩm organic', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(26, 'Công ty CP Fresh Mart', 'freshmart@gmail.com', '0926789012', 'Quận Gò Vấp, TP.HCM', 'Rau củ quả tươi', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(27, 'Công ty TNHH An Nhiên', 'annhien@gmail.com', '0927890123', 'Quận 12, TP.HCM', 'Thực phẩm eat clean', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(28, 'Công ty CP Đại Nam', 'dainam@gmail.com', '0928901234', 'Quận Bình Tân, TP.HCM', 'Bao bì & vật tư', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(29, 'Công ty TNHH Thịnh Vượng', 'thinhvuong@gmail.com', '0929012345', 'Quận Tân Bình, TP.HCM', 'Nhà cung cấp lâu năm', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1),
(30, 'Công ty CP Phát Đạt', 'phatdat@gmail.com', '0930123456', 'TP. Thủ Đức, TP.HCM', 'Cung ứng chuỗi cửa hàng', '2026-01-22 10:10:00', '2026-01-30 23:59:31', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `units`
--

CREATE TABLE `units` (
  `id` bigint(20) NOT NULL COMMENT 'ID đơn vị, tự tăng',
  `name` varchar(50) NOT NULL COMMENT 'Tên đơn vị: hộp, chiếc, cái…',
  `description` varchar(255) DEFAULT NULL COMMENT 'Mô tả đơn vị (tùy chọn)',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật',
  `is_deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu các đơn vị sản phẩm';

--
-- Đang đổ dữ liệu cho bảng `units`
--

INSERT INTO `units` (`id`, `name`, `description`, `created_at`, `updated_at`, `is_deleted`) VALUES
(1, 'Cái', 'Đơn vị tính lẻ từng sản phẩm', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(2, 'Chai', 'Sản phẩm dạng chai', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(3, 'Lon', 'Nước ngọt, bia lon', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(4, 'Gói', 'Bánh kẹo, mì gói', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(5, 'Hộp', 'Sản phẩm đóng hộp', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(6, 'Thùng', 'Nhiều sản phẩm đóng chung', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(7, 'Lốc', 'Nhóm chai/lon bán chung', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(8, 'Bịch', 'Sản phẩm đóng túi nilon', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(9, 'Bao', 'Gạo, đường, bột số lượng lớn', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(10, 'Kg', 'Đơn vị khối lượng kilogram', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(11, 'Gram', 'Đơn vị khối lượng gram', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(12, 'Lít', 'Đơn vị dung tích', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(13, 'ml', 'Dung tích nhỏ', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(14, 'Chục', 'Bán theo 10 cái', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(15, 'Tá', 'Bán theo 12 cái', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(16, 'Cuộn', 'Giấy vệ sinh, màng bọc', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(17, 'Túi', 'Đóng gói nhỏ', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(18, 'Miếng', 'Pho mát, bánh lẻ', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(19, 'Hũ', 'Gia vị, thực phẩm đóng hũ', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(20, 'Khay', 'Thực phẩm tươi sống', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(21, 'Bộ', 'Combo sản phẩm', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(22, 'Cặp', 'Hai sản phẩm bán chung', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(23, 'Gói lớn', 'Bao bì size lớn', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(24, 'Gói nhỏ', 'Bao bì size nhỏ', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(25, 'Thanh', 'Kẹo, socola dạng thanh', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(26, 'Miếng lớn', 'Phần thực phẩm lớn', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(27, 'Miếng nhỏ', 'Phần thực phẩm nhỏ', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(28, 'Viên', 'Thuốc, kẹo viên', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(29, 'Gói combo', 'Combo nhiều sản phẩm', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1),
(30, 'Phần', 'Suất ăn, đồ chế biến', '2025-01-01 00:00:00', '2026-01-30 23:59:53', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL COMMENT 'ID người dùng, tự tăng',
  `username` varchar(150) NOT NULL COMMENT 'Tên đăng nhập, duy nhất',
  `password_hash` varchar(255) NOT NULL COMMENT 'Mật khẩu đã hash (BCrypt)',
  `fullName` varchar(255) DEFAULT NULL COMMENT 'Họ và tên đầy đủ',
  `dateOfBirth` date DEFAULT NULL COMMENT 'Ngày sinh',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email liên hệ, duy nhất',
  `phone` varchar(50) DEFAULT NULL COMMENT 'Số điện thoại, duy nhất',
  `address` varchar(255) DEFAULT NULL COMMENT 'Địa chỉ cư trú',
  `gender` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tới bảng roles',
  `active` int(11) NOT NULL,
  `createdAt` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo tài khoản',
  `updatedAt` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật gần nhất',
  `lastLogin` datetime DEFAULT NULL COMMENT 'Lần đăng nhập cuối cùng',
  `refresh_token_hash` varchar(512) DEFAULT NULL COMMENT 'Hash refresh token',
  `refreshTokenExpiry` datetime DEFAULT NULL COMMENT 'Hết hạn refresh token',
  `reset_password_token_hash` varchar(512) DEFAULT NULL COMMENT 'Hash token quên mật khẩu',
  `resetPasswordTokenExpiry` datetime DEFAULT NULL COMMENT 'Hết hạn token quên mật khẩu',
  `img_url` varchar(100) DEFAULT NULL,
  `img_urlID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin người dùng';

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `password_hash`, `fullName`, `dateOfBirth`, `email`, `phone`, `address`, `gender`, `role_id`, `active`, `createdAt`, `updatedAt`, `lastLogin`, `refresh_token_hash`, `refreshTokenExpiry`, `reset_password_token_hash`, `resetPasswordTokenExpiry`, `img_url`, `img_urlID`) VALUES
(1, 'admin', '$2a$10$z3B/HszO41oskpHr.zinXO7bihEewXV6/q2J1eFMIUFnE.djlw/ue', 'Đỗ Mai Anh', '2004-03-23', 'domaianhh20@gmail.com', '0899463504', 'D10/7L Đường Nữ Dân Công Huyện Bình Chánh Thành Phố HCM', 1, 1, 1, '2026-01-16 23:56:42', '2026-01-30 16:44:59', '2026-01-27 17:08:27', 'f292b63e8ea46379d507821f885457db38e9a289ca2fdabd7557cbe1509dee90', '2026-02-03 17:08:27', NULL, NULL, 'https://res.cloudinary.com/dxy2gp1lg/image/upload/v1769703586/FreshMart/sqa53w2zuw2dtmy68vbo.jpg', 'FreshMart/sqa53w2zuw2dtmy68vbo');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `ux_categories_name` (`name`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `ux_customers_email` (`email`),
  ADD UNIQUE KEY `ux_customers_phone` (`phone`);

--
-- Chỉ mục cho bảng `imports`
--
ALTER TABLE `imports`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `import_number` (`import_number`),
  ADD KEY `idx_imports_supplier` (`supplier_id`),
  ADD KEY `idx_imports_staff` (`staff_id`),
  ADD KEY `idx_imports_is_deleted` (`is_deleted`);

--
-- Chỉ mục cho bảng `import_items`
--
ALTER TABLE `import_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_import_item_import` (`import_id`),
  ADD KEY `idx_import_item_product` (`product_id`);

--
-- Chỉ mục cho bảng `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_inventory_product` (`product_id`);

--
-- Chỉ mục cho bảng `inventory_exports`
--
ALTER TABLE `inventory_exports`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `idx_export_code` (`code`),
  ADD KEY `idx_export_created` (`created_at`),
  ADD KEY `fk_export_user` (`created_by`);

--
-- Chỉ mục cho bảng `inventory_export_items`
--
ALTER TABLE `inventory_export_items`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_export_product` (`export_id`,`product_id`),
  ADD KEY `idx_export_items_export` (`export_id`),
  ADD KEY `idx_export_items_product` (`product_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `order_number` (`order_number`),
  ADD UNIQUE KEY `ux_orders_order_number` (`order_number`),
  ADD KEY `idx_orders_customer` (`customer_id`),
  ADD KEY `idx_orders_staff` (`staff_id`),
  ADD KEY `idx_orders_status` (`status`),
  ADD KEY `idx_orders_is_deleted` (`is_deleted`),
  ADD KEY `fk_orders_promotion` (`promotion_id`);

--
-- Chỉ mục cho bảng `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_oi_order` (`order_id`),
  ADD KEY `idx_oi_product` (`product_id`);

--
-- Chỉ mục cho bảng `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idx_payments_order` (`order_id`);

--
-- Chỉ mục cho bảng `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `ux_permissions_name` (`name`),
  ADD KEY `idx_permissions_group_id` (`group_id`);

--
-- Chỉ mục cho bảng `permission_groups`
--
ALTER TABLE `permission_groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_permission_groups_code` (`code`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sku` (`sku`),
  ADD UNIQUE KEY `ux_products_sku` (`sku`),
  ADD UNIQUE KEY `UK_qfr8vf85k3q1xinifvsl1eynf` (`barcode`),
  ADD KEY `fk_products_unit` (`unit_id`),
  ADD KEY `idx_products_category` (`category_id`),
  ADD KEY `idx_products_supplier` (`supplier_id`);

--
-- Chỉ mục cho bảng `promotions`
--
ALTER TABLE `promotions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_promotions_code` (`code`),
  ADD KEY `idx_promotions_start_at` (`start_at`),
  ADD KEY `idx_promotions_end_at` (`end_at`),
  ADD KEY `idx_promotions_is_active` (`is_active`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Chỉ mục cho bảng `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `fk_rp_permission` (`permission_id`);

--
-- Chỉ mục cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `ux_suppliers_email` (`email`),
  ADD UNIQUE KEY `ux_suppliers_phone` (`phone`);

--
-- Chỉ mục cho bảng `units`
--
ALTER TABLE `units`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `ux_units_name` (`name`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `ux_users_username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `ux_users_email` (`email`),
  ADD UNIQUE KEY `ux_users_phone` (`phone`),
  ADD KEY `idx_users_active` (`active`),
  ADD KEY `idx_users_role` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID danh mục, tự tăng', AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID khách hàng, tự tăng', AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT cho bảng `imports`
--
ALTER TABLE `imports`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID phiếu nhập, tự tăng';

--
-- AUTO_INCREMENT cho bảng `import_items`
--
ALTER TABLE `import_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID chi tiết nhập';

--
-- AUTO_INCREMENT cho bảng `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID inventory, tự tăng', AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT cho bảng `inventory_exports`
--
ALTER TABLE `inventory_exports`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `inventory_export_items`
--
ALTER TABLE `inventory_export_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID đơn hàng, tự tăng';

--
-- AUTO_INCREMENT cho bảng `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID dòng sản phẩm trong order, tự tăng';

--
-- AUTO_INCREMENT cho bảng `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID thanh toán, tự tăng';

--
-- AUTO_INCREMENT cho bảng `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID quyền, tự tăng';

--
-- AUTO_INCREMENT cho bảng `permission_groups`
--
ALTER TABLE `permission_groups`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID sản phẩm, tự tăng', AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT cho bảng `promotions`
--
ALTER TABLE `promotions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID khuyến mãi, tự tăng', AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID quyền, tự tăng', AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID nhà cung cấp, tự tăng', AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `units`
--
ALTER TABLE `units`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID đơn vị, tự tăng', AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID người dùng, tự tăng', AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `imports`
--
ALTER TABLE `imports`
  ADD CONSTRAINT `fk_import_staff` FOREIGN KEY (`staff_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `fk_import_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`);

--
-- Các ràng buộc cho bảng `import_items`
--
ALTER TABLE `import_items`
  ADD CONSTRAINT `fk_import_item_import` FOREIGN KEY (`import_id`) REFERENCES `imports` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_import_item_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `fk_inventory_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `inventory_exports`
--
ALTER TABLE `inventory_exports`
  ADD CONSTRAINT `fk_export_user` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE SET NULL;

--
-- Các ràng buộc cho bảng `inventory_export_items`
--
ALTER TABLE `inventory_export_items`
  ADD CONSTRAINT `fk_export_item_export` FOREIGN KEY (`export_id`) REFERENCES `inventory_exports` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_export_item_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `fk_orders_promotion` FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_orders_staff` FOREIGN KEY (`staff_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `fk_orderitem_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_orderitem_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `fk_payments_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `permissions`
--
ALTER TABLE `permissions`
  ADD CONSTRAINT `fk_permissions_group` FOREIGN KEY (`group_id`) REFERENCES `permission_groups` (`id`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_products_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `fk_products_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  ADD CONSTRAINT `fk_products_unit` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`);

--
-- Các ràng buộc cho bảng `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD CONSTRAINT `fk_rp_permission` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_rp_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
