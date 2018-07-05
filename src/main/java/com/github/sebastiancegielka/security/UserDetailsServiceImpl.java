package com.github.sebastiancegielka.security;

import com.github.sebastiancegielka.model.User;
import com.github.sebastiancegielka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(s);

        return new UserDetailsImpl(byEmail.orElseThrow(() -> new UsernameNotFoundException(s)));
    }
}

