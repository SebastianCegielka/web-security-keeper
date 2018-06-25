package com.github.sebastiancegielka.repository;

import com.github.sebastiancegielka.dto.PasswordEntryDTO;
import com.github.sebastiancegielka.model.PasswordEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Long> {
    PasswordEntry findByUuid(String uuid);
    Set<PasswordEntry> findAllByWebsite(String website);
}
