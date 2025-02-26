package com.ms.library.exceptions;

public class LoanQuantityByStudentException extends RuntimeException {

  public LoanQuantityByStudentException() {
    super("An student can only take out one loan at a time.");
  }

    public LoanQuantityByStudentException(String message) {
        super(message);
    }
}
