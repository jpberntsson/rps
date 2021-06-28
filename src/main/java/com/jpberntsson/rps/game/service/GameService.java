package com.jpberntsson.rps.game.service;

import com.jpberntsson.rps.game.domain.Game;
import com.jpberntsson.rps.game.domain.Sign;
import com.jpberntsson.rps.game.repository.GameRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;

    public Game createGame(String player1Id, String player2Id) {
        return gameRepository.save(new Game(player1Id, player2Id));
    }

    public Optional<Game> findGame(String gameId) {
        return gameRepository.findById(gameId);
    }

    public Optional<Game> makePlayerMove(String gameId, String playerId, Sign sign) {
        return gameRepository.findById(gameId).map(game -> {
            game.makePlayerMove(playerId, sign);
            return gameRepository.save(game);
        });
    }
}
