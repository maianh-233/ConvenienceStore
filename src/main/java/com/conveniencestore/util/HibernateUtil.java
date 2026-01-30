package com.conveniencestore.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HibernateUtil – Singleton quản lý SessionFactory
 * 
 * Tính năng:
 * - Lazy-loading, thread-safe
 * - Load config tự động từ resources/hibernate.cfg.xml
 * - Logging chuẩn SLF4J
 * - Shutdown an toàn
 */
public final class HibernateUtil {

    // Logger chuẩn enterprise
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);

    // Private constructor: không cho tạo instance
    private HibernateUtil() {
    }

    /**
     * Holder idiom – đảm bảo thread-safe, lazy-loading
     */
    private static class SessionFactoryHolder {
        private static final SessionFactory INSTANCE = buildSessionFactory();

        private static SessionFactory buildSessionFactory() {
            try {
                Configuration configuration = new Configuration();
                configuration.configure(); 

                //THÊM ENTITY TẠI ĐÂY
                configuration.addAnnotatedClass(com.conveniencestore.entity.User.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Role.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Product.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Customer.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Payment.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Inventory.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Unit.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Category.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Supplier.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Promotion.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.RolePermission.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Permission.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Order.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.OrderItem.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Payment.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Import.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.ImportItem.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.InventoryExport.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.InventoryExportItem.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.Permission.class);
                configuration.addAnnotatedClass(com.conveniencestore.entity.PermissionGroup.class);


                

                LOGGER.info("Hibernate Configuration loaded successfully");

                SessionFactory sessionFactory = configuration.buildSessionFactory();
                LOGGER.info("SessionFactory created successfully");
                return sessionFactory;

            } catch (HibernateException e) {
                LOGGER.error("Lỗi khởi tạo Hibernate SessionFactory", e);
                throw new ExceptionInInitializerError(e);
            }
        }

    }

    /**
     * Lấy SessionFactory dùng chung cho toàn project
     */
    public static SessionFactory getSessionFactory() {
        return SessionFactoryHolder.INSTANCE;
    }

    /**
     * Shutdown SessionFactory khi tắt app
     */
    public static void shutdown() {
        SessionFactory sessionFactory = SessionFactoryHolder.INSTANCE;
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            try {
                sessionFactory.close();
                LOGGER.info("Hibernate SessionFactory closed successfully");
            } catch (HibernateException e) {
                LOGGER.warn("Lỗi khi đóng SessionFactory", e);
            }
        }
    }
}
