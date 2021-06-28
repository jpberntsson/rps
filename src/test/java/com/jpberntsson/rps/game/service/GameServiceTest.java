package com.jpberntsson.rps.game.service;

import com.jpberntsson.rps.game.domain.Game;
import com.jpberntsson.rps.game.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;

import static com.jpberntsson.rps.game.domain.Sign.PAPER;
import static com.jpberntsson.rps.game.domain.Sign.ROCK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameServiceTest {


    GameService gameService;

    @Mock
    GameRepository repository = mock(GameRepository.class);

    @BeforeEach
    public void setGameService() {
        gameService = new GameService(repository);
    }

    @Test
    public void createGame() {
        Game newGame = new Game("player_1_id", "player_2_id");
        when(repository.save(any(Game.class))).thenReturn(newGame);
        Game game = gameService.createGame("player_1_id", "player_2_id");

        assertTrue(game.getPlayers().contains("player_1_id"));
        assertTrue(game.getPlayers().contains("player_2_id"));
        verify(repository).save(any(Game.class));
    }

    @Test
    public void getGame() {
        Game game = new Game("player_1_id", "player_2_id");
        when(repository.findById(game.getId())).thenReturn(Optional.of(game));

        Optional<Game> result = gameService.findGame(game.getId());
        assertEquals(game.getId(), result.get().getId());
    }

    @Test
    public void makeMoveForPlayer() {
        Game game = new Game("player_1_id", "player_2_id");
        game.makePlayerMove("player_1_id", ROCK);

        when(repository.findById(game.getId())).thenReturn(Optional.of(game));

        gameService.makePlayerMove(game.getId(), "player_2_id", PAPER);

        ArgumentCaptor<Game> gameCaptor = ArgumentCaptor.forClass(Game.class);
        verify(repository).save(gameCaptor.capture());

        //If the move was registered correctly player 2 should have gained 1 point.
        assertEquals(gameCaptor.getValue().getPlayerScore("player_2_id"), 1);

    }

}