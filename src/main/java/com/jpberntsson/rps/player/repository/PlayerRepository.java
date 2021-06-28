package com.jpberntsson.rps.player.repository;

import com.jpberntsson.rps.player.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String> {
}
