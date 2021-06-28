package com.jpberntsson.rps.game.repository;

import com.jpberntsson.rps.game.domain.Game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {
}
