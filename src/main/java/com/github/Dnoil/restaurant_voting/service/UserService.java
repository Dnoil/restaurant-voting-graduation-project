package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.User;
import com.github.Dnoil.restaurant_voting.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    //TODO throw exception
    public User get(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
