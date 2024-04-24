package com.github.Dnoil.restaurant_voting.web.controller;

import com.github.Dnoil.restaurant_voting.model.User;
import com.github.Dnoil.restaurant_voting.security.SecurityHandler;
import com.github.Dnoil.restaurant_voting.service.UserService;
import com.github.Dnoil.restaurant_voting.to.UserTo;
import com.github.Dnoil.restaurant_voting.util.UserUtil;
import com.github.Dnoil.restaurant_voting.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @GetMapping
    public User get() {
        log.info("get {}", SecurityHandler.authUserId());
        return userService.get(SecurityHandler.authUserId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) {
        log.info("create from UserTo {}", userTo);
        ValidationUtil.checkNew(userTo);
        User created = userService.create(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user").build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo) {
        log.info("update {}", userTo);
        ValidationUtil.assureIdConsistent(userTo, SecurityHandler.authUserId());
        userService.update(userTo);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        log.info("delete {}", SecurityHandler.authUserId());
        userService.delete(SecurityHandler.authUserId());
    }
}
