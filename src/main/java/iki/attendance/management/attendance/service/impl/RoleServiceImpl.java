package iki.attendance.management.attendance.service.impl;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.RoleDto;
import iki.attendance.management.attendance.entity.Role;
import iki.attendance.management.attendance.exception.AlreadyFound;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.RoleRepository;
import iki.attendance.management.attendance.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        if(roleRepository.existsByName(roleDto.getName())){
            throw new AlreadyFound("Role Already Added!");
        }
        Role role=modelMapper.map(roleDto,Role.class);
        Role savedRole=roleRepository.save(role);
        return modelMapper.map(savedRole,RoleDto.class);
    }

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles=roleRepository.findAll();
        return roles.stream().map(role -> modelMapper.map(role,RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public MessageResponse deleteRole(Long id) {
        Role role=roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found !"));
        roleRepository.delete(role);
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Deleted Successfully !");;
        return messageResponse;
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role role=roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role Not Found !"));
        return modelMapper.map(role,RoleDto.class);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Long id) {
        if(roleRepository.existsByName(roleDto.getName())){
            throw new AlreadyFound("Role Already Added!");
        }
        Role role=roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role Not Found !"));
        role.setName(roleDto.getName());
        Role updatedRole=roleRepository.save(role);
        return modelMapper.map(updatedRole,RoleDto.class);
    }

}
