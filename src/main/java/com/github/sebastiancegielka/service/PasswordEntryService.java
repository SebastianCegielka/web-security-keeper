package com.github.sebastiancegielka.service;

import com.github.sebastiancegielka.dto.PasswordEntryCreateDTO;
import com.github.sebastiancegielka.dto.PasswordEntryDTO;
import com.github.sebastiancegielka.model.PasswordEntry;

import java.util.Set;

public interface PasswordEntryService {

    PasswordEntryDTO findOneById(Long id);
    PasswordEntryDTO findOneByUuid(String uuid);
    Set<PasswordEntryDTO> findByWebsite(String website);
    Set<PasswordEntryDTO> findAll();
    PasswordEntryDTO createEntry(PasswordEntryCreateDTO entry);
    void deleteEntryById(Long id);
}
