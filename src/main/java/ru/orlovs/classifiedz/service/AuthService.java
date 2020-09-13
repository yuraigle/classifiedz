package ru.orlovs.classifiedz.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orlovs.classifiedz.controller.dto.LoginRequest;
import ru.orlovs.classifiedz.controller.dto.LoginResponse;
import ru.orlovs.classifiedz.controller.dto.RegisterRequest;
import ru.orlovs.classifiedz.domain.User;
import ru.orlovs.classifiedz.domain.UserRepo;
import ru.orlovs.classifiedz.security.JwtTokenProvider;

import java.time.ZonedDateTime;

@Log4j2
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(RegisterRequest req) {

        boolean exists = userRepo.findByEmail(req.getEmail()).isPresent();
        if (exists) {
            throw new AuthenticationServiceException("email.duplicate");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setName(req.getName());
        user.setCreatedAt(ZonedDateTime.now());
        user.setRole("member");
        userRepo.save(user);
    }

    public LoginResponse login(LoginRequest req) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        String token = jwtTokenProvider.createToken(authenticate);
        return new LoginResponse(token);
    }
}
