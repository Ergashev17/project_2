package org.example.project_2.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.project_2.entity.Order;
import org.example.project_2.entity.Status;

import java.util.List;

import static org.example.project_2.config.DBConfig.entityManagerFactory;

public class StatusRepo {


    public static List<Status> findAll() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            List<Status> statuses = entityManager.createQuery("SELECT s FROM Status s", Status.class)
                    .getResultList();
            entityManager.close();
            return statuses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Status findById(int statusId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Status.class, statusId);
        } finally {
            entityManager.close();
        }
    }

    public static void edit(Status status) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(status);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
}
