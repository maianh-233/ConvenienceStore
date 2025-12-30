-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 30, 2025 at 11:45 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `conveniencestore`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL COMMENT 'ID danh mục, tự tăng',
  `name` varchar(100) NOT NULL COMMENT 'Tên danh mục, duy nhất',
  `description` varchar(255) DEFAULT NULL COMMENT 'Mô tả danh mục (tùy chọn)',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0 = chưa xóa, 1 = đã xóa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu các danh mục sản phẩm';

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL COMMENT 'ID khách hàng, tự tăng',
  `fullName` varchar(150) NOT NULL COMMENT 'Họ và tên khách hàng',
  `dateOfBirth` date DEFAULT NULL COMMENT 'Ngày sinh',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email liên hệ (duy nhất)',
  `phone` varchar(50) DEFAULT NULL COMMENT 'Số điện thoại (duy nhất)',
  `address` varchar(500) DEFAULT NULL COMMENT 'Địa chỉ đầy đủ',
  `identityNumber` varchar(50) DEFAULT NULL COMMENT 'Căn cước / CMND',
  `gender` tinyint(4) DEFAULT 0 COMMENT 'Giới tính: 0=Nam, 1=Nữ',
  `tier` enum('REGULAR','VIP','PREMIUM') DEFAULT 'REGULAR' COMMENT 'Hạng khách: REGULAR, VIP, PREMIUM',
  `points` int(11) DEFAULT 0 COMMENT 'Điểm tích lũy của khách hàng',
  `password` varchar(255) DEFAULT NULL COMMENT 'Mật khẩu (nên lưu hashed)',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm thêm khách hàng',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật gần nhất'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin khách hàng';

-- --------------------------------------------------------

--
-- Table structure for table `imports`
--

