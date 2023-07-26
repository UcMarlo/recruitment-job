package com.recruitment.job.infrastructure.postgres.counter.exception;

import com.recruitment.job.domain.exception.ResourceNotFoundException;

public class RequestCounterNotFoundException extends ResourceNotFoundException {
    public RequestCounterNotFoundException(String message) {
        super(message);
    }
}
