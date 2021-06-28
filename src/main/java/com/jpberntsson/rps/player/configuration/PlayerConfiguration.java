package com.jpberntsson.rps.player.configuration;

import com.jpberntsson.rps.player.repository.PlayerRepository;
import com.jpberntsson.rps.player.service.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration {
    @Bean
    public PlayerService playerService(PlayerRepository repository) {
        return new PlayerService(repository);
    }
}
