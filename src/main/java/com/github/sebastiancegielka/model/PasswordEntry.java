package com.github.sebastiancegielka.model;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class PasswordEntry extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String website;
    private String login;
    @Lob
    private char[] password;
    @OneToOne(mappedBy = "entry",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private EncodingKey encodingKey;

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


    public Long getId() {
        return id;
    }

    public EncodingKey getEncodingKey() {
        return encodingKey;
    }

    public void setEncodingKey(EncodingKey encodingKey) {
        this.encodingKey = encodingKey;
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
}
