package com.github.sebastiancegielka.dto;

public class PasswordEntryFindAllDTO {
    private String uuid;
    private String website;
    private String login;

    public PasswordEntryFindAllDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
}
