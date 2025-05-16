package org.example.app;

import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hillel-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Student student = new Student();
        student.setFirstName("Ivan");
        student.setLastName("Petrov");
        student.setEmail("igiu@example.com");

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            System.out.println("Студент успешно сохранен!");
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            if (e.getCause() != null && e.getCause().getMessage().contains("Duplicate entry")) {
                System.out.println("Ошибка: студент с таким email уже существует.");
            } else {
                System.out.println("Произошла неизвестная ошибка при сохранении.");
                e.printStackTrace();
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
