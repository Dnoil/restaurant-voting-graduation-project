package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.User;
import com.github.Dnoil.restaurant_voting.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository dishRepository;

    public List<User> getAll() {
        return dishRepository.findAll();
    }

    public User get(int id) {
        return dishRepository.getReferenceById(id);
    }

    public User create(User user) {
        return dishRepository.save(user);
    }

    public User update(User user) {
        return dishRepository.save(user);
    }

    public void delete(int id) {
        dishRepository.deleteById(id);
    }
}
