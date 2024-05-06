package com.github.dnoil.restaurant.voting.error;

public class IllegalVotingTimeException extends RuntimeException {
    public IllegalVotingTimeException(String message) {
        super(message);
    }
}
