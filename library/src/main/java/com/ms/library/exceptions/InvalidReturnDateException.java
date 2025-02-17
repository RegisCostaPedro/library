package com.ms.library.exceptions;

public class InvalidReturnDateException extends RuntimeException {

  public InvalidReturnDateException(){
    super("The return date cannot be a date before the loan registration date.");
  }

    public InvalidReturnDateException(String message) {
        super(message);
    }
}
