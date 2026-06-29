package iki.attendance.management.attendance.service;

import iki.attendance.management.attendance.dto.JwtApiResponse;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(UserDto userDto,Long id);
    MessageResponse deleteUser(Long id);
}
