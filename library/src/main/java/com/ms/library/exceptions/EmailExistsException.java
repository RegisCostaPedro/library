package com.ms.library.exceptions;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException() {
        super("This email is already in use.");
    }

    public EmailExistsException(String message) {
        super(message);
    }
}
