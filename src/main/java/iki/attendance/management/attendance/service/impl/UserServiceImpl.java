package iki.attendance.management.attendance.service.impl;

import iki.attendance.management.attendance.dto.JwtApiResponse;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.UserDto;
import iki.attendance.management.attendance.entity.Role;
import iki.attendance.management.attendance.entity.User;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.RoleRepository;
import iki.attendance.management.attendance.repository.UserRepository;
import iki.attendance.management.attendance.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found !"));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        Role role=roleRepository.findById(userDto.getRoleId()).orElseThrow(() ->
                new NotFoundException("Not Found !"));
        User user=userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not Found !"));
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(role);
        User updatedUser=userRepository.save(user);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public MessageResponse deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not Found !"));
        userRepository.delete(user);
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Deleted Successfully !");
        return messageResponse;
    }
}
