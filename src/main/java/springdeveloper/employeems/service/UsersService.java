package springdeveloper.employeems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springdeveloper.employeems.dto.ChangeOwnPasswordRequest;
import springdeveloper.employeems.dto.ChangePasswordRequest;
import springdeveloper.employeems.dto.UserDTO;
import springdeveloper.employeems.model.Users;
import springdeveloper.employeems.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Users registerUser(Users user) {
        if(usersRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already taken");
        } else if (user.getPassword().length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters");
        }
        user.setCreatedAt(LocalDateTime.now());
        // رمز عبور را با BCrypt رمزنگاری کن
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return usersRepository.save(user);
    }

    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean login(String username, String rawPassword) {
        return usersRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(false);
    }
    public List<UserDTO> getAllUsers() {
        List<Users> users = usersRepository.findAll();

        List<UserDTO> dtos = users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole()
                ))
                .collect(Collectors.toList());

        return dtos;
    }


    public boolean deleteUserById(Integer id) {
        if(usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String changePassword(ChangePasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        Users user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(encodedPassword);

        usersRepository.save(user);
        return "Password updated successfully";
    }

    public String changeOwnPassword(ChangeOwnPasswordRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        if (request.getNewPassword().length() < 6) {
            throw new RuntimeException("New password must be at least 6 characters");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("New password and confirmation do not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        usersRepository.save(user);
        return "Password successfully updated for " + username;
    }

}

