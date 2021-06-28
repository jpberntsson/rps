package com.jpberntsson.rps.game.controller;

import com.jpberntsson.rps.game.domain.Game;
import com.jpberntsson.rps.game.domain.Round;
import lombok.Getter;

@Getter
public class GameRest {

    private Integer playerScore;
    private String playerSign;

    private Integer opponentScore;
    private String opponentSign;
    private String opponentName;

    private Boolean playerWon;
    private Boolean isTie;
    private Boolean currentRoundFinished;

    public GameRest(Game game, String playerId, String opponentName) {
        String opponentId = game.getOpponentIdOf(playerId);

        playerScore = game.getPlayerScore(playerId);
        opponentScore = game.getPlayerScore(opponentId);

        Round currentRound = game.getCurrentRound();
        isTie = currentRound.isTied();
        playerWon = currentRound.isFinished() && playerId.equals(currentRound.getWinnerId());
        currentRoundFinished = currentRound.isFinished();

        playerSign = currentRound.getMoveForPlayer(playerId) != null ? currentRound.getMoveForPlayer(playerId).toString() : null;
        if(currentRound.isFinished()) {
            opponentSign = currentRound.getMoveForPlayer(opponentId) != null ? currentRound.getMoveForPlayer(opponentId).toString() : null;
        } else {
            opponentSign = currentRound.getMoveForPlayer(opponentId) != null ? "***" : null;
        }

        this.opponentName = opponentName;
    }
}
