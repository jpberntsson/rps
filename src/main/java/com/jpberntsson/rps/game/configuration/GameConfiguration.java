package com.jpberntsson.rps.game.configuration;

import com.jpberntsson.rps.game.repository.GameRepository;
import com.jpberntsson.rps.game.service.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {
    @Bean
    public GameService gameService(GameRepository repository) {
        return new GameService(repository);
    }
}
