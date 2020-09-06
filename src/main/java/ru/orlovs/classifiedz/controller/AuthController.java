package ru.orlovs.classifiedz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.orlovs.classifiedz.controller.dto.LoginRequest;
import ru.orlovs.classifiedz.controller.dto.LoginResponse;
import ru.orlovs.classifiedz.controller.dto.RegisterRequest;
import ru.orlovs.classifiedz.controller.dto.UserDto;
import ru.orlovs.classifiedz.security.AccountDetails;
import ru.orlovs.classifiedz.service.AuthService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
        return authService.login(req);
    }

    // это надо из бд брать
    @GetMapping("/me")
    public Map<String, UserDto> me() {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails details = (AccountDetails) authenticate.getPrincipal();
        Map<String, UserDto> result = new HashMap<>();
        result.put("user", new UserDto(details.getId(), details.getEmail(), details.getRole()));
        return result;
    }
}
