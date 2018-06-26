package com.github.sebastiancegielka.repository;

import com.github.sebastiancegielka.model.EncodingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<EncodingKey, Long> {
}
