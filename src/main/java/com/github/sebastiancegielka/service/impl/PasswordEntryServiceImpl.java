package com.github.sebastiancegielka.service.impl;

import com.github.sebastiancegielka.dto.PasswordEntryCreateDTO;
import com.github.sebastiancegielka.dto.PasswordEntryDTO;
import com.github.sebastiancegielka.dto.PasswordEntryFindAllDTO;
import com.github.sebastiancegielka.exception.EntryNotFoundException;
import com.github.sebastiancegielka.mapper.PasswordEntryMapper;
import com.github.sebastiancegielka.model.EncodingKey;
import com.github.sebastiancegielka.model.PasswordEntry;
import com.github.sebastiancegielka.repository.PasswordEntryRepository;
import com.github.sebastiancegielka.service.PasswordEntryService;
import com.github.sebastiancegielka.utility.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PasswordEntryServiceImpl implements PasswordEntryService {

    private PasswordEntryRepository repository;
    private PasswordEntryMapper mapper;
    private PasswordUtility passwordUtility;

    @Autowired
    public PasswordEntryServiceImpl(PasswordEntryRepository repository, PasswordEntryMapper mapper, PasswordUtility passwordUtility) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordUtility = passwordUtility;
    }

    @Override
    public PasswordEntryDTO findOneByUuid(String uuid) {
        PasswordEntry entry = repository.findByUuid(uuid);
        if (entry != null) {
            return mapper.toEntryDTO(passwordUtility.decryptPassword(entry));
        } else throw new EntryNotFoundException("uuid", uuid);
    }

    @Override
    public Set<PasswordEntryDTO> findByWebsite(String website) {
        Set<PasswordEntry> entry = repository.findAllByWebsite(website);
        Set<PasswordEntry> decryptedEntry = new HashSet<>();

        if(entry.isEmpty()){
            throw new EntryNotFoundException("website", website);
        }
        for (PasswordEntry passwordEntry : entry) {
            decryptedEntry.add(passwordUtility.decryptPassword(passwordEntry));
        }
        return decryptedEntry.stream()
                .map(x->mapper.toEntryDTO(x))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PasswordEntryFindAllDTO> findAll() {
        List<PasswordEntry> entryList = repository.findAll();

        return entryList.stream()
                .map(x->mapper.toEntryFindAllDTO(x))
                .collect(Collectors.toSet());
    }

    @Override
    public PasswordEntryFindAllDTO createEntry(PasswordEntryCreateDTO entry) {
        PasswordEntry newEntry = new PasswordEntry();
        if(passwordUtility.validate(entry)){
            try {
                PasswordEntryCreateDTO checkedEntry = passwordUtility.checkPassword(entry);

                String password = checkedEntry.getPassword();
                SecretKey key = passwordUtility.generateKey();
                String encryptedPass = passwordUtility.encryptPassword(password, key);

                newEntry = mapper.toEntry(entry);

                EncodingKey encryptKey = new EncodingKey();
                encryptKey.setSecretKey(key);
                encryptKey.setEntry(newEntry);

                newEntry.setPassword(encryptedPass);
                newEntry.setEncodingKey(encryptKey);

                repository.save(newEntry);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return mapper.toEntryFindAllDTO(newEntry);
    }

    @Override
    public void deleteEntryByUuid(String uuid) {
        PasswordEntry entry = repository.findByUuid(uuid);
        if(entry != null) {
            Long id = entry.getId();
            repository.deleteById(id);
        } else throw new EntryNotFoundException("uuid", uuid);
    }
}
