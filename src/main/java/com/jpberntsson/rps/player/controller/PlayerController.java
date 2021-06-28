package com.jpberntsson.rps.player.controller;

import com.jpberntsson.rps.player.domain.Player;
import com.jpberntsson.rps.player.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PlayerController {

    PlayerService playerService;

    @GetMapping(value = "/player/{playerId}", produces = "application/json")
    public Player getPlayer(@PathVariable String playerId) {
        return playerService.findPlayer(playerId).get();
    }

    @PostMapping(value = "/player", produces = "application/json")
    public Player createPlayer(@RequestBody CreatePlayerRest player) {
        return playerService.createPlayer(player.getAlias());
    }
}
