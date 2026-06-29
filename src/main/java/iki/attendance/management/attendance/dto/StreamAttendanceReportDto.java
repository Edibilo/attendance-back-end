package iki.attendance.management.attendance.dto;

public class StreamAttendanceReportDto {

    private String className;

    private String stream;

    private Long totalStudents;

    private Long maleCount;

    private Long femaleCount;

    private Long presentCount;

    private Long absentCount;

    private Long sickCount;

    private Long totalDays;

    private Double percentage;

    public StreamAttendanceReportDto(
            String className,
            String stream,
            Long totalStudents,
            Long maleCount,
            Long femaleCount,
            Long presentCount,
            Long absentCount,
            Long sickCount,
            Long totalDays
    ) {

        this.className = className;
        this.stream = stream;
        this.totalStudents = totalStudents;
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
        this.presentCount = presentCount;
        this.absentCount = absentCount;
        this.sickCount = sickCount;
        this.totalDays = totalDays;
    }

    public String getClassName() {
        return className;
    }

    public String getStream() {
        return stream;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public Long getMaleCount() {
        return maleCount;
    }

    public Long getFemaleCount() {
        return femaleCount;
    }

    public Long getPresentCount() {
        return presentCount;
    }

    public Long getAbsentCount() {
        return absentCount;
    }

    public Long getSickCount() {
        return sickCount;
    }

    public Long getTotalDays() {
        return totalDays;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}