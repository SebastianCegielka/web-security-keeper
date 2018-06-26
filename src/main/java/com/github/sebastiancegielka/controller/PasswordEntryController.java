package com.github.sebastiancegielka.controller;

import com.github.sebastiancegielka.dto.PasswordEntryCreateDTO;
import com.github.sebastiancegielka.dto.PasswordEntryDTO;
import com.github.sebastiancegielka.dto.PasswordEntryFindAllDTO;
import com.github.sebastiancegielka.service.PasswordEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/pass-manager")
public class PasswordEntryController {

    private PasswordEntryService passwordEntryService;

    @Autowired
    public PasswordEntryController(PasswordEntryService passwordEntryService) {
        this.passwordEntryService = passwordEntryService;
    }

    @GetMapping("/uuid/{uuid}")
    public PasswordEntryDTO findByUuid(@PathVariable String uuid){
        return passwordEntryService.findOneByUuid(uuid);
    }

    @GetMapping("/{website}")
    public Set<PasswordEntryDTO> findByWebsite(@PathVariable String website){
        return passwordEntryService.findByWebsite(website);
    }

    @GetMapping
    public Set<PasswordEntryFindAllDTO> findAll(){
        return passwordEntryService.findAll();
    }

    @PostMapping
    public PasswordEntryFindAllDTO createEntry(@RequestBody PasswordEntryCreateDTO passwordEntry){
        return passwordEntryService.createEntry(passwordEntry);
    }

    @DeleteMapping("/{uuid}")
    public void deleteEntry(@PathVariable String uuid){
        passwordEntryService.deleteEntryByUuid(uuid);
    }
}
