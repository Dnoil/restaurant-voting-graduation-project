package com.github.Dnoil.restaurant_voting.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank
    @Size(max = 128)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    @Column(name = "registered", nullable = false)
    @NotNull
    private Date registered = new Date();

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "unq_user_role")})
    @Column(name = "role", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}