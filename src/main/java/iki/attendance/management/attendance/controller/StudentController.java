package iki.attendance.management.attendance.controller;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.StudentDto;
import iki.attendance.management.attendance.entity.Student;
import iki.attendance.management.attendance.entity.StudentClassHistory;
import iki.attendance.management.attendance.service.StudentClassHistoryService;
import iki.attendance.management.attendance.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;
    private final StudentClassHistoryService studentClassHistoryService;


    public StudentController(StudentService studentService, StudentClassHistoryService studentClassHistoryService) {
        this.studentService = studentService;
        this.studentClassHistoryService = studentClassHistoryService;
    }

    //List All Students
    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> list=studentService.getAllStudents();
        return ResponseEntity.ok(list);
    }

    //Create New Student and its class history
    @PostMapping
    ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto){
        StudentDto dto=studentService.createStudent(studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //List All Student Based on Pagination Sorting and Filter
    @GetMapping
    public Page<StudentClassHistory> getStudents(

            @RequestParam(required = false)
            Long levelId,

            @RequestParam(required = false)
            Integer year,

            @RequestParam(required = false)
            String fullName,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size
    ) {

        return studentClassHistoryService.filterStudents(

                levelId,

                year,

                fullName,

                page,

                size
        );
    }

    //Delete Student
    @DeleteMapping("delete/{id}")
    ResponseEntity<MessageResponse> deleteStudent(@PathVariable Long id){
        MessageResponse messageResponse=studentService.deleteStudent(id);
        return ResponseEntity.ok(messageResponse);
    }

    //get student by id
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        StudentDto dto=studentService.getStudentById(id);
        return ResponseEntity.ok(dto);
    }

    //update student and student class history
    @PutMapping("edit/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto,@PathVariable Long id){
        StudentDto dto=studentService.updateStudent(studentDto,id);
        return ResponseEntity.ok(dto);
    }
}
