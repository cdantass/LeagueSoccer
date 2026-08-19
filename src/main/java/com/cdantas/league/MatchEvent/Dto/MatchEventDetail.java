package com.cdantas.league.MatchEvent.Dto;

import com.cdantas.league.MatchEvent.Entity.MatchEvent;

public record MatchEventDetail(
        Long id,
        Long matchId,
        String type,
        Integer minute,
        Long teamId,
        String teamName,
        Long playerId,
        String playerName,
        String description
) {
    public MatchEventDetail(MatchEvent event) {
        this(
                event.getId(),
                event.getMatch().getId(),
                event.getType().name(),
                event.getMinute(),
                event.getTeam() != null ? event.getTeam().getId() : null,
                event.getTeam() != null ? event.getTeam().getName() : null,
                event.getPlayer() != null ? event.getPlayer().getId() : null,
                event.getPlayer() != null ? event.getPlayer().getName() : null,
                event.getDescription()
        );
    }
}