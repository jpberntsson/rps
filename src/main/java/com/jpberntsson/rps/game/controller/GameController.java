package com.jpberntsson.rps.game.controller;

import com.jpberntsson.rps.game.domain.Game;
import com.jpberntsson.rps.game.domain.Sign;
import com.jpberntsson.rps.game.service.GameService;
import com.jpberntsson.rps.player.domain.Player;
import com.jpberntsson.rps.player.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GameController {
    GameService gameService;
    PlayerService playerService;

    @GetMapping(value = "/game/{gameId}", produces = "application/json")
    public GameRest getGame(@PathVariable String gameId, @RequestParam String playerId) {
        Game game = gameService.findGame(gameId).get();
        Player opponent = playerService.findPlayer(game.getOpponentIdOf(playerId)).get();

        return new GameRest(gameService.findGame(gameId).get(), playerId, opponent.getAlias());
    }

    @PutMapping(value = "/game/{gameId}", produces = "application/json")
    public GameRest makePlayerMove(@PathVariable String gameId, @RequestBody PlayerMoveRest playerMoveRest) {
        Game game = gameService.findGame(gameId).get();
        Player opponent = playerService.findPlayer(game.getOpponentIdOf(playerMoveRest.getPlayerId())).get();

        return new GameRest(gameService.makePlayerMove(gameId, playerMoveRest.getPlayerId(), Sign.parseString(playerMoveRest.getSign())).get(), playerMoveRest.getPlayerId(), opponent.getAlias());
    }

}
