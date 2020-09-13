package ru.orlovs.classifiedz;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.orlovs.classifiedz.controller.dto.RegisterRequest;
import ru.orlovs.classifiedz.domain.UserRepo;
import ru.orlovs.classifiedz.service.AuthService;

@Log4j2
@Component
@RequiredArgsConstructor
public class ClassifiedzStartupRunner implements ApplicationRunner {

    private final AuthService authService;
    private final UserRepo userRepo;

    @Override
    public void run(ApplicationArguments args) {
        seedUsers();
    }

    private void seedUsers() {
        if (userRepo.count() == 0) {
            authService.register(new RegisterRequest("admin@adz.me", "password", "Yuri Orlov"));
            authService.register(new RegisterRequest("member@adz.me", "password", "John Doe"));

            userRepo.findByEmail("admin@adz.me").ifPresent(user -> {
                user.setRole("admin");
                userRepo.save(user);
            });

            log.info("1st admin created. email: admin@adz.me password: password");
        }
    }
}
