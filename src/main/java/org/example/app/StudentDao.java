package org.example.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class StudentDao implements GenericDao<Student, Long> {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hillel-persistence-unit");

    @Override
    public void save(Student entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Student findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Student student = em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", email)
                .getSingleResult();
        em.close();
        return student;
    }

    @Override
    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        em.close();
        return students;
    }

    @Override
    public Student update(Student entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student updated = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return updated;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.getTransaction().begin();
            em.remove(student);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
}
