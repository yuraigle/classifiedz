package ru.orlovs.classifiedz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orlovs.classifiedz.domain.User;
import ru.orlovs.classifiedz.domain.UserRepo;
import ru.orlovs.classifiedz.security.JwtTokenProvider;
import ru.orlovs.classifiedz.controller.dto.LoginRequest;
import ru.orlovs.classifiedz.controller.dto.RegisterRequest;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void register(RegisterRequest req) {

        boolean exists = userRepository.findByEmail(req.getEmail()).isPresent();
        if (exists) {
            throw new AuthenticationServiceException("email.duplicate");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setName(req.getName());
        user.setCreatedAt(ZonedDateTime.now());
        user.setRole("member");
        userRepository.save(user);
    }

    public String login(LoginRequest req) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        return jwtProvider.createToken(authenticate);
    }

    @PostConstruct
    private void createAdmin() {
        long cnt = userRepository.count();
        if (cnt == 0) {
            User admin = new User();
            admin.setEmail("admin@adz.me");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setName("Administrator");
            admin.setCreatedAt(ZonedDateTime.now());
            admin.setRole("admin");
            userRepository.save(admin);

            log.info("1st admin user created. Email: admin@adz.me password: password");
        }
    }
}
