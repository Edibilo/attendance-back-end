package iki.attendance.management.attendance.controller;

import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.UserDto;
import iki.attendance.management.attendance.entity.User;
import iki.attendance.management.attendance.service.AuthService;
import iki.attendance.management.attendance.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }
   //get All Users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> list=userService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    //Get User By Id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto=userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    //Update User
    @PutMapping("edit/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Long id){
        UserDto dto=userService.updateUser(userDto,id);
        return ResponseEntity.ok(dto);
    }

    //Delete User
    @DeleteMapping("delete/{id}")
    ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id){
        MessageResponse messageResponse=userService.deleteUser(id);
        return ResponseEntity.ok(messageResponse);
    }

    //User Profile
    @GetMapping("profile")
    ResponseEntity<User> profile(){
        User user=authService.profile();
        return ResponseEntity.ok(user);
    }
}
