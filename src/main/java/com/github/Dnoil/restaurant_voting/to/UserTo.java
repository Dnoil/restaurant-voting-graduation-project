package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.BaseEntity;
import com.github.Dnoil.restaurant_voting.model.Role;

import com.github.Dnoil.restaurant_voting.model.User;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserTo extends BaseTo {
    private String email;
    private String login;
    private Date registered = new Date();
    private boolean enabled = true;
    private Set<Role> roles;

    public UserTo(User user) {
        super(user);
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.registered = user.getRegistered();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }
}
