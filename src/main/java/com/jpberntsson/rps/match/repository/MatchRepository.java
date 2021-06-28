package com.jpberntsson.rps.match.repository;

import com.jpberntsson.rps.match.domain.Match;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends CrudRepository<Match, String> {

    List<Match> findByPlayer2IdIsNull();
}
