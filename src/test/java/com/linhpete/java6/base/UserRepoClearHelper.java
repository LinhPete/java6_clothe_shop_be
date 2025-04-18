package com.linhpete.java6.base;

import com.linhpete.java6.persistence.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class UserRepoClearHelper {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepoClearHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void clearAllUsersAndResetAutoIncrement() {
        try {
            userRepository.deleteAll();
            entityManager.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
        } catch (Exception e) {
            System.err.println("Không thể reset AUTO_INCREMENT hoặc xóa dữ liệu: " + e.getMessage());
        }
    }
}

