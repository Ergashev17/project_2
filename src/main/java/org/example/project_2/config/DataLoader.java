package org.example.project_2.config;

import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.project_2.entity.Order;
import org.example.project_2.entity.Status;
import org.example.project_2.entity.User;
import org.example.project_2.entity.enums.StatusName;
import org.example.project_2.repo.OrderRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.project_2.config.DBConfig.entityManager;
import static org.example.project_2.config.DBConfig.entityManagerFactory;

@WebListener
public class DataLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        entityManagerFactory = Persistence.createEntityManagerFactory("git_project");
        entityManager = entityManagerFactory.createEntityManager();
//        initData();
        ServletContextListener.super.contextInitialized(sce);
    }


    private void initData() {
        entityManager.getTransaction().begin();
        List<Order> orders = new ArrayList<>();
        List<Status> statuses = new ArrayList<>();
        for (StatusName statusName : StatusName.values()) {
            Status status = Status.builder()
                    .name(statusName)
                    .build();
            statuses.add(status);
            entityManager.persist(status);
        }
        int i = 0;
        Random random = new Random();
        if (OrderRepo.findAll().isEmpty()) {
            while (i < 3) {
                Order order = Order.builder()
                        .status(statuses.get(random.nextInt(0, 3)))
                        .build();
                orders.add(order);
                entityManager.persist(order);
                i++;
            }
        } else {
            System.out.println("Uje order table da malumot boru ");
        }

        User user1 = User.builder()
                .firstName("Hikmat")
                .lastName("Hikmatov")
                .age(24)
                .email("hikmat@gmail.com")
                .password("1")
                .orders(List.of(orders.get(random.nextInt(0, 3))))
                .build();
        User user2 = User.builder()
                .firstName("Toshmat")
                .lastName("Toshmatov")
                .age(22)
                .email("toshmat@gmail.com")
                .password("2")
                .orders(List.of(orders.get(random.nextInt(0, 3))))
                .build();
        User user3 = User.builder()
                .firstName("Eshmat")
                .lastName("Eshmatov")
                .email("eshmat@gmail.com")
                .age(21)
                .password("3")
                .orders(List.of(orders.get(random.nextInt(0, 3))))
                .build();


        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.getTransaction().commit();
    }

}
