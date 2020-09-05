package ru.orlovs.classifiedz.controller.dto;

import lombok.Data;

@Data
public class ErrorMessage {

    private final String message;
    private final String field;

    public ErrorMessage(String message) {
        this.message = message;
        this.field = null;
    }

    public ErrorMessage(String message, String field) {
        this.message = message;
        this.field = field;
    }
}
