package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Role;
import com.github.Dnoil.restaurant_voting.model.User;

import java.util.Date;
import java.util.Set;

public class UserTo extends BaseTo {
    private final String email;
    private final String login;
    private final Date registered;
    private final boolean enabled;
    private final Set<Role> roles;

    public UserTo(User user) {
        super(user);
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.registered = user.getRegistered();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }
}
