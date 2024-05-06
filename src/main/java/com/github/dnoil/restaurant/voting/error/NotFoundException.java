package com.github.dnoil.restaurant.voting.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
