package com.jpberntsson.rps.player.service;

import com.jpberntsson.rps.player.domain.Player;
import com.jpberntsson.rps.player.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@AllArgsConstructor
public class PlayerService {

    @NonNull
    private PlayerRepository repository;

    public Player createPlayer(String alias) {
        return repository.save(new Player(alias));
    }

    public Optional<Player> findPlayer(String id) {
        return repository.findById(id);
    }
}
