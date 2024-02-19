package com.example.school.management.system.exceptions;

public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException(String type, int id) {
        this(type, "id", String.valueOf(id));
    }

    public IllegalOperationException(String type, String attribute, String value) {
        super(String.format("%s with %s %s not found.", type, attribute, value));
    }

    public IllegalOperationException(String notFoundError) {
    }
}
