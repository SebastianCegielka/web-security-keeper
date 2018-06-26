package com.github.sebastiancegielka.exception;

public class EntryNotFoundException extends RuntimeException {

    private String param;
    private String value;

    public EntryNotFoundException(String param, String value) {
        this.param = param;
        this.value = value;
    }

    public EntryNotFoundException() {
    }

    public String message(){
        return "Entry of given" + param + " = " + value + "was not found";
    }
}
