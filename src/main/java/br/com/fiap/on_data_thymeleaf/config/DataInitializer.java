package br.com.fiap.on_data_thymeleaf.config;

import br.com.fiap.on_data_thymeleaf.entity.Role;
import br.com.fiap.on_data_thymeleaf.entity.User;
import br.com.fiap.on_data_thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEnabled(true);
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEnabled(true);
            user.setRole(Role.USER);

            userRepository.save(user);
        }
    }
}
