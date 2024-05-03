package com.github.Dnoil.restaurant.voting.security;

import com.github.Dnoil.restaurant.voting.model.User;
import lombok.Getter;
import lombok.NonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Getter
    private final User user;

    public AuthorizedUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    @Override
    public String toString() {
        return "AuthUser:" + id() + '[' + user.getEmail() + ']';
    }

}