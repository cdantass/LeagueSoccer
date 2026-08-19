package com.cdantas.league.MatchEvent.Service;

import com.cdantas.league.Match.Repository.MatchRepository;
import com.cdantas.league.MatchEvent.Dto.MatchEventDetail;
import com.cdantas.league.MatchEvent.Dto.MatchEventRegister;
import com.cdantas.league.MatchEvent.Entity.MatchEvent;
import com.cdantas.league.MatchEvent.Entity.MatchEventType;
import com.cdantas.league.MatchEvent.Repository.MatchEventRepository;
import com.cdantas.league.SoccerPlayer.Repository.PlayerRepository;
import com.cdantas.league.Team.Repository.TeamRepository;
import com.cdantas.league.infra.exception.MatchEventNotFound;
import com.cdantas.league.infra.exception.MatchNotFound;
import com.cdantas.league.infra.exception.PlayerNotFound;
import com.cdantas.league.infra.exception.TeamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class MatchEventService {

    @Autowired
    private MatchEventRepository matchEventRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    public MatchEventDetail register(Long matchId, MatchEventRegister dto) {
        var match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFound("Partida não encontrada: " + matchId));

        var event = MatchEvent.builder()
                .match(match)
                .type(MatchEventType.valueOf(dto.type()))
                .minute(dto.minute())
                .description(dto.description())
                .createdAt(new Date(System.currentTimeMillis()));

        if (dto.teamId() != null) {
            var team = teamRepository.findById(dto.teamId())
                    .orElseThrow(() -> new TeamNotFound("Time não encontrado: " + dto.teamId()));
            event.team(team);
        }

        if (dto.playerId() != null) {
            var player = playerRepository.findById(dto.playerId())
                    .orElseThrow(() -> new PlayerNotFound("Jogador não encontrado: " + dto.playerId()));
            event.player(player);
        }

        var saved = matchEventRepository.save(event.build());
        return new MatchEventDetail(saved);
    }

    public List<MatchEventDetail> listByMatch(Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new MatchNotFound("Partida não encontrada: " + matchId);
        }
        return matchEventRepository.findByMatchIdOrderByMinuteAsc(matchId)
                .stream()
                .map(MatchEventDetail::new)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        var event = matchEventRepository.findById(id)
                .orElseThrow(() -> new MatchEventNotFound("Evento não encontrado: " + id));
        matchEventRepository.delete(event);
    }
}