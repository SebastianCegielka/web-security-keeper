package com.github.sebastiancegielka.repository;

import com.github.sebastiancegielka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String s);
    Optional<User> findById(Long id);
}
