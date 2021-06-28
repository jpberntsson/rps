package com.jpberntsson.rps.game.domain;

/**
 * Implements the rules for deciding a winning round of rock, paper, scissor
 */
public class RuleEngine {

    public enum Ruling {
        TIE,
        PLAYER_1_WIN,
        PLAYER_2_WIN
    }

    public Ruling inferRuling(Sign player1Sign, Sign player2Sign) {
        if(player1Sign == player2Sign) {
            return Ruling.TIE;
        }

        //The three conditions below describe all winning combinations for player 1
        if(player1Sign == Sign.ROCK && player2Sign == Sign.SCISSORS) {
            return Ruling.PLAYER_1_WIN;
        } else if (player1Sign == Sign.PAPER && player2Sign == Sign.ROCK) {
            return Ruling.PLAYER_1_WIN;
        } else if (player1Sign == Sign.SCISSORS && player2Sign == Sign.PAPER) {
            return Ruling.PLAYER_1_WIN;
        }

        //If it's not a tie and player 1 didn't win we can deduce that player 2 is the winner
        return Ruling.PLAYER_2_WIN;


    }
}
