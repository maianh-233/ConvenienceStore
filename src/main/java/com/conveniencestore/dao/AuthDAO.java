package com.conveniencestore.dao;

import com.conveniencestore.DTO.UserRequestDTO;
import com.conveniencestore.entity.User;
import com.conveniencestore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.conveniencestore.entity.Role;

import java.util.Optional;

public class AuthDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthDAO.class);

    // 1. Tìm bằng Username (Dùng Optional để an toàn)
    public Optional<User> findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.username = :username AND u.active = 1";
            return session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .uniqueResultOptional();
        } catch (Exception e) {
            LOGGER.error("Lỗi findByUsername: {}", username, e);
            return Optional.empty();
        }
    }

    // 2. Tìm bằng Email
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email = :email AND u.active = 1";
            return session.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (Exception e) {
            LOGGER.error("Lỗi findByEmail: {}", email, e);
            return Optional.empty();
        }
    }

    // 3. Tìm bằng Refresh Token Hash
    public Optional<User> findByRefreshTokenHash(String hash) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.refreshTokenHash = :hash";
            return session.createQuery(hql, User.class)
                    .setParameter("hash", hash)
                    .uniqueResultOptional();
        } catch (Exception e) {
            LOGGER.error("Lỗi findByRefreshTokenHash", e);
            return Optional.empty();
        }
    }

    // 4. Cập nhật User (Có Transaction bảo vệ)
    public boolean update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            LOGGER.error("Lỗi khi update User ID: {}", user.getId(), e);
            return false;
        }
    }

    // 5. Thêm hàm Tìm bằng Reset Password Token (Dành cho quên mật khẩu)
    public Optional<User> findByResetTokenHash(String hash) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u FROM User u WHERE u.resetPasswordTokenHash = :hash";
            return session.createQuery(hql, User.class)
                    .setParameter("hash", hash)
                    .uniqueResultOptional();
        } catch (Exception e) {
            LOGGER.error("Lỗi findByResetTokenHash", e);
            return Optional.empty();
        }
    }

    // 6. Tìm bằng ID (Dùng Optional để an toàn)
    public Optional<User> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.id = :id AND u.active = 1";
            return session.createQuery(hql, User.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            LOGGER.error("Lỗi findById: {}", id, e);
            return Optional.empty();
        }
    }

    // 7. Chỉnh sửa thông tin
    public User updateUserInfo(UserRequestDTO dto) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            User user = session.createQuery(
                "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.id = :id",
                User.class
            ).setParameter("id", dto.getId())
            .uniqueResult();

            if (user == null || user.getActive() == 0) {
                return null;
            }

            user.setUsername(dto.getUsername());
            user.setFullName(dto.getFullName());
            user.setDateOfBirth(dto.getDateOfBirth());
            user.setEmail(dto.getEmail());
            user.setPhone(dto.getPhone());
            user.setAddress(dto.getAddress());
            user.setGender(dto.getGender());

            if (dto.getPassword() != null) {
                user.setPasswordHash(dto.getPassword());
            }

            session.merge(user);
            tx.commit();
            return user;

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Lỗi updateUserInfo User ID: {}", dto.getId(), e);
            return null;
        }
    }

    public User updateUserAvatar(Long userId, String imgUrl, String imgUrlID) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            User user = session.createQuery(
                "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.id = :id",
                User.class
            ).setParameter("id", userId)
            .uniqueResult();

            if (user == null || user.getActive() == 0) {
                return null;
            }

            user.setImgUrl(imgUrl);
            user.setImgUrlID(imgUrlID);

            session.merge(user);
            tx.commit();
            return user;

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Lỗi updateUserAvatar User ID: {}", userId, e);
            return null;
        }
    }

}