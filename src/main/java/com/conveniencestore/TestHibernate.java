package com.conveniencestore;

import org.hibernate.Session;

import com.conveniencestore.util.HibernateUtil;

public class TestHibernate {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("✅ Hibernate chạy OK");
        }
    }
}