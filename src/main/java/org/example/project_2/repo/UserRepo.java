package org.example.project_2.repo;

import jakarta.persistence.EntityManager;
import org.example.project_2.entity.Order;
import org.example.project_2.entity.User;

import java.util.List;

import static org.example.project_2.config.DBConfig.entityManagerFactory;

public class UserRepo {
    public static List<User> findAll() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<User> users = entityManager.createQuery("select u from User u join fetch u.orders", User.class)
                    .getResultList();
            entityManager.close();
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
