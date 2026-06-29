package iki.attendance.management.attendance.security;

import iki.attendance.management.attendance.entity.User;
import iki.attendance.management.attendance.exception.NotFoundException;
import iki.attendance.management.attendance.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user=userRepository.findByNameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(
                () -> new NotFoundException("Incorrect Login Credentials")
        );

        Set<GrantedAuthority> authorities= Collections.singleton(
                new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
