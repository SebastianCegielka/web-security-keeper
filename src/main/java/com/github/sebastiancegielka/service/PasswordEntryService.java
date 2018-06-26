package com.github.sebastiancegielka.service;

import com.github.sebastiancegielka.dto.PasswordEntryCreateDTO;
import com.github.sebastiancegielka.dto.PasswordEntryDTO;
import com.github.sebastiancegielka.dto.PasswordEntryFindAllDTO;

import java.util.Set;

public interface PasswordEntryService {

    PasswordEntryDTO findOneByUuid(String uuid);
    Set<PasswordEntryDTO> findByWebsite(String website);
    Set<PasswordEntryFindAllDTO> findAll();
    PasswordEntryFindAllDTO createEntry(PasswordEntryCreateDTO entry);
    void deleteEntryByUuid(String uuid);

}
