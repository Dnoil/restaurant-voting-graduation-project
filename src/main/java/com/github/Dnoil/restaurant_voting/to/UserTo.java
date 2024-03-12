package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Role;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserTo extends BaseTo {
    private String email;
    private String login;
    private String password;
    private Date registered = new Date();
    private boolean enabled = true;
    private Set<Role> roles;
}
