package com.github.sebastiancegielka.model;

import javax.crypto.SecretKey;
import javax.persistence.*;

@Entity
public class EncodingKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private SecretKey secretKey;
    @OneToOne
    private PasswordEntry entry;

    public EncodingKey() {
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
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
