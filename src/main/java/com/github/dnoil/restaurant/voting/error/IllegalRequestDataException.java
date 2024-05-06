package com.github.dnoil.restaurant.voting.error;

public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(String msg) {
        super(msg);
    }
}
