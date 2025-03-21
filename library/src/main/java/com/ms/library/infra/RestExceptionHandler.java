package com.ms.library.infra;

import com.ms.library.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoBooksAvailableException.class)
    private ResponseEntity<RestErrorMessage> noBooksAvailableHandler(NoBooksAvailableException exception) {
        RestErrorMessage threatErrorResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatErrorResponse);
    }

    @ExceptionHandler(BookInUseException.class)
    private ResponseEntity<RestErrorMessage> bookInUserException(BookInUseException exception) {
        RestErrorMessage threatErrorResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatErrorResponse);
    }

    @ExceptionHandler(InvalidReturnDateException.class)
    private ResponseEntity<RestErrorMessage> invalidReturnDateException(InvalidReturnDateException exception) {
        RestErrorMessage threatErrorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatErrorResponse);
    }


    @ExceptionHandler(LoanReturnDateOutOfLimitException.class)
    private ResponseEntity<RestErrorMessage> LoanReturnDateOutOfLimitException(LoanReturnDateOutOfLimitException exception) {
        RestErrorMessage threatErrorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatErrorResponse);
    }

    @ExceptionHandler(LoanQuantityByStudentException.class)
    private ResponseEntity<RestErrorMessage> LoanQuantityByStudentException(LoanQuantityByStudentException exception) {
        RestErrorMessage threatErrorResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatErrorResponse);
    }

    @ExceptionHandler(LoanDevolutionStatusException.class)
    private ResponseEntity<RestErrorMessage> LoanDevolutionStatusException(LoanDevolutionStatusException exception) {
        RestErrorMessage threatErrorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatErrorResponse);
    }

    @ExceptionHandler(EmailExistsException.class)
    private ResponseEntity<RestErrorMessage> emailExistsException(EmailExistsException exception) {
        RestErrorMessage threatErrorResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatErrorResponse);
    }


}
