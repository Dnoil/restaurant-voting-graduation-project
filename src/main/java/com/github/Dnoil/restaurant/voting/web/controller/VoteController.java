package com.github.Dnoil.restaurant.voting.web.controller;

import com.github.Dnoil.restaurant.voting.model.Vote;
import com.github.Dnoil.restaurant.voting.service.VoteService;
import com.github.Dnoil.restaurant.voting.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    private VoteService voteService;

    @GetMapping
    public List<Vote> getAll() {
        return voteService.getAllActual();
    }

    @GetMapping("/old/user")
    public List<Vote> getAllWithOld(@RequestParam int id) {
        return voteService.getAllByUserId(id);
    }

    @GetMapping("/user")
    public Vote getActual(@RequestParam int id) {
        return voteService.getActual(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody @Valid Vote vote) {
        ValidationUtil.checkNew(vote);
        Vote created = voteService.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Vote vote, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(vote, id);
        voteService.update(vote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        voteService.delete(id);
    }
}
