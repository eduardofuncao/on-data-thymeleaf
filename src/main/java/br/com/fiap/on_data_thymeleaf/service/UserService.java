package br.com.fiap.on_data_thymeleaf.service;

import br.com.fiap.on_data_thymeleaf.entity.Role;
import br.com.fiap.on_data_thymeleaf.entity.User;
import br.com.fiap.on_data_thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public User createUser(User user, String roleName) {
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign role
        try {
            Role role = Role.valueOf(roleName);
            user.setRole(role);
        } catch (IllegalArgumentException e) {
            user.setRole(Role.USER); // Default role
        }
        System.out.println("here");

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userDetails, String roleName) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());

            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }

            user.setEnabled(userDetails.isEnabled());

            try {
                Role role = Role.valueOf(roleName);
                user.setRole(role);
            } catch (IllegalArgumentException e) {
                // mantém role caso não encontre
            }

            return userRepository.save(user);
        }
        return null;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
