package com.ms.library.exceptions;

public class BookInUseException extends RuntimeException {

    public BookInUseException() {
        super("The book is already reserved, try to reserve it at another time.");
    }

    public BookInUseException(String message) {
        super(message);
    }
}
