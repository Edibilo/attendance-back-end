package iki.attendance.management.attendance.service;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.StudentDto;
import iki.attendance.management.attendance.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    List<StudentDto> getAllStudents();
    MessageResponse deleteStudent(Long id);
    StudentDto getStudentById(Long id);
    StudentDto updateStudent(StudentDto studentDto,Long id);
}
