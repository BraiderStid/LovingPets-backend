package com.lovingpets.user_service.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("User profile not found with id: " + userId);
    }
}
