package iki.attendance.management.attendance.service.impl;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.entity.Level;
import iki.attendance.management.attendance.entity.Student;
import iki.attendance.management.attendance.entity.StudentClassHistory;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.ClassRepository;
import iki.attendance.management.attendance.repository.StudentClassHistoryRepository;
import iki.attendance.management.attendance.service.StudentClassHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class StudentClassHistoryServiceImpl implements StudentClassHistoryService {

    private final StudentClassHistoryRepository studentClassHistoryRepository;
    private final ClassRepository classRepository;

    public StudentClassHistoryServiceImpl(StudentClassHistoryRepository studentClassHistoryRepository, ClassRepository classRepository) {
        this.studentClassHistoryRepository = studentClassHistoryRepository;
        this.classRepository = classRepository;
    }


    @Override
    public Page<StudentClassHistory> filterStudents(Long levelId, Integer year, String fullName, int page, int size) {
        return studentClassHistoryRepository.filterStudents(

                levelId,

                year,

                fullName,

                PageRequest.of(page, size)
        );
    }

    @Override
    public MessageResponse promoteClass(Long fromLevelId, Long toLevelId) {
        int currentYear = Year.now().getValue();
        int nextYear = currentYear + 1;

        // 1. GET CURRENT CLASS STUDENTS
        List<StudentClassHistory> students =
                studentClassHistoryRepository
                        .findByLevelIdAndYear(fromLevelId, currentYear);

        if (students.isEmpty()) {
            return new MessageResponse("No students found in selected class");
        }

        // 2. GET NEXT CLASS
        Level nextLevel = classRepository.findById(toLevelId)
                .orElseThrow(() -> new NotFoundException("Next class not found"));

        int promotedCount = 0;

        // 3. PROMOTE LOOP
        for (StudentClassHistory history : students) {

            Student student = history.getStudent();

            // ❗ PREVENT DUPLICATE PROMOTION
            boolean alreadyExists =
                    studentClassHistoryRepository
                            .existsByStudentAndYear(student, nextYear);

            if (alreadyExists) {
                continue;
            }

            StudentClassHistory newRecord = new StudentClassHistory();
            newRecord.setStudent(student);
            newRecord.setLevel(nextLevel);
            newRecord.setYear(nextYear);

            studentClassHistoryRepository.save(newRecord);

            promotedCount++;
        }

        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage(promotedCount+" students promoted successfully");
        return messageResponse;
    }
    }

