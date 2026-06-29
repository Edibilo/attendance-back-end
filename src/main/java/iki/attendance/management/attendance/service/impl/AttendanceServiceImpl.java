package iki.attendance.management.attendance.service.impl;

import iki.attendance.management.attendance.dto.*;
import iki.attendance.management.attendance.entity.Attendance;
import iki.attendance.management.attendance.entity.Level;
import iki.attendance.management.attendance.entity.Student;
import iki.attendance.management.attendance.enumerator.AttendanceStatus;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.AttendanceRepository;
import iki.attendance.management.attendance.repository.ClassRepository;
import iki.attendance.management.attendance.repository.StudentClassHistoryRepository;
import iki.attendance.management.attendance.repository.StudentRepository;
import iki.attendance.management.attendance.service.AttendanceService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final StudentClassHistoryRepository studentClassHistoryRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, ClassRepository classRepository, StudentRepository studentRepository, StudentClassHistoryRepository studentClassHistoryRepository) {
        this.attendanceRepository = attendanceRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.studentClassHistoryRepository = studentClassHistoryRepository;
    }

    @Override
    public Page<AttendanceViewDto> getStudents(
            Long levelId,
            String search,
            int page,
            int size,
            String sortBy,
            String dir,
            LocalDate date
    ) {

        if (levelId == null) {
            throw new IllegalArgumentException("levelId is required");
        }

        int year = date.getYear();

        Sort sort = Sort.by(
                dir != null && dir.equalsIgnoreCase("DESC")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC,
                sortBy != null ? sortBy : "student.firstName"
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return studentClassHistoryRepository.findStudentsByLevelAndDate(
                levelId,
                search == null ? "" : search.trim(),
                date,
                year,
                pageable
        );
    }
    @Override
    public MessageResponse saveBulk(BulkAttendanceRequest bulkAttendanceRequest) {
        List<Attendance> list = new ArrayList<>();

        Level level = classRepository
                .findById(bulkAttendanceRequest.levelId)
                .orElseThrow(() -> new RuntimeException("Level not found"));

        for (AttendanceItem i : bulkAttendanceRequest.records) {

            Student student = studentRepository
                    .findById(i.studentId)
                    .orElseThrow(() -> new NotFoundException("Student not found"));

            Attendance att = attendanceRepository
                    .findByStudentIdAndLevelIdAndDate(
                            i.studentId,
                            bulkAttendanceRequest.levelId,
                            bulkAttendanceRequest.date
                    )
                    .orElse(new Attendance());

            att.setStudent(student);
            att.setLevel(level);
            att.setDate(bulkAttendanceRequest.date);
            att.setStatus(AttendanceStatus.valueOf(i.status));
            list.add(att);
        }

        attendanceRepository.saveAll(list);
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Saved Successfully !");
        return messageResponse;
    }

    public StudentAttendanceReportDto
    getStudentReport(

            Long studentId,

            LocalDate startDate,

            LocalDate endDate
    ) {

        StudentAttendanceReportDto dto =

                attendanceRepository
                        .getStudentAttendanceReport(

                                studentId,

                                startDate,

                                endDate
                        );

        if (dto == null) {

            return new StudentAttendanceReportDto(

                    studentId,

                    "No Record",

                    0L,

                    0L,

                    0L,

                    0L
            );
        }

        return dto;
    }

    @Override
    public Page<ClassAttendanceReportDto> getClassWiseReport(
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        List<ClassAttendanceReportDto> list =
                attendanceRepository.getClassWiseReport(startDate, endDate);

        // calculate percentage
        list.forEach(r -> {

            double percentage =
                    (r.getTotalStudents() == 0 || r.getTotalDays() == 0)
                            ? 0
                            : (r.getPresentCount() * 100.0)
                            / (r.getTotalStudents() * r.getTotalDays());

            // round to 2 decimal places
            r.setPercentage(
                    Math.round(percentage * 100.0) / 100.0
            );
        });

        // pagination only
        int start = page * size;
        int end = Math.min(start + size, list.size());

        List<ClassAttendanceReportDto> content =
                list.subList(start, end);

        return new PageImpl<>(
                content,
                PageRequest.of(page, size),
                list.size()
        );
    }

    @Override
    public Page<StreamAttendanceReportDto> getStreamWiseReport(LocalDate startDate, LocalDate endDate,
                                                               int page, int size, String sortBy, String direction) {
        Pageable pageable =
                PageRequest.of(page, size);

        Page<StreamAttendanceReportDto> reports =
                attendanceRepository
                        .getStreamWiseReport(
                                startDate,
                                endDate,
                                pageable
                        );

        reports.forEach(report -> {

            double percentage = 0;

            if (
                    report.getTotalStudents() > 0
                            &&
                            report.getTotalDays() > 0
            ) {

                percentage =
                        (
                                report.getPresentCount()
                                        * 100.0
                        )
                                /
                                (
                                        report.getTotalStudents()
                                                *
                                                report.getTotalDays()
                                );
            }

            report.setPercentage(
                    Math.round(percentage * 100.0) / 100.0
            );
        });

        return reports;
    }

    @Override
    public TodayDashboardDto todayDashboardDto() {
        TodayDashboardDto dto = new TodayDashboardDto();

        dto.setDate(LocalDate.now().toString());

        // ================= SCHOOL =================
        Object[] school = (Object[]) attendanceRepository.getSchoolStudents();
        Object[] att = (Object[]) attendanceRepository.getTodayAttendance();

        SchoolSummaryDto summary = new SchoolSummaryDto();

        summary.setTotalStudents((Long) school[0]);
        summary.setMale((Long) school[1]);
        summary.setFemale((Long) school[2]);

        summary.setPresent((Long) att[0]);
        summary.setAbsent((Long) att[1]);
        summary.setSick((Long) att[2]);

        long total = (Long) att[0] + (Long) att[1] + (Long) att[2];

        double rate = total == 0 ? 0.0 :
                ((Long) att[0] * 100.0 / total);

        summary.setAttendanceRate(
                Math.round(rate * 100.0) / 100.0
        );

        dto.setSchoolSummary(summary);

        // ================= CLASS =================
        dto.setClassWise(attendanceRepository.getClassWiseToday());

        // ================= STREAM =================
        dto.setStreamWise(attendanceRepository.getStreamWiseToday());

        return dto;
    }


}




