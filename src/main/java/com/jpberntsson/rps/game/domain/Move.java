package com.jpberntsson.rps.game.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Move {

    public Move() {}

    @Getter
    private String playerId;
    @Getter
    private Sign sign;
}
