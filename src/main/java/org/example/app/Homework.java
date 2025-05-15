package org.example.app;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "homework")

public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDate deadline;

    private int mark;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Геттери і сеттери

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public int getMark() { return mark; }
    public void setMark(int mark) { this.mark = mark; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Homework)) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", mark=" + mark +
                '}';
    }
}
