package iki.attendance.management.attendance.dto;

import java.time.LocalDate;
import java.util.List;

public class BulkAttendanceRequest {
    public Long levelId;
    public LocalDate date;
    public List<AttendanceItem> records;

    public BulkAttendanceRequest(){

    }

    public BulkAttendanceRequest(Long levelId, LocalDate date, List<AttendanceItem> records) {
        this.levelId = levelId;
        this.date = date;
        this.records = records;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<AttendanceItem> getRecords() {
        return records;
    }

    public void setRecords(List<AttendanceItem> records) {
        this.records = records;
    }
}
