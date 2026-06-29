package iki.attendance.management.attendance.service.impl;

import iki.attendance.management.attendance.dto.JwtApiResponse;
import iki.attendance.management.attendance.dto.MessageResponse;
import iki.attendance.management.attendance.dto.LoginDto;
import iki.attendance.management.attendance.dto.UserDto;
import iki.attendance.management.attendance.entity.Role;
import iki.attendance.management.attendance.entity.User;
import iki.attendance.management.attendance.exception.AlreadyFound;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.RoleRepository;
import iki.attendance.management.attendance.repository.UserRepository;
import iki.attendance.management.attendance.security.JwtUtilsTokenProvider;
import iki.attendance.management.attendance.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilsTokenProvider jwtUtilsTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, AuthenticationManager authenticationManager,
                           JwtUtilsTokenProvider jwtUtilsTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtilsTokenProvider = jwtUtilsTokenProvider;
    }

    @Override
    public MessageResponse register(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new AlreadyFound("Email Already Used !");
        }
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role=roleRepository.findByName("ROLE_TEACHER").orElseThrow(
                () ->new NotFoundException("Role Not Found !"));
        user.setRole(role);

        userRepository.save(user);
        MessageResponse apiResponse=new MessageResponse();
        apiResponse.setMessage("Saved Successfully");
        return apiResponse;
    }

    @Override
    public JwtApiResponse login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getNameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtApiResponse apiResponse=new JwtApiResponse();
        apiResponse.setToken(jwtUtilsTokenProvider.generateToken(authentication));
        apiResponse.setRole(profile().getRole().getName());
        apiResponse.setUsername(profile().getName());
        return apiResponse;
    }

    public User profile() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String username=authentication.getName();
        return userRepository.findByNameOrEmail(username,username).orElseThrow(() ->
                new NotFoundException("Not Found !"));
    }

}
