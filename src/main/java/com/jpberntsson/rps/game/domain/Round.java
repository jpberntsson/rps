package com.jpberntsson.rps.game.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
public class Round {

    @Id
    @Getter
    private String id = UUID.randomUUID().toString();

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Getter @Setter
    private String winnerId;

    @AttributeOverrides({
            @AttributeOverride(name="playerId",column=@Column(name="move_1_playerId")),
            @AttributeOverride(name="sign",column=@Column(name="move_1_sign"))
            })
    @Embedded
    @Getter
    private Move firstMove;

    @AttributeOverrides({
            @AttributeOverride(name="playerId",column=@Column(name="move_2_playerId")),
            @AttributeOverride(name="sign",column=@Column(name="move_2_sign"))
    })
    @Embedded
    @Getter
    private Move secondMove;

    @ManyToOne
    private Game game;

    public void registerMove(String playerId, Sign sign) {
        if(firstMove == null) {
            firstMove = new Move(playerId, sign);
            return;
        }

        if(secondMove == null && !firstMove.getPlayerId().equals(playerId)) {
            secondMove = new Move(playerId, sign);
        }
    }

    public boolean isFinished() {
        return firstMove != null && secondMove != null;
    }

    public boolean isTied() {
        return isFinished() && winnerId == null;
    }

    public Sign getMoveForPlayer(String id) {
        if(firstMove != null && firstMove.getPlayerId().equals(id)) {
            return firstMove.getSign();
        } else if(secondMove != null && secondMove.getPlayerId().equals(id)) {
            return secondMove.getSign();
        } else {
            return null;
        }
    }
}
