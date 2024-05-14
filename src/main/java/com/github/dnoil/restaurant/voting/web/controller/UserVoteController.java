package com.github.dnoil.restaurant.voting.web.controller;

import com.github.dnoil.restaurant.voting.model.Vote;
import com.github.dnoil.restaurant.voting.security.AuthorizedUser;
import com.github.dnoil.restaurant.voting.service.VoteService;
import com.github.dnoil.restaurant.voting.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {

    private VoteService voteService;

    @GetMapping()
    public List<Vote> getAllByUserId(@ApiIgnore @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return voteService.getAllByUserId(authorizedUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody @Valid Vote vote, @ApiIgnore @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        ValidationUtil.checkNew(vote);
        vote.setUser(authorizedUser.getUser());
        Vote created = voteService.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/votes/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Vote vote, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(vote, id);
        voteService.update(vote);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiIgnore @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        voteService.deleteByUserId(authorizedUser.id());
    }
}
