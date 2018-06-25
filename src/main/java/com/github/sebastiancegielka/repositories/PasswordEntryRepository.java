package com.github.sebastiancegielka.repositories;

import com.github.sebastiancegielka.model.PasswordEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Long> {
}
