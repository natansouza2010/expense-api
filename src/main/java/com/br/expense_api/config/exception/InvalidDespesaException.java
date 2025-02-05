package com.br.expense_api.config.exception;

public class InvalidDespesaException extends RuntimeException {
    public InvalidDespesaException(String message) {
        super(message);
    }
}