CREATE TABLE `imports` (
  `id` bigint(20) NOT NULL COMMENT 'ID phiếu nhập, tự tăng',
  `import_number` varchar(100) NOT NULL COMMENT 'Mã phiếu nhập, duy nhất',
  `supplier_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tham chiếu nhà cung cấp',
  `staff_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tham chiếu nhân viên nhập hàng',
  `total_amount` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Tổng tiền phiếu nhập',
  `note` varchar(500) DEFAULT NULL COMMENT 'Ghi chú phiếu nhập',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0 = chưa xóa, 1 = đã xóa',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo phiếu nhập',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật phiếu nhập'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng phiếu nhập hàng';

-- --------------------------------------------------------

--
-- Table structure for table `import_items`
--

CREATE TABLE `import_items` (
  `id` bigint(20) NOT NULL COMMENT 'ID chi tiết nhập',
  `import_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại liên kết với phiếu nhập',
  `product_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại liên kết với sản phẩm',
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT 'Số lượng nhập',
  `unit_price` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Giá nhập 1 sản phẩm',
  `total_price` decimal(12,2) NOT NULL DEFAULT 0.00 COMMENT 'Tổng tiền = quantity * unit_price'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Chi tiết sản phẩm trong phiếu nhập';

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL COMMENT 'ID inventory, tự tăng',
  `product_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại liên kết với bảng products (1 product ↔ 1 inventory)',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT 'Số lượng hiện tại của sản phẩm',
  `last_checked_at` datetime DEFAULT NULL COMMENT 'Lần kiểm tra tồn kho gần nhất',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật inventory'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu tồn kho của từng sản phẩm';

-- --------------------------------------------------------

--
-- Table structure for table `orders`
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
  `is_online` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Đơn online hay bán tại cửa hàng (1=online, 0=offline)',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0 = chưa xóa, 1 = đã xóa',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo đơn hàng',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật đơn hàng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu đơn hàng';

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
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
-- Table structure for table `payments`
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
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL COMMENT 'ID quyền, tự tăng',
  `name` varchar(100) NOT NULL COMMENT 'Tên quyền chi tiết, ví dụ: ORDER_CREATE, PRODUCT_EDIT',
  `description` varchar(255) DEFAULT NULL COMMENT 'Mô tả quyền, ví dụ: Cho phép tạo đơn hàng'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
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
  `image_url` varchar(1024) DEFAULT NULL COMMENT 'URL ảnh sản phẩm',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT 'Trạng thái sản phẩm: 1=hoạt động, 0=ngừng bán',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo sản phẩm',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật sản phẩm'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin sản phẩm của cửa hàng';

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL COMMENT 'ID quyền, tự tăng',
  `name` varchar(50) NOT NULL COMMENT 'Tên quyền: ADMIN, STAFF,...'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu các quyền người dùng';

-- --------------------------------------------------------

--
-- Table structure for table `role_permissions`
--

CREATE TABLE `role_permissions` (
  `role_id` bigint(20) NOT NULL COMMENT 'ID role',
  `permission_id` bigint(20) NOT NULL COMMENT 'ID permission'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
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
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0 = chưa xóa, 1 = đã xóa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin nhà cung cấp';

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

CREATE TABLE `units` (
  `id` bigint(20) NOT NULL COMMENT 'ID đơn vị, tự tăng',
  `name` varchar(50) NOT NULL COMMENT 'Tên đơn vị: hộp, chiếc, cái…',
  `description` varchar(255) DEFAULT NULL COMMENT 'Mô tả đơn vị (tùy chọn)',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo',
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Soft delete: 0 = chưa xóa, 1 = đã xóa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu các đơn vị sản phẩm';

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL COMMENT 'ID người dùng, tự tăng',
  `username` varchar(150) NOT NULL COMMENT 'Tên đăng nhập, duy nhất',
  `password_hash` varchar(255) NOT NULL COMMENT 'Mật khẩu đã hash (BCrypt)',
  `fullName` varchar(255) DEFAULT NULL COMMENT 'Họ và tên đầy đủ',
  `dateOfBirth` date DEFAULT NULL COMMENT 'Ngày sinh',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email liên hệ, duy nhất',
  `phone` varchar(50) DEFAULT NULL COMMENT 'Số điện thoại, duy nhất',
  `identityNumber` varchar(50) DEFAULT NULL COMMENT 'Căn cước công dân / CMND',
  `address` varchar(255) DEFAULT NULL COMMENT 'Địa chỉ cư trú',
  `gender` tinyint(4) DEFAULT 0 COMMENT 'Giới tính: 0=Nam, 1=Nữ',
  `role_id` bigint(20) NOT NULL COMMENT 'Khóa ngoại tới bảng roles',
  `active` tinyint(4) DEFAULT 1 COMMENT 'Trạng thái hoạt động: 1=active, 0=nghỉ việc',
  `locked` tinyint(4) DEFAULT 0 COMMENT 'Tài khoản khóa: 1=khóa, 0=mở',
  `createdAt` datetime DEFAULT current_timestamp() COMMENT 'Thời điểm tạo tài khoản',
  `updatedAt` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Thời điểm cập nhật gần nhất',
  `lastLogin` datetime DEFAULT NULL COMMENT 'Lần đăng nhập cuối cùng',
  `refresh_token_hash` varchar(512) DEFAULT NULL COMMENT 'Hash refresh token',
  `refreshTokenExpiry` datetime DEFAULT NULL COMMENT 'Hết hạn refresh token',
  `reset_password_token_hash` varchar(512) DEFAULT NULL COMMENT 'Hash token quên mật khẩu',
  `resetPasswordTokenExpiry` datetime DEFAULT NULL COMMENT 'Hết hạn token quên mật khẩu'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng lưu thông tin người dùng';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `imports`
--
ALTER TABLE `imports`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `import_number` (`import_number`),
  ADD KEY `idx_imports_supplier` (`supplier_id`),
  ADD KEY `idx_imports_staff` (`staff_id`),
  ADD KEY `idx_imports_is_deleted` (`is_deleted`);

--
-- Indexes for table `import_items`
--
ALTER TABLE `import_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_import_item_import` (`import_id`),
  ADD KEY `idx_import_item_product` (`product_id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_inventory_product` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `order_number` (`order_number`),
  ADD KEY `idx_orders_customer` (`customer_id`),
  ADD KEY `idx_orders_staff` (`staff_id`),
  ADD KEY `idx_orders_status` (`status`),
  ADD KEY `idx_orders_is_deleted` (`is_deleted`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_oi_order` (`order_id`),
  ADD KEY `idx_oi_product` (`product_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idx_payments_order` (`order_id`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sku` (`sku`),
  ADD KEY `fk_products_unit` (`unit_id`),
  ADD KEY `idx_products_category` (`category_id`),
  ADD KEY `idx_products_supplier` (`supplier_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `fk_rp_permission` (`permission_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `units`
--
ALTER TABLE `units`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD KEY `fk_users_role` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID danh mục, tự tăng';

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID khách hàng, tự tăng';

--
-- AUTO_INCREMENT for table `imports`
--
ALTER TABLE `imports`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID phiếu nhập, tự tăng';

--
-- AUTO_INCREMENT for table `import_items`
--
ALTER TABLE `import_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID chi tiết nhập';

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID inventory, tự tăng';

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID đơn hàng, tự tăng';

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID dòng sản phẩm trong order, tự tăng';

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID thanh toán, tự tăng';

--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID quyền, tự tăng';

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID sản phẩm, tự tăng';

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID quyền, tự tăng';

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID nhà cung cấp, tự tăng';

--
-- AUTO_INCREMENT for table `units`
--
ALTER TABLE `units`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID đơn vị, tự tăng';

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID người dùng, tự tăng';

--
-- Constraints for dumped tables
--

--
-- Constraints for table `imports`
--
ALTER TABLE `imports`
  ADD CONSTRAINT `fk_import_staff` FOREIGN KEY (`staff_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `fk_import_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`);

--
-- Constraints for table `import_items`
--
ALTER TABLE `import_items`
  ADD CONSTRAINT `fk_import_item_import` FOREIGN KEY (`import_id`) REFERENCES `imports` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_import_item_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `fk_inventory_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `fk_orders_staff` FOREIGN KEY (`staff_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `fk_orderitem_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_orderitem_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `fk_payments_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_products_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `fk_products_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  ADD CONSTRAINT `fk_products_unit` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`);

--
-- Constraints for table `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD CONSTRAINT `fk_rp_permission` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_rp_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
