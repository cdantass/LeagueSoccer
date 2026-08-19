package com.cdantas.league.MatchEvent.Dto;

public record MatchEventRegister(
        Long matchId,
        String type,
        Integer minute,
        Long teamId,
        Long playerId,
        String description
) {}