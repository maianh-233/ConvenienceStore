package com.conveniencestore.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil
 * - Tạo duy nhất 1 SessionFactory
 * - Load cấu hình từ hibernate.cfg.xml
 * - Dùng cho toàn bộ DAO
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml") // load từ resources
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("❌ Lỗi khởi tạo SessionFactory");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Lấy SessionFactory dùng chung
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Đóng SessionFactory khi tắt app
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
