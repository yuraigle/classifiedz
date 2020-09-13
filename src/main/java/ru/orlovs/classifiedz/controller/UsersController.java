package ru.orlovs.classifiedz.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.orlovs.classifiedz.domain.User;
import ru.orlovs.classifiedz.domain.UserRepo;

import java.util.Optional;

@AllArgsConstructor
@PreAuthorize("hasAuthority('admin')")
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserRepo userRepo;

    @GetMapping(path = {"", "/", "/list"}, produces = "application/hal+json")
    public Page<User> list(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @GetMapping(path = "/{id}", produces = "application/hal+json")
    public Optional<User> read(@PathVariable Long id) {
        return userRepo.findById(id);
    }
}
