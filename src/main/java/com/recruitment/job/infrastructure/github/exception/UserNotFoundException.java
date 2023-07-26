package com.recruitment.job.infrastructure.github.exception;

import com.recruitment.job.domain.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
