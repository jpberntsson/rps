package com.jpberntsson.rps.game.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static com.jpberntsson.rps.game.domain.Sign.PAPER;
import static com.jpberntsson.rps.game.domain.Sign.ROCK;
import static com.jpberntsson.rps.game.domain.Sign.SCISSORS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {

    private static final String PLAYER_1_ID = "player_1_id";
    private static final String PLAYER_2_ID = "player_2_id";
    private static final String UNREGISTERED_PLAYER_ID = "player_3_id";


    @Mock
    private RuleEngine engine = mock(RuleEngine.class);

    private Game game;


    @BeforeEach
    public void setGame() {
        game = new Game(PLAYER_1_ID, PLAYER_2_ID);

        /*
         *   There appears to be some compatibility issues with JUnit 5, intellij and mockito
         *   hence this "manual" injection
         */
        game.setRuleEngine(engine);

    }

    @Test
    public void getPlayers() {
        List<String> playerList = game.getPlayers();
    }

    @Test
    public void play_0_rounds() {
        assertEquals(0, game.getPlayerScore(PLAYER_1_ID));
        assertEquals(0, game.getPlayerScore(PLAYER_2_ID));
    }

    @Test
    public void play_1_round() {
        when(engine.inferRuling(ROCK, PAPER)).thenReturn(RuleEngine.Ruling.PLAYER_1_WIN);

        game.makePlayerMove(PLAYER_1_ID, ROCK);
        game.makePlayerMove(PLAYER_2_ID, PAPER);

        assertEquals(1, game.getPlayerScore(PLAYER_1_ID));
        assertEquals(0, game.getPlayerScore(PLAYER_2_ID));
    }

    @Test
    public void play_3_rounds() {
        when(engine.inferRuling(PAPER, ROCK)).thenReturn(RuleEngine.Ruling.PLAYER_1_WIN);
        when(engine.inferRuling(ROCK, PAPER)).thenReturn(RuleEngine.Ruling.PLAYER_2_WIN);

        game.makePlayerMove(PLAYER_1_ID, PAPER);
        game.makePlayerMove(PLAYER_2_ID, ROCK);

        game.makePlayerMove(PLAYER_1_ID, ROCK);
        game.makePlayerMove(PLAYER_2_ID, PAPER);

        game.makePlayerMove(PLAYER_1_ID, PAPER);
        game.makePlayerMove(PLAYER_2_ID, ROCK);

        assertEquals(2, game.getPlayerScore(PLAYER_1_ID));
        assertEquals(1, game.getPlayerScore(PLAYER_2_ID));
    }

    @Test
    public void play_2_rounds_with_1_draw() {
        when(engine.inferRuling(PAPER, ROCK)).thenReturn(RuleEngine.Ruling.PLAYER_1_WIN);
        when(engine.inferRuling(ROCK, ROCK)).thenReturn(RuleEngine.Ruling.TIE);

        game.makePlayerMove(PLAYER_1_ID, PAPER);
        game.makePlayerMove(PLAYER_2_ID, ROCK);

        game.makePlayerMove(PLAYER_1_ID, ROCK);
        game.makePlayerMove(PLAYER_2_ID, ROCK);

        assertEquals(1, game.getPlayerScore(PLAYER_1_ID));
        assertEquals(0, game.getPlayerScore(PLAYER_2_ID));
    }

    @Test
    public void registering_a_move_twice_for_the_first_player_to_move_in_the_same_round() {
        when(engine.inferRuling(ROCK, PAPER)).thenReturn(RuleEngine.Ruling.PLAYER_2_WIN);

        game.makePlayerMove(PLAYER_1_ID, ROCK);
        game.makePlayerMove(PLAYER_1_ID, SCISSORS);
        game.makePlayerMove(PLAYER_2_ID, PAPER);

        assertEquals(0, game.getPlayerScore(PLAYER_1_ID));
        assertEquals(1, game.getPlayerScore(PLAYER_2_ID));
    }

    @Test
    public void registering_move_for_unregistered_player() {
        when(engine.inferRuling(SCISSORS, PAPER)).thenReturn(RuleEngine.Ruling.PLAYER_1_WIN);
        when(engine.inferRuling(SCISSORS, ROCK)).thenReturn(RuleEngine.Ruling.PLAYER_2_WIN);

        game.makePlayerMove(PLAYER_1_ID, SCISSORS);
        game.makePlayerMove(UNREGISTERED_PLAYER_ID, ROCK);
        game.makePlayerMove(PLAYER_2_ID, PAPER);

        assertEquals(1, game.getPlayerScore(PLAYER_1_ID));
        assertEquals(0, game.getPlayerScore(PLAYER_2_ID));
        assertEquals(0, game.getPlayerScore(UNREGISTERED_PLAYER_ID));
    }

    @Test
    public void getOpponentIdOf() {
        assertEquals(PLAYER_2_ID, game.getOpponentIdOf(PLAYER_1_ID));
        assertEquals(PLAYER_1_ID, game.getOpponentIdOf(PLAYER_2_ID));
    }

    @Test
    public void getMoveForPlayer() {
        when(engine.inferRuling(ROCK, PAPER)).thenReturn(RuleEngine.Ruling.PLAYER_2_WIN);

        game.makePlayerMove(PLAYER_1_ID, ROCK);
        game.makePlayerMove(PLAYER_2_ID, PAPER);

        assertEquals(ROCK, game.getCurrentRound().getMoveForPlayer(PLAYER_1_ID));
        assertEquals(PAPER, game.getCurrentRound().getMoveForPlayer(PLAYER_2_ID));
    }
}