package com.github.sebastiancegielka.service.impl;

import com.github.sebastiancegielka.dto.PasswordEntryCreateDTO;
import com.github.sebastiancegielka.dto.PasswordEntryDTO;
import com.github.sebastiancegielka.mapper.PasswordEntryMapper;
import com.github.sebastiancegielka.model.SecretKey;
import com.github.sebastiancegielka.model.PasswordEntry;
import com.github.sebastiancegielka.repository.PasswordEntryRepository;
import com.github.sebastiancegielka.service.PasswordEntryService;
import com.github.sebastiancegielka.utility.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PasswordEntryDTO findOneById(Long id) {
        PasswordEntry entry = repository.findById(id).orElseThrow(RuntimeException::new);
        return mapper.toEntryDTO(passwordUtility.decryptPassword(entry));
    }

    @Override
    public PasswordEntryDTO findOneByUuid(String uuid) {
        PasswordEntry entry = repository.findByUuid(uuid);
        if (entry != null) {
            return mapper.toEntryDTO(passwordUtility.decryptPassword(entry));
        } else throw new RuntimeException();
    }

    @Override
    public Set<PasswordEntryDTO> findByWebsite(String website) {
        Set<PasswordEntry> entry = repository.findAllByWebsite(website);
        Set<PasswordEntry> decryptedEntry = new HashSet<>();

        if(entry.isEmpty()){
            throw new RuntimeException();
        }
        for (PasswordEntry passwordEntry : entry) {
            decryptedEntry.add(passwordUtility.decryptPassword(passwordEntry));
        }
        return decryptedEntry.stream()
                .map(x->mapper.toEntryDTO(x))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PasswordEntryDTO> findAll() {
        List<PasswordEntry> entryList = repository.findAll();
        List<PasswordEntry> decryptedEntryList = new ArrayList<>();
        for (PasswordEntry passwordEntry : entryList) {
            decryptedEntryList.add(passwordUtility.decryptPassword(passwordEntry));
        }
        return decryptedEntryList.stream()
                .map(x->mapper.toEntryDTO(x))
                .collect(Collectors.toSet());
    }

    @Override
    public PasswordEntryDTO createEntry(PasswordEntryCreateDTO entry) {
        PasswordEntry newEntry = new PasswordEntry();
        if(passwordUtility.validate(entry)){
            PasswordEntryCreateDTO checkedEntry = passwordUtility.checkPassword(entry);

            String password = checkedEntry.getPassword();
            String key = passwordUtility.generateString(8);

            String encryptedPass = passwordUtility.encryptPassword(password, key);

            newEntry = mapper.toEntry(entry);

            SecretKey encryptKey = new SecretKey();
            encryptKey.setSecretKey(key);
            encryptKey.setEntry(newEntry);

            newEntry.setPassword(encryptedPass);
            newEntry.setSecretKey(encryptKey);

            repository.save(newEntry);
        }
        return mapper.toEntryDTO(newEntry);
    }

    @Override
    public void deleteEntryById(Long id) {
        repository.deleteById(id);
    }


}
