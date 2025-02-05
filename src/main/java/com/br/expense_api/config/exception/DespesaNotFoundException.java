package com.br.expense_api.config.exception;

public class DespesaNotFoundException extends RuntimeException {
    public DespesaNotFoundException(String message) {
        super(message);
    }
}
