package iki.attendance.management.attendance.dto;

public class SchoolSummaryDto {

    private Long totalStudents;
    private Long male;
    private Long female;

    private Long present;
    private Long absent;
    private Long sick;

    private Double attendanceRate;

    public SchoolSummaryDto(Long totalStudents, Long male, Long female, Long present,
                            Long absent, Long sick, Double attendanceRate) {
        this.totalStudents = totalStudents;
        this.male = male;
        this.female = female;
        this.present = present;
        this.absent = absent;
        this.sick = sick;
        this.attendanceRate = attendanceRate;
    }

    public SchoolSummaryDto(){

    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
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

    public Double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(Double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }
}