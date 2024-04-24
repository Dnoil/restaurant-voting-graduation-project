package com.github.Dnoil.restaurant_voting.util;

import com.github.Dnoil.restaurant_voting.model.Role;
import com.github.Dnoil.restaurant_voting.model.User;
import com.github.Dnoil.restaurant_voting.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail(), userTo.getLogin(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(UserTo userTo, User user) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setLogin(userTo.getLogin());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getLogin(), user.getPassword());
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
