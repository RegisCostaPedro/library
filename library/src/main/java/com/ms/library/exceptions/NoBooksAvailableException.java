package com.ms.library.exceptions;

public class NoBooksAvailableException extends RuntimeException{

    public NoBooksAvailableException(){
        super("Book not available for loan or reservation.");
    }

    public NoBooksAvailableException(String message){
        super(message);
    }
}
