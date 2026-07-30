package com.cdantas.league.Match.Dto;

import com.cdantas.league.Match.Entity.Match;
import com.cdantas.league.Match.Entity.Status;

import java.time.LocalDateTime;

public record MatchDetail(
        Long id,
        Long homeTeamId,
        String homeTeamName,
        String homeTeamAbbreviation,
        String homeTeamLogoUrl,
        Long awayTeamId,
        String awayTeamName,
        String awayTeamAbbreviation,
        String awayTeamLogoUrl,
        LocalDateTime matchDate,
        Status status,
        int homeScore,
        int awayScore,
        long venue
) {
    public MatchDetail(Match match) {
        this(
                match.getId(),
                match.getHomeTeam().getId(),
                match.getHomeTeam().getName(),
                match.getHomeTeam().getAbbreviation(),
                match.getHomeTeam().getLogoUrl(),
                match.getAwayTeam().getId(),
                match.getAwayTeam().getName(),
                match.getAwayTeam().getAbbreviation(),
                match.getAwayTeam().getLogoUrl(),
                match.getMatchDate(),
                match.getStatus(),
                match.getHomeScore(),
                match.getAwayScore(),
                match.getVenue()
        );
    }
}