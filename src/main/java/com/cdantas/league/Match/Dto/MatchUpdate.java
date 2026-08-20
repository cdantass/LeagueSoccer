package com.cdantas.league.Match.Dto;

import jakarta.validation.constraints.PositiveOrZero;

public record MatchUpdate(
        String status,
        @PositiveOrZero Integer homeScore,
        @PositiveOrZero Integer awayScore
) {}