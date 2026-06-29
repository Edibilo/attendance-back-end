package iki.attendance.management.attendance.controller;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.RoleDto;
import iki.attendance.management.attendance.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    //Create New role
    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto){
        RoleDto roleDto1=roleService.createRole(roleDto);
        return new ResponseEntity<>(roleDto1, HttpStatus.CREATED);
    }

    //Get All Role
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> list=roleService.getRoles();
        return ResponseEntity.ok(list);
    }

    //Delete Role
    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageResponse> deleteRole(@PathVariable Long id){
        MessageResponse roleDto=roleService.deleteRole(id);
        return ResponseEntity.ok(roleDto);
    }

    //Get Single Role By Id
    @GetMapping("{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
        RoleDto roleDto=roleService.getRoleById(id);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<RoleDto> editRole(@RequestBody RoleDto roleDto,@PathVariable Long id){
        RoleDto roleDto1=roleService.updateRole(roleDto,id);
        return ResponseEntity.ok(roleDto1);
    }
}
