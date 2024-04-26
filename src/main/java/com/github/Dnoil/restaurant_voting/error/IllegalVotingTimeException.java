package com.github.Dnoil.restaurant_voting.error;

public class IllegalVotingTimeException extends RuntimeException {
    public IllegalVotingTimeException(String message) {
        super(message);
    }
}
