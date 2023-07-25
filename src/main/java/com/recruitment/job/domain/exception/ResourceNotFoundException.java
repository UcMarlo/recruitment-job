package com.recruitment.job.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Cannot find requested resource");
    }
}
