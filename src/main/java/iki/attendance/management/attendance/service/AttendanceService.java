package iki.attendance.management.attendance.service;

import iki.attendance.management.attendance.dto.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface AttendanceService {

    Page<AttendanceViewDto> getStudents(
            Long levelId,
            String search,
            int page,
            int size,
            String sortBy,
            String dir,
            LocalDate date);

    MessageResponse saveBulk(BulkAttendanceRequest bulkAttendanceRequest);

    StudentAttendanceReportDto getStudentReport(

            Long studentId,

            LocalDate startDate,

            LocalDate endDate
    );

    Page<ClassAttendanceReportDto>
    getClassWiseReport(

            LocalDate startDate,

            LocalDate endDate,

            int page,

            int size,

            String sortBy,

            String direction
    );


    Page<StreamAttendanceReportDto>
    getStreamWiseReport(

            LocalDate startDate,

            LocalDate endDate,

            int page,

            int size,

            String sortBy,

            String direction
    );

    public TodayDashboardDto todayDashboardDto();

}
