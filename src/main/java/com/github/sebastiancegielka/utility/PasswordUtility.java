package com.github.sebastiancegielka.utility;

import com.github.sebastiancegielka.utility.cipher.PasswordSecurity;
import com.github.sebastiancegielka.dto.PasswordEntryCreateDTO;
import com.github.sebastiancegielka.exception.ValidationError;
import com.github.sebastiancegielka.exception.ValidationException;
import com.github.sebastiancegielka.model.PasswordEntry;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordUtility {

    private PasswordSecurity passwordSecurity;

    @Autowired
    public PasswordUtility(PasswordSecurity passwordSecurity) {
        this.passwordSecurity = passwordSecurity;
    }

    public boolean validate(PasswordEntryCreateDTO entry){
        List<ValidationError> errors = new ArrayList<>();
        if(entry.getLogin() == null){
            ValidationError error = new ValidationError("Login", "May not be null");
            errors.add(error);
        } else if (entry.getLogin().isEmpty()){
            ValidationError error = new ValidationError("Login", "May not be empty");
            errors.add(error);
        }
        if(entry.getWebsite() == null){
            ValidationError error = new ValidationError("Website", "May not be null");
            errors.add(error);
        } else if (entry.getWebsite().isEmpty()){
            ValidationError error = new ValidationError("Website", "May not be empty");
            errors.add(error);
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        } else return true;
    }

    public PasswordEntryCreateDTO checkPassword(PasswordEntryCreateDTO entry){
        if(entry.getPassword().isEmpty() || entry.getPassword()==null){
            entry.setPassword(generateString(12));
        }
        return entry;
    }

    public String generateString(int strLength){
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        return randomStringGenerator.generate(strLength);
    }

    public PasswordEntry decryptPassword(PasswordEntry entry){
        String pass = passwordSecurity.decrypt(entry.getPassword(), entry.getSecretKey().getSecretKey());
        entry.setPassword(pass);
        return entry;
    }

    public String encryptPassword(String password, String key){
        return passwordSecurity.encrypt(password, key);
    }
}
