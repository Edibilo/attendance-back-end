package iki.attendance.management.attendance.repository;

import iki.attendance.management.attendance.dto.AttendanceViewDto;
import iki.attendance.management.attendance.entity.Level;
import iki.attendance.management.attendance.entity.Student;
import iki.attendance.management.attendance.entity.StudentClassHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentClassHistoryRepository extends JpaRepository<StudentClassHistory, Long> {

    void deleteByStudent(Student student);

    @Query("""
            SELECT new iki.attendance.management.attendance.dto.AttendanceViewDto(
                s.student.id,
                CONCAT(
                    COALESCE(s.student.firstName, ''), ' ',
                    COALESCE(s.student.middleName, ''), ' ',
                    COALESCE(s.student.lastName, '')
                ),
                COALESCE(a.status, 'NOT_MARKED')
            )
            FROM StudentClassHistory s
            LEFT JOIN Attendance a
                ON a.student.id = s.student.id
                AND a.date = :date
            WHERE s.level.id = :levelId
            AND s.year = :year
            AND (
                :search IS NULL OR :search = '' OR
                LOWER(s.student.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(s.student.middleName) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(s.student.lastName) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(CONCAT(
                    s.student.firstName, ' ',
                    s.student.middleName, ' ',
                    s.student.lastName
                )) LIKE LOWER(CONCAT('%', :search, '%'))
            )
            """)
    Page<AttendanceViewDto> findStudentsByLevelAndDate(
            @Param("levelId") Long levelId,
            @Param("search") String search,
            @Param("date") LocalDate date,
            @Param("year") int year,
            Pageable pageable
    );

    @Query("""
                SELECT sch
                FROM StudentClassHistory sch
                WHERE

                (:levelId IS NULL
                    OR sch.level.id = :levelId)

                AND

                (:year IS NULL
                    OR sch.year = :year)

                AND
                (
                    :fullName IS NULL

                    OR LOWER(
                        CONCAT(
                            sch.student.firstName,
                            ' ',
                            sch.student.middleName,
                            ' ',
                            sch.student.lastName
                        )
                    )

                    LIKE LOWER(
                        CONCAT('%', :fullName, '%')
                    )
                )
            """)
    Page<StudentClassHistory> filterStudents(

            @Param("levelId")
            Long levelId,

            @Param("year")
            Integer year,

            @Param("fullName")
            String fullName,

            Pageable pageable
    );

    Optional<StudentClassHistory> findByStudentAndYear(Student student,int year);

    List<StudentClassHistory> findByLevelIdAndYear(Long levelId, int year);

    boolean existsByStudentAndYear(
            Student student,
            Integer year
    );
}
