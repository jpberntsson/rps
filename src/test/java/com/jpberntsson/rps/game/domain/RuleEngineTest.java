package com.jpberntsson.rps.game.domain;

import org.junit.jupiter.api.Test;

import static com.jpberntsson.rps.game.domain.RuleEngine.Ruling.PLAYER_1_WIN;
import static com.jpberntsson.rps.game.domain.RuleEngine.Ruling.PLAYER_2_WIN;
import static com.jpberntsson.rps.game.domain.RuleEngine.Ruling.TIE;
import static com.jpberntsson.rps.game.domain.Sign.PAPER;
import static com.jpberntsson.rps.game.domain.Sign.ROCK;
import static com.jpberntsson.rps.game.domain.Sign.SCISSORS;
import static org.junit.jupiter.api.Assertions.*;

class RuleEngineTest {

    RuleEngine engine = new RuleEngine();


    //Rounds where player 1 wins
    @Test
    public void rock_scissor_round() {
        assertEquals(PLAYER_1_WIN, engine.inferRuling(ROCK, SCISSORS));
    }

    @Test
    public void paper_rock_round() {
        assertEquals(PLAYER_1_WIN, engine.inferRuling(PAPER, ROCK));
    }

    @Test
    public void scissor_paper_round() {
        assertEquals(PLAYER_1_WIN, engine.inferRuling(SCISSORS, PAPER));
    }



    //Rounds that end up in a tie
    @Test
    public void rock_rock_round() {
        assertEquals(TIE, engine.inferRuling(ROCK, ROCK));
    }

    @Test
    public void paper_paper_round() {
        assertEquals(TIE, engine.inferRuling(PAPER, PAPER));
    }

    @Test
    public void scissor_scissor_round() {
        assertEquals(TIE, engine.inferRuling(SCISSORS, SCISSORS));
    }


    //Rounds where player 2 wins
    @Test
    public void rock_paper_round() {
        assertEquals(PLAYER_2_WIN, engine.inferRuling(ROCK, PAPER));
    }

    @Test
    public void paper_scissor_round() {
        assertEquals(PLAYER_2_WIN, engine.inferRuling(PAPER, SCISSORS));
    }

    @Test
    public void scissor_rock_round() {
        assertEquals(PLAYER_2_WIN, engine.inferRuling(SCISSORS, ROCK));
    }




}