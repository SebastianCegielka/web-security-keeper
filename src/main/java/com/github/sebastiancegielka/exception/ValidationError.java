package com.github.sebastiancegielka.exception;

public class ValidationError {

    private String desciption;
    private String field;

    public ValidationError(String desciption, String field) {
        this.desciption = desciption;
        this.field = field;
    }

    public ValidationError() {
    }

    public String getDesciption() {
        return desciption;
    }

    public String getField() {
        return field;
    }

    @Override
    public String toString() {
        return String.format("{\"field\":\"%s\","+
        "\"description\":\"%s\"}", field, desciption);
    }
}
