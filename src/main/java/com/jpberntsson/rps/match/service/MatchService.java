package com.jpberntsson.rps.match.service;

import com.jpberntsson.rps.game.domain.Game;
import com.jpberntsson.rps.game.service.GameService;
import com.jpberntsson.rps.match.domain.Match;
import com.jpberntsson.rps.match.repository.MatchRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MatchService {

    private MatchRepository repository;

    private GameService gameService;

    public Match createNewMatch(String playerId) {
        List<Match> matchList = repository.findByPlayer2IdIsNull();

        if(matchList.isEmpty()) {
            return repository.save(new Match(playerId));
        }

        Match match = matchList.get(0);

        if(!match.getPlayer1Id().equals(playerId)) {
            match.setPlayer2Id(playerId);
            Game game = gameService.createGame(match.getPlayer1Id(), match.getPlayer2Id());
            match.setGameId(game.getId());
        }

        return repository.save(match);
    }

    public Optional<Match> findMatch(String matchId) {
        return repository.findById(matchId);
    }
}
