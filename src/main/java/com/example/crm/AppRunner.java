package com.example.crm;

import com.example.crm.model.User;
import com.example.crm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppRunner {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123")); // store encoded password
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }
            if (userRepository.findByUsername("sales").isEmpty()) {
                User u = new User();
                u.setUsername("sales");
                u.setPassword(encoder.encode("sales123"));
                u.setRole("SALES");
                userRepository.save(u);
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                User u = new User();
                u.setUsername("user");
                u.setPassword(encoder.encode("user123"));
                u.setRole("USER");
                userRepository.save(u);
            }
        };
    }
}
