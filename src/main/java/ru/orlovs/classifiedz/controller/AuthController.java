package ru.orlovs.classifiedz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.orlovs.classifiedz.controller.dto.LoginRequest;
import ru.orlovs.classifiedz.controller.dto.LoginResponse;
import ru.orlovs.classifiedz.controller.dto.RegisterRequest;
import ru.orlovs.classifiedz.security.AccountDetails;
import ru.orlovs.classifiedz.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public String register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
        return "OK";
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {
        String token = authService.login(req);
        return new LoginResponse(token);
    }

    @GetMapping(value = "/me")
    public AccountDetails me() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (AccountDetails) details;
    }
}
