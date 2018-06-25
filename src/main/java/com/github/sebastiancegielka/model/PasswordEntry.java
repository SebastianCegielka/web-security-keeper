package com.github.sebastiancegielka.model;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
public class PasswordEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid = UUID.randomUUID().toString();
    private String website;
    private String login;
    @Lob
    private char[] password;
    @OneToOne(mappedBy = "entry",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private SecretKey secretKey;

    public PasswordEntry() {
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        StringBuilder sb = new StringBuilder();
        for (char c : password) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void setPassword(String pass) {
        this.password = pass.toCharArray();
    }

    public String getUuid() {
        return uuid;
    }

    public Long getId() {
        return id;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordEntry that = (PasswordEntry) o;
        return Objects.equals(website, that.website) &&
                Objects.equals(login, that.login) &&
                Arrays.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(website, login);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "PasswordEntry{" +
                "id=" + id +
                ", website='" + website + '\'' +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
