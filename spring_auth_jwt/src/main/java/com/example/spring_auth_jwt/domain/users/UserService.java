package com.example.spring_auth_jwt.domain.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring_auth_jwt.domain.users.dto.UserRequestDTO;
import com.example.spring_auth_jwt.security.encoder.PepperPasswordEncoder;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PepperPasswordEncoder pepperPasswordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).get();
    }

    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> pepperPasswordEncoder.matches(password, user.getPassword()));
    }

    public long countAdmins() {
        return userRepository.countByRole(UserRole.ADMIN);
    }

    public void registerUser(UserRequestDTO dto) {
        if(this.userRepository.findByUsername(dto.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        String encryptedPassword = pepperPasswordEncoder.encode(dto.password());
        User user = new User(
            dto.username(),
            encryptedPassword,
            UserRole.USER
        );
        this.userRepository.save(user);
    }

    public void registerAdmin(UserRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        String encryptedPassword = pepperPasswordEncoder.encode(dto.password());
        User user = new User(
            dto.username(),
            encryptedPassword,
            UserRole.ADMIN
        );
        userRepository.save(user);
    }
    
}
