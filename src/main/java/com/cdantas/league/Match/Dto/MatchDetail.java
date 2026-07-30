package com.cdantas.league.Match.Dto;

import com.cdantas.league.Match.Entity.Match;
import com.cdantas.league.Match.Entity.Status;

import java.util.Date;

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
        Date matchDate,
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
                match.getHomeTeam().getLogo_url(),
                match.getAwayTeam().getId(),
                match.getAwayTeam().getName(),
                match.getAwayTeam().getAbbreviation(),
                match.getAwayTeam().getLogo_url(),
                match.getMatchDate(),
                match.getStatus(),
                match.getHomeScore(),
                match.getAwayScore(),
                match.getVenue()
        );
    }
}