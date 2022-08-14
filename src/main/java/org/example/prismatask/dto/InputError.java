package org.example.prismatask.dto;

public class InputError {

    private String message;

    public InputError() {
    }

    public InputError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
