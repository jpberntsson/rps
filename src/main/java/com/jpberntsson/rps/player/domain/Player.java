package com.jpberntsson.rps.player.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Player {

    @Id
    @Getter
    private String id = UUID.randomUUID().toString();
    @Getter
    private String alias;

    public Player(String alias) {
        this.alias = alias;
    }

    public Player() {}
}
