package com.cdantas.league.Match.Dto;

import com.cdantas.league.Match.Entity.Match;
import com.cdantas.league.Match.Entity.Status;

import java.time.LocalDateTime;

public record MatchList(
        Long id,
        Long homeTeamId,
        String homeTeamName,
        Long awayTeamId,
        String awayTeamName,
        LocalDateTime matchDate,
        Status status
) {
    public MatchList(Match match) {
        this(
                match.getId(),
                match.getHomeTeam().getId(),
                match.getHomeTeam().getName(),
                match.getAwayTeam().getId(),
                match.getAwayTeam().getName(),
                match.getMatchDate(),
                match.getStatus()
        );
    }
}