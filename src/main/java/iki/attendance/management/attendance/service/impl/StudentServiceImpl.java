package iki.attendance.management.attendance.service.impl;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.StudentDto;
import iki.attendance.management.attendance.entity.Level;
import iki.attendance.management.attendance.entity.StudentClassHistory;
import iki.attendance.management.attendance.entity.Student;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.AttendanceRepository;
import iki.attendance.management.attendance.repository.ClassRepository;
import iki.attendance.management.attendance.repository.StudentClassHistoryRepository;
import iki.attendance.management.attendance.repository.StudentRepository;
import iki.attendance.management.attendance.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final StudentClassHistoryRepository studentClassHistoryRepository;
    private final ModelMapper modelMapper;
    private final AttendanceRepository attendanceRepository;

    public StudentServiceImpl(StudentRepository studentRepository, ClassRepository classRepository, StudentClassHistoryRepository studentClassHistoryRepository, ModelMapper modelMapper, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.studentClassHistoryRepository = studentClassHistoryRepository;
        this.modelMapper = modelMapper;
        this.attendanceRepository = attendanceRepository;
    }


    @Override
    public StudentDto createStudent(StudentDto studentDto) {

       Student student=new Student();
       student.setDistrict(studentDto.getDistrict());
       student.setFirstName(studentDto.getFirstName());
       student.setWard(studentDto.getWard());
       student.setRegion(studentDto.getRegion());
       student.setGenderStatus(studentDto.getGenderStatus());
       student.setLastName(studentDto.getLastName());
       student.setBirthDate(studentDto.getBirthDate());
       student.setMiddleName(studentDto.getMiddleName());
       student.setParentName(studentDto.getParentName());
       student.setPhoneNumber(studentDto.getPhoneNumber());
       Student savedStudent=studentRepository.save(student);

       Level level=classRepository.findById(studentDto.getClassId()).orElseThrow(
               () -> new NotFoundException("Class Not Found !")
       );

       StudentClassHistory classHistory=new StudentClassHistory();
       classHistory.setStudent(student);
       classHistory.setLevel(level);
       classHistory.setYear(Year.now().getValue());
       studentClassHistoryRepository.save(classHistory);

       return modelMapper.map(savedStudent,StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students=studentRepository.findAll();
        return students.stream().map(student ->
                modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public MessageResponse deleteStudent(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student of that Id Not Found !"));
        studentClassHistoryRepository.deleteByStudent(student);
        attendanceRepository.deleteByStudent(student);
        attendanceRepository.flush();
        studentClassHistoryRepository.flush();
        studentRepository.delete(student);
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Deleted Successfully !");
        return messageResponse;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student of that Id Not Found !"));

        StudentClassHistory studentClassHistory=studentClassHistoryRepository.
                findByStudentAndYear(student,Year.now().getValue()).orElseThrow(
                        () -> new NotFoundException("Not Found !"));

        StudentDto dto=modelMapper.map(student,StudentDto.class);
        dto.setClassId(studentClassHistory.getLevel().getId());
        return dto;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long id) {
        Student student=studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student of that Id Not Found !"));
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setRegion(studentDto.getRegion());
        student.setWard(studentDto.getWard());
        student.setDistrict(studentDto.getDistrict());
        student.setBirthDate(studentDto.getBirthDate());
        student.setFirstName(studentDto.getFirstName());
        student.setParentName(studentDto.getParentName());
        student.setLastName(studentDto.getLastName());
        student.setMiddleName(studentDto.getMiddleName());
        student.setGenderStatus(studentDto.getGenderStatus());
        Student updatedStudent=studentRepository.save(student);

        return modelMapper.map(updatedStudent,StudentDto.class);
    }

}










