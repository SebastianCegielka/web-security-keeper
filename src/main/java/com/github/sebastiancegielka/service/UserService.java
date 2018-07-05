package com.github.sebastiancegielka.service;

import com.github.sebastiancegielka.dto.UserDTO;
import com.github.sebastiancegielka.model.User;

public interface UserService {

    void createVerificationToken(User user);

    void saveVerifiedUser(User user);

    User findUserByEmail(String email);

    User saveUser(UserDTO userDTO);

    User findByToken(String token);
}
