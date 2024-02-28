package com.project.planner.services;

import com.project.planner.common.requests.AuthenticationRequest;
import com.project.planner.common.responses.AuthenticationResponse;
import com.project.planner.exceptions.EntityInstanceDoesNotExist;
import com.project.planner.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, AuthenticationManager authenticationManager, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AuthenticationResponse register(AuthenticationRequest registerRequest) {
        User newUser = new User(registerRequest.getEmail(), this.passwordEncoder.encode(registerRequest.getPassword()));

        User registeredUser = this.userService.registerNewUser(newUser);

        System.out.println("New User registered!");
        System.out.println(registeredUser.toString());

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getEmail(),
                        registerRequest.getPassword()
                )
        );

        var jwtToken = this.jwtService.generateToken(newUser);

        return AuthenticationResponse.builder()
                .setToken(jwtToken)
                .build();
    }

    public Boolean checkPassword(String email, String password) {
        return this.passwordEncoder.matches(password, this.userService.getUser(email).getPassword());
    }

    public  AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        if (!this.checkPassword(authenticationRequest.getEmail(), authenticationRequest.getPassword())) {
            throw new EntityInstanceDoesNotExist(HttpStatus.UNAUTHORIZED, "User don't exists");
        }

        User newUser = this.userService.getUser(authenticationRequest.getEmail());

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        var jwtToken = this.jwtService.generateToken(newUser);

        return AuthenticationResponse.builder()
                .setToken(jwtToken)
                .setEmail(authenticationRequest.getEmail())
                .build();
    }
}
