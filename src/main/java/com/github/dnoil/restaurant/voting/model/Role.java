package com.github.dnoil.restaurant.voting.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name() + "_ROLE";
    }
}
