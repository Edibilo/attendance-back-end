package iki.attendance.management.attendance.controller;

import iki.attendance.management.attendance.dto.ClassDto;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.service.ClassService;
import iki.attendance.management.attendance.service.StudentClassHistoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("classes")
@RestController
@CrossOrigin("*")
public class ClassController {

    private final ClassService classService;
    private final StudentClassHistoryService studentClassHistoryService;

    public ClassController(ClassService classService, StudentClassHistoryService studentClassHistoryService) {
        this.classService = classService;
        this.studentClassHistoryService = studentClassHistoryService;
    }


    //create new class
    @PostMapping
    public ResponseEntity<ClassDto> createClass(@Valid @RequestBody ClassDto classDto){
        ClassDto classDto1=classService.createClass(classDto);
        return new ResponseEntity<>(classDto1, HttpStatus.CREATED);
    }

    //get all classes
    @GetMapping
    public ResponseEntity<List<ClassDto>> getAllClasses(){
        List<ClassDto> list=classService.getAllClasses();
        return ResponseEntity.ok(list);
    }

    //delete class Id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageResponse> deleteClass(@PathVariable Long id){
       MessageResponse messageResponse= classService.deleteClass(id);
       return ResponseEntity.ok(messageResponse);
    }

    //get class by Id
    @GetMapping("{id}")
    public ResponseEntity<ClassDto> deleteClassById(@PathVariable Long id){
        ClassDto dto=classService.getClassById(id);
        return ResponseEntity.ok(dto);
    }

    //updated class
    @PutMapping("edit/{id}")
    public ResponseEntity<ClassDto> updateClass(@RequestBody ClassDto classDto,@PathVariable Long id){
        ClassDto dto=classService.updateClass(classDto,id);
        return ResponseEntity.ok(classDto);
    }

    @PostMapping("promote")
    public ResponseEntity<MessageResponse> promote(@RequestParam Long fromLevelId,@RequestParam Long toLevelId){
        MessageResponse s=studentClassHistoryService.promoteClass(fromLevelId,toLevelId);
        return ResponseEntity.ok(s);
    }
}
