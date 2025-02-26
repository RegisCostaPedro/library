package com.ms.library.exceptions;

public class LoanDevolutionStatusException extends RuntimeException {

  public LoanDevolutionStatusException() {
    super("The book's  already been returned.");
  }

    public LoanDevolutionStatusException(String message) {
        super(message);
    }
}
