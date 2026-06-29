package iki.attendance.management.attendance.service.impl;

import iki.attendance.management.attendance.dto.ClassDto;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.RoleDto;
import iki.attendance.management.attendance.entity.Level;
import iki.attendance.management.attendance.exception.AlreadyFound;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.ClassRepository;
import iki.attendance.management.attendance.service.ClassService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ModelMapper modelMapper;

    public ClassServiceImpl(ClassRepository classRepository, ModelMapper modelMapper) {
        this.classRepository = classRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClassDto createClass(ClassDto classDto) {
        if(classRepository.existsByNameAndStream(classDto.getName(),classDto.getStream())){
            throw new AlreadyFound("Already Found !");
        }
        Level aClass=modelMapper.map(classDto, Level.class);
        Level savedClass=classRepository.save(aClass);
        return modelMapper.map(savedClass,ClassDto.class);
    }

    @Override
    public List<ClassDto> getAllClasses() {
        List<Level> classes=classRepository.findAll();
        return classes.stream().map(aClass -> modelMapper.map(aClass,ClassDto.class)).collect(Collectors.toList());
    }

    @Override
    public MessageResponse deleteClass(Long id) {
        Level level=classRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not Found !"));
        classRepository.delete(level);
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Deleted Successfully !");
        return messageResponse;
    }

    @Override
    public ClassDto getClassById(Long id) {
        Level level=classRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not Found !"));
        return modelMapper.map(level,ClassDto.class);
    }

    @Override
    public ClassDto updateClass(ClassDto classDto, Long id) {
        Level level=classRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not Found !"));
        level.setName(classDto.getName());
        level.setStream(classDto.getStream());
        Level updatedClass=classRepository.save(level);
        return modelMapper.map(updatedClass,ClassDto.class);
    }
}
