package com.lovingpets.pet_service.domain.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Account with id " + id + " does not exist in auth-service");
    }
}