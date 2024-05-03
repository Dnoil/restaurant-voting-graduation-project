package com.github.Dnoil.restaurant.voting.to;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UserTo extends BaseTo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Email
    @NotBlank
    @Size(min = 5, max = 128)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    private String login;

    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    public UserTo(Integer id, String name, String email, String login, String password) {
        super(id, name);
        this.email = email;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
