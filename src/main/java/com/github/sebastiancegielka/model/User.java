package com.github.sebastiancegielka.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private Set<PasswordEntry> entries;

    private boolean enabled;

    public User(@NotNull String email, @NotNull String password, Set<PasswordEntry> entries, boolean enabled) {
        this.email = email;
        this.password = password;
        this.entries = entries;
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PasswordEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<PasswordEntry> entries) {
        this.entries = entries;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }
}
