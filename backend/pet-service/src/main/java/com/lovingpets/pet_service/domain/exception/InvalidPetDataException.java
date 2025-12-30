package com.lovingpets.pet_service.domain.exception;

public class InvalidPetDataException extends RuntimeException {

    public InvalidPetDataException(String message) {
        super(message);
    }
}
