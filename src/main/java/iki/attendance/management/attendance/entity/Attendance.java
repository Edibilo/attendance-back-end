package iki.attendance.management.attendance.entity;

import iki.attendance.management.attendance.enumerator.AttendanceStatus;
import iki.attendance.management.attendance.enumerator.GenderStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "attendances", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "level_id", "date"})
})
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Level level;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
    private LocalDate date;

    @PrePersist
    void prePersist() {
        if (status == null) {
            status = AttendanceStatus.PRESENT;
        }
    }

    public Attendance(Long id, Student student, Level level, AttendanceStatus status, LocalDate date) {
        this.id = id;
        this.student = student;
        this.level = level;
        this.status = status;
        this.date = date;
    }

    public Attendance() {

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

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
