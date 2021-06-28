package com.jpberntsson.rps.match.controller;

import com.jpberntsson.rps.match.domain.Match;
import com.jpberntsson.rps.match.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class MatchController {

    MatchService matchService;

    @PostMapping(value = "/match", produces = "application/json")
    public Match createMatch(@RequestBody CreateMatchRest matchRest) {
        return matchService.createNewMatch(matchRest.getPlayerId());
    }

    @GetMapping(value = "/match/{matchId}", produces = "application/json")
    public Match getMatch(@PathVariable String matchId) {
        return matchService.findMatch(matchId).get();
    }
}
