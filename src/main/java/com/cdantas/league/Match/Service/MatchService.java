package com.cdantas.league.Match.Service;

import com.cdantas.league.Match.Dto.MatchDetail;
import com.cdantas.league.Match.Dto.MatchList;
import com.cdantas.league.Match.Dto.MatchRegister;
import com.cdantas.league.Match.Dto.MatchUpdate;
import com.cdantas.league.Match.Entity.Match;
import com.cdantas.league.Match.Entity.Status;
import com.cdantas.league.Match.Repository.MatchRepository;
import com.cdantas.league.Team.Repository.TeamRepository;
import com.cdantas.league.infra.exception.MatchNotFound;
import com.cdantas.league.infra.exception.TeamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public MatchDetail register(MatchRegister matchRegister) {
        if (matchRegister.homeTeamId().equals(matchRegister.awayTeamId())) {
            throw new IllegalArgumentException("Time da casa e visitante não podem ser o mesmo");
        }

        var homeTeam = teamRepository.findById(matchRegister.homeTeamId())
                .orElseThrow(() -> new TeamNotFound("Time da casa não encontrado: " + matchRegister.homeTeamId()));

        var awayTeam = teamRepository.findById(matchRegister.awayTeamId())
                .orElseThrow(() -> new TeamNotFound("Time visitante não encontrado: " + matchRegister.awayTeamId()));

        var match = Match.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .matchDate(matchRegister.matchDate())
                .status(matchRegister.status() != null ? matchRegister.status() : Status.not_started)
                .homeScore(matchRegister.homeScore())
                .awayScore(matchRegister.awayScore())
                .venue(matchRegister.venue())
                .build();

        matchRepository.save(match);
        return new MatchDetail(match);
    }

    public Page<MatchList> list(Pageable pageable) {
        return matchRepository.findAll(pageable).map(MatchList::new);
    }

    public MatchDetail detail(Long id) {
        var match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFound("Partida não encontrada: " + id));
        return new MatchDetail(match);
    }

    @Transactional
    public MatchDetail update(Long id, MatchUpdate matchUpdate) {
        var match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFound("Partida não encontrada: " + id));

        if (matchUpdate.status() != null) {
            match.setStatus(Status.valueOf(matchUpdate.status()));
        }
        if (matchUpdate.homeScore() != null) {
            match.setHomeScore(matchUpdate.homeScore());
        }
        if (matchUpdate.awayScore() != null) {
            match.setAwayScore(matchUpdate.awayScore());
        }

        return new MatchDetail(match);
    }

    @Transactional
    public void delete(Long id) {
        var match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFound("Partida não encontrada: " + id));
        matchRepository.delete(match);
    }
}