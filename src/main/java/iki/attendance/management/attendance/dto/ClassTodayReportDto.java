package iki.attendance.management.attendance.dto;

public class ClassTodayReportDto {

    private String className;

    private Long students;
    private Long male;
    private Long female;

    private Long present;
    private Long absent;
    private Long sick;

    private Double percentage;

    public ClassTodayReportDto(String className, Long students, Long male, Long female,
                               Long present, Long absent, Long sick, Double percentage) {
        this.className = className;
        this.students = students;
        this.male = male;
        this.female = female;
        this.present = present;
        this.absent = absent;
        this.sick = sick;
        this.percentage = percentage;
    }

    public ClassTodayReportDto(){

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getStudents() {
        return students;
    }

    public void setStudents(Long students) {
        this.students = students;
    }

    public Long getMale() {
        return male;
    }

    public void setMale(Long male) {
        this.male = male;
    }

    public Long getFemale() {
        return female;
    }

    public void setFemale(Long female) {
        this.female = female;
    }

    public Long getPresent() {
        return present;
    }

    public void setPresent(Long present) {
        this.present = present;
    }

    public Long getAbsent() {
        return absent;
    }

    public void setAbsent(Long absent) {
        this.absent = absent;
    }

    public Long getSick() {
        return sick;
    }

    public void setSick(Long sick) {
        this.sick = sick;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}