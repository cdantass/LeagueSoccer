package com.cdantas.league.Match.Dto;

import com.cdantas.league.Match.Entity.Status;

import java.time.LocalDateTime;

public record MatchRegister(Long homeTeamId, Long awayTeamId, LocalDateTime matchDate, Status status, int homeScore, int awayScore, Long venue) {
}