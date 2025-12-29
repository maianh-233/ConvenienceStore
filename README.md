# ConvenienceStore Project

## Giới thiệu

Đây là dự án Java mô phỏng quản lý cửa hàng tiện lợi. Dự án dùng Maven và Hibernate để quản lý cơ sở dữ liệu MySQL, phục vụ làm đồ án hoặc làm việc nhóm.

## Công nghệ sử dụng

* Java 17+ (hoặc 21)
* Maven
* Hibernate
* MySQL
* Git & GitHub

## Cấu trúc dự án

```
D:.
│   .gitignore
│   pom.xml
│   README.md
│   
├───.vscode
│       settings.json
│       
├───src
│   └───main
│       ├───java
│       │   └───com
│       │       └───conveniencestore
│       │           │   Main.java
│       │           │   TestHibernate.java
│       │           │
│       │           ├───bus
│       │           ├───config
│       │           ├───dao
│       │           ├───entity
│       │           ├───gui
│       │           ├───logging
│       │           └───util
│       │                   HibernateUtil.java
│       │
│       └───resources
│               hibernate.cfg.xml
│
└───target
    ├───classes
    │   │   hibernate.cfg.xml
    │   │
    │   └───com
    │       └───conveniencestore
    │           │   Main.class
    │           │   TestHibernate.class
    │           │
    │           ├───bus
    │           ├───config
    │           ├───dao
    │           ├───entity
    │           ├───gui
    │           ├───logging
    │           └───util
    │                   HibernateUtil.class
    │
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    ├───maven-status
    │   └───maven-compiler-plugin
    │       └───compile
    │           └───default-compile
    │                   createdFiles.lst
    │                   inputFiles.lst
    │
    └───test-classes
```

## Cài đặt môi trường

1. Cài Java và Maven, kiểm tra bằng `java -version` và `mvn -v`
2. Cài MySQL hoặc XAMPP và chạy server
3. Tạo database `conveniencestore`

## Cấu hình Hibernate

Trong `src/main/resources/hibernate.cfg.xml`, chỉnh:

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/conveniencestore</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password"></property>
```

## Chạy dự án

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=com.conveniencestore.Main
```

Hoặc test Hibernate:

```bash
mvn exec:java -Dexec.mainClass=com.conveniencestore.TestHibernate
```

## Quy tắc làm việc nhóm

* Branch `main` để code ổn định
* Branch `dev` để tích hợp chung
* Branch `feature/*` cho từng chức năng
* Commit message:

  * `Add`: thêm chức năng
  * `Fix`: sửa lỗi
  * `Update`: cập nhật
  * `Refactor`: thay đổi cấu trúc code

Ví dụ tạo branch tính năng:

```bash
git checkout dev
git checkout -b feature/login
```

## Không commit

* Thư mục `target/`
* File IDE cá nhân
* File chứa mật khẩu thật

## Thành viên nhóm

* Mai Anh – Leader / Backend
* (Thêm các thành viên khác)

## Ghi chú

* Luôn pull `dev` trước khi code
* Tạo Pull Request để merge, không commit trực tiếp vào `main`
