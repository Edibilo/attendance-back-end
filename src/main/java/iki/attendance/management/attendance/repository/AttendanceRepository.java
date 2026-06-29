package iki.attendance.management.attendance.repository;

import iki.attendance.management.attendance.dto.*;
import iki.attendance.management.attendance.entity.Attendance;
import iki.attendance.management.attendance.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    void deleteByStudent(Student student);

    // 🔍 Find attendance for one student
    Optional<Attendance> findByStudentIdAndLevelIdAndDate(
            Long studentId,
            Long levelId,
            LocalDate date
    );

    @Query("""
            SELECT new iki.attendance.management.attendance.dto.StudentAttendanceReportDto(

                s.id,

                CONCAT(
                    s.firstName, ' ',
                    s.middleName, ' ',
                    s.lastName
                ),

                SUM(
                    CASE
                        WHEN a.status = 'PRESENT'
                        THEN 1L
                        ELSE 0L
                    END
                ),

                SUM(
                    CASE
                        WHEN a.status = 'ABSENT'
                        THEN 1L
                        ELSE 0L
                    END
                ),

                SUM(
                    CASE
                        WHEN a.status = 'SICK'
                        THEN 1L
                        ELSE 0L
                    END
                ),

                COUNT(a.id)

            )

            FROM Attendance a

            JOIN a.student s

            WHERE s.id = :studentId

            AND a.date BETWEEN :startDate
            AND :endDate

            GROUP BY
                s.id,
                s.firstName,
                s.middleName,
                s.lastName
            """)
    StudentAttendanceReportDto
    getStudentAttendanceReport(

            @Param("studentId")
            Long studentId,

            @Param("startDate")
            LocalDate startDate,

            @Param("endDate")
            LocalDate endDate
    );

    @Query("""
                SELECT new iki.attendance.management.attendance.dto.ClassAttendanceReportDto(

                    l.name,

                    COUNT(DISTINCT s.id),

                    COUNT(DISTINCT l.stream),

                    COUNT(DISTINCT CASE
                        WHEN s.genderStatus = 'MALE'
                        THEN s.id
                    END),

                    COUNT(DISTINCT CASE
                        WHEN s.genderStatus = 'FEMALE'
                        THEN s.id
                    END),

                    COALESCE(SUM(CASE
                        WHEN a.status = 'PRESENT'
                        THEN 1 ELSE 0
                    END), 0L),

                    COALESCE(SUM(CASE
                        WHEN a.status = 'ABSENT'
                        THEN 1 ELSE 0
                    END), 0L),

                    COALESCE(SUM(CASE
                        WHEN a.status = 'SICK'
                        THEN 1 ELSE 0
                    END), 0L),

                    COUNT(DISTINCT a.date)

                )

                FROM StudentClassHistory sch

                JOIN sch.student s
                JOIN sch.level l

                LEFT JOIN Attendance a
                    ON a.student.id = s.id
                    AND a.date BETWEEN :startDate AND :endDate

                GROUP BY l.name

                ORDER BY l.name
            """)
    List<ClassAttendanceReportDto> getClassWiseReport(

            @Param("startDate")
            LocalDate startDate,

            @Param("endDate")
            LocalDate endDate
    );

    @Query("""
                SELECT new iki.attendance.management.attendance.dto.StreamAttendanceReportDto(

                    TRIM(l.name),

                    TRIM(l.stream),

                    COUNT(DISTINCT s.id),

                    COUNT(DISTINCT CASE
                        WHEN s.genderStatus = 'MALE'
                        THEN s.id
                    END),

                    COUNT(DISTINCT CASE
                        WHEN s.genderStatus = 'FEMALE'
                        THEN s.id
                    END),

                    COALESCE(SUM(CASE
                        WHEN a.status = 'PRESENT'
                        THEN 1 ELSE 0
                    END), 0L),

                    COALESCE(SUM(CASE
                        WHEN a.status = 'ABSENT'
                        THEN 1 ELSE 0
                    END), 0L),

                    COALESCE(SUM(CASE
                        WHEN a.status = 'SICK'
                        THEN 1 ELSE 0
                    END), 0L),

                    COUNT(DISTINCT a.date)

                )

                FROM StudentClassHistory sch

                JOIN sch.student s

                JOIN sch.level l

                LEFT JOIN Attendance a
                    ON a.student.id = s.id
                    AND a.date BETWEEN :startDate AND :endDate

                GROUP BY TRIM(l.name), TRIM(l.stream)

                ORDER BY TRIM(l.name), TRIM(l.stream)
            """)
    Page<StreamAttendanceReportDto>
    getStreamWiseReport(

            @Param("startDate")
            LocalDate startDate,

            @Param("endDate")
            LocalDate endDate,

            Pageable pageable
    );

    @Query("""
            SELECT
            COALESCE(COUNT(DISTINCT s.id),0),
            COALESCE(SUM(CASE WHEN s.genderStatus='MALE' THEN 1 ELSE 0 END),0),
            COALESCE(SUM(CASE WHEN s.genderStatus='FEMALE' THEN 1 ELSE 0 END),0)
            FROM Student s
            """)
    Object getSchoolStudents();

    @Query("""
            SELECT
            COALESCE(SUM(CASE WHEN a.status='PRESENT' THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN a.status='ABSENT' THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN a.status='SICK' THEN 1 ELSE 0 END), 0)
            FROM Attendance a
            WHERE a.date = CURRENT_DATE
            """)
    Object getTodayAttendance();

    @Query("""
            SELECT new iki.attendance.management.attendance.dto.ClassTodayReportDto(

                l.name,

                COUNT(DISTINCT s.id),

                SUM(CASE WHEN s.genderStatus='MALE' THEN 1 ELSE 0 END),
                SUM(CASE WHEN s.genderStatus='FEMALE' THEN 1 ELSE 0 END),

                SUM(CASE WHEN a.status='PRESENT' THEN 1 ELSE 0 END),
                SUM(CASE WHEN a.status='ABSENT' THEN 1 ELSE 0 END),
                SUM(CASE WHEN a.status='SICK' THEN 1 ELSE 0 END),

                0.0

            )

            FROM Attendance a
            JOIN a.student s
            JOIN StudentClassHistory sch ON sch.student.id = s.id
            JOIN sch.level l

            WHERE a.date = CURRENT_DATE
            GROUP BY l.name
            """)
    List<ClassTodayReportDto> getClassWiseToday();

    @Query("""
            SELECT new iki.attendance.management.attendance.dto.StreamTodayReportDto(

                l.name,
                l.stream,

                SUM(CASE WHEN s.genderStatus='MALE' THEN 1 ELSE 0 END),
                SUM(CASE WHEN s.genderStatus='FEMALE' THEN 1 ELSE 0 END),

                SUM(CASE WHEN a.status='PRESENT' THEN 1 ELSE 0 END),
                SUM(CASE WHEN a.status='ABSENT' THEN 1 ELSE 0 END),
                SUM(CASE WHEN a.status='SICK' THEN 1 ELSE 0 END)

            )

            FROM Attendance a
            JOIN a.student s
            JOIN StudentClassHistory sch ON sch.student.id = s.id
            JOIN sch.level l

            WHERE a.date = CURRENT_DATE
            GROUP BY l.name, l.stream
            """)
    List<StreamTodayReportDto> getStreamWiseToday();

}
