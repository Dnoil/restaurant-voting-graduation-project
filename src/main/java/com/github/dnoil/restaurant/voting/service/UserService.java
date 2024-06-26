package com.github.dnoil.restaurant.voting.service;

import com.github.dnoil.restaurant.voting.config.SecurityConfig;
import com.github.dnoil.restaurant.voting.model.User;
import com.github.dnoil.restaurant.voting.repository.UserRepository;
import com.github.dnoil.restaurant.voting.security.AuthorizedUser;
import com.github.dnoil.restaurant.voting.to.UserTo;
import com.github.dnoil.restaurant.voting.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.dnoil.restaurant.voting.util.ValidationUtil.checkNotFound;
import static com.github.dnoil.restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.findById(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "E-mail can not be null");
        return checkNotFound(userRepository.getByEmail(email), "E-mail: " + email);
    }

    public User create(User user) {
        Assert.notNull(user, "User can not be null");
        return prepareAndSave(user);
    }

    public void update(User user) {
        Assert.notNull(user, "User can not be null");
        prepareAndSave(user);
    }

    public void update(UserTo userTo) {
        User user = get(userTo.id());
        prepareAndSave(UserUtil.updateFromTo(userTo, user));
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User with e-mail " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    public User prepareAndSave(User user) {
        return userRepository.save(UserUtil.prepareToSave(user, SecurityConfig.PASSWORD_ENCODER));
    }
}
