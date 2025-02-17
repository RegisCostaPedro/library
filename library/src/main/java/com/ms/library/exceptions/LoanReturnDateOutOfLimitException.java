package com.ms.library.exceptions;

public class LoanReturnDateOutOfLimitException extends RuntimeException {

  public LoanReturnDateOutOfLimitException() {
    super("The return date of loan can't be more than 15 days.");
  }

    public LoanReturnDateOutOfLimitException(String message) {
        super(message);
    }
}
