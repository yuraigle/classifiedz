package ru.orlovs.classifiedz.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin
@RepositoryRestResource(path = "users")
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
