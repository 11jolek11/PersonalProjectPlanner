package com.project.planner.configs;

import com.project.planner.exceptions.ResourceDoesNotExist;
import com.project.planner.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> this.userRepository.findUserByEmail(username)
                .orElseThrow(
                        () -> {
                            return new ResourceDoesNotExist(HttpStatus.UNAUTHORIZED,
                                    "User with provided credentials not found");
                        }
                );
    }

    @Bean
    public PasswordEncoder deafultPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
