package iki.attendance.management.attendance.dto;

public class ClassAttendanceReportDto {

    private String className;

    private Long totalStudents;

    private Long streamCount;

    private Long maleCount;

    private Long femaleCount;

    private Long presentCount;

    private Long absentCount;

    private Long sickCount;

    private Long totalDays;

    private Double percentage;

    public ClassAttendanceReportDto(
            String className,
            Long totalStudents,
            Long streamCount,
            Long maleCount,
            Long femaleCount,
            Long presentCount,
            Long absentCount,
            Long sickCount,
            Long totalDays
    ) {

        this.className = className;

        this.totalStudents = totalStudents;

        this.streamCount = streamCount;

        this.maleCount = maleCount;

        this.femaleCount = femaleCount;

        this.presentCount = presentCount;

        this.absentCount = absentCount;

        this.sickCount = sickCount;

        this.totalDays = totalDays;

        this.percentage =
                (totalStudents == 0 || totalDays == 0)
                        ? 0.0
                        : (presentCount * 100.0) / (totalStudents * totalDays);
    }

    public ClassAttendanceReportDto(){

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getStreamCount() {
        return streamCount;
    }

    public void setStreamCount(Long streamCount) {
        this.streamCount = streamCount;
    }

    public Long getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(Long maleCount) {
        this.maleCount = maleCount;
    }

    public Long getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(Long femaleCount) {
        this.femaleCount = femaleCount;
    }

    public Long getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(Long presentCount) {
        this.presentCount = presentCount;
    }

    public Long getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(Long absentCount) {
        this.absentCount = absentCount;
    }

    public Long getSickCount() {
        return sickCount;
    }

    public void setSickCount(Long sickCount) {
        this.sickCount = sickCount;
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