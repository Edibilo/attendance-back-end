package iki.attendance.management.attendance.controller;

import iki.attendance.management.attendance.dto.*;
import iki.attendance.management.attendance.service.AttendanceService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("attendances")
@CrossOrigin("*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public Page<AttendanceViewDto> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(required = false) Long levelId,
            @RequestParam(required = false) String date
    ) {

        LocalDate attendanceDate;

        if (date == null || date.isBlank()) {
            attendanceDate = LocalDate.now();
        } else {
            attendanceDate = LocalDate.parse(date);
        }

        return attendanceService.getStudents(
                levelId,
                search,
                page,
                size,
                null,
                null,
                attendanceDate
        );
    }

    @PostMapping
    public void bulkAttendance(){

    }

    @PostMapping("/bulk")
    public ResponseEntity<MessageResponse> saveBulk(@RequestBody BulkAttendanceRequest request
    ) {

       MessageResponse messageResponse= attendanceService.saveBulk(request);

        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @GetMapping("/dashboards/{studentId}")
    public StudentAttendanceReportDto
    studentReport(

            @PathVariable Long studentId,

            @RequestParam String startDate,

            @RequestParam String endDate
    ) {

        return attendanceService
                .getStudentReport(

                        studentId,

                        LocalDate.parse(startDate),

                        LocalDate.parse(endDate)
                );
    }

    @GetMapping("/class-reports")
    public Page<ClassAttendanceReportDto>
    getClassWiseReport(

            @RequestParam String startDate,

            @RequestParam String endDate,

            @RequestParam(
                    defaultValue = "0"
            ) int page,

            @RequestParam(
                    defaultValue = "10"
            ) int size,

            @RequestParam(
                    defaultValue = "className"
            ) String sortBy,

            @RequestParam(
                    defaultValue = "asc"
            ) String direction
    ) {

        return attendanceService
                .getClassWiseReport(

                        LocalDate.parse(startDate),

                        LocalDate.parse(endDate),

                        page,

                        size,

                        sortBy,

                        direction
                );
    }

    @GetMapping("/stream-reports")
    public Page<StreamAttendanceReportDto>
    getStreamWiseReport(

            @RequestParam String startDate,

            @RequestParam String endDate,

            @RequestParam(
                    defaultValue = "0"
            ) int page,

            @RequestParam(
                    defaultValue = "10"
            ) int size,

            @RequestParam(
                    defaultValue = "className"
            ) String sortBy,

            @RequestParam(
                    defaultValue = "asc"
            ) String direction
    ) {

        return attendanceService
                .getStreamWiseReport(

                        LocalDate.parse(startDate),

                        LocalDate.parse(endDate),

                        page,

                        size,

                        sortBy,

                        direction
                );
    }

    @GetMapping("/today-dashboard")
    public ResponseEntity<TodayDashboardDto> todayDashboardDto(){
     TodayDashboardDto dto=attendanceService.todayDashboardDto();
     return ResponseEntity.ok(dto);
    }

}
