package iki.attendance.management.attendance.dto;

public class AttendanceItem {
    public Long studentId;
    public String status;

    public AttendanceItem(Long studentId, String status) {
        this.studentId = studentId;
        this.status = status;
    }
    public AttendanceItem(){

    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
