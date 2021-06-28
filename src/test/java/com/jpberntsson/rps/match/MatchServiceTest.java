package com.jpberntsson.rps.match;

import com.jpberntsson.rps.game.domain.Game;
import com.jpberntsson.rps.game.service.GameService;
import com.jpberntsson.rps.match.domain.Match;
import com.jpberntsson.rps.match.repository.MatchRepository;
import com.jpberntsson.rps.match.service.MatchService;
import com.jpberntsson.rps.player.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MatchServiceTest {

    private MatchService matchService;

    @Mock
    private GameService gameService = mock(GameService.class);

    @Mock
    private MatchRepository matchRepository = mock(MatchRepository.class);

    @BeforeEach
    public void setMatchService() {
        matchService = new MatchService(matchRepository, gameService);
    }

    @Test
    public void createNewMatch_first_player() {
        Player player = new Player("test");

        when(matchRepository.findByPlayer2IdIsNull()).thenReturn(Collections.emptyList());

        matchService.createNewMatch(player.getId());

        ArgumentCaptor<Match> matchCaptor = ArgumentCaptor.forClass(Match.class);;
        verify(matchRepository).save(matchCaptor.capture());

        assertEquals(player.getId(), matchCaptor.getValue().getPlayer1Id());

    }

    @Test
    public void createNewMatch_second_player() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        Match match = new Match(player1.getId());


        when(matchRepository.findByPlayer2IdIsNull()).thenReturn(Arrays.asList(match));

        Game game = new Game(player1.getId(), player2.getId());
        when(gameService.createGame(player1.getId(), player2.getId())).thenReturn(game);

        matchService.createNewMatch(player2.getId());

        ArgumentCaptor<Match> matchCaptor = ArgumentCaptor.forClass(Match.class);
        verify(matchRepository).save(matchCaptor.capture());

        assertEquals(player1.getId(), matchCaptor.getValue().getPlayer1Id());
        assertEquals(player2.getId(), matchCaptor.getValue().getPlayer2Id());
        assertEquals(game.getId(), matchCaptor.getValue().getGameId());
    }

    @Test
    public void createNewMatch_same_player() {
        Player player1 = new Player("player1");

        Match match = new Match(player1.getId());

        when(matchRepository.findByPlayer2IdIsNull()).thenReturn(Arrays.asList(match));

        matchService.createNewMatch(player1.getId());

        ArgumentCaptor<Match> matchCaptor = ArgumentCaptor.forClass(Match.class);;
        verify(matchRepository).save(matchCaptor.capture());

        assertEquals(player1.getId(), matchCaptor.getValue().getPlayer1Id());
        assertNull(matchCaptor.getValue().getPlayer2Id());
    }

}