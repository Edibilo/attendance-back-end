package iki.attendance.management.attendance.dto;

public class StudentClassHistoryDto {
    private Long id;
    private Long studentId;
    private Long levelId;
    private int year;

    public StudentClassHistoryDto(Long id, Long studentId, Long levelId, int year) {
        this.id = id;
        this.studentId = studentId;
        this.levelId = levelId;
        this.year = year;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }
}
