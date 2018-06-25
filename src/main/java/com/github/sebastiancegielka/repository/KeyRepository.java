package com.github.sebastiancegielka.repository;

import com.github.sebastiancegielka.model.SecretKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<SecretKey, Long> {
}
