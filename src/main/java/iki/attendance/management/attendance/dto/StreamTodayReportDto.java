package iki.attendance.management.attendance.dto;

public class StreamTodayReportDto {

    private String className;
    private String stream;

    private Long male;
    private Long female;

    private Long present;
    private Long absent;
    private Long sick;

    public StreamTodayReportDto(String className, String stream, Long male, Long female,
                                Long present, Long absent, Long sick) {
        this.className = className;
        this.stream = stream;
        this.male = male;
        this.female = female;
        this.present = present;
        this.absent = absent;
        this.sick = sick;
    }

    public StreamTodayReportDto(){

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
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
}