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

    public static Order findById(int orderId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Order.class, orderId);
        } finally {
            entityManager.close();
        }
    }

    public static void edit(Order order) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(order);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
