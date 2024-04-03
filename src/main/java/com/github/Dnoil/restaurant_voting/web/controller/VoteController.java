package com.github.Dnoil.restaurant_voting.web.controller;

import com.github.Dnoil.restaurant_voting.model.Vote;
import com.github.Dnoil.restaurant_voting.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    private VoteService voteService;

    @GetMapping
    public void getAll() {
        voteService.getAll();
    }

    @GetMapping("no-votes")
    public void getAllWithNoVotes() {
        voteService.getAllWithNoVotes();
    }

    @GetMapping("/{id}")
    public void get(@PathVariable int id) {
        voteService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote) {
        Vote created = voteService.createOrUpdate(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/vote/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote) {
        voteService.createOrUpdate(vote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        voteService.delete(id);
    }
}
