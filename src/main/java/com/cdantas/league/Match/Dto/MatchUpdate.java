package com.cdantas.league.Match.Dto;

public record MatchUpdate(
        String status,
        Integer homeScore,
        Integer awayScore
) {}