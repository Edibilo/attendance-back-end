package iki.attendance.management.attendance.service;

import iki.attendance.management.attendance.dto.JwtApiResponse;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.LoginDto;
import iki.attendance.management.attendance.dto.UserDto;
import iki.attendance.management.attendance.entity.User;

public interface AuthService {
    MessageResponse register(UserDto userDto);
    JwtApiResponse login(LoginDto loginDto);
    User profile();
}
