package com.github.sebastiancegielka.service;

import com.github.sebastiancegielka.model.PasswordEntry;

import java.util.Set;

public interface PasswordEntryService {

    PasswordEntry findOneById(Long id);
    Set<PasswordEntry> findByWebsite(String website);
    Set<PasswordEntry> findAll();
    PasswordEntry createEntry(PasswordEntry entry);
    void deleteEntryById(Long id);
}
