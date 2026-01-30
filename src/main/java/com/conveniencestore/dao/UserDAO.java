package com.conveniencestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.conveniencestore.entity.User;
import com.conveniencestore.util.HibernateUtil;

public class UserDAO {

    /* ================= ADD ================= */
    public boolean insert(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /* ================= UPDATE ================= */
    public boolean update(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /* ================= SOFT DELETE ================= */
    public boolean softDelete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user == null)
                return false;

            user.setActive(0);
            session.merge(user);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            return false;
        }
    }

    /* ================= RESTORE ================= */
    public boolean restore(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user == null)
                return false;

            user.setActive(1);
            session.merge(user);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            return false;
        }
    }

    /* ================= FIND BY ID ================= */
    public User findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("""
                        SELECT u
                        FROM User u
                        JOIN FETCH u.role
                        WHERE u.id = :id
                    """, User.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    /* ================= PAGINATION (NO FILTER) ================= */
    public List<User> findAll(int page, int size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("""
                        SELECT u
                        FROM User u
                        JOIN FETCH u.role
                        ORDER BY u.createdAt DESC
                    """, User.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

    /* ================= SEARCH + FILTER ================= */
    public List<User> searchAndFilter(
            String keyword,
            Integer active,
            Long roleId,
            int page,
            int size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            StringBuilder hql = new StringBuilder("""
                        SELECT u
                        FROM User u
                        JOIN FETCH u.role
                        WHERE 1=1
                    """);

            if (keyword != null && !keyword.isBlank()) {
                hql.append("""
                            AND (
                                LOWER(u.username) LIKE :kw
                                OR LOWER(u.fullName) LIKE :kw
                                OR LOWER(u.email) LIKE :kw
                                OR u.phone LIKE :kw
                            )
                        """);
            }

            if (active != null) {
                hql.append(" AND u.active = :active ");
            }

            if (roleId != null) {
                hql.append(" AND u.role.id = :roleId ");
            }

            hql.append(" ORDER BY u.createdAt DESC ");

            Query<User> query = session.createQuery(hql.toString(), User.class);

            if (keyword != null && !keyword.isBlank()) {
                query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            }
            if (active != null) {
                query.setParameter("active", active);
            }
            if (roleId != null) {
                query.setParameter("roleId", roleId);
            }

            return query
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        }
    }

    /* ================= COUNT ================= */
    public long countSearchAndFilter(
            String keyword,
            Integer active,
            Long roleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            StringBuilder hql = new StringBuilder("""
                        SELECT COUNT(u)
                        FROM User u
                        WHERE 1=1
                    """);

            if (keyword != null && !keyword.isBlank()) {
                hql.append("""
                            AND (
                                LOWER(u.username) LIKE :kw
                                OR LOWER(u.fullName) LIKE :kw
                                OR LOWER(u.email) LIKE :kw
                                OR u.phone LIKE :kw
                            )
                        """);
            }

            if (active != null)
                hql.append(" AND u.active = :active ");
            if (roleId != null)
                hql.append(" AND u.role.id = :roleId ");

            Query<Long> query = session.createQuery(hql.toString(), Long.class);

            if (keyword != null && !keyword.isBlank()) {
                query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            }
            if (active != null)
                query.setParameter("active", active);
            if (roleId != null)
                query.setParameter("roleId", roleId);

            return query.uniqueResult();
        }

    }

    // Kiểm tra email đã tồn tại hay chưa
    public boolean existsByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
                        SELECT COUNT(u.id)
                        FROM User u
                        WHERE u.email = :email
                        AND u.active = 1
                    """;

            Long count = session.createQuery(hql, Long.class)
                    .setParameter("email", email)
                    .uniqueResult();

            return count != null && count > 0;

        } catch (Exception e) {
            return true; // lỗi coi như trùng cho an toàn
        }
    }

    // Kiểm tra số điện thoại đã tồn tại hay chưa
    public boolean existsByPhone(String phone) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
                        SELECT COUNT(u.id)
                        FROM User u
                        WHERE u.phone = :phone
                          AND u.active = 1
                    """;

            Long count = session.createQuery(hql, Long.class)
                    .setParameter("phone", phone)
                    .uniqueResult();

            return count != null && count > 0;

        } catch (Exception e) {
            return true;
        }
    }
}
