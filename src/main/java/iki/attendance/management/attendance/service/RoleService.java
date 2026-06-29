package iki.attendance.management.attendance.service;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    List<RoleDto> getRoles();
    MessageResponse deleteRole(Long id);
    RoleDto getRoleById(Long id);
    RoleDto updateRole(RoleDto roleDto,Long id);
}
