package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.User;

import java.util.Date;

public class UserTo extends BaseTo {
    private final String email;
    private final String login;
    private final Date registered;
    private final boolean enabled;

    public UserTo(User user) {
        super(user);
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.registered = user.getRegistered();
        this.enabled = user.isEnabled();
    }
}
