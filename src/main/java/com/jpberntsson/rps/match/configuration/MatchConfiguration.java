package com.jpberntsson.rps.match.configuration;

import com.jpberntsson.rps.game.service.GameService;
import com.jpberntsson.rps.match.repository.MatchRepository;
import com.jpberntsson.rps.match.service.MatchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchConfiguration {
    @Bean
    public MatchService matchService(MatchRepository repository, GameService gameService) {
        return new MatchService(repository, gameService);
    }
}
