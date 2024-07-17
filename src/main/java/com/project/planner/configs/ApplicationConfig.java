package com.project.planner.configs;

import com.project.planner.exceptions.ResourceDoesNotExist;
import com.project.planner.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaAuditing
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

    @Bean
    public AuthenticationProvider authenticationProvider() {
         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
         daoAuthenticationProvider.setPasswordEncoder(deafultPasswordEncoder());
         daoAuthenticationProvider.setUserDetailsService(userDetailsService());

         return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager defaultAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
