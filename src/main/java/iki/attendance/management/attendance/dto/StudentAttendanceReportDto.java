package iki.attendance.management.attendance.dto;

public class StudentAttendanceReportDto {

    private Long studentId;

    private String fullName;

    private Long presentDays;

    private Long absentDays;

    private Long sickDays;

    private Long totalDays;

    private Double percentage;

    public StudentAttendanceReportDto(

            Long studentId,

            String fullName,

            Long presentDays,

            Long absentDays,

            Long sickDays,

            Long totalDays
    ) {

        this.studentId = studentId;

        this.fullName = fullName;

        this.presentDays = presentDays;

        this.absentDays = absentDays;

        this.sickDays = sickDays;

        this.totalDays = totalDays;

        if (totalDays == 0) {

            this.percentage = 0.0;

        } else {

            this.percentage =
                    (presentDays * 100.0)
                            / totalDays;
        }
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(Long presentDays) {
        this.presentDays = presentDays;
    }

    public Long getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(Long absentDays) {
        this.absentDays = absentDays;
    }

    public Long getSickDays() {
        return sickDays;
    }

    public void setSickDays(Long sickDays) {
        this.sickDays = sickDays;
    }

    public Long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Long totalDays) {
        this.totalDays = totalDays;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}