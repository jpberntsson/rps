package com.jpberntsson.rps.match.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Match {

    @Id
    @Getter
    private String id = UUID.randomUUID().toString();

    @Getter
    @Setter
    private String player1Id;
    @Getter
    @Setter
    private String player2Id;
    @Getter
    @Setter
    private String gameId;

    public Match() {}

    public Match(String player1Id) {
        this.player1Id = player1Id;
    }

}
