package ru.orlovs.classifiedz.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
