package iki.attendance.management.attendance.controller;

import iki.attendance.management.attendance.dto.JwtApiResponse;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.LoginDto;
import iki.attendance.management.attendance.dto.UserDto;
import iki.attendance.management.attendance.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    private ResponseEntity<MessageResponse> createUser(@RequestBody UserDto userDto){
        MessageResponse apiResponse=authService.register(userDto);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<JwtApiResponse> login(@Valid @RequestBody LoginDto loginDto){
        JwtApiResponse apiResponse=authService.login(loginDto);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }
}
