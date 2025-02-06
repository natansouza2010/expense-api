package com.br.expense_api.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(DespesaNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleDespesaNotFoundException(DespesaNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DespesaAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetails> handleDespesaAlreadyExistsException(DespesaAlreadyExistsException ex) {
        return buildResponseEntity(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(InvalidDespesaException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidDespesaException(InvalidDespesaException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    private ResponseEntity<ExceptionDetails> buildResponseEntity(HttpStatus status, String message) {
        ExceptionDetails details = new ExceptionDetails(status.value(), message);
        return new ResponseEntity<>(details, status);
    }
}
