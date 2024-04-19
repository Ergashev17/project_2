package org.example.project_2.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.project_2.entity.Order;

import java.util.List;

import static org.example.project_2.config.DBConfig.entityManagerFactory;

public class OrderRepo {

    public static List<Order> findAll() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<Order> orders = entityManager.createQuery("SELECT o FROM Order o", Order.class)
                    .getResultList();
            entityManager.close();
            return orders;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
