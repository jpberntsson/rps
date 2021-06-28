package com.jpberntsson.rps.game.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.*;


@Entity
public class Game {

    @ElementCollection
    private List<String> playerIdList = new ArrayList<>(2);

    @OrderBy("created")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    @Getter
    @Id
    private String id;

    @Transient
    @Setter
    private RuleEngine ruleEngine = new RuleEngine();

    public Game() {}

    public Game(String player1Id, String player2Id) {
        playerIdList.add(player1Id);
        playerIdList.add(player2Id);
        id = UUID.randomUUID().toString();
        startNewRound();
    }



    public List<String> getPlayers() {
        return Collections.unmodifiableList(playerIdList);
    }
    public List<Round> getRounds() {return Collections.unmodifiableList(rounds);}


    public void makePlayerMove(String playerId, Sign sign) {
        if(!playerIdList.contains(playerId)) {
            return;
        }
        if(getCurrentRound().isFinished()) {
            startNewRound();
        }
        getCurrentRound().registerMove(playerId, sign);
        if(getCurrentRound().isFinished()) {
            switch (ruleEngine.inferRuling(getCurrentRound().getFirstMove().getSign(), getCurrentRound().getSecondMove().getSign())) {
                case PLAYER_1_WIN:
                    getCurrentRound().setWinnerId(getCurrentRound().getFirstMove().getPlayerId());
                    break;
                case PLAYER_2_WIN:
                    getCurrentRound().setWinnerId(getCurrentRound().getSecondMove().getPlayerId());
            }
        }
    }

    public int getPlayerScore(String playerId) {
        return rounds.stream().filter(round -> playerId.equals(round.getWinnerId()))
                .collect(Collectors.toList()).size();
    }

    public Round getCurrentRound() {
        return rounds.get(rounds.size()-1);
    }

    public String getOpponentIdOf(String playerId) {
        int index = playerIdList.indexOf(playerId);
        if(index == 0) {
            return playerIdList.get(1);
        } else {
            return playerIdList.get(0);
        }
    }

    private void startNewRound() {
        rounds.add(new Round());
    }
}
