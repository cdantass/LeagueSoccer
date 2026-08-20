package com.cdantas.league.Match.Dto;

import com.cdantas.league.Match.Entity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record MatchRegister(
        @NotNull Long homeTeamId,
        @NotNull Long awayTeamId,
        LocalDateTime matchDate,
        Status status,
        @PositiveOrZero int homeScore,
        @PositiveOrZero int awayScore,
        Long venue
) {}