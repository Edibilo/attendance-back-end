package iki.attendance.management.attendance.service;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.entity.StudentClassHistory;
import org.springframework.data.domain.Page;

public interface StudentClassHistoryService {
    Page<StudentClassHistory> filterStudents(

            Long levelId,

            Integer year,

            String fullName,

            int page,

            int size
    );

    MessageResponse promoteClass(
            Long fromLevelId,
            Long toLevelId
    );
}
