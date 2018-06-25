package com.github.sebastiancegielka.model;

import javax.persistence.*;

@Entity
public class SecretKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String secretKey;
    @OneToOne
    private PasswordEntry entry;

    public SecretKey() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public PasswordEntry getEntry() {
        return entry;
    }

    public void setEntry(PasswordEntry entry) {
        this.entry = entry;
    }

    public Long getId() {
        return id;
    }
}
