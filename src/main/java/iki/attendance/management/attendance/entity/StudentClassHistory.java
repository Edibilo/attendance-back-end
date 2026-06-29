package iki.attendance.management.attendance.entity;

import jakarta.persistence.*;

@Table(name = "classHistory")
@Entity
public class StudentClassHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Level level;
    private int year;

    public StudentClassHistory(Long id, Student student, Level level, int year) {
        this.id = id;
        this.student = student;
        this.level = level;
        this.year = year;
    }

    public StudentClassHistory(){

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
