package com.br.expense_api.config.exception;

public class DespesaAlreadyExistsException extends RuntimeException {
    public DespesaAlreadyExistsException(String message) {
        super(message);
    }
}
