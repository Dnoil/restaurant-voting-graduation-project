package com.github.dnoil.restaurant.voting.web.controller;

import com.github.dnoil.restaurant.voting.model.Vote;
import com.github.dnoil.restaurant.voting.service.VoteService;
import com.github.dnoil.restaurant.voting.util.ValidationUtil;
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
@RequestMapping(value = "/api/admin/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController {

    private VoteService voteService;

    @GetMapping
    public List<Vote> getAllActual() {
        return voteService.getAllActual();
    }

    @GetMapping("/user")
    public List<Vote> getAllByUserId(@RequestParam int id) {
        return voteService.getAllByUserId(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody @Valid Vote vote) {
        ValidationUtil.checkNew(vote);
        Vote created = voteService.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/admin/votes/{id}").buildAndExpand(created.getId()).toUri();
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
