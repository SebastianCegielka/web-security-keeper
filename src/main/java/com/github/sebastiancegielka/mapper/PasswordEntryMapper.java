package com.github.sebastiancegielka.mapper;

import com.github.sebastiancegielka.dto.PasswordEntryCreateDTO;
import com.github.sebastiancegielka.dto.PasswordEntryDTO;
import com.github.sebastiancegielka.model.PasswordEntry;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PasswordEntryMapper {
    PasswordEntryDTO toEntryDTO(PasswordEntry entry);


    PasswordEntry toEntry(PasswordEntryCreateDTO createEntry);
}
