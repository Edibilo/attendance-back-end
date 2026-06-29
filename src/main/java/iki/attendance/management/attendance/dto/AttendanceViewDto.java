package iki.attendance.management.attendance.dto;

import iki.attendance.management.attendance.enumerator.AttendanceStatus;

public class AttendanceViewDto {
    public Long studentId;
    public String fullName;
    public AttendanceStatus status;

    public AttendanceViewDto(Long studentId, String fullName, AttendanceStatus status) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.status = status;
    }
    public AttendanceViewDto(){

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

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
