package com.lovingpets.user_service.domain.exception;

public class UserProfileAlreadyExistsException extends RuntimeException {

    public UserProfileAlreadyExistsException(Long userId) {
        super("User profile already exists for userId: " + userId);
    }
}