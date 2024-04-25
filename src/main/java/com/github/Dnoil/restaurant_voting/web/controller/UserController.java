package com.github.Dnoil.restaurant_voting.web.controller;

import com.github.Dnoil.restaurant_voting.model.User;
import com.github.Dnoil.restaurant_voting.security.AuthorizedUser;
import com.github.Dnoil.restaurant_voting.service.UserService;
import com.github.Dnoil.restaurant_voting.to.UserTo;
import com.github.Dnoil.restaurant_voting.util.UserUtil;
import com.github.Dnoil.restaurant_voting.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
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
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("get {}", authorizedUser);
        return authorizedUser.getUser();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody @Valid UserTo userTo) {
        log.info("create from UserTo {}", userTo);
        ValidationUtil.checkNew(userTo);
        User created = userService.prepareAndSave(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user").build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@RequestBody @Valid UserTo userTo, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("update {} with id {}", userTo, authorizedUser.id());
        ValidationUtil.assureIdConsistent(userTo, authorizedUser.id());
        User user = authorizedUser.getUser();
        userService.prepareAndSave(UserUtil.updateFromTo(userTo, user));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("delete {}", authorizedUser);
        userService.delete(authorizedUser.id());
    }
}
