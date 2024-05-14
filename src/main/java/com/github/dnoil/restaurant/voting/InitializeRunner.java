package com.github.dnoil.restaurant.voting;

import com.github.dnoil.restaurant.voting.model.Role;
import com.github.dnoil.restaurant.voting.model.User;
import com.github.dnoil.restaurant.voting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) {
        User admin = new User(0, "Some Admin", "admin@gmail.com", "admin",
                "admin", Role.ADMIN, Role.USER);
        userRepository.save(admin);
    }
}
