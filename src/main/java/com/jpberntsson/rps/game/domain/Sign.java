package com.jpberntsson.rps.game.domain;

public enum Sign {
    ROCK,
    PAPER,
    SCISSORS;

    public static Sign parseString(String sign) {
        if(PAPER.toString().equals(sign.toUpperCase())) {
            return PAPER;
        } else if(SCISSORS.toString().equals(sign.toUpperCase())) {
            return SCISSORS;
        } else {
            return ROCK;
        }
    }
}
