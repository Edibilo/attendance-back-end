package iki.attendance.management.attendance.service;

import iki.attendance.management.attendance.dto.ClassDto;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.RoleDto;

import java.util.List;

public interface ClassService {
    ClassDto createClass(ClassDto classDto);
    List<ClassDto> getAllClasses();
    MessageResponse deleteClass(Long id);
    ClassDto getClassById(Long id);
    ClassDto updateClass(ClassDto classDto,Long id);
}
